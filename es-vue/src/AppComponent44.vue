<template>
  <header-sb-component/>
  <nav-bar-sb-component/>
  <router-view/>
</template>

<script>

import {SessionSbService} from "@/services/SessionSbService";
import HeaderSbComponent from "@/components/HeaderSbComponent.vue"
import NavBarSbComponent from "@/components/NavBarSbComponent.vue";
import CONFIG from '../app-config.js'
import {shallowReactive} from "vue";
import {ScootersAdaptor} from "@/services/scooters-adaptor";
import {FetchInterceptor} from "@/services/FetchInterceptor";
export default {
  name: "AppComponent44",
  components: {NavBarSbComponent, HeaderSbComponent},
  provide() {
    // TODO Use a dynamic (hashed) JWT_STORAGE_ITEM name for greater security
    this.theSessionService = shallowReactive(
        new SessionSbService(CONFIG.BACKEND_URL + "/authentication", CONFIG.JWT_STORAGE_ITEM));
    this.theFetchInterceptor = new FetchInterceptor(this.theSessionService, this.$router)
    return {
      scootersService: new ScootersAdaptor(CONFIG.BACKEND_URL + "/scooters"),
      sessionService: this.theSessionService,
    }
  },
  unmounted() {
    console.log("App.unmounted() has been called");
    this.theFetchInterceptor.unregister();
  }
}
</script>


<style scoped>

</style>