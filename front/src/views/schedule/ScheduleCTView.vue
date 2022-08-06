<template>
  <div class="container">
    <div class="row">
      <div id="calendar" class="col-lg-7">
        <my-calendar></my-calendar>
      </div>
      <div class="col-lg-1">
      </div>
      <div id="sidebar" class="col-lg-4">
        <show-event-CT v-if="isUpcomingSchedules" v-show="!selectedDate"></show-event-CT>
        <add-event-CT v-show="selectedDate || !isUpcomingSchedules"></add-event-CT>
      </div>
    </div>
  </div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';

import MyCalendar from './components/MyCalendar.vue';
import AddEventCT from './components/AddEventCT.vue';
import ShowEventCT from './components/ShowEventCT.vue';

export default {
  name: 'SheduleView',
  components: {
    MyCalendar,
    AddEventCT,
    ShowEventCT,
  },
  setup() {
    const store = useStore();

    const isUpcomingSchedules = computed(() => store.getters.isUpcomingSchedules);
    const selectedDate = computed(() => store.getters.selectedDate);
    const fetchUpcomingSchedules = () => {
      store.dispatch('fetchUpcomingSchedules');
    };
    return {
      isUpcomingSchedules,
      selectedDate,
      fetchUpcomingSchedules,
    };
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
