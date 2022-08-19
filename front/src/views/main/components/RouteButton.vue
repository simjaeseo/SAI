<template>
  <div class="container" >
    <div class="row">
      <div class="col-lg-7" id='left-box'>
        <p id='schedule-text1'>오늘의 일정</p>
        <div id="today-schedule">
          <!-- 학생일때 -->
          <div v-if="currentUser.memberStatus === 'TRAINEE'">
            <div v-if="soloSt.length > 0
              && soloSt[0].scheduleDate === myToday">
              <h5>
                {{ soloSt[0].startTime }}
                {{ soloSt[0].consultantName }}
                {{ soloSt[0].detail }}
              </h5>
            </div>
            <div v-else-if="withCt.length > 0
              && withCt[0].scheduleDate === myToday">
              <h5>
                {{ withCt[0].startTime }}
                {{ withCt[0].consultantName }}
                {{ withCt[0].detail }}
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
            </div>
            <div v-else>
              <h5>오늘 일정이 없습니다.</h5>
            </div>
          </div>
          <div v-if="currentUser.memberStatus === 'CONSULTANT'">
            <div v-if="soloCt.length > 0
              && soloCt[0].scheduleDate === myToday">
              <h5>
                {{ soloCt[0].startTime }}
                {{ soloCt[0].consultantName }}
                {{ soloCt[0].detail }}
              </h5>
            </div>
            <div v-else-if="withSt.length > 0
              && withSt[0].scheduleDate === myToday">
              <h5>
                {{ withSt[0].startTime }}
                {{ withSt[0].consultantName }}
                {{ withSt[0].detail }}
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
import { computed, onBeforeMount } from 'vue';
import { useStore } from 'vuex';

export default {
  name: 'RouteButton',
  async setup() {
    const myToday = `${new Date().getFullYear()}-${`0${new Date().getMonth() + 1}`.slice(-2)}-${`0${new Date().getDate()}`.slice(-2)}`;

    const store = useStore();
    const currentUser = computed(() => store.getters.currentUser);
    const upcomingSchedules = computed(() => store.getters.upcomingSchedules);
    const soloCt = [];
    const soloSt = [];
    const withCt = [];
    const withSt = [];
    onBeforeMount(() => {
      // 컨설턴트랑 일정 (교육생 입장)
      console.log(upcomingSchedules.value);
      for (let i = 0; i < upcomingSchedules.value.length; i += 1) {
        if (upcomingSchedules.value[i].consultantId != null) {
          withCt.push(upcomingSchedules.value[i]);
        }
      }
      // 학생이랑 일정 (컨설턴트 입장)
      for (let i = 0; i < upcomingSchedules.value.length; i += 1) {
        if (upcomingSchedules.value[i].studentId != null) {
          withSt.push(upcomingSchedules.value[i]);
        }
      }
      // 개인일정 교육생
      for (let i = 0; i < upcomingSchedules.value.length; i += 1) {
        if (upcomingSchedules.value[i].consultantId === null) {
          soloSt.push(upcomingSchedules.value[i]);
        }
      }
      // 개인일정 컨설턴트
      for (let i = 0; i < upcomingSchedules.value.length; i += 1) {
        if (upcomingSchedules.value[i].studentId === null) {
          soloCt.push(upcomingSchedules.value[i]);
        }
      }
      console.log(withCt);
    });

    const fetchUpcomingSchedules = async () => {
      await store.dispatch('fetchUpcomingSchedules');
    };

    await fetchUpcomingSchedules();

    return {
      myToday,
      currentUser,
      upcomingSchedules,
      fetchUpcomingSchedules,
      withCt,
      withSt,
      soloCt,
      soloSt,
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
