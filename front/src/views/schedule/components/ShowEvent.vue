<template>
  <div id="component-body">
    <div v-if="upcomingSchedules">
      <div id="today-schedule">
          <div>
              <h3>다가오는 일정</h3><br>
              <div v-for="upcomingSchedule in upcomingSchedules" :key="upcomingSchedule">
                <hr>
                <div
                id="consulting"
                v-if="upcomingSchedule.consultantName || upcomingSchedule.studentName">
                  <div class="schedule-head">
                    <div class="consulting-circle"></div>
                    <p>
                      {{ upcomingSchedule.detail }}
                    </p>
                  </div>
                  <p>
                    {{ upcomingSchedule.scheduleDate.slice(5, 7) }}월
                    {{ upcomingSchedule.scheduleDate.slice(-2) }}일
                    {{ upcomingSchedule.startTime }}
                  </p>
                  <p v-if="upcomingSchedule.consultantName">
                    담당 컨설턴트 : {{ upcomingSchedule.consultantName }}
                  </p>
                  <p v-else>
                    교육생 : {{ upcomingSchedule.studentName}}
                  </p>
                  <router-link to='/interview/ct' id='routerlink'>
                    <button
                      v-if="(this.myToday.slice(5, 7) === upcomingSchedule.scheduleDate.slice(5, 7)
                      && this.myToday.slice(-2) === upcomingSchedule.scheduleDate.slice(-2))&&
                  (Number(Date().slice(15, 18)) == Number(upcomingSchedule.startTime.slice(0, 2))||
                      (Number(Date().slice(15, 18)) ==
                      Number(upcomingSchedule.startTime.slice(0, 2) - 1) &&
                      Number(Date().slice(15, 18)) > 30))"
                      class="btn">
                        미팅룸 바로가기
                    </button><br>
                  </router-link>
                </div>
                <div id="personal-schedule" v-else>
                  <div class="schedule-head">
                    <div class="personal-circle"></div>
                    <p>
                      {{ upcomingSchedule.detail }}
                    </p>
                  </div>
                  <p>
                    {{ upcomingSchedule.scheduleDate.slice(5, 7) }}월
                    {{ upcomingSchedule.scheduleDate.slice(-2) }}일
                    {{ upcomingSchedule.startTime }}
                  </p>
                </div>
            </div>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';

export default {
  name: 'ShowEvent',
  data() {
    return {
      myToday: `${new Date().getFullYear()}-${`0${new Date().getMonth() + 1}`.slice(-2)}-${`0${new Date().getDate()}`.slice(-2)}`,
    };
  },
  setup() {
    const store = useStore();

    const upcomingSchedules = computed(() => store.getters.upcomingSchedules);
    return {
      upcomingSchedules,
    };
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
  text-align: start;
  text-indent: 1px;
  margin-top: 50px;
}

h3 h4 h5 {
  color: #5a5a5a;
  margin: 0%;
  padding: 0%;
}

h5 {
  text-align: center;
  margin-top: 50px;
  margin-bottom: 30px;
}

p {
  text-align: start;
  text-indent: 15px;
  margin: 1vh 0vw;
}

#component-body {
  background-color: #ffffff;
  padding: 0%;
}

#consulting {
  /* background-color: #818ac3; */
  margin: 0.5px 0px;
  padding: 0.5px 0px;
  border-radius: 15px;
}

#personal-schedule {
  /* background-color: #ffbcbc; */
  margin: 0.5px 0px;
  padding: 1px 0px;
  border-radius: 15px;
}

.consulting-circle {
  height: 10px;
  width: 10px;
  background-color: #5c6ac4;
  border-radius: 50%;
  vertical-align: middle;
  position: absolute;
  top: 35%;
}

.personal-circle {
  height: 10px;
  width: 10px;
  background-color: #ff9e9e;
  border-radius: 50%;
  vertical-align: middle;
  position: absolute;
  top: 35%;
}

.schedule-head {
  position: relative;
}
</style>
