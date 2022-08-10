<template>
  <div>
    <div v-if="upcomingSchedules">
      <div id="today-schedule">
          <div>
            <!-- <h3>{{ upcomingSchedules[0].category }}</h3> -->
            <h4>{{ myToday }} {{ upcomingSchedules[0].startTime }}</h4>
            <br><br>
            <p v-if="upcomingSchedules[0].consultantName">
              담당 컨설턴트 : {{ upcomingSchedules[0].consultantName }}
            </p>
            <p>상세 정보 : {{ upcomingSchedules[0].detail }}</p>
            <button
            v-if="upcomingSchedules[0].category === 'practice'"
            class="btn">
              면접 바로가기
            </button>
          </div>
      </div>
      <div id='layer'>
        <h5>다가오는 일정</h5>
      </div>
      <div>
        <p v-for="upcomingSchedule in upcomingSchedules" :key="upcomingSchedule">
          {{ upcomingSchedule.scheduleDate.slice(5, 7) }}월
          {{ upcomingSchedule.scheduleDate.slice(-2) }}일
          {{ upcomingSchedule.detail }}
          {{ upcomingSchedule }}
        </p>
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
      myToday: `${new Date().getFullYear()}-${`0${new Date().getMonth() + 1}`.slice(-2)}-${`0${new Date().getDay()}`.slice(-2)}`,
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
