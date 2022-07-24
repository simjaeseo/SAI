<template>
  <div>
    <div id="calendar">
      <my-calendar></my-calendar>
    </div>
    <div id="sidebar">
      <button v-on:click="transChange" class="btn btn-primary"></button>
      <show-event v-show=trans></show-event>
      <add-event v-show=!trans></add-event>
    </div>
  </div>
</template>

<script>
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
  data() {
    return {
      trans: false,
    };
  },
  methods: {
    transChange() {
      this.trans = !this.trans;
    },
  },
};
</script>

<style>
#calendar {
  border: 1px solid black;
  position: absolute;
  top: 10%;
  left: 5%;
  width: 60vw;
  height: 42vw;
}
#sidebar {
  border: 1px solid black;
  position: absolute;
  top: 10%;
  left: 68%;
  width: 30vw;
  height: 42vw
}
</style>
