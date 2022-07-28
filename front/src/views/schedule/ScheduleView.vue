<template>
  <div>
    <div id="calendar">
      <my-calendar></my-calendar>
    </div>
    <div id="sidebar">
      <show-event v-show="!selectDate"></show-event>
      <add-event v-show="selectDate"></add-event>
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

    const selectDate = computed(() => store.getters.selectedDate);
    return { selectDate };
  },
};
</script>

<style scoped>
#calendar {
  border: 1px solid black;
  position: absolute;
  top: 10%;
  left: 5%;
  width: 60vw;
  height: 44vw;
}

#sidebar {
  border: 1px solid black;
  position: absolute;
  top: 10%;
  left: 68%;
  width: 30vw;
  height: 44vw;
}

#trans-button {
  display: flex;
  justify-content: center;
}
</style>
