<template>
  <div class='mt-5'>
    <div id='signup-text-wrap'>
      <p id='signup-text1'>회원가입 컨설턴트용</p>
    </div>
    <div id='form-input-wrap'>
      <form action=''>
        <p id='login-text1' class="mb-4">로그인 정보</p>
        <div>
          <label for='user_signup_email'><p id="login-text2">이메일</p>
            <input type='text' id='user_signup_email'
            class='form-control'>
          </label>
          <button class="btn mx-2" id="double-check-btn">중복확인</button>
        </div>
        <div class='mt-5 mb-5' id='pw-input-wrap'>
          <label for='user_signup_pw1'><p id="login-text2">비밀번호</p>
            <input type='password' id='user_signup_pw1'
            placeholder='8자 이상 영문,숫자,특수문자 조합'
            class='form-control'>
          </label>
          <label for='user_signup_pw2' class="mx-2"><p id="login-text2">비밀번호 확인</p>
            <input type='password' id='user_signup_pw2'
            placeholder='8자 이상 영문,숫자,특수문자 조합'
            class='form-control'>
          </label>
        </div>
        <hr>
        <div class='mt-5 mb-5'>
          <p id='login-text1'>개인 정보</p>
          <div>
            <label for='user_signup_name'><p id='login-text2'>이름</p>
              <input type='text' id='user_signup_name'
              class='form-control'>
            </label>
            <label for='user_signup_date_of_birth' class="mx-2"><p id='login-text2'>생년월일</p>
              <input type='text' id='user_signup_date_of_birth'
              class='form-control'>
            </label>
          </div>
          <div>
            <div id='form-select-wrap'>
              <p id='login-text3'>지역</p>
              <select class='form-select' id='form-select' aria-label='Default select example'>
                <option selected>지역</option>
                <option value='1'>서울</option>
                <option value='2'>대전</option>
                <option value='3'>광주</option>
                <option value='4'>구미</option>
                <option value='5'>부울경</option>
              </select>
            </div>
            <div id='form-select-wrap' class="mx-2">
              <p id='login-text4'>연락처</p>
              <select class='form-select' id='form-select3' aria-label='Default select example'>
                <option value='1'>010</option>
                <option value='2'>011</option>
                <option value='3'>016</option>
                <option value='4'>017</option>
                <option value='5'>018</option>
                <option value='5'>019</option>
              </select>
              <label for="user_signup_number1">
                <input type="text" class="form-control" id='user_signup_number1'>
              </label>
              <label for="user_signup_number2">
                <input type="text" class="form-control" id='user_signup_number2'>
              </label>
            </div>
          </div>
        </div>
        <div id='user-signup-btn'>
          <button class='btn' id='signup-btn'>회원가입</button>
        </div>
      </form>
        <router-link to='/helpPassword' id='sign-up-text'>
          <button class='btn' id='signup-btn'>비밀번호 찾기</button>
        </router-link>
    </div>
  </div>
</template>

<script>

export default {
  name: 'SignupForm',
};
</script>

<style scoped>
#signup-text-wrap {
  text-align: center;
  margin-bottom: 100px;
}
#signup-text1 {
  font-weight: bold;
  font-size: 40px;
  margin-top: 100px;
}
#form-input-wrap {
  text-align: left;
  width: 700px;
  margin: 0 auto;
}
#login-text1 {
  font-size: 18px;
  font-weight: 900;
  line-height: 2;
  font-stretch: normal;
  letter-spacing: normal;
}
.form-control {
  margin-bottom: 3px;
  width: 342px;
  height: 50px;
  font-size: 1rem;
  line-height: 7;
  color: #000000;
  background-clip: padding-box;
  border: 1.9px solid #cecece;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  border-radius: 0.25rem;
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
.form-control:focus {
  color: #000000;
  background-color: rgb(255, 255, 255);
  border-color: #ffffff;
  outline: 0;
  box-shadow: 0 0 0 0.1rem #5c6ac4;
}
#user_signup_number1 {
  margin-bottom: 3px;
  margin-left: 20px;
  width: 100px;
  height: 50px;
  font-size: 1rem;
  line-height: 7;
  color: #000000;
  background-clip: padding-box;
  border: 1.9px solid #cecece;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  border-radius: 0.25rem;
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
#user_signup_number1:focus {
  color: #000000;
  background-color: rgb(255, 255, 255);
  border-color: #ffffff;
  outline: 0;
  box-shadow: 0 0 0 0.1rem #5c6ac4;
}
#user_signup_number2 {
  margin-bottom: 3px;
  margin-left: 20px;
  width: 100px;
  height: 50px;
  font-size: 1rem;
  line-height: 7;
  color: #000000;
  background-clip: padding-box;
  border: 1.9px solid #cecece;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  border-radius: 0.25rem;
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
#user_signup_number2:focus {
  color: #000000;
  background-color: rgb(255, 255, 255);
  border-color: #ffffff;
  outline: 0;
  box-shadow: 0 0 0 0.1rem #5c6ac4;
}
#double-check-btn{
  z-index: 1000;
  color: #5c6ac4;
  background-color: white;
  border: 1px solid #5c6ac4;
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
  font-size: 15px;
  border-radius: 0.25rem;
  padding: 0.5rem;
  height: 50px;
  width: 90px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

@media (prefers-reduced-motion: reduce) {
  .double-check-btn {
    transition: none;
  }
}
#double-check-btn:hover {
  color: white;
  background-color: #5c6ac4;
}
#login-text2 {
  font-size: 13px;
  font-weight: 900;
  line-height: 2;
  font-stretch: normal;
  letter-spacing: normal;
  margin: 0;
}
#login-text3 {
  font-size: 13px;
  font-weight: 900;
  line-height: 2;
  font-stretch: normal;
  letter-spacing: normal;
  margin: 0;
}
#login-text4 {
  font-size: 13px;
  font-weight: 900;
  line-height: 2;
  font-stretch: normal;
  letter-spacing: normal;
  margin: 0;
}
#form-select {
  width: 342px;
  height: 50px;
  display: inline-block;
}
#form-select:focus {
  color: #000000;
  background-color: rgb(255, 255, 255);
  border-color: #ffffff;
  outline: 0;
  box-shadow: 0 0 0 0.1rem #5c6ac4;
}
#form-select2 {
  width: 167px;
  height: 50px;
  margin-left: 9px;
  display: inline-block;
}
#form-select2:focus {
  color: #000000;
  background-color: rgb(255, 255, 255);
  border-color: #ffffff;
  outline: 0;
  box-shadow: 0 0 0 0.1rem #5c6ac4;
}
#form-select3 {
  width: 100px;
  height: 50px;
  display: inline-block;
}
#form-select3:focus {
  color: #000000;
  background-color: rgb(255, 255, 255);
  border-color: #ffffff;
  outline: 0;
  box-shadow: 0 0 0 0.1rem #5c6ac4;
}
#form-select-wrap{
  display: inline-block;
}
#user-signup-btn {
  display: flex;
  justify-content: center;
}
#signup-btn{
  margin-top: 20px;
  z-index: 1000;
  background-color: #ffffff;
  color: #5c6ac4;
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
  border: 1px solid #5c6ac4;
  font-size: 15px;
  border-radius: 0.25rem;
  padding: 0.5rem;
  width: 99.8%;
  height: 50px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
#signup-btn:hover {
  background-color: #5c6ac4;
  color: white;
}
@media (prefers-reduced-motion: reduce) {
  .signup-btn {
    transition: none;
  }
}

</style>
