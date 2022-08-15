<template>
  <div class="container">
    <div class="row">
      <div id="calendar" class="col-lg-7">
        <my-calendar></my-calendar>
      </div>
      <div class="col-lg-1">
      </div>
      <div id="sidebar" class="col-lg-4">
        <side-bar :key="cKey" @forceRerender="forceRerender"></side-bar>
      </div>
    </div>
  </div>
</template>

<script>
import { computed, onUnmounted } from 'vue';
import { useStore } from 'vuex';

import MyCalendar from './components/MyCalendar.vue';
import SideBar from './components/SideBar.vue';

export default {
  name: 'SheduleView',
  components: {
    MyCalendar,
    SideBar,
  },
  setup() {
    const store = useStore();

    const UpcomingSchedules = computed(() => store.getters.UpcomingSchedules);
    const fetchUpcomingSchedules = () => {
      store.dispatch('fetchUpcomingSchedules');
    };

    onUnmounted(() => {
      store.commit('RESET_DATE');
    });

    return {
      UpcomingSchedules,
      fetchUpcomingSchedules,
      // forceRerender,
    };
  },
  data() {
    return {
      cKey: 0,
    };
  },
  methods: {
    forceRerender() {
      this.cKey += 1;
    },
  },
  created() {
    this.fetchUpcomingSchedules();
  },
};
</script>

<style scoped>
.container {
  margin-top: 100px;
  height: 110vh;
}
#calendar {
  border: 1px solid rgb(192, 192, 192);
  border-radius: 10px;
  background-color: #adb5e90c;
}

#sidebar {
  border-radius: 10px;
  border: 1px solid rgb(192, 192, 192);
}
</style>
