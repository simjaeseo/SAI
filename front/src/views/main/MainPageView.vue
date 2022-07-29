<template>
  <div id="main-page-view" class="container">
    <div id="route-button" class="row">
      <p id='main-text1'>안녕하세요 user님!</p>
      <div class="col-lg-8" id='user-schedule-box1'>
        <route-button></route-button>
      </div>
      <div class="col-lg-1">
      </div>
      <div class="col-lg-3" id='user-schedule-box2'>
        <router-link to="schedule" id="main-calendar">
          <main-calendar></main-calendar>
        </router-link>
      </div>
    </div>
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
#main-text1 {
  font-weight: bold;
  font-size: 30px;
}
.container {
  margin-top: 150px;
}
#user-schedule-box1 {
  border: 1px solid black;
  background-color: #5c6ac40c;
  border-radius: 10px;
  display: flex;
}
#user-schedule-box2 {
  border: 1px solid black
}
</style>
