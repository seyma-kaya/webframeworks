<template>
  <div class="sign-in">
    <h2 class="header-title">Please provide your login credentials:</h2>

    <table class="scooter-details">
      <tr>
        <th class="subtitle-scooter">Email:</th>
        <td><input type="text" id="emailInput" class="inputField" v-model="emailInput"></td>
      </tr>

      <tr>
        <th class="subtitle-scooter">Password:</th>
        <td><input type="password" id="passInput" class="inputField" v-model="passwordInput">
        </td>
      </tr>
    </table>
    <p class="error">{{ errorMessage }}</p>
    <h5>Current token:</h5>
    <div class="token">{{sessionService._currentToken}}</div>
    <button type="submit" v-on:click="sendForm()">Sign In</button>
  </div>
</template>

<script>
export default {
  name: "SignIn.vue",
  inject: ["sessionService"],

  data() {
    return {
      emailInput: null,
      passwordInput: null,
      errorMessage: null,
    }
  },
  methods:{
    async sendForm(){
      this.errorMessage = null;
      let account = await this.sessionService.asyncSignIn(this.emailInput, this.passwordInput);
      if (account == null) {
        this.errorMessage = "Could not authenticate with provided credentials";
      }
    }
    }
}
</script>

<style scoped>
.scooter-details {
  width: 45%;
  margin-top: 70px;
}

.header-title {
  text-align: left;
  font-weight: bold;

}

.subtitle-scooter {
  text-align: right;
  width: 50%;
}

.inputField {
  float: left;
  width: 80%;
}
.error{
  color: darkred;
}
</style>