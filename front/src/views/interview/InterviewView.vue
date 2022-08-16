<template>
  <div>
    <progress-bar-first></progress-bar-first>
    <mode-select></mode-select>
    <div class="container">
      <router-link to="/manual" id="manual-text">
        <p id="text">
          모의면접이 처음이신가요?&#128064; <br>
          사용법 알아보기
        </p>
      </router-link>
    </div>
  </div>
</template>

<script>
import ProgressBarFirst from '@/views/interview/components/ProgressBarFirst.vue';
import ModeSelect from '@/views/interview/components/ModeSelect.vue';
import { computed } from 'vue';
import { useStore } from 'vuex';

export default {
  // name: 'InterviewView',
  components: {
    ProgressBarFirst,
    ModeSelect,
  },
  data() {
    return {
      myToday: `${new Date().getFullYear()}-${`0${new Date().getMonth() + 1}`.slice(-2)}-${`0${new Date().getDay()}`.slice(-2)}`,
    };
  },
  setup() {
    const store = useStore();

    const upcomingSchedules = computed(() => store.getters.upcomingSchedules);
    const fetchUpcomingSchedules = () => {
      store.dispatch('fetchUpcomingSchedules');
    };
    return {
      upcomingSchedules,
      fetchUpcomingSchedules,
    };
  },
  created() {
    this.fetchUpcomingSchedules();
  },
};
</script>

<style scoped>
#manual-text {
  text-decoration: none;
  color: gray;
  font-weight: 300;
}
#manual-text:hover {
  text-decoration: none;
  color: rgb(95, 95, 95);
  cursor: pointer;
  font-weight: 400;
}
.container {
  margin-top: 50px;
  text-align: center;
}
</style>
