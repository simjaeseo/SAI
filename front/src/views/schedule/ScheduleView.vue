scheduleView

<template>
  <div class="container">
    <div class="row">
      <div id="calendar" class="col-lg-7">
        <my-calendar></my-calendar>
        <div> {{ UpcomingSchedules }}</div>
      </div>
      <div class="col-lg-1">
      </div>
      <div id="sidebar" class="col-lg-4">
        <show-event v-if="isUpcomingSchedules" v-show="!selectedDate"></show-event>
        <add-event v-show="selectedDate || !isUpcomingSchedules"></add-event>
      </div>
    </div>
  </div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';

import MyCalendar from './components/MyCalendar.vue';
import AddEvent from './components/AddEvent.vue';
import ShowEvent from './components/ShowEvent.vue';

export default {
  name: 'SheduleView',
  components: {
    MyCalendar,
    AddEvent,
    ShowEvent,
  },
  setup() {
    const store = useStore();

    const isUpcomingSchedules = computed(() => store.getters.isUpcomingSchedules);
    const UpcomingSchedules = computed(() => store.getters.UpcomingSchedules);
    const selectedDate = computed(() => store.getters.selectedDate);
    const fetchUpcomingSchedules = () => {
      store.dispatch('fetchUpcomingSchedules');
    };
    return {
      isUpcomingSchedules,
      selectedDate,
      UpcomingSchedules,
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

