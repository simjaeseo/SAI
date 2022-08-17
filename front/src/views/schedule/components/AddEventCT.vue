<template>
  <form>

    <div id="btn-box">
        <button
        class="btn"
        :style="[Credential.selectedConsultant == '' ?
        {background:'#5c6ac4', color:'#ffffff'} : {background:'#ffffff'}]"
        disabled>
          개인일정추가
        </button>
    </div>

    <div>
      <div v-for="time in times" :key="time" id='time-box' class="container">
        <button
        v-if="daySchedules.includes(time)"
        class="scheduled"
        disabled>
          {{ time }}
        </button>
        <button
        v-else
        @click.prevent="Credential.startTime = time"
        :style="[Credential.startTime == time ?
        {background:'#5c6ac4', color:'#ffffff'} : {background:'#ffffff'}]"
        >
          {{ time }}
        </button>
      </div>

    <div id="div-category">
      <label for="category">분류
        <select
        name="category"
        id="category"
        class="form-select mb-3"
        @change="selectCategory"
        required>
          <option selected disabled>분류</option>
          <option value="block">신청 막기</option>
          <option value="else">기타</option>
        </select>
      </label>
    </div>
    <div id="div-category">
      <label for="detail">상세정보
        <input v-model="Credential.scheduleDetail" type="text" id="detail" class="form-control">
      </label>
    </div>
    <div id="add-button">
      <button class="btn" @click.prevent="createScheduleCT(Credential), forceRerender()">등록</button>
    </div>
    </div>
  </form>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';

export default {
  name: 'AddEvent',
  data() {
    return {
      times: [],
      hour: 9,
      selectedTime: '',
      Credential: {
        startTime: null,
        selectedConsultant: '',
        scheduleDetail: '',
        selectedCategory: null,
      },
    };
  },
  setup() {
    const store = useStore();

    // time picker
    const daySchedules = computed(() => store.getters.daySchedules);

    // detail
    const createScheduleCT = (detail) => {
      store.dispatch('createScheduleCT', detail);
    };
    return {
      daySchedules,

      createScheduleCT,
    };
  },
  methods: {
    forceRerenderSelectCT() {
      this.CTKey += 1;
    },
    forceRerender() {
      this.$emit('forceRerender');
    },
    timeSet() {
      for (let i = 0; this.hour < 18 || i === 30; i += 30) {
        if (i === 60) {
          this.hour += 1;
          i = 0;
        }
        if (i === 0) {
          this.times.push(`${`0${this.hour}`.slice(-2)}:00`);
        } else {
          this.times.push(`${`0${this.hour}`.slice(-2)}:${i}`);
        }
      }
    },
    selected() {
      const btn = document.getElementsByClassName('btn');
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
    selectCategory(e) {
      this.Credential.selectedCategory = e.target.value;
    },
    check(item) {
      console.log(item);
    },
  },
  mounted() {
    this.timeSet();
  },
};
</script>

<style scoped>
#btn-box {
  display: flex;
  justify-content: space-evenly;
  margin-top: 20px;
}
.btn {
  width: 150px;
  border: 1px solid #ced4da;
  color: #141414;
}
.btn:focus{
  width: 150px;
  border: 1px solid #5c6ac4;
  color: #141414;
}

.form-select {
  width: 150px;
  display: inline;
}
.form-select:focus {
  box-shadow: none;
  border: 1px solid #5c6ac4;
}

#time-box {
  margin-top: 40px;
  margin-bottom: 20px;
  display: inline;
}

button {
  border: 1px solid #ced4da;
  width: 68px;
  height: 40px;
  margin: 5px 5px 10px 5px;
  border-radius: 10px;
}

.scheduled{
  background-color: rgb(170, 170, 170);
}

#disable-time {
  background-color: red;
}

#add-button {
  text-align: end;
}
label {
  display: inline;
}
.form-control {
  height: 270px;
}
.form-control:focus {
  border: 1px solid #5c6ac4;
  box-shadow: none;
}
.btn {
  border: 1px solid #5c6ac4;
  color: #5c6ac4;
  margin-top: 10px;
  margin-bottom: 10px;
}
.btn:hover{
  border: 1px solid #5c6ac4;
  background-color: #5c6ac4;
  color: #ffffff;
  margin-top: 10px;
  margin-bottom: 10px;
}
.form-select:focus {
  border: 1px solid #5c6ac4;
  box-shadow: none;
}
</style>
