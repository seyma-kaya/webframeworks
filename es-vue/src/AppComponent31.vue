<template>
  <HeaderComponent/>
  <NavBarComponent/>
  <ScootersOverview32/>
</template>


<script>
import HeaderComponent from './components/HeaderComponent.vue'
import NavBarComponent from "./components/NavBarComponent";
import ScootersOverview32 from "@/components/scooters/ScootersOverview32";
import UnknownRoute from "@/components/UnknownRoute";


export default {
  name: 'App',
  components: {
    UnknownRoute,
    HeaderComponent,
    NavBarComponent,
    ScootersOverview32,
  }
}
</script>
