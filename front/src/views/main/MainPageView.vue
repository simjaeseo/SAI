<template>
  <div id="main-page-view" class="container">
    <div id="route-button" class="row">
      <p id='main-text1'>{{ currentUser.name }}님 안녕하세요!</p>
      <div class="col-lg-7" id='user-schedule-box1'>
        <Suspense>
          <route-button></route-button>
        </Suspense>
      </div>
      <div class="col-lg-1">
      </div>
      <div class="col-lg-4" id='user-schedule-box2'>
        <router-link v-if="currentUser.memberStatus === 'TRAINEE'"
        to="/schedule" id="main-calendar">
          <Suspense>
            <main-calendar></main-calendar>
          </Suspense>
        </router-link>
        <router-link v-else to="/scheduleCT" id="main-calendar">
          <Suspense>
            <main-calendar></main-calendar>
          </Suspense>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';
import RouteButton from './components/RouteButton.vue';
import MainCalendar from './components/MainCalendar.vue';

export default {
  name: 'MainPage',
  components: {
    RouteButton,
    MainCalendar,
  },
  setup() {
    const store = useStore();

    const currentUser = computed(() => store.getters.currentUser);
    const isLoggedIn = computed(() => store.getters.isLoggedIn);
    const consultants = computed(() => store.getters.myConsultants);
    return {
      currentUser,
      isLoggedIn,
      consultants,
    };
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
