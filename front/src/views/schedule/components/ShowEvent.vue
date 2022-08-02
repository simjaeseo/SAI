<template>
  <div>
    <div id="next-schedule">
      <h3>1:1 모의 화상 면접</h3>
      <h4>7월 30일 11 : 30</h4>
      <button class="btn">면접 바로가기</button>
      <br><br>
      <p>담당 컨설턴트 : 류채윤<br>타겟 기업 : 카카오<br>면접 분류 : 직무</p>
      <br>
    </div>
    <div id='layer'>
      <h5>다가오는 일정</h5>
    </div>
    <div>
      <p>7월 20일  카카오 서류 마감</p>
      <p>7월 21일  마카오 서류 마감</p>
      <p>7월 22일  코카오 서류 마감</p>
      <p>7월 23일  킹카오 서류 마감</p>
    </div>
    <!-- 주석! -->
    <!-- <div id="today-schedule">
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
    </div> -->
  </div>
</template>

<script>
// import { computed } from 'vue';
// import { useStore } from 'vuex';

export default {
  name: 'ShowEvent',
  // setup() {
  //   const store = useStore();

  //   const upcomingSchedules = computed(() => store.getters.upcomingSchedules);
  //   const fetchUpcomingSchedules = () => {
  //     store.dispatch('fetchUpcomingSchedules');
  //   };
  //   const today = new Date();

  //   const monthDate = `${today.getMonth + 1}월 ${today.getDate}일`;
  //   const myToday = `${today.getFullYear()}-${today.getMonth + 1}-${today.getDate}`;
  //   return {
  //     upcomingSchedules,
  //     fetchUpcomingSchedules,
  //     monthDate,
  //     myToday,
  //   };
  // },
  // created() {
  //   this.fetchSchedules();
  // },
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
