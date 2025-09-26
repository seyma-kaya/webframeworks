<template>
  <div class="header">
    <img class="banner" alt="banner" src="@/assets/banner.jpeg">
    <img class="hvaLogo" alt="logoHva" src="@/assets/hva_logo_400x400.png">
    <img class="adamLogo" alt="logoAdam" src="@/assets/amsterdam_logo.png">

    <h1 class="title">E-scooter Amsterdam</h1>
    <h3 class="sub-title">Get your kicks on Route 66!</h3>
    <h2 class="date"> Today is {{currentDate()}}</h2>
  </div>
</template>

<script>
  export default {
    methods: {
        currentDate() {
          const options = {
            weekday: "long",
            year: "numeric",
            month: "long",
            day: "numeric",
          };
          return new Date().toLocaleDateString('en-EN', options);
        }
      }
  }

</script>

<style>
  .banner{
    width: 100%;
    height: 200px;
  }

  .hvaLogo{
    position: absolute;
    top: 50px;
    left: 30px;
    height: 140px;
  }

  .adamLogo{
    position: absolute;
    top: 50px;
    right: 50px;
    height: 140px;
  }

  .title{
    position: absolute;
    top: 20px;
    left: 36%;
    font-family: Arial, serif;
    color: #ffffff;
    text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000, 1px 1px 0 #000;
  }

  .sub-title{
    position: absolute;
    left: 60%;
    right: 10%;
    top: 120px;
    border-color: white;
    color: white;
    text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000, 1px 1px 0 #000;
    font-family: Arial, serif;

  }
  .date{
    position: absolute;
    left: 15%;
    right: 50%;
    top: 120px;
    color: #dddddd;
    text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000, 1px 1px 0 #000;

  }

</style>
