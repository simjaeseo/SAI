<template>
  <div>
    <button v-for="time in times" :key="time">{{ time }}</button>
  </div>
</template>

<script>
export default {
  name: 'TimePicker',
  data() {
    return {
      times: [],
      hour: 9,
    };
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
    console.log(this.time);
  },
};
</script>

<style scoped>
button {
  width: 5.5vw;
  height: 3vw;
  margin: 0.2vw;
  background: #5E6CBE;
}
</style>
