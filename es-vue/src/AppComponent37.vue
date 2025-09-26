<template>
  <header-component/>
  <nav-bar-component/>
  <router-view/>
</template>

<script>
import HeaderComponent from "@/components/HeaderComponent.vue";
import NavBarComponent from "@/components/NavBarComponent.vue";
import {ScootersAdaptor} from "@/services/scooters-adaptor.js";
import CONFIG from '@/app-config.js'

export default {
  name: "AppComponent37",
  components: {NavBarComponent, HeaderComponent},
  provide(){
    return{
      scootersService: new ScootersAdaptor(CONFIG.BACKEND_URL + "/scooters")
    }
  }
}
</script>

<style scoped>

</style>