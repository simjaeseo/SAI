<template>
  <div class='container'>
    <p id='update-pw-text1'>비밀번호 변경</p>
    <password-update-form></password-update-form>
  </div>
</template>

<script>
import PasswordUpdateForm from './components/PasswordUpdateForm.vue';

export default {
  components: { PasswordUpdateForm },
  name: 'PasswordUpdateView',
};
</script>

<style scoped>
.container {
  text-align: center;
}
#update-pw-text1 {
  font-size: 30px;
  font-weight: 900;
  line-height: 2;
  font-stretch: normal;
  letter-spacing: normal;
  margin-top: 100px;
}
</style>
