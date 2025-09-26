<template>
  <div class="scooterDetail">
    <table class="scooter-details">
      <tr>
        <td class="detail-title">
          Scooter details(id={{ copyScooter.id }})
        </td>
      </tr>
      <tr>
        <th class="subtitle-scooter">Tag:</th>
        <td><input type="text" id="tagInput" class="inputField" placeholder="Tag"
                   v-model="copyScooter.tag"></td>
      </tr>

      <tr>
        <th class="subtitle-scooter">Status:</th>
        <td><select id="statusInput" class="inputField" v-model="copyScooter.status">
          <option>{{copyScooter.status}}</option>
        </select></td>
      </tr>
      <tr>
        <th class="subtitle-scooter">Battery Charge(%):</th>
        <td><input type="text" id="batteryInput" class="inputField" placeholder="Battery"
                   v-model="copyScooter.batteryCharge"></td>
      </tr>

      <tr>
        <th class="subtitle-scooter">GPS Location:</th>
        <td><input type="text" id="gpsInput" class="inputField" placeholder="GPS"
                   v-model="copyScooter.gpsLocation"></td>
      </tr>
      <tr>
        <th class="subtitle-scooter">Total Mileage(km):</th>
        <td><input type="text" id="mileageInput" class="inputField" placeholder="Mileage" v-model="copyScooter.mileage">
        </td>
      </tr>
    </table>
    <div class="form-scooter">
      <div>
        <button type="button"
                class="delete-btn"
                v-bind:disabled="hasChanged"
                @click="$emit('delete-scooter')">
          Delete
        </button>
        <button type="button"
                class="save-btn"
                v-bind:disabled="!hasChanged"
                @click="$emit('save-scooter', this.copyScooter)">
          Save
        </button>
        <button type="button"
                class="clear-btn"
                @click="clearInput()">
          Clear
        </button>
        <button type="button"
                class="cancel-btn"
                @click="$emit('clear-scooter')">
          Cancel
        </button>
        <button type="button"
                class="reset-btn"
                v-bind:disabled="!hasChanged"
                @click="reInitialise()">
          Reset
        </button>
      </div>
    </div>


  </div>
</template>

<script>
import {Scooter} from "@/models/scooter";

export default {
  name: "ScootersDetail34",
  props: ['currentScooter',
  ],
  emits: ['delete-scooter', 'clear-scooter', 'save-scooter'],
  created() {
    this.reInitialise();
  },
  data(){
    return{
      copyScooter: null,
    }
  },
  computed:{
    hasChanged(){
      return this.currentScooter.tag !== this.copyScooter.tag ||
          this.currentScooter.status !== this.copyScooter.status ||
          this.currentScooter.batteryCharge !== this.copyScooter.batteryCharge ||
          this.currentScooter.gpsLocation !== this.copyScooter.gpsLocation ||
          this.currentScooter.mileage !== this.copyScooter.mileage
    },
  },
  watch:{
    currentScooter(){
      this.reInitialise();
    }
  },
  methods:{
    reInitialise(){
      this.copyScooter = Scooter.copyConstructor(this.currentScooter);
    },
    clearInput(){
      this.copyScooter.tag = "";
      this.copyScooter.status = null;
      this.copyScooter.batteryCharge = "";
      this.copyScooter.gpsLocation = "";
      this.copyScooter.mileage = "";
    },

  }
}
</script>

<style scoped>
.scooter-details {
  width: 45%;
  margin-top: 70px;
}
.detail-title{
  text-align: end;
  font-weight: bold;
  background-color: #FF8C00FF;
}
.subtitle-scooter {
  text-align: right;
  width: 50%;
}

.inputField {
  float: left;
  width: 80%;
}

</style>