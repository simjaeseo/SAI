<template>
  <div class='container' id='signup-wrap1'>
    <signup-form id='signup-form'></signup-form>
  </div>
</template>

<script>
import SignupForm from '@/views/accounts/components/SignupForm.vue';

export default {
  name: 'SignupView',
  components: { SignupForm },
};
</script>

<style scoped>
#signup-wrap1 {
  height: 1500px;
}

</style>
