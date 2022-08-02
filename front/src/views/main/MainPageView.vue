<template>
  <div id="main-page-view" class="container">
    <div id="route-button" class="row">
      <p id='main-text1'>안녕하세요 user님!</p>
      <div class="col-lg-7" id='user-schedule-box1'>
        <route-button></route-button>
      </div>
      <div class="col-lg-1">
      </div>
      <div class="col-lg-4" id='user-schedule-box2'>
        <router-link to="schedule" id="main-calendar">
          <main-calendar></main-calendar>
        </router-link>
      </div>
      <div>
        {{ currentUser.name }}
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import RouteButton from './components/RouteButton.vue';
import MainCalendar from './components/MainCalendar.vue';

export default {
  name: 'MainPage',
  components: {
    RouteButton,
    MainCalendar,
  },
  computed: {
    ...mapGetters(['currentUser', 'isLoggedIn']),
  },
};
</script>

<style scoped>
#main-calendar {
  text-decoration: none;
}
#main-text1 {
  font-weight: bold;
  font-size: 30px;
}
.container {
  margin-top: 100px;
}
#user-schedule-box1 {
  border: 1px solid rgb(218, 218, 218);
  background-color: #5c6ac40c;
  border-radius: 10px;
  display: flex;
  height: 384px;
}
#user-schedule-box2 {
  border: 1px solid rgb(218, 218, 218);
  border-radius: 10px;
}
</style>
