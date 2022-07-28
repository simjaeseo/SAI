<template>
  <div class="calendar">
      <h2>
        <a href="#" v-on:click="onClickPrev(currentMonth)">◀</a>
        {{currentYear}}년 {{currentMonth}}월
        <a href="#" v-on:click="onClickNext(currentMonth)">▶</a>
      </h2>
      <table class="table table-hover">
          <thead>
            <tr>
              <td id="th" v-for="(weekName, index) in weekNames" v-bind:key="index">
                {{weekName}}
              </td>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(row, index) in currentCalendarMatrix" :key="index">
              <td v-for="(day, index2) in row" :key="index2">
                <button v-if="selectDate === day"
                @click.prevent="pickDate(day)"
                id="picked">
                  {{day}}
                </button>
                <button  v-else-if="day" @click.prevent="pickDate(day)">
                  {{day}}
                </button>
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
    };
  },
  setup() {
    const store = useStore();

    const selectDate = computed(() => store.getters.selectedDate);
    const pickDate = (date) => {
      store.dispatch('pickDate', date);
    };
    return { pickDate, selectDate };
  },
  mounted() {
    this.init();
  },
  methods: {
    clickDate(date) {
      if (this.selectedDate !== date) {
        this.selectedDate = date;
      } else {
        this.selectedDate = false;
      }
      console.log(date, this.selectedDate);
      this.$emit('pick-date', this.selectedDate);
    },
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

<style scoped>
#picked {
  /* border-radius: 50%; */
  background-color:#5E6CBE;
  padding: 0.1vw;
  color:#ffffff;
}

td {
  height: 6vw;
  font-size: 0.3vw;
}

a {
  text-decoration: none;
}

h2 {
  display: flex;
  justify-content: space-between;
  text-align: center;
  padding: 1%;
}

#th {
  height: 2vw;
  width: 4vw;
  font-size: 1vw;
}

#test {
  background-color: aqua;
  border-radius: 20px;
  text-indent: 0.4vw;
  margin-bottom: 0.4vw;
}

#test2 {
  background-color: pink;
  border-radius: 20px;
  text-indent: 0.4vw;
  margin-bottom: 0.4vw;
}
</style>
