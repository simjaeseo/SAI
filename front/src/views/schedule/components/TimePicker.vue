<template>
  <div id='time-box' class="container">
    <h5>시간 선택</h5>
    <button class="btn" @click="selected">09:00</button>
    <button class="btn" @click="selected">09:30</button>
    <button class="btn" @click="selected">10:00</button>
    <button class="btn" @click="selected">10:30</button>
    <button class="btn" @click="selected">11:00</button>
    <button class="btn" @click="selected">11:30</button>
    <button class="btn" @click="selected">12:00</button>
    <button class="btn" @click="selected">12:30</button>
    <button class="btn" @click="selected">13:00</button>
    <button class="btn" @click="selected">13:30</button>
    <button class="btn" @click="selected">14:00</button>
    <button class="btn" @click="selected">14:30</button>
    <button class="btn" @click="selected">15:00</button>
    <button class="btn" @click="selected">15:30</button>
    <button class="btn" @click="selected">16:00</button>
    <button class="btn" @click="selected">16:30</button>
    <button class="btn" @click="selected">17:00</button>
    <button class="btn" @click="selected">17:30</button>
    <button class="btn" @click="selected">18:00</button>
    <button class="btn" @click="selected">18:30</button>
    <button class="btn" @click="selected">19:00</button>
    <button class="btn" @click="selected">19:30</button>
    <button class="btn" @click="selected">20:00</button>
    <button class="btn" @click="selected">20:30</button>
    <button class="btn" @click="selected">21:00</button>
  </div>
</template>

<script>
export default {
  name: 'TimePicker',
  data() {
    return {
      times: [],
      hour: 9,
      selectedTime: '',
    };
  },
  methods: {
    // timeSet() {
    //   for (let i = 0; this.hour < 18 || i === 30; i += 30) {
    //     if (i === 60) {
    //       this.hour += 1;
    //       i = 0;
    //     }
    //     if (i === 0) {
    //       this.times.push(`${this.hour}:00`);
    //     } else {
    //       this.times.push(`${this.hour}:${i}`);
    //     }
    //   }
    // },
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
  // mounted() {
  //   this.timeSet();
  // },
};
</script>

<style scoped>
#time-box {
  margin-top: 40px;
  margin-bottom: 20px;
}
button {
  border: 1px solid #ced4da;
  width: 65px;
  margin: 5px 5px 5px 5px;
}
.clicked {
  color: white;
  background-color: #5c6ac4;
}
</style>
