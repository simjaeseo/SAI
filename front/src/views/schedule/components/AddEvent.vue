<template>
  <form>

    <div id="btn-box">
      <select
      class="form-select"
      aria-label="Default select example"
      @change="pickMyConsultant"
      required>
        <option selected disabled>컨설턴트상담</option>
        <option
        v-for="myConsultant in myConsultants"
        :key="myConsultant"
        :value="myConsultant.id">
          {{ myConsultant.name }}
        </option>
      </select>
        <button
        class="btn"
        @click.prevent="pickMyConsultant">
          개인일정추가
        </button>
    </div>

    <div>
      <div v-for="time in times" :key="time" id='time-box' class="container">
        <button
        v-if="CTDaySchedules.includes(time)"
        class="btn btn-danger"
        >
          {{ time }}
        </button>
        <button
        v-else-if="daySchedules.includes(time)"
        class="btn btn-primary"
        >
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
        v-if="Credential.selectedConsultant"
        name="category"
        id="category"
        class="form-select mb-3"
        @change="selectCategory"
        required>
          <option selected disabled>분류</option>
          <option value="practice">1:1 모의 면접</option>
          <option value="consulting-job">직무 상담</option>
          <option value="consulting-introduction">자소서 상담</option>
          <option value="else">기타</option>
        </select>
        <select
        v-else
        name="category"
        id="category"
        class="form-select mb-3"
        @change="selectCategory"
        required>
          <option selected disabled>분류</option>
          <option value="document">서류 지원</option>
          <option value="coding-test">코딩 테스트</option>
          <option value="interview">면접</option>
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
      <button class="btn" @click="createSchedule(Credential)">등록</button>
    </div>

    <button @click="check(Credential)"></button>
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
        selectedConsultant: null,
        scheduleDetail: '',
        selectedCategory: null,
      },
    };
  },
  setup() {
    const store = useStore();

    const myConsultants = computed(() => store.getters.myConsultants);
    const fetchMyConsultants = () => {
      store.dispatch('fetchMyConsultants');
    };
    const noConsultant = (value) => {
      store.dispatch('pickMyConsultant', value);
    };

    // time picker
    const CTDaySchedules = computed(() => store.getters.CTDaySchedules);
    const daySchedules = computed(() => store.getters.daySchedules);
    const pickTime = (time) => {
      store.dispatch('pickTime', time);
    };

    // detail
    const entryScheduleDetail = (detail) => {
      store.dispatch('entryScheduleDetail', detail);
    };
    const createSchedule = (detail) => {
      store.dispatch('createSchedule', detail);
    };
    return {
      myConsultants,
      fetchMyConsultants,
      noConsultant,

      pickTime,
      CTDaySchedules,
      daySchedules,

      entryScheduleDetail,
      createSchedule,
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
    pickMyConsultant(e) {
      this.Credential.selectedConsultant = e.target.value;
    },
    selectCategory(e) {
      this.Credential.selectedCategory = e.target.value;
    },
    check(item) {
      console.log(item);
    },
  },
  created() {
    this.fetchMyConsultants();
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
