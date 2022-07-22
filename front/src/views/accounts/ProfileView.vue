<template>
  <div class='container'>
    <p id='profile_text1'>~~님의 마이페이지</p>
    <hr>
    <div class="row">
      <profile-image></profile-image>
    </div>
  </div>
</template>

<script>
import ProfileImage from '@/views/accounts/components/ProfileImage.vue';

export default {
  name: 'ProfileView',
  components: { ProfileImage },
};
</script>

<style scoped>
#profile_text1 {
  font-size: 30px;
  font-weight: 900;
  line-height: 2;
  font-stretch: normal;
  letter-spacing: normal;
  margin-top: 50px;
  text-align: start;
}
</style>
