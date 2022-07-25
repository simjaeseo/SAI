<template>
  <div id="main-page-view">
    <div id="route-button">
      <p>안녕하세요 user님!</p>
      <route-button class="components"></route-button>
    </div>
    <router-link to="schedule" id="main-calendar">
      <main-calendar></main-calendar>
    </router-link>
  </div>
</template>

<script>
import RouteButton from './components/RouteButton.vue';
import MainCalendar from './components/MainCalendar.vue';

export default {
  name: 'MainPage',
  components: {
    RouteButton,
    MainCalendar,
  },
};
</script>

<style scoped>
#main-page-view {
  display: flex;
  justify-content: space-around;
}

#route-button {
  border: 1px solid black;
  margin-top: 10%;
  width: 50vw;
}

#main-calendar {
  border: 1px solid black;
  margin-top: 10%;
  width: 20vw;
  height: 20vw;
}

.components {
  border: 1px solid black;
}
</style>
