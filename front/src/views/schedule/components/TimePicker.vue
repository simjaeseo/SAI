<template>
  <div>
    <button v-for="time in times"
    :key="time"
    @click.prevent="pickTime(time)"
    :style="[selectStartTime == time ? {background:'#ffffff'} : {background:'#5c6ac4'}]"
    >
      {{ time }}
    </button>
  </div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';

export default {
  name: 'TimePicker',
  data() {
    return {
      times: [],
      hour: 9,
    };
  },
  setup() {
    const store = useStore();

    const selectStartTime = computed(() => store.getters.selectedStartTime);
    const pickTime = (time) => {
      store.dispatch('pickTime', time);
    };
    return { pickTime, selectStartTime };
  },
  methods: {
    timeSet() {
      for (let i = 0; this.hour < 18 || i === 30; i += 30) {
        if (i === 60) {
          this.hour += 1;
          i = 0;
        }
        if (i === 0) {
          this.times.push(`${this.hour}:00`);
        } else {
          this.times.push(`${this.hour}:${i}`);
        }
      }
    },
  },
  mounted() {
    this.timeSet();
  },
};
</script>

<style scoped>
button {
  width: 5.5vw;
  height: 3vw;
  margin: 0.2vw;
  font-size: 1vw;
}
</style>
