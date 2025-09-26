<template>
<div class="navbar">
  <router-link class="nav-item" to="/">Home</router-link>
  <div class="dropdown">
    <button class="dropbtn">Scooters
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <router-link class="nav-item" to="/scooters-list">Scooters overview</router-link>
      <router-link class="nav-item" to="/scooters-detail">Scooter details</router-link>
      <router-link class="nav-item" to="/scooters-overview">Available Scooters</router-link>
      <router-link class="nav-item" to="/scooters-overview37">Overview 37</router-link>
      <a href="#">Abandoned Scooters</a>
    </div>
  </div>
  <a href="#">My Trips</a>
  <a href="#">My Account</a>
  <div class="topnav-right">
  <router-link to="/sign-up">Sign Up</router-link>
    <router-link to="/sign-in">Log In</router-link>
  </div>


</div>
</template>

<script>
export default {
  name: "NavBarComponent",
}
</script>

<style scoped>
.navbar {
  overflow: hidden;
  background-color: #ffe5c4;
  font-family: Arial,serif;
}

/* Links inside the navbar */
.navbar a {
  float: left;
  font-size: 18px;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  color: #8b0000;
  background-color: grey;
  align-content: space-between;
}


/* The dropdown container */
.dropdown {
  float: left;
  overflow: hidden;
}

/* Dropdown button */
.dropdown .dropbtn {
  font-size: 18px;
  border: none;
  outline: none;
  color: darkred;
  padding: 14px 16px;
  background-color: grey;
  font-family: Arial, serif; /* Important for vertical align on mobile phones */
  margin: 0; /* Important for vertical align on mobile phones */
}

/* Add a red background color to navbar links on hover */
.navbar a:hover, .dropdown:hover .dropbtn {
  background-color: lightcoral;
}

/* Dropdown content (hidden by default) */
.dropdown-content {
  display: none;
  position: absolute;
  background-color: grey;
  min-width: 160px;
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
  /*z-index: 1;*/
}

/* Links inside the dropdown */
.dropdown-content a {
  float: none;
  color: darkred;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  text-align: left;
}

/* Add a grey background color to dropdown links on hover */
.dropdown-content a:hover {
  background-color: #ddd;
}

/* Show the dropdown menu on hover */
.dropdown:hover .dropdown-content {
  display: block;
}

.topnav-right{
  float: right;
}
</style>