const { defineConfig } = require('@vue/cli-service');
const { doubleCsrf } = require("csrf-csrf");
module.exports = defineConfig({
  transpileDependencies: true
})
