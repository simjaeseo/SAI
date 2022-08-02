<template>
  <div class="container">
    <div class="row">
      <div id="calendar" class="col-lg-7">
        <my-calendar></my-calendar>
      </div>
      <div class="col-lg-1">
      </div>
      <div id="sidebar" class="col-lg-4">
        <show-event v-show="!selectDate"></show-event>
        <add-event v-show="selectDate"></add-event>
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

    const selectDate = computed(() => store.getters.selectedDate);
    return { selectDate };
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
