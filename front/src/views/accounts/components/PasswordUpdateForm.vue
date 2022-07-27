<template>
  <div>
    <form action="">
      <div class="mt-5">
        <label for='find_user_input_name'>
          <p id='find_user_input_text'>현재비밀번호</p>
          <input type='password' id='find_user_input_name' class='form-control'>
        </label>
      </div>
      <div>
        <label for='find_user_input_email'>
          <p id='find_user_input_text'>변경할 비밀번호</p>
          <input type='password' id='find_user_input_email'
          class='form-control'>
        </label>
      </div>
      <div>
        <label for='find_user_input_email'>
          <p id='find_user_input_text'>변경할 비밀번호</p>
          <input type='password' id='find_user_input_email'
          class='form-control'>
        </label>
      </div>
      <div>
        <router-link to='/'>
        <button class='btn' id='find-pw-btn' type='submit'>
        변경완료</button></router-link>
      </div>
    </form>
      <div>
        <router-link to='/'>
        <button class='btn' id='login-btn'>
        홈으로</button></router-link>
      </div>
  </div>
</template>

<script>
export default {
  name: 'PasswordUpdateForm',
};
</script>

<style scoped>
#find_user_input_text {
  font-size: 13px;
  font-weight: 900;
  line-height: 2;
  font-stretch: normal;
  letter-spacing: normal;
  margin: 0;
  text-align: start;
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
#find-pw-btn{
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
  width: 300px;
  height: 50px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

@media (prefers-reduced-motion: reduce) {
  .find-pw-btn {
    transition: none;
  }
}
#login-btn{
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
  width: 300px;
  height: 50px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

@media (prefers-reduced-motion: reduce) {
  .login-btn {
    transition: none;
  }
}
</style>
