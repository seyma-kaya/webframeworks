<template>
  <div class="scooter">
    <h2 class="sc-title">All scooter details:</h2>
    <table class="sc-table">
      <tr>
        <th>Tag:</th>
      </tr>
      <tr v-for="scooter in scooters" v-bind:key="scooter.id" @click="setScooter(scooter)">
        <td :class="{highlightedText: scooter === this.selectedScooter}" v-on:click="onSelect(scooter)">{{ scooter.tag }}</td>
      </tr>
    </table>
    <button class="sc-button"
            v-on:click="onNewScooter()">Add scooter
    </button>

    <div v-if="selectedScooter" class="sc-detail">
      <router-view v-bind:currentScooter='selectedScooter'
                   @clear-scooter="setScooter(this.selectedScooter); onSelect(this.selectedScooter)"
                   @delete-scooter="deleteScooter()" @save-scooter="saveScooter">
      </router-view>
    </div>
    <div v-else class="description">
      <h3>Select a scooter from the list at the left!</h3>
    </div>

  </div>
</template>

<script>
import {Scooter} from "@/models/scooter";
import ScootersDetail34 from "@/components/scooters/ScootersDetail34.vue";

export default {
  name: "ScootersOverview33",
  components:
  ScootersDetail34
  ,
  emits: ['de-select'],

  created() {
    for (let i = 0; i < 8; i++) {
      this.scooters.push(
          Scooter.createSampleScooter(this.nextId()));
    }
    this.selectedScooter = this.findSelectedFromRouteParams(this.$route?.params?.id);
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
      this.onSelect(newScooter);

    },
    setScooter(scooter) {
      if (this.selectedScooter === scooter) {
        this.selectedScooter = null
      } else {
        this.selectedScooter = scooter;
      }

    },
    deleteScooter() {
      const index = this.scooters.indexOf(this.selectedScooter);
      this.scooters.splice(index, 1);
      this.selectedScooter = null;
    },
    saveScooter(scooter){
      const index = this.scooters.indexOf(this.selectedScooter);
      this.scooters[index] = scooter;
      this.setScooter(scooter);
      this.onSelect(scooter);
    },
    findSelectedFromRouteParams(id) {
      if (id > 0) {
        id = parseInt(id);
        return this.scooters.find(value => value.id === id);

      }
      return null;
    },

    onSelect(scooter) {
      let parentPath = this.$route?.fullPath.replace(new RegExp("/\\d*$"),'');
      if (scooter != null && scooter !== this.selectedScooter) {
        // navigate to new child route
        this.$router.push(parentPath + "/" + scooter.id);
      } else {
        // navigate to parent route
        this.$router.push(parentPath);
      }
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

.description {
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

.scooter {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-gap: 10px;
}

.highlightedText {
  color: #fd5d25;
}


</style>
