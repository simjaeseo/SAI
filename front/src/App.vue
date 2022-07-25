<template>
  <nav>
    <router-link to="/">Main</router-link> |
    <router-link to="/schedule">Schdule</router-link> |
    <router-link to="/login">login</router-link> |
    <router-link to="/signup">signup</router-link> |
    <router-link to="/helpPassword">helpPassword</router-link> |
    <router-link to="/helpid">helpid</router-link> |
    <router-link to="/profile">profile</router-link> |
    <router-link to="/update">updateUser</router-link> |
    <router-link to="/management">management</router-link> |
    <router-link to="/interview">interview</router-link> |
    <router-link to="/interview/solo">InterviewSolo</router-link> |
    <router-link to="/interview/ct">InterviewCt</router-link> |
    <router-link to="/analysis/comprehensive">AnalysisComprehensive</router-link> |
    <router-link to="/analysis/detail">AnalysisDetail</router-link>
  </nav>
  <router-view/>
</template>

<script>
export default {
  name: 'App',
  components: {
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
}
</style>
