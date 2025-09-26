<template>
  <header-component/>
  <nav-bar-component/>
  <router-view/>
</template>

<script>
import HeaderComponent from "@/components/HeaderComponent.vue";
import NavBarComponent from "@/components/NavBarComponent.vue";

export default {
  name: "AppComponent33",
  components: {NavBarComponent, HeaderComponent},
}
</script>

<style scoped>

</style>