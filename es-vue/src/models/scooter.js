export class Scooter{
    id;
    tag;
    status;
    gpsLocation;
    mileage;
    batteryCharge;

    static Status = {
        IDLE: "IDLE",
        INUSE: "INUSE",
        MAINTENANCE: "MAINTENANCE"
    }

    constructor(id, tag, status, gpsLocation, mileage, batteryCharge) {
        this.id = id;
        this.tag = tag;
        this.status = status;
        this.gpsLocation = gpsLocation;
        this.mileage = mileage;
        this.batteryCharge = batteryCharge;
    }

     static createSampleScooter(pId = 0){
        const longSample = Math.random() * (52.5 - 52.3) + 52.3;
        const lanSample = Math.random() * (5.00 - 4.85) + 4.85;
        const tagSample = Math.random().toString(36).substring(2,10);
        const statusArray = Object.keys(this.Status);
        const statusSample = statusArray[Math.floor(Math.random()*statusArray.length)]
        const gpsSample = String(longSample.toFixed(4) + "N, " + lanSample.toFixed(4) + "E")
        const mileageSample = Math.floor(Math.random() * 10001) + 1;
        const batterySample = Math.floor(Math.random() * 96) + 5;
        return new Scooter(pId, tagSample, statusSample, gpsSample, mileageSample,batterySample);
    }

    static copyConstructor(scooter){
        if (scooter === null || scooter === undefined) {
            return null;
        }
        return Object.assign(new Scooter(0), scooter);
    }
}