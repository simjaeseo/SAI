<template>
  <div class='container' id='my_page1'>
    <div class='d-flex justify-content-between'>
      <p id='profile_text1'>컨설턴트 개인정보 수정</p>
      <router-link to="/password/update" id='pw-router'>
        <button class="btn btn-primary" id='pw-router-btn'>비밀번호 변경</button>
      </router-link>
    </div>
    <hr>
    <div class='container'>
    <ProfileUpdateFormCT></ProfileUpdateFormCT>
    </div>
    <!-- 프로필수정버튼 -->
  </div>
</template>

<script>
import ProfileUpdateFormCT from './components/ProfileUpdateFormCT.vue';

export default {
  name: 'ProfileUpdateCTView',
  components: { ProfileUpdateFormCT },
};
</script>

<style scoped>
#pw-router-btn {
  margin-top: 100px;
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
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

#pw-router-btn:hover{
  color: white;
  background-color:  #5c6ac4;;
}
@media (prefers-reduced-motion: reduce) {
  #pw-router-btn {
    transition: none;
  }
}
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
  #update-btn {
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
  margin-top: 100px;
  margin-bottom: 0px;
  display: inline;
  height: 50px;
}
</style>
