package app.rest;

import app.exceptions.BadRequestException;

import app.exceptions.PreConditionFailed;
import app.exceptions.ResourceNotFoundException;
import app.model.Scooter;
import app.model.Status;
import app.model.Trip;
import app.model.ViewClasses;
import app.repositories.EntityRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/scooters")
public class ScooterController {

    @Autowired
    EntityRepository<Scooter> scootersRepo;

    @Autowired
    EntityRepository<Trip> tripsRepo;

    @GetMapping(path = "test", produces = "application/json")
    public List<Scooter> getTestScooter() {
        return List.of(
                new Scooter(30001, "ABC123", "52.747728N 4.8237719E", Status.MAINTENANCE, 2337, 76),
                new Scooter(30004, "XYZ789", "52.747728N 4.8237719E", Status.MAINTENANCE, 2334, 98.1));
    }

    @GetMapping(path = "", produces = "application/json")
    public List<Scooter> getAllScooters(@RequestParam(required = false, name = "statusCheck") String status,
                                        @RequestParam(required = false, name = "battery") Double batteryCharge) {

        int paramsCount =
                (status != null ? 1 : 0) +
                        (batteryCharge != null ? 1 : 0);

        if (paramsCount == 0) {
            return scootersRepo.findAll();
        } else if (batteryCharge != null && paramsCount == 1) {
            return scootersRepo.findByQuery("Scooter_find_by_battery", batteryCharge);
        } else if (!status.isEmpty() && paramsCount == 1) {
            if (Status.isInEnum(status)) {
                return scootersRepo.findByQuery("Scooter_find_by_status", Status.valueOf(status));
            } else {
                throw new BadRequestException(
                        "Status=" + status + " is not a valid status value");
            }
        } else {
            throw new BadRequestException(
                    "Illegal combination of battery=, status= query parameters");
        }
    }

    @JsonView(ViewClasses.Summary.class)
    @GetMapping(path = "summary", produces = "application/json")
    public List<Scooter> getScootersSummary() {

        return scootersRepo.findAll();
    }

    @GetMapping(path = "{id}/trips", produces = "application/json")
    public List<Trip> getScootersTrips(@RequestParam(required = false, name = "from") @DateTimeFormat(pattern = "dd-MMMM-yyyy HH:mm:ss.SSS") LocalDateTime start,
                                       @RequestParam(required = false, name = "to") @DateTimeFormat(pattern = "dd-MMMM-yyyy HH:mm:ss.SSS") LocalDateTime end,
                                       @PathVariable() long id) {

        int paramsCount =
                (start != null ? 1 : 0) +
                        (end != null ? 1 : 0);

        Scooter scooter = scootersRepo.findById(id);
        if (paramsCount == 0) {
            return scooter.getTrips();
        } else if (paramsCount == 2) {
            return tripsRepo.findByQuery("Trip_find_by_scooterId_and_period", start, end, scooter);
        } else {
            throw new BadRequestException(
                    "Illegal combination of params");
        }

    }

    @GetMapping(path = "{id}", produces = "application/json")
    public ResponseEntity<Scooter> getById(@PathVariable() long id) {
        Scooter scooter = this.scootersRepo.findById(id);

        if (scooter == null) {
            throw new ResourceNotFoundException("Cannot provide a scooter with id: " + id);
        }
        return ResponseEntity.ok().body(scooter);
    }

    @DeleteMapping(path = "{id}", produces = "application/json")
    public ResponseEntity<Scooter> deleteById(@PathVariable() long id) {
        Scooter scooter = this.scootersRepo.deleteById(id);

        if (scooter == null) {
            throw new ResourceNotFoundException("Cannot provide a scooter with id: " + id);
        }

        return ResponseEntity.ok().body(scooter);
    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<Scooter> postScooter(@RequestBody Scooter scooter) {
        return ResponseEntity.status(201).body(scootersRepo.save(scooter));
    }

    @PostMapping(path = "{id}/trips", produces = "application/json")
    public ResponseEntity<Trip> postTrip(@RequestBody Trip trip, @PathVariable() long id) {
        Scooter linkedScooter = scootersRepo.findById(id);

        if (linkedScooter.getStatus() == Status.INUSE) {
            throw new ResourceNotFoundException("Scooter-Id= " + id + " with status InUse cannot be claimed for another trip");
        }
        if (linkedScooter.getBatteryCharge() < 10) {
            throw new ResourceNotFoundException("Cannot provide a scooter with a battery status of less then 10%");
        }

        trip.setStartLocation(linkedScooter.getGpsLocation());
        trip.setStartDate(LocalDateTime.now());
        linkedScooter.setStatus(Status.INUSE);
        trip.setScooter(linkedScooter);
        linkedScooter.setCurrentTrip(trip);
        return ResponseEntity.status(201).body(tripsRepo.save(trip));
    }

    @PutMapping(path = "{id}", produces = "application/json")
    public ResponseEntity<Scooter> saveScooter(@PathVariable() long id, @RequestBody Scooter scooter) {
        Scooter oldScooter = scootersRepo.findById(id);

        if (oldScooter.getId() != scooter.getId()) {
            throw new PreConditionFailed("Scooter-id: " + scooter.getId() + " does not match path parameter: " + id);
        }

        scootersRepo.save(scooter);
        return ResponseEntity.ok(scooter);
    }

}
