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
      selectedTime: '',
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
    selected() {
      const btn = document.getElementsByClassName('btn');
      console.log(btn);
      function handleClick(event) {
        if (event.target.classList[1] === 'clicked') {
          event.target.classList.remove('clicked');
        } else {
          for (let i = 0; i < btn.length; i += 1) {
            btn[i].classList.remove('clicked');
          }

          event.target.classList.add('clicked');
        }
      }

      function init() {
        for (let i = 0; i < btn.length; i += 1) {
          console.log(btn[i]);
          btn[i].addEventListener('click', handleClick);
        }
      }

      init();
    },
  },
  mounted() {
    this.timeSet();
  },
};
</script>

<style scoped>
#time-box {
  margin-top: 40px;
  margin-bottom: 20px;
}
button {
  width: 5.5vw;
  height: 3vw;
  margin: 0.2vw;
  font-size: 1vw;
}
</style>
