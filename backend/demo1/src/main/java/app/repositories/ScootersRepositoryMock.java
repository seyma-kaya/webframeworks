package app.repositories;


import app.models.Scooter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ScootersRepositoryMock implements EntityRepository<Scooter> {
    private List<Scooter> scooters;


    public ScootersRepositoryMock(){
        this.scooters = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Scooter scooter;
            scooter = Scooter.createSampleScooter(30000 + i);
            this.scooters.add(scooter);
        }
    }
    @Override
    public List<Scooter> findAll() {
        return this.scooters;
    }
    @Override
    public Scooter findById(long id) {
        return this.scooters.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Scooter save(Scooter scooter) {
        boolean isInList = false;
        for (Scooter value : scooters) {
            if (value.getId() == scooter.getId()) {
                isInList = true;
                value.setTag(scooter.getTag());
                value.setStatus(scooter.getStatus());
                value.setMileage(scooter.getMileage());
                value.setBatteryCharge(scooter.getBatteryCharge());
                value.setGpsLocation(scooter.getGpsLocation());
                break;
            }
        }

        if (!isInList){
            if (scooter.getId() <= 0) {
                Scooter lastScooter = scooters.get(scooters.size() - 1);
                long lastId = lastScooter.getId();

                scooter.setId(lastId + 1);
            }
            this.scooters.add(scooter);
        }
        return scooter;
    }

    @Override
    public Scooter deleteById(long id) {
        Scooter scooter = this.findById(id);
        this.scooters.remove(scooter);
        return scooter;

    }

    @Override
    public List<Scooter> findByQuery(String jpqlName, Object... params) {
        return null;
    }

}

