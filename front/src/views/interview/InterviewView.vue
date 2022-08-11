<template>
  <progress-bar-first></progress-bar-first>
  <mode-select></mode-select>
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

</style>
