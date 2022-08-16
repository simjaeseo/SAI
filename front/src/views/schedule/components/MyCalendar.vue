<template>
  <div class="calendar">
      <h2>
        <!-- <a href="#" @click="onClickPrev(currentMonth)">◀</a> -->
        <a href="#" @click="onClickPrev()">◀</a>
        {{currentYear}}년 {{currentMonth}}월
        <a href="#" @click="onClickNext()">▶</a>
        <!-- <a href="#" @click="onClickNext(currentMonth)">▶</a> -->
      </h2>
      <table class="table">
          <thead>
            <tr>
              <td id="th" v-for="(weekName, index) in weekNames" :key="index">
                {{weekName}}
              </td>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(row, index) in currentCalendarMatrix" :key="index">
              <td v-for="(day, index2) in row" :key="index2">
                <button
v-if="selectedDate === `${currentYear}-${`0${currentMonth}`.slice(-2)}-${`0${day}`.slice(-2)}`"
@click.prevent="pickDate(`${currentYear}-${`0${currentMonth}`.slice(-2)}-${`0${day}`.slice(-2)}`)"
                id="picked">
                  {{day}}
                </button>
                <button  v-else-if="day"
@click.prevent="pickDate(`${currentYear}-${`0${currentMonth}`.slice(-2)}-${`0${day}`.slice(-2)}`)">
                  {{day}}
                </button>
                <div v-for="sc in schedules" :key="sc" id="schedule">
                  <div v-if="sc.category !== 'block'">
                    <div
v-if="sc.scheduleDate === `${currentYear}-${`0${currentMonth}`.slice(-2)}-${`0${day}`.slice(-2)}`"
                    class="schedule-summary">
                      <div
:style="[sc.category == 'practice' || 'consulting-job' || 'consulting-introduction' ?
                      {background:'#deepblue'} : {background:'#deeppink'}]">
                        {{ sc.detail }}
                      </div>
                      <div class="schedule-detail">
                        {{ sc.scheduleDate.slice(5, 7) }}월
                        {{ sc.scheduleDate.slice(-2) }}일
                        {{ sc.startTime }}<br>
                        {{ sc.detail }}<br>
                        <span v-show="sc.consultantName">
                        {{ sc.consultantName }}
                        </span><br>
                        <button
                        class="btn btn-danger"
                        @click="deleteSchedule(sc.id)">
                        삭 제
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              </td>
            </tr>
          </tbody>
      </table>
  </div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';

export default {
  name: 'MyCalendar',
  data() {
    const cKey = 0;
    return {
      weekNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
      rootYear: 1904,
      rootDayOfWeekIndex: 4, // 2000년 1월 1일은 토요일
      currentYear: new Date().getFullYear(),
      currentMonth: new Date().getMonth() + 1,
      currentDay: new Date().getDate(),
      currentMonthStartWeekIndex: null,
      currentCalendarMatrix: [],
      endOfDay: null,
      memoDatas: [],
      yearMonth: `${new Date().getFullYear()}-${`0${new Date().getMonth() + 1}`.slice(-2)}`,
      cKey,
    };
  },
  setup() {
    const store = useStore();

    const schedules = computed(() => store.getters.schedules);
    const selectedDate = computed(() => store.getters.selectedDate);
    const pickDate = (date) => {
      store.dispatch('pickDate', date);
    };
    const fetchSchedules = () => {
      store.dispatch('fetchSchedules');
    };
    const deleteSchedule = (scheduleId) => {
      store.dispatch('deleteSchedule', scheduleId);
    };
    const fetchCTDaySchedules = (date) => {
      store.dispatch('fetchCTDaySchedules', date);
    };
    return {
      pickDate,
      selectedDate,
      schedules,
      fetchSchedules,
      deleteSchedule,
      fetchCTDaySchedules,
    };
  },
  methods: {
    init() {
      this.currentMonthStartWeekIndex = this.getStartWeek(this.currentYear, this.currentMonth);
      this.endOfDay = this.getEndOfDay(this.currentYear, this.currentMonth);
      this.initCalendar();
    },
    initCalendar() {
      this.currentCalendarMatrix = [];
      let day = 1;
      for (let i = 0; i < 6; i += 1) {
        const calendarRow = [];
        for (let j = 0; j < 7; j += 1) {
          if (i === 0 && j < this.currentMonthStartWeekIndex) {
            calendarRow.push('');
          } else if (day <= this.endOfDay) {
            calendarRow.push(day);
            day += 1;
          } else {
            calendarRow.push('');
          }
        }
        this.currentCalendarMatrix.push(calendarRow);
      }
    },
    getEndOfDay(year, month) {
      switch (month) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
          return 31;
        case 4:
        case 6:
        case 9:
        case 11:
          return 30;
        case 2:
          if ((year % 4 === 0 && year % 100 !== 0) || (year % 400 === 0)) {
            return 29;
          }

          return 28;

        default:
          return 0;
      }
    },
    getStartWeek(targetYear, targetMonth) {
      let year = this.rootYear;
      let month = 1;
      let sumOfDay = this.rootDayOfWeekIndex;
      while (true) {
        if (targetYear > year) {
          for (let i = 0; i < 12; i += 1) {
            sumOfDay += this.getEndOfDay(year, month + i);
          }
          year += 1;
        } else if (targetYear === year) {
          if (targetMonth > month) {
            sumOfDay += this.getEndOfDay(year, month);
            month += 1;
          } else if (targetMonth === month) {
            return (sumOfDay) % 7;
          }
        }
      }
    },
    onClickPrev() {
      if (this.currentMonth === 1) {
        this.currentMonth = 12;
        this.currentYear -= 1;
      } else {
        this.currentMonth -= 1;
      }
      this.init();
    },
    onClickNext() {
      if (this.currentMonth === 12) {
        this.currentMonth = 1;
        this.currentYear += 1;
      } else {
        this.currentMonth += 1;
      }
      this.init();
    },
    isToday(year, month, day) {
      const date = new Date();
      return year === date.getFullYear() && month === date.getMonth() + 1 && day === date.getDate();
    },
  },
  mounted() {
    this.init();
  },
  created() {
    this.fetchSchedules();
  },
};
</script>

<style scoped>
button {
  background: none;
  border: none;
  padding: 0;
  font-size: 10px;
}
button:hover {
  color: white;
}
.calender {
  height: 600px;
}
#picked {
  width: 15px;
  border-radius: 50px;
  background-color: rgb(192, 191, 191);
  color: white;
}

td {
  height: 100px;
  font-size: 10px;
}
td:hover {
  background-color: #5c6ac475;
}

a {
  text-decoration: none;
  color: #5a5a5a7a;
}

h2 {
  display: flex;
  justify-content: space-between;
  text-align: center;
  padding-top: 20px;
  color: #5a5a5a;
}

#th {
  height: 100px;
  text-align: center;
  font-weight: 500;
  color: #5a5a5a;
}

.schedule-summary {
  position: relative;
  display: block;
  border-radius: 5%;
  background: rgba(0, 191, 255, 0.459);
  color: white;
}

.schedule-detail {
  visibility: hidden;
  width: 5vw;
  background-color: gray;
  color: white;
  text-align: center;
  border-radius: 5%;
  padding: 1vw;
  position: absolute;
  z-index: 1;
}

.schedule-summary:hover .schedule-detail {
  visibility: visible;
}

.schedule-summary .schedule-detail::after {
  position: absolute;
  border-style: solid;
  border-width: 2vw;
}

.schedule-summary .schedule-detail {
  width: 120px;
  top: 105%;
  left: 50%;
  margin-left: -60px;
}
</style>
