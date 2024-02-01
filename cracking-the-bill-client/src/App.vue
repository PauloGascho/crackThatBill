<template>
  <!-- <img alt="Vue logo" src="./assets/logo.png"> -->
  <HomeForm/>
  <a target="_blank" href="https://icons8.com/icon/105067/bill">Bill</a> icon by <a target="_blank" href="https://icons8.com">Icons8</a>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component';
import HomeForm from './components/HomeForm.vue'

@Options({
  components: {
    HomeForm,
  },
})
export default class App extends Vue {}
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
