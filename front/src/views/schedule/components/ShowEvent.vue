<template>
  <div>
    <div id="today-schedule">
        <div v-if="upcomingSchedules[0].schedule_date == myToday">
          <h3>{{ upcomingSchedules[0].detail }}</h3>
          <h4>{{ monthDate }} {{ upcomingSchedules[0].start_time }}</h4>
          <br><br>
          <p v-if="upcomingSchedules[0].category === '1:1 모의 면접'">
            담당 컨설턴트 : {{ upcomingSchedules[0].consultant_name }}</p>
          <p>상세 정보 : {{ upcomingSchedules[0].detail }}</p>
          <button v-if="upcomingSchedules[0].category === '1:1 모의 면접'" class="btn">면접 바로가기</button>
        </div>
    </div>
    <div id='layer'>
      <h5>다가오는 일정</h5>
    </div>
    <div>
      <p v-for="upcomingSchedule in upcomingSchedules" :key="upcomingSchedule">
        {{ upcomingSchedule.schedule_date.slice(5, 7) }}월
         {{ upcomingSchedule.schedule_date.slice(8) }}일
         {{ upcomingSchedule.detail }}
      </p>
    </div>
  </div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';

export default {
  name: 'ShowEvent',
  setup() {
    const store = useStore();

    const upcomingSchedules = computed(() => store.getters.upcomingSchedules);
    const fetchUpcomingSchedules = (today) => {
      store.dispatch('fetchUpcomingSchedules', today);
    };
    const today = new Date();

    const year = today.getFullYear();
    const month = (`0${today.getMonth() + 1}`).slice(-2);
    const day = (`0${today.getDate()}`).slice(-2);

    const monthDate = `${month}월 ${day}일`;
    const myToday = `${year}-${month}-${day}`;
    return {
      upcomingSchedules,
      fetchUpcomingSchedules,
      monthDate,
      myToday,
    };
  },
  created() {
    this.fetchUpcomingSchedules(this.myToday);
  },
};
</script>

<style scoped>
.btn {
  margin-top: 10px;
  background-color: #5c6ac4;
  color: white;
  padding-top: 10px;
  padding-bottom: 10px;
}
#today-schedule {
  text-align: center;
  margin-top: 50px;
}

h3 h4 h5 {
  color: #5a5a5a;
}
h5 {
  text-align: center;
  margin-top: 50px;
  margin-bottom: 30px;
}
p {
  color: #5a5a5a;
  text-align: center
};
</style>
