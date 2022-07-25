<template>
  <div>
    <div>
      <p>안녕하세요 user님!</p>
      <route-button></route-button>
    </div>
  </div>
</template>

<script>
import RouteButton from './components/RouteButton.vue';

export default {
  name: 'MainPage',
  components: {
    RouteButton,
  },
};
</script>

<style>

</style>
