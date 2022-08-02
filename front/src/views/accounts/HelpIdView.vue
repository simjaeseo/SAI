<template>
  <div class='container'>
    <p id='help_pw_text1'>아이디찾기</p>
    <p id='help_pw_text2'>가입시 입력한 이름과 생년월일을 입력하세요.</p>
    <find-ID></find-ID>
  </div>
</template>

<script>
import FindID from '@/views/accounts/components/FindID.vue';

export default {
  name: 'HelpPassword',
  components: { FindID },
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
