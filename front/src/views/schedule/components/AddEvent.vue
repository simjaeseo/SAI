<template>
  <form>
    <div id="btn-box">
      <select-CT
      id="select-ct"
      class="form-select"
      :selectedConsultant="Credential.selectedConsultant"
      :key="CTKey"
      @pickMyConsultant="pickMyConsultant">
      </select-CT>
      <button
      class="btn"
      @click.prevent="pickMyConsultant(null), forceRerenderSelectCT()"
      :style="[Credential.selectedConsultant == '' ?
      {background:'#5c6ac4', color:'#ffffff'} : {background:'#ffffff'}]">
        개인 일정
      </button>
    </div>

    <div class="mt-4">
      <div v-for="time in times" :key="time" id='time-box'>
        <button
        id="time-btn"
        v-if="CTDaySchedules.includes(time)"
        class="btn btn-danger"
        >
          {{ time }}
        </button>
        <button
        id="time-btn"
        v-else-if="daySchedules.includes(time)"
        class="btn btn-primary"
        >
          {{ time }}
        </button>
        <button
        id="time-btn"
        v-else
        @click.prevent="Credential.startTime = time"
        :style="[Credential.startTime == time ?
        {background:'#5c6ac4', color:'#ffffff'} : {background:'#ffffff'}]"
        >
          {{ time }}
        </button>
      </div>

    <div id="div-category">
      <label for="category">
        <select
        v-if="Credential.selectedConsultant"
        name="category"
        id="category"
        class="form-select mb-3"
        @change="selectCategory"
        required>
          <option selected disabled>상담 분야</option>
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
          <option selected disabled>선택</option>
          <option value="document">서류 지원</option>
          <option value="coding-test">코딩 테스트</option>
          <option value="interview">면접</option>
          <option value="else">기타</option>
        </select>
      </label>
    </div>
    <div id="div-category">
      <label for="detail">상세정보
        <textarea v-model="Credential.scheduleDetail" type="text" id="detail"
        class="form-control" row="3"></textarea>
      </label>
    </div>
    <div id="add-button">
      <button class="btn" @click.prevent="createSchedule(Credential), forceRerender()">등록</button>
    </div>
    </div>
  </form>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';
import SelectCT from './SelectCT.vue';

export default {
  name: 'AddEvent',
  components: {
    SelectCT,
  },
  props: ['updateKey'],
  data() {
    return {
      times: [],
      hour: 9,
      selectedTime: '',
      CTKey: 0,
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

    const selectedDate = computed(() => store.getters.selectedDate);
    const myConsultants = computed(() => store.getters.myConsultants);
    const fetchMyConsultants = () => {
      store.dispatch('fetchMyConsultants');
    };

    // time picker
    const CTDaySchedules = computed(() => store.getters.CTDaySchedules);
    const fetchCTDaySchedules = (data) => {
      store.dispatch('fetchCTDaySchedules', data);
    };
    const resetCTDaySchedules = () => {
      store.commit('RESET_CT_DAY_SCHEDULES');
    };
    const daySchedules = computed(() => store.getters.daySchedules);
    const pickTime = (time) => {
      store.dispatch('pickTime', time);
    };

    // detail
    const createSchedule = (detail) => {
      store.dispatch('createSchedule', detail);
    };
    return {
      myConsultants,
      selectedDate,
      fetchMyConsultants,
      fetchCTDaySchedules,
      resetCTDaySchedules,

      pickTime,
      CTDaySchedules,
      daySchedules,

      createSchedule,
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
    pickMyConsultant(value) {
      console.log(value);
      this.Credential.selectedConsultant = value;
      const data = {
        consultant: this.Credential.selectedConsultant,
        date: this.selectedDate,
      };
      if (this.Credential.selectedConsultant) {
        this.fetchCTDaySchedules(data);
      } else {
        this.resetCTDaySchedules();
      }
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
#category {
  margin-top: 30px;
  width: 100%;
}
#time-btn {
  display: inline;
  width: 70px;
  height: 40px;
  margin-right: 7px;
}
#select-ct {
  height: 40px;
  margin: 10px 5px 10px 5px;
  border-radius: 10px;
  border-color: #5c6ac4;
  text-align: center;
  padding: 0;
  color: #5c6ac4;
}
#select-ct:hover {
  height: 40px;
  margin: 10px 5px 10px 5px;
  border-radius: 10px;
  border-color: #5c6ac4;
  text-align: center;
  padding: 0;
  color: #5c6ac4;
  font-weight: 500;
  cursor: pointer;
}
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
.btn:hover {
  border: 1px solid #5c6ac4;
  color: #5c6ac4;
  margin-top: 10px;
  margin-bottom: 10px;
  font-weight: 500;
}

.form-select:focus {
  border: 1px solid #5c6ac4;
  box-shadow: none;
}
</style>
