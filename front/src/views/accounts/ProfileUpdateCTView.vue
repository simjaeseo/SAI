<template>
  <div>
    <div class='container' id='my_page1'>
      <p id='profile_text1'>~~님의 마이페이지</p>
      <hr>
      <div class='container'>
        <profile-data></profile-data>
      </div>
      <!-- 프로필수정버튼 -->
      <div id='profile-update-btn'>
        <router-link to='/update'>
          <button class='btn' id='update-btn'>프로필 수정</button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import ProfileData from '@/views/accounts/components/ProfileData.vue';

export default {
  name: 'ProfileUpdateCTView',
  components: {
    ProfileData,
  },
};
</script>

<style scoped>
#update-btn{
  margin-top: 20px;
  z-index: 1000;
  background-color: #ffffff;
  color:  #5c6ac4;
  display: inline-block;
  font-weight: 400;
  line-height: 1.5;
  text-align: center;
  text-decoration: none;
  vertical-align: middle;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
  border: 1px solid  #5c6ac4;
  font-size: 15px;
  border-radius: 0.25rem;
  padding: 0.5rem;
  width: 200px;
  height: 50px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

#update-btn:hover{
  color: white;
  background-color:  #5c6ac4;;
}
@media (prefers-reduced-motion: reduce) {
  .update-btn {
    transition: none;
  }
}
#profile-update-btn {
  text-align: center;
  margin-top: 70px;
}
#my_page1 {
  height: 180vh;
}
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
