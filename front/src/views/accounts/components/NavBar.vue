<template>
  <div class="container">
    <nav class="navbar navbar-expand-lg navbar-light sticky-top">
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
            <li class="nav-item">
              <router-link to='/'>
                <p>메인</p>
              </router-link>
            </li>
            <li class="nav-item">
              <router-link to='/'>
                <p>Home</p>
              </router-link>
            </li>
            <li class="nav-item">
              <router-link to='/'>
                <p>Home</p>
              </router-link>
            </li>
            <li class="nav-item">
              <img src="@/assets/icon-head-push-bell.jpg" alt="notice-icon" id='notice-icon'>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle"
              href="#" id="navbarDropdownMenuLink"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false">
                <img src="@/assets/profile2.png" alt="" id='nav-user-profile-img'>
                user님
              </a>
              <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <li>
                  <router-link to='/profile'>
                    <span>마이페이지</span>
                  </router-link>
                </li>
                <li>
                  <router-link to='/password/update'>
                    <span>비밀번호수정</span>
                  </router-link>
                </li>
                <li>
                  <router-link to='/'>
                    <span>로그아웃</span>
                  </router-link>
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
              <a href="https://edu.ssafy.com/comm/login/SecurityJobLoginSSOForm.do" target="_blank" id='job-ssafy-link'>
              <span id='link-text'>Meeting!<br>SSAFY</span></a>
            </div>
          </ul>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  name: 'Nav-bar',
};
</script>

<style scoped>
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
  margin-top: 20px;
  width: 74.5px;
  height: 48.5px;
  cursor: pointer;
}
p {
  margin-top: 40px;
}
</style>
