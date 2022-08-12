<template>
  <div id='app'>
    <div style="border-bottom: 1px solid #e9eef1;">
      <nav class="navbar navbar-light navbar-expand-lg sticky-top container pb-0 pt-0">
          <router-link to='/'>
            <img src="@/assets/logo8.png" alt="로고" id='logo-img'>
          </router-link>
          <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas"
          data-bs-target="#navbarOffcanvasLg" aria-controls="navbarOffcanvasLg">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="offcanvas offcanvas-end" tabindex="-1" id="navbarOffcanvasLg"
          aria-labelledby="offcanvasNavbarLabel">
            <div class="offcanvas-header">
              <h5 class="offcanvas-title" id="offcanvasNavbarLabel">SAI</h5>
              <button type="button" class="btn-close text-reset"
              data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
              <ul class="navbar-nav justify-content-end flex-grow-1">
                <li class="nav-item">
                  <router-link to='/' id='dropdown-router'>
                    <p id='dropdown-link-text-out'>HOME</p>
                  </router-link>
                </li>
                <li class="nav-item" v-if="currentUser.memberStatus === 'TRAINEE'">
                  <router-link to='/interview' id='dropdown-router'>
                    <p id='dropdown-link-text-out'>모의면접</p>
                  </router-link>
                </li>
                <li class="nav-item" v-if="currentUser.memberStatus === 'CONSULTANT'">
                  <router-link to='/management' id='dropdown-router'>
                    <p id='dropdown-link-text-out'>교육생관리</p>
                  </router-link>
                </li>
                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" href="#" id="offcanvasNavbarDropdown"
                  role="button" data-bs-toggle="dropdown" aria-expanded="false"
                  >
                    마이페이지
                  </a>
                  <ul class="dropdown-menu" aria-labelledby="offcanvasNavbarDropdown">
                    <li v-if="currentUser.memberStatus === 'TRAINEE'">
                    <router-link :to="{ name: 'Profile', params: { id: `${ currentUser.id }`} }"
                    id='dropdown-router'
                    class="dropdown-item">
                    <p id='dropdown-link-text'>회원정보</p></router-link>
                    </li>
                    <li v-if="currentUser.memberStatus === 'CONSULTANT'">
                    <router-link
                    :to="{ name: 'ProfileUpdateCTView', params: { id: `${ currentUser.id }`} }"
                    id='dropdown-router'
                    class="dropdown-item">
                    <p id='dropdown-link-text'>회원정보 수정</p></router-link>
                    </li>
                    <li><router-link to='/schedule' id='dropdown-router'
                    class="dropdown-item">
                    <p id='dropdown-link-text'>일정관리</p></router-link>
                    </li>
                    <li><router-link to='/password/update' id='dropdown-router'
                    class="dropdown-item">
                    <p id='dropdown-link-text'>비밀번호변경</p></router-link>
                    </li>
                    <li>
                      <hr class="dropdown-divider">
                    </li>
                    <li><router-link to='/login' id='dropdown-router'
                    class="dropdown-item">
                    <p id='dropdown-link-text' @click="logout()" @keyup="logout()">
                    로그아웃</p></router-link>
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
    </div>
    <div>
      <router-link to="/">Main</router-link> |
      <router-link to="/schedule">Schedule</router-link> |
      <router-link to="/scheduleCT">ScheduleCT</router-link> |
      <router-link to="/login">login</router-link> |
      <router-link to="/signup">signup</router-link> |
      <router-link to="/signupCT">signupCT</router-link> |
      <router-link to="/helpPassword">helpPassword</router-link> |
      <router-link to="/password/update">PasswordUpdate</router-link> |
      <router-link to="/helpid">helpid</router-link> |
      <router-link :to="{ name: 'Profile', params: { id: `${ currentUser.id }`} }">
        profile</router-link> |
      <router-link to="/update">updateUser</router-link> |
      <router-link to="/management">management</router-link> |
      <router-link to="/interview">interview</router-link> |
      <router-link to="/interview/solo">InterviewSolo</router-link> |
      <router-link to="/interview/ct">InterviewCt</router-link> |
      <router-link to="/analysis/comprehensive">AnalysisComprehensive</router-link> |
      <router-link to="/analysis/detail">AnalysisDetail</router-link>
    </div>
  <router-view/>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';

export default {

  name: 'App',
  methods: {
    ...mapActions(['logout']),
  },
  computed: {
    ...mapGetters(['currentUser', 'isLoggedIn']),
  },
};
</script>

<style scoped>
.nav-link {
  color: rgba(0, 0, 0, 0.753);
  font-weight: 500;
}
#dropdown-link-text-out {
  margin-top: 40px;
  margin-left: 30px;
}
#offcanvasNavbarDropdown {
  margin-top: 32px;
  margin-left: 25px;
  margin-right: 10px;
}
#offcanvasNavbarDropdown:hover {
  color:#5c6ac4;
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

#link-text2 {
  display: inline-block;
  padding: 0 10px 8px 0;
  border-bottom: 2px solid #fff;
  vertical-align: top;
  text-align: left;
}

#ssafy-git-link {
  display: block;
  width: 93.38px;
  height: 101px;
  padding: 30px 18px 0 19px;
  color: #fafafa;
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

#offcanvasNavbarDropdown {
  padding-left: 8px;
  padding-right: 8px;
}
#dropdown-link-text {
  padding-bottom: 7px;
  margin: 0;
  padding: 8px;
}
#dropdown-router {
  text-decoration: none;
  color: rgba(0, 0, 0, 0.753);
  font-weight: 500;
}
#dropdown-router:hover {
  text-decoration: none;
  color: #5c6ac4;
}
@media screen and (max-width: 1000px) {
  #job {
    display: none;
  }
}
@media screen and (max-width: 1000px) {
  #git {
    display: none;
  }
}
@media screen and (max-width: 1000px) {
  #most {
    display: none;
  }
}
@media screen and (max-width: 1000px) {
  #dm-icon {
    display: none;
  }
}
@media screen and (max-width: 1000px) {
  #alrert-icon {
    display: none;
  }
}
@media screen and (max-width: 1000px) {
  #user-icon {
    display: none;
  }
}
#logo-img {
  width: 74.5px;
  height: 48.5px;
  cursor: pointer;
}
</style>
