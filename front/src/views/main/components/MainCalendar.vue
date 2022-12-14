<template>
  <div class="calendar">
    <p class="d-flex justify-content-center" id='text-year'>
      {{currentYear}}년 {{currentMonth}}월
    </p>
      <table class="table">
          <thead>
            <tr>
              <td v-for="(weekName, index) in weekNames" :key="index">
                {{weekName}}
              </td>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(row, index) in currentCalendarMatrix" :key="index">
              <td v-for="(day, index2) in row" :key="index2" id="calendar-date">
                <p v-if="isToday(currentYear, currentMonth, day)" class="rounded">
                  {{day}}
                </p>
                <p v-else-if="
                scheduleDate.includes(
                  `${currentYear}-${`0${currentMonth}`.slice(-2)}-${`0${day}`.slice(-2)}`
                )"
                class="rounded2">
                  {{day}}
                </p>
                <p v-else id="calendar-date">
                  {{day}}
                </p>
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
  name: 'MainCalendar',
  async setup() {
    const store = useStore();

    const fetchSchedules = async () => {
      await store.dispatch('fetchSchedules');
    };

    await fetchSchedules();

    const schedules = computed(() => store.getters.schedules);
    const scheduleDate = [];

    for (let i = 0; i < schedules.value.length; i += 1) {
      if (schedules.value[i].category !== 'block') {
        scheduleDate.push(schedules.value[i].scheduleDate);
      }
    }

    return {
      scheduleDate,
      fetchSchedules,
    };
  },
  data() {
    return {
      weekNames: ['월', '화', '수', '목', '금', '토', '일'],
      rootYear: 1904,
      rootDayOfWeekIndex: 4, // 2000년 1월 1일은 토요일
      currentYear: new Date().getFullYear(),
      currentMonth: new Date().getMonth() + 1,
      currentDay: new Date().getDate(),
      currentMonthStartWeekIndex: null,
      currentCalendarMatrix: [],
      endOfDay: null,
      memoDatas: [],
    };
  },
  mounted() {
    this.init();
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
          if ((year % 4 === 0 && year % 100 !== 0) || year % 400 === 0) {
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
    onClickPrev(month) {
      this.month -= 1;
      if (month <= 0) {
        this.currentMonth = 12;
        this.currentYear -= 1;
      } else {
        this.currentMonth -= 1;
      }
      this.init();
    },
    onClickNext(month) {
      this.month += 1;
      if (month > 12) {
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
};
</script>

<style scoped type="text/css">
tbody td {
  border-color: white;
}
span {
  font-weight: 200;
  color: #5a5a5a;
}
thead {
  text-align: center;
  font-weight: 500;
  color: #5a5a5a;
}
#text-year {
  text-decoration: none;
  margin-top: 20px;
  color: #5a5a5a;
}
.rounded {
  border-radius: 80%;
  border:solid 1px #ffffff;
  background-color:#5c6ac475;
  /* padding:10px; */
  color:#ffffff;
}

.rounded2 {
  border-radius: 50px;
  border:solid 1px #ffffff;
  background-color:#ffd9d9;
  color:#ffffff;
}

#calendar-date {
  text-align: center;
  margin-bottom: 5px;
}
</style>
