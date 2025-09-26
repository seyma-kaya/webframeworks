import {Scooter} from "@/models/scooter";

export class ScootersAdaptor {
    resourcesUrl;   // the URL of the scooters resource endpoint
    constructor(resourcesUrl) {
        this.resourcesUrl = resourcesUrl;
        console.log("Created Scootersadaptor for " + resourcesUrl);
    }

    async fetchJson(url, options = null ) {
        let response = await fetch(url, options)
        if (response.ok) {
            return await response.json();
        } else {
            // the response body provides the http-error information
            console.log(response, !response.bodyUsed ? await response.text() : "");
            return null;
        }
    }

    async asyncfindAll(){
        console.log('Scootersadaptor.asyncFindAll()...');
        const scootersData = await this.fetchJson(this.resourcesUrl);
        return scootersData?.map(Scooter.copyConstructor);
    }

    async asyncFindById(id) {
        console.log('Scootersadaptor.asyncFindById(' + id + ')...');
        const scootersData = await this.fetchJson(this.resourcesUrl + "/" + id);
        return Scooter.copyConstructor(scootersData);
    }

    async asyncSave(scooter)  {
        console.log('Scooters.asyncDeleteById(' + scooter + ')...');
        if (scooter.id === 0){
            return await this.fetchJson(this.resourcesUrl, {
                method: "POST",
                headers: {
                    'Content-Type' : 'application/json'
                },
                body: JSON.stringify({
                    id: scooter.id,
                    tag: scooter.tag,
                    status: scooter.status,
                    gpsLocation: scooter.gpsLocation,
                    mileage: scooter.mileage,
                    batteryCharge: scooter.batteryCharge
                })
            });
        } else {
            return this.fetchJson(this.resourcesUrl + "/" + scooter.id,
                {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(
                        scooter
                    )
                });
        }
    }
    async asyncDeleteById(id) {
        console.log('Scooters.asyncDeleteById(' + id + ')...');
        return await this.fetchJson(this.resourcesUrl + "/" + id, {
            method: "DELETE",
        });
    }

}