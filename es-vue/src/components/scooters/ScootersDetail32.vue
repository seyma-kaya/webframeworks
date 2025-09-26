<template>
  <div class="scooterDetail">
    <table class="scooter-details">
      <tr>
      <td class="detail-title">
        Scooter details(id={{ currentScooter.id }})
      </td>
      </tr>
      <tr>
        <th class="subtitle-scooter">Tag:</th>
        <td><input type="text" id="tagInput" class="inputField" placeholder="Tag"
                   v-model="localCurrentScooter.tag"></td>
      </tr>

      <tr>
        <th class="subtitle-scooter">Status:</th>
        <td><select id="statusInput" class="inputField" v-model="localCurrentScooter.status">
          <option>{{currentScooter.status}}</option>
        </select></td>
      </tr>
      <tr>
        <th class="subtitle-scooter">Battery Charge(%):</th>
        <td><input type="text" id="batteryInput" class="inputField" placeholder="Battery"
                   v-model="localCurrentScooter.batteryCharge"></td>
      </tr>

      <tr>
        <th class="subtitle-scooter">GPS Location:</th>
        <td><input type="text" id="gpsLocation" class="inputField" placeholder="GPS"
                   v-model="localCurrentScooter.gpsLocation"></td>
      </tr>
      <tr>
        <th class="subtitle-scooter">Total Mileage(km):</th>
        <td><input type="text" id="mileage" class="inputField" placeholder="Mileage" v-model="localCurrentScooter.mileage">
        </td>
      </tr>
    </table>
    <div class="row">
      <div class="col-sm-12">
        <button type="button"
                class="delete-button"
                @click="$emit('delete-scooter')"
        >
          Delete
        </button>
      </div>
    </div>


  </div>
</template>

<script>
export default {
  name: "ScootersDetail32",
  props: ['currentScooter',
  'delete-scooter',
  ],
  computed: {
    localCurrentScooter: {
      get() {
        return this.currentScooter
      },
    },
  },
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
.delete-button{
  margin-left: 270px;
  margin-top: 10px;
  position: absolute;
}

</style>