<template>
  <div class='container' id='signup-wrap1'>
    <SignupFormCT></SignupFormCT>
  </div>
</template>

<script>
import SignupFormCT from '@/views/accounts/components/SignupFormCT.vue';

export default {
  name: 'SignupCTView',
  components: { SignupFormCT },
};
</script>

<style scoped>
#signup-wrap1 {
  height: 1500px;
}

</style>
