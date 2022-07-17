<template>
  <div id="app">
    <LetterChains />
  </div>
</template>

<script>
import LetterChains from "./components/LetterChains.vue";

export default {
  name: "App",
  components: {
    LetterChains,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
