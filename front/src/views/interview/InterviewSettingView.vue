<template>
    <div>
      <progress-bar-second></progress-bar-second>
      <setting-check></setting-check>
    </div>
</template>

<script>
import ProgressBarSecond from '@/views/interview/components/ProgressBarSecond.vue';
import SettingCheck from '@/views/interview/components/SettingCheck.vue';

export default {
  components: {
    ProgressBarSecond,
    SettingCheck,
  },
  data() {
    return {
      sampleData: '',
    };
  },
  setup() {},
  created() {}, // 해당 vue 파일이 실행 되는 순간
  mounted() {}, // 템플릿 내 HTML DOM이 화면에 로딩이 되는 순간, 마운트가 다 끝난 순간 실행
  unmounted() {}, // 컴포넌트 이동 시 unmount가 일어나면서 해당 코드 자동 실행
  methods: {}, // 함수 정의해서 컴포넌트 내에서 사용 가능하게 함
};
</script>

<style>

</style>
