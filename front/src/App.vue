<template>
  <div id='app'>
    <nav class="navbar navbar-expand-lg navbar-light bg-white" id='sai-nav'>
      <div class="container-fluid">
        <router-link to='/'>
          <img src="@/assets/logo8.png" alt="로고" id='logo-img'>
        </router-link>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
        data-bs-target="#navbarNavDropdown"
        aria-controls="navbarNavDropdown"
        aria-expanded="false"
        aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarNavDropdown">
          <ul class="navbar-nav">
            <!-- 네브바 링크 뭐 추가할지 생각 해봐야 함 -->
            <!-- v-if써서 컨설턴트님일경우 교육생관리 링크 필요 -->
            <!-- <li class="nav-item" id='nav-router'>
              <router-link to='/' id='router-inner-text'>
                <p>메인페이지</p>
              </router-link>
            </li> -->
            <!-- <li class="nav-item" id='nav-router'>
              <router-link to='/interview' id='router-inner-text'>
                <p>모의면접</p>
              </router-link>
            </li> -->
            <li class="nav-item">
              <img src="@/assets/icon-head-dm2.png" alt="notice-icon" id='notice-icon2'>
            </li>
            <li class="nav-item">
              <img src="@/assets/icon-head-push-bell.jpg" alt="notice-icon" id='notice-icon'>
            </li>
            <li class="nav-item dropdown" id='user-icon'>
              <a class="nav-link dropdown-toggle"
              href="#" id="navbarDropdownMenuLink"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false">
                <img src="@/assets/profile2.png" alt="" id='nav-user-profile-img'>
                user님
              </a>
              <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" id='dropdown-ul'>
                <li id='dropdown-li'>
                  <router-link to='/profile' id='dropdown-router'>
                    <p id='dropdown-link-text'>마이페이지</p>
                  </router-link>
                </li>
                <li id='dropdown-li'>
                  <router-link to='/password/update' id='dropdown-router'>
                    <p id='dropdown-link-text'>비밀번호수정</p>
                  </router-link>
                </li>
                <li id='dropdown-li'>
                  <a href="#" @click="logout()" @keyup="logout()">
                    <p id='dropdown-link-text'>로그아웃</p>
                  </a>
                </li>
              </ul>
            </li>
            <div id='job'>
              <a href="https://edu.ssafy.com/comm/login/SecurityJobLoginSSOForm.do" target="_blank" id='job-ssafy-link'>
              <span id='link-text'>JOB<br>SSAFY</span></a>
            </div>
            <div id='git'>
              <a href="https://edu.ssafy.com/comm/login/SecurityJobLoginSSOForm.do" target="_blank" id='ssafy-git-link'>
              <span id='link-text2'>SSAFY<br>GIT</span></a>
            </div>
            <div id='most'>
              <a href="https://edu.ssafy.com/comm/login/SecurityJobLoginSSOForm.do" target="_blank" id='meeting-ssafy-link'>
              <span id='link-text3'>Meeting!<br>SSAFY</span></a>
            </div>
          </ul>
        </div>
      </div>
    </nav>
    <nav>
      <router-link to="/">Main</router-link> |
      <router-link to="/schedule">Schedule</router-link> |
      <router-link to="/scheduleCT">ScheduleCT</router-link> |
      <router-link to="/login">login</router-link> |
      <router-link to="/signup">signup</router-link> |
      <router-link to="/signupCT">signupCT</router-link> |
      <router-link to="/helpPassword">helpPassword</router-link> |
      <router-link to="/password/update">PasswordUpdate</router-link> |
      <router-link to="/helpid">helpid</router-link> |
      <router-link to="/profile">profile</router-link> |
      <router-link to="/update">updateUser</router-link> |
      <router-link to="/management">management</router-link> |
      <router-link to="/interview">interview</router-link> |
      <router-link to="/interview/solo">InterviewSolo</router-link> |
      <router-link to="/interview/ct">InterviewCt</router-link> |
      <router-link to="/analysis/comprehensive">AnalysisComprehensive</router-link> |
      <router-link to="/analysis/detail">AnalysisDetail</router-link>
    </nav>
  <router-view/>
  </div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  name: 'App',
  methods: {
    ...mapActions(['logout']),
  },
};
</script>

<style scoped>
#dropdown-link-text {
  padding-top: 7px;
  padding-bottom: 7px;
  margin: 0;
}
#dropdown-ul {
  padding: 0px;
}
#dropdown-li {
  text-align: center;
}
#dropdown-li:hover {
  text-align: center;
  background-color: #f6f5f5a2;
}
#dropdown-router {
  text-decoration: none;
  color: #1f2328;
}
#router-inner-text {
  text-decoration: none;
  color: #4f5256;
  font-weight: bold;
}
#router-inner-text:hover {
  text-decoration: none;
  color:  #5c6ac4;
  font-weight: bold;
}
#nav-router {
  margin-left: 20px;
  font-size: 20px;
}
#user-icon {
  margin-right: 20px;
}
#sai-nav {
  border-bottom: 1px solid rgb(202, 202, 202);
  padding: 0;
}
#link-text3 {
  display: inline-block;
  padding: 0 10px 8px 0;
  border-bottom: 2px solid #fff;
  vertical-align: top;
  text-align: left;
}
#meeting-ssafy-link {
  display: block;
  width: 93.38px;
  height: 101px;
  padding: 30px 9px 0 9px;
  color: #fff;
  text-decoration: none;
}

#ssafy-git-link {
  display: block;
  width: 93.38px;
  height: 101px;
  padding: 30px 18px 0 19px;
  color: #fafafa;
}
#most {
  display: block;
  top: 0;
  height: 100%;
  background-color: #3fce32;
  font-size: 16px;
  line-height: 19px;
  font-weight: 700;
  text-align: right;
}
#git {
  display: block;
  top: 0;
  height: 100%;
  background-color: #3297CE;
  font-size: 16px;
  line-height: 19px;
  font-weight: 700;
  text-align: right;
}
#link-text {
  display: inline-block;
  padding: 0 0 8px 10px;
  border-bottom: 2px solid #080808;
  vertical-align: top;
}
#link-text2 {
  display: inline-block;
  padding: 0 10px 8px 0;
  border-bottom: 2px solid #fff;
  vertical-align: top;
  text-align: left;
}
#job-ssafy-link {
  display: block;
  width: 93.38px;
  height: 101px;
  padding: 30px 18px 0 19px;
  color: #1f2328;
}
#job {
  display: block;
  top: 0;
  height: 100%;
  background-color: #e9eef1;
  font-size: 16px;
  line-height: 19px;
  font-weight: 700;
  text-align: right;
}
#notice-icon {
  width: 33px;
  height: 33px;
  margin-top: 35px;
  margin-right: 20px;
  cursor: pointer;
}
#notice-icon2{
  width: 30px;
  height: 30px;
  margin-top: 38px;
  margin-right: 20px;
  margin-left: 20px;
  cursor: pointer;
}
.dropdown {
  margin-top: 25px;
}
#nav-user-profile-img {
  border-radius: 100%;
  width: 40px;
  height: 40px;
}
#logo-img {
  width: 74.5px;
  height: 48.5px;
  cursor: pointer;
}
p {
  margin-top: 40px;
}
</style>
