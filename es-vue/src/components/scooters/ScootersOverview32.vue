<template>
<div class="scooter">
  <h2 class="sc-title">All scooter details:</h2>
  <table class="sc-table">
    <tr>
      <th>Tag:</th>
    </tr>
    <tr v-for="scooter in scooters" v-bind:key="scooter.id" @click="setScooter(scooter)">
      <td :class="{highlightedText: scooter === this.selectedScooter}">{{ scooter.tag }}</td>
    </tr>
  </table>
  <button class="sc-button"
          v-on:click="onNewScooter()">Add scooter
  </button>

  <div v-if="selectedScooter" class="sc-detail">
    <ScootersDetail32 @delete-scooter="deleteScooter()"
                   :currentScooter="selectedScooter">
    </ScootersDetail32>
  </div>
  <div v-else class="description">
    <h3>Select a scooter from the list at the left!</h3>
  </div>

</div>
</template>

<script>
import {Scooter} from "@/models/scooter.js";
import ScootersDetail32 from "@/components/scooters/ScootersDetail32";

export default {
  name: "ScootersOverview32",
  emits: ['de-select'],
  components:{
    ScootersDetail32
  },
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
      selectedScooter: null
    }
  },
  methods: {
    nextId() {
      const idRandom = Math.floor(Math.random() * 3) + 1
      return this.lastId += idRandom;
    },
    onNewScooter() {
      let newScooter = Scooter.createSampleScooter(this.nextId());
      this.scooters.push(newScooter);
      this.selectedScooter = newScooter;

    },
    setScooter(scooter){
      if (this.selectedScooter === scooter){
        this.selectedScooter = null
      }else {
        this.selectedScooter = scooter;
      }
    },
    deleteScooter(){
      const index = this.scooters.indexOf(this.selectedScooter);
      this.scooters.splice(index, 1);
      this.selectedScooter = null;
    },
  }
}
</script>

<style scoped>
.sc-title {
  margin-left: 41%
}
.sc-button {
  margin-left: 27%;
  margin-top: 300px;
  bottom: 150px;
}
.description{
  margin-left: 700px;
  position: absolute;
  margin-top: 110px;
}
table {
  margin-left: 20%;
  position: absolute;
  margin-top: 70px;
  width: 13%;
}

.scooter{
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-gap: 10px;
}

.highlightedText {
  color: #fd5d25;
}



</style>