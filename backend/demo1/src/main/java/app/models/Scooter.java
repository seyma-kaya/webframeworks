package app.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jdk.jfr.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@NamedQueries({
        @NamedQuery(name="Scooter_find_by_status",
        query = "select s from Scooter s where s.status = ?1"),
        @NamedQuery(name = "Scooter_find_by_battery", query = "select s from Scooter s where s.batteryCharge < ?1")
})
@Entity
public class Scooter {
    @Id
    @SequenceGenerator(name="Scooters_ids", initialValue=30001)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Scooters_ids")
    @JsonView(ViewClasses.Summary.class)
    public long id;
    @JsonView(ViewClasses.Summary.class)

    public String tag;
    public String gpsLocation;
    @JsonView(ViewClasses.Summary.class)

    public Status status;
    @JsonView(ViewClasses.Summary.class)

    public double batteryCharge;
    public double mileage;

    @OneToOne
    @JsonIgnore
    private Trip currentTrip;
    @OneToMany(mappedBy = "scooter")
    @JsonManagedReference
    private List<Trip> trips = new ArrayList<>();
    private static Random randomizer = new Random();


    public Scooter(long id, String tag, String gpsLocation, Status status, double mileage, double batteryCharge) {
        this.id = id;
        this.tag = tag;
        this.gpsLocation = gpsLocation;
        this.status = status;
        this.mileage = mileage;
        this.batteryCharge = batteryCharge;
    }

    public Scooter() {

    }

    public boolean associateTrip(Trip trip){
        if (trip == null || this.getTrips().contains(trip)){
            return false;
        }

        this.addTrip(trip);
        trip.associateScooter(this);
        return true;
    }

    public Trip getCurrentTrip() {
        return currentTrip;
    }

    public void setCurrentTrip(Trip currentTrip) {
        this.currentTrip = currentTrip;
    }

    public boolean dissociateTrip(Trip trip){
        if (!this.getTrips().contains(trip) || this.getCurrentTrip() == trip){
            return false;
        }
        this.removeTrip(trip);
        return true;
    }

    public static Scooter createSampleScooter(long id) {
        double longSample = randomizer.nextDouble(52.5 - 52.3) + 52.3;
        double lanSample = randomizer.nextDouble(5.00 - 4.85) + 4.85;
        String CHARS = "abcdefghijklmnopqrstuvwzyx1234567890";
        StringBuilder tag = new StringBuilder();
        while (tag.length() < 8) { // length of the random string.
            int index = (int) (randomizer.nextFloat() * CHARS.length());
            tag.append(CHARS.charAt(index));
        }
        String newTag = tag.toString();
        String gpsLocation = longSample + "N," + lanSample + "E";
        double batteryCharge = randomizer.nextDouble(99);
        double mileage = randomizer.nextDouble(10000);

        return new Scooter(id, newTag, gpsLocation, Status.getRandom(), mileage, batteryCharge);

    }

    public long getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public String getGpsLocation() {
        return gpsLocation;
    }

    public Status getStatus() {
        return status;
    }

    public double getBatteryCharge() {
        return batteryCharge;
    }

    public double getMileage() {
        return mileage;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setGpsLocation(String gpsLocation) {
        this.gpsLocation = gpsLocation;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setBatteryCharge(double batteryCharge) {
        this.batteryCharge = batteryCharge;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public void addTrip(Trip trip) {
        this.trips.add(trip);
    }

    public void removeTrip(Trip trip){
        this.trips.remove(trip);
    }
}