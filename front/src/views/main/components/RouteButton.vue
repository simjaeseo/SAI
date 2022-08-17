<template>
  <div class="container" >
    <div class="row">
      <div class="col-lg-7" id='left-box'>
        <p id='schedule-text1'>오늘의 일정</p>
        <div id="today-schedule">
          <div>
            <div v-if="upcomingSchedules.length > 0
              && upcomingSchedules[0].scheduleDate === myToday">
              <h5>
                {{ upcomingSchedules[0].startTime }}
                {{ upcomingSchedules[0].consultantName }}
                {{ upcomingSchedules[0].detail }}
                <router-link to='/interview/ct' id='routerlink'>
                  <button
                  v-if="this.myToday.slice(5, 7) === upcomingSchedules[0].scheduleDate.slice(5, 7)
                    && this.myToday.slice(-2) === upcomingSchedules[0].scheduleDate.slice(-2)&&
                    Number(Date().slice(15, 18)) ==
                    Number(upcomingSchedules[0].startTime.slice(0, 2)) - 1 ||
                    Number(Date().slice(15, 18)) ==
                    Number(upcomingSchedules[0].startTime.slice(0, 2)) ||
                    Number(Date().slice(15, 18)) ==
                    Number(upcomingSchedules[0].startTime.slice(0, 2)) - 3"
                    id="schedule-btn1" class="btn">
                      입장하기
                  </button>
                </router-link>
              </h5>
              <br>
            </div>
            <div v-else>
              <h5>오늘 일정이 없습니다.</h5>
            </div>
          </div>
      </div>
      </div>
      <div class="col-lg-5" id='right-box'>
        <router-link to="/interview"
        v-if="currentUser.memberStatus === 'TRAINEE'"
        style="text-decoration:none">
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
  name: 'RouteButton',
  async setup() {
    const myToday = `${new Date().getFullYear()}-${`0${new Date().getMonth() + 1}`.slice(-2)}-${`0${new Date().getDate()}`.slice(-2)}`;

    const store = useStore();

    const fetchUpcomingSchedules = async () => {
      await store.dispatch('fetchUpcomingSchedules');
    };

    await fetchUpcomingSchedules();

    const currentUser = computed(() => store.getters.currentUser);
    const upcomingSchedules = computed(() => store.getters.upcomingSchedules);

    return {
      myToday,
      currentUser,
      upcomingSchedules,
      fetchUpcomingSchedules,
    };
  },
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
