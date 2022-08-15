<template>
  <div class="container" >
    <div class="row">
      <div class="col-lg-7" id='left-box'>
        <p id='schedule-text1'>오늘의 일정</p>
        <div id="today-schedule">
          <div>
            <!-- <h3>{{ upcomingSchedules[0].category }}</h3> -->
              <div v-for="upcomingSchedule in upcomingSchedules" :key="upcomingSchedule">
                <h5>
                  <!-- {{ this.myToday.slice(5, 7) }} {{ this.myToday.slice(-2)}} -->
                  {{ upcomingSchedule.startTime }}
                  {{ upcomingSchedule.consultantName }}
                  {{ upcomingSchedule.detail }}
                  <!-- {{ upcomingSchedule.scheduleDate.slice(5, 7) }}월
                  {{ upcomingSchedule.scheduleDate.slice(-2) }}일 -->
                  <router-link to='/interview/ct' id='routerlink'>
                    <button
                      v-if="this.myToday.slice(5, 7) === upcomingSchedule.scheduleDate.slice(5, 7)
                      && this.myToday.slice(-2) === upcomingSchedule.scheduleDate.slice(-2)&&
                      Number(Date().slice(15, 18)) ==
                      Number(upcomingSchedule.startTime.slice(0, 2)) - 1 ||
                      Number(Date().slice(15, 18)) ==
                      Number(upcomingSchedule.startTime.slice(0, 2))"
                      id="schedule-btn1" class="btn">
                        입장하기
                    </button>
                  </router-link>
                </h5>
                <br>
            </div>
          </div>
      </div>
      </div>
      <div class="col-lg-5" id='right-box'>
        <router-link to="/interview" style="text-decoration:none">
          <button id='schedule-btn2' class="btn">모의면접시작</button>
        </router-link>
        <router-link v-if="currentUser.memberStatus === 'TRAINEE'"
        to="/schedule" style="text-decoration:none">
          <button id='schedule-btn3' class="btn">일정관리</button>
        </router-link>
        <router-link v-else to="/scheduleCT" style="text-decoration:none">
          <button id='schedule-btn3' class="btn">일정관리</button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';

export default {
  data() {
    return {
      myToday: `${new Date().getFullYear()}-${`0${new Date().getMonth() + 1}`.slice(-2)}-${`0${new Date().getDate()}`.slice(-2)}`,
    };
  },
  setup() {
    const store = useStore();
    const currentUser = computed(() => store.getters.currentUser);
    const upcomingSchedules = computed(() => store.getters.upcomingSchedules);
    const fetchUpcomingSchedules = () => {
      store.dispatch('fetchUpcomingSchedules');
    };
    return {
      currentUser,
      upcomingSchedules,
      fetchUpcomingSchedules,
    };
  },
  created() {
    this.fetchUpcomingSchedules();
  }, // 해당 vue 파일이 실행 되는 순간
};
</script>

<style scoped>
#schedule-btn3 {
  display: block;
  background-color: #ffffff;
  color: #5c6ac4;
  border: 1px solid #5c6ac4;
  font-size: 13px;
  width: 90%;
  padding-top: 15px;
  padding-bottom: 15px;
  margin-top: 30px;
}
#schedule-btn2 {
  display: block;
  background-color: #ffffff;
  color: #5c6ac4;
  border: 1px solid #5c6ac4;
  font-size: 13px;
  width: 90%;
  padding-top: 15px;
  padding-bottom: 15px;
}
#schedule-btn2:hover {
  background-color: #5c6ac4;
  color: white;
}
#schedule-btn3:hover {
  background-color: #5c6ac4;
  color: white;
}
#schedule-text1 {
  font-size: 30px;
  margin-top: 20px;
}
#schedule-btn1 {
  background-color: #5c6ac4;
  color: #ffffff;
  border: 1px solid #5c6ac4;
  font-size: 13px;
  margin-left: 10px;
}
.container {
  margin: auto;
}
#left-box {
  text-align: center;
}
</style>
