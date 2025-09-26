<template>
  <table class="sc-table">
    <tr>
      <th>Id:</th>
      <th>Tag:</th>
      <th>Status:</th>
      <th>GPSLocation:</th>
      <th>Mileage:</th>
      <th>Battery Charge:</th>
    </tr>
    <tr v-for="scooter of scooters" v-bind:key = "scooter.id">
      <td class="center">{{scooter.id}}</td>
      <td class="left">{{scooter.tag}}</td>
      <td class="left">{{scooter.status}}</td>
      <td class="center" v-if="scooter.status !== 'INUSE'">{{scooter.gpsLocation}}</td>
      <td class="center" v-else></td>
      <td class="center">{{scooter.mileage}} km</td>
      <td class="right">{{scooter.batteryCharge}}%</td>
    </tr>
  </table>
  <button class="sc-button"
          v-on:click="onNewScooter()">New Scooter</button>

</template>

<script>
import {Scooter} from "@/../../../../backend/demo1/src/main/java/app/models/scooter.js";

export default {
  name: "ScooterOverview31",
  data(){
    return{
      scooters: [],
      lastId: 30000
    }
  },
  created(){
    for (let i = 0; i < 8; i++) {
      this.scooters.push(
      Scooter.createSampleScooter(this.nextId()));
    }
  },
  methods: {
    nextId(){
      const idRandom = Math.floor(Math.random() * 3) + 1
      return this.lastId += idRandom;
    },
    onNewScooter(){
      this.scooters.push(
      Scooter.createSampleScooter(this.nextId()))
    }
  }
}
</script>

<style scoped>
table{
  margin-left: 170px;
  margin-top: 30px;
  width: 70%;
}

.sc-button{
  right: 70px;
}
</style>