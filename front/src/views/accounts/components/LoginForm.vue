<template>
  <div id='wrap-div1'>
    <div id='wrap-div2'>
      <img src='@/assets/logo1.png' alt='logo' id='logo-image'>
    </div>
    <form id='form-wrap1'>
      <div id='user-id-wrap'>
        <label for='user_id' id='id_text'><h6>아이디</h6>
          <input type='text' id='user_id'
          placeholder="아이디를 입력하세요"
          class='form-control'>
        </label>
      </div>
      <div id='user-pw-wrap'>
        <label for='user_pw'><h6>비밀번호</h6>
          <input type='password' id='user_pw'
          placeholder='비밀번호를 입력하세요'
          class='form-control'>
        </label>
      </div>
      <div id='help-wrap'>
        <p><router-link to='/helpid' id='help-text'>아이디 찾기 |</router-link>
        <router-link to='/helpPassword' id='help-text'> 비밀번호 찾기</router-link></p>
      </div>
      <div id='user-login-btn-wrap'>
        <button class='btn' id='login-btn' type="submit">로그인</button>
      </div>
    </form>
      <div id='signup-btn-wrap'>
        <router-link to='/signup' id='sign-up-text'>
        <button class='btn' id='signup-btn'>
        회원가입</button></router-link>
      </div>
  </div>
</template>

<script>
export default {
  name: 'LoginForm',
};
</script>

<style scoped>
#sign-up-wrap {
  text-align: center;
}
#form-wrap1 {
  margin-bottom: 10px;
}
#wrap-div2 {
  text-align: center;
  margin-bottom: 5%;
}
#user-id-wrap {
  display : flex;
  justify-content: center;
  align-items : center;
}
#user-pw-wrap {
  display : flex;
  justify-content: center;
  align-items : center;
}
#user-login-btn-wrap {
  display : flex;
  justify-content: center;
  align-items : center;
}
#login-btn{
  margin-top: 20px;
  z-index: 1000;
  background-color: #5c6ac4;
  color: white;
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
  border: 1px solid transparent;
  font-size: 15px;
  border-radius: 0.25rem;
  padding: 0.5rem;
  width: 400px;
  height: 50px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

@media (prefers-reduced-motion: reduce) {
  .login-btn {
    transition: none;
  }
}

.form-control {
  margin-bottom: 3px;
  width: 300px;
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
  box-shadow: 0 0 0 0.1rem #5c6ac496;
}
#logo-image {
  width: 20%;
  animation: tada 2s ease-out;
}
#router-link-signup {
  text-decoration: none;
  color:  #5c6ac4;
}
#signup-btn-wrap {
  display : flex;
  justify-content: center;
  align-items : center;
}
#signup-btn{
  margin-top: 10px;
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
  border: 1px solid #5c6ac4;;
  font-size: 15px;
  border-radius: 0.25rem;
  padding: 0.5rem;
width:  400px;
  height: 50px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
#help-wrap {
  text-align: end;
  width: 400px;
  margin: auto;
}
#help-text {
  font-size: 10px;
  text-decoration: none;
  color: #707070;
}
#sign-up-text {
  text-decoration: none;
  color: #5c6ac4;
}
.form-control {
  width: 400px;
}

</style>
