<template>
  <progress-bar></progress-bar>
  <mode-select></mode-select>
  <setting-check></setting-check>
  <question-select></question-select>
</template>

<script>
import ProgressBar from '@/views/interview/components/ProgressBar.vue';
import ModeSelect from '@/views/interview/components/ModeSelect.vue';
import SettingCheck from '@/views/interview/components/SettingCheck.vue';
import QuestionSelect from '@/views/interview/components/QuestionSelect.vue';

export default {
  // name: 'InterviewView',
  components: {
    ProgressBar,
    ModeSelect,
    SettingCheck,
    QuestionSelect,
  },
};
</script>

<style scoped>

</style>
