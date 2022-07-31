<template>
  <div class='container'>
    <p id='help_pw_text1'>비밀번호찾기</p>
    <p id='help_pw_text2'>가입시 입력한 이메일로 임시비밀번호가 발송됩니다.</p>
    <find-password></find-password>
  </div>
</template>

<script>
import FindPassword from '@/views/accounts/components/FindPassword.vue';

export default {
  name: 'HelpPassword',
  components: { FindPassword },
};
</script>

<style scoped>
.container {
  text-align: center;
}
#help_pw_text1 {
  font-weight: bold;
  font-size: 30px;
  margin-top: 100px;
}
#help_pw_text2 {
  color: rgb(168, 168, 168);
  font-size: 15px;
  margin-bottom: 50px;
}

</style>
