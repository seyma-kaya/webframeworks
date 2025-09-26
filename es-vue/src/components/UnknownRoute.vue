<template>
<div class="unknown">
  <div class="errorMsg">
    <h1>OOPS AN ERROR HAS OCCURED...</h1>
    <h3>THERE IS NO COMPONENT UNDER THE PATH {{$route.path}}</h3>
  </div>
</div>
</template>

<script>
export default {
  name: "UnknownRoute",
}
</script>

<style scoped>
.errorMsg{
  background-color: #E19F9FFF;
}
</style>