<template>
  <div class="scooters">
    <h2 class="sc-title">All scooters:</h2>
    <table class="sc-table">
      <tr>
        <th>Id:</th>
        <th>Tag:</th>
        <th>Status:</th>
        <th>GPSLocation:</th>
        <th>Mileage:</th>
        <th>Battery Charge:</th>
      </tr>
      <tr v-for="scooter of scooters" v-bind:key="scooter.id">
        <td class="center">{{ scooter.id }}</td>
        <td class="left">{{ scooter.tag }}</td>
        <td class="left">{{ scooter.status }}</td>
        <td class="center" v-if="scooter.status !== 'INUSE'">{{ scooter.gpsLocation }}</td>
        <td class="center" v-else></td>
        <td class="center">{{ scooter.mileage }} km</td>
        <td class="right">{{ scooter.batteryCharge }}%</td>
      </tr>
    </table>
    <button class="sc-button"
            v-on:click="onNewScooter()">Add scooter
    </button>
  </div>
</template>

<script>
import {Scooter} from "@/models/scooter.js";

export default {
  name: "ScootersOverview31",
  created() {
    for (let i = 0; i < 8; i++) {
      this.scooters.push(
          Scooter.createSampleScooter(this.nextId()));
    }
  },
  data() {
    return {
      scooters: [],
      lastId: 30000,
    }
  },
  methods: {
    nextId() {
      const idRandom = Math.floor(Math.random() * 3) + 1
      return this.lastId += idRandom;
    },
    onNewScooter() {
      this.scooters.push(
          Scooter.createSampleScooter(this.nextId()));
    }
  }
}
</script>

<style scoped>
table {
  margin-left: 170px;
  margin-top: 30px;
  width: 70%;
}

.sc-button {
  position: absolute;
  right: 130px;
}

.sc-title {
  margin-left: 170px;
}


</style>