package app.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Random;

@NamedQueries({
        @NamedQuery(name = "Trip_find_by_scooterId_and_period",
                query = "select t from Trip t where (t.startDate between ?1 and ?2) and t.scooter = ?3")
})
@Entity
public class Trip {
    @Id
    @SequenceGenerator(name = "Trips_ids", initialValue = 100001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Trips_ids")
    public long id;
    public LocalDateTime startDate;
    public LocalDateTime endDate;
    public String startLocation;
    public String endLocation;
    public double mileage;
    public double costOfTrip;

    @ManyToOne
    @JsonBackReference
    private Scooter scooter;
    private static Random randomizer = new Random();

    public Trip(long id, LocalDateTime startDate, String startLocation, Scooter scooter) {
        this.id = id;
        this.startDate = startDate;
        this.startLocation = startLocation;
        this.scooter = scooter;
    }

    public Trip() {

    }

    public boolean associateScooter(Scooter scooter) {
        if (scooter == null || this.getScooter() == scooter){
            return false;
        }
        this.setScooter(scooter);
        scooter.associateTrip(this);
        return true;
    }

    public static Trip createSampleTrip(long id, Scooter scooter) {
        double longSample1 = randomizer.nextDouble(52.5 - 52.3) + 52.3;
        double lanSample1 = randomizer.nextDouble(5.00 - 4.85) + 4.85;
        String startLocation = longSample1 + "N," + lanSample1 + "E";


        return new Trip(id, LocalDateTime.now(), startLocation, scooter);

    }

    public long getId() {
        return id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public double getMileage() {
        return mileage;
    }

    public double getCostOfTrip() {
        return costOfTrip;
    }

    public Scooter getScooter() {
        return scooter;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public void setCostOfTrip(double costOfTrip) {
        this.costOfTrip = costOfTrip;
    }

    public void setScooter(Scooter scooter) {
        this.scooter = scooter;
    }
}
