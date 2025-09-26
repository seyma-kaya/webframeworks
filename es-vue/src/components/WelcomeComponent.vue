<template>
  <div class="welcome" id="pageColumns">
    <div id="column-left">
      <h3 class="styling-name">Pick a brand:</h3>
        <select name="scooter" id="scooterSelection" class="select-box">
          <option value="brand1">Lime-S</option>
          <option value="brand2">Brand 2</option>
          <option value="brand3">Brand 3</option>
          <option value="brand4">Brand 4</option>
        </select>
      <h3 class="styling-name">Availability:</h3>
      <ul>Leidseplein 7</ul>
      <ul>Rembrandtplein 14</ul>
      <ul>Centraal Station 12</ul>
    </div>
    <div id="column-middle">
      <h3 class="styling-name">Photo gallery:</h3>
      <img class="pictureOne" alt="scooterFoto" src="@/assets/scooterPlaatje.jpg">
      <img class="pictureTwo" alt="scooterFoto" src="@/assets/scooter.jpg">
      <img class="pictureThree" alt="scooterFoto" src="@/assets/steppen.jpg">
    </div>
    <div id="column-right">
      <h3 class="styling-name">City Tours</h3>
      <p class="styling-text">Enjoy on of our city tours with your E-scooter. Go shopping in the nine streets,
        visit the Albert Cuyp market or the museum square. It is also allowed to take a moped on to the subway
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: "WelcomeComponent"
}
</script>

<style>
#column-left {
  float: left;
  width: 30%;
}
#column-middle {
  float: left;
  width: 40%;
}
#column-right {
  float: right;
  width: 30%;
}
#scooterSelection{
  margin-left: 20px;
}
.styling-name, .styling-text {
  margin-left: 20px;
  margin-top: 30px;
}

.select-box {
  margin-left: 20px;
}
.pictureOne{
  width: 220px;
  height: 150px;
}

.pictureTwo{
  width: 220px;
  height: 150px;
  padding-left: 20px;
}
.pictureThree{
  width: 220px;
  height: 150px;
  padding-top: 20px;
}


</style>