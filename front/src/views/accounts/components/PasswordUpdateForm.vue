<template>
  <div>
    <form @submit.prevent="passwordupdateform">
      <div class="mt-5">
        <label for='find_user_input_name'>
          <p id='find_user_input_text'>현재비밀번호</p>
          <input type='password' id='find_user_input_name' class='form-control'
          v-model="state.credentials.oldpw"
          required/>
        </label>
      </div>
      <div>
        <label for='find_user_input_email'>
          <p id='find_user_input_text'>변경할 비밀번호</p>
          <input type='password' id='find_user_input_email'
          class='form-control'
          placeholder="8이상 16자리 이하 영문+숫자"
          @change="checkStrNumPasswd1"
          v-model="state.credentials.newpw1"
          required/>
        </label>
      </div>
      <div>
        <label for='find_user_input_email'>
          <p id='find_user_input_text'>비밀번호 재확인</p>
          <input type='password' id='find_user_input_email'
          class='form-control'
          placeholder="8이상 16자리 이하 영문+숫자"
          @change="checkStrNumPasswd2"
          v-model="state.credentials.newpw2"
          required/>
        </label>
            <p v-if='state.isCorrect' id='correct-pw'>비밀번호가 일치합니다.</p>
      </div>
      <div>
        <button v-if='state.isCorrect' class='btn' id='find-pw-btn' type='submit'>변경하기</button>
      </div>
    </form>
      <div>
        <router-link :to="{ name: 'Main' }">
        <button class='btn' id='login-btn'>
        홈으로</button></router-link>
      </div>
  </div>
</template>

<script>
import { reactive, computed, onBeforeMount } from 'vue';
import { useStore } from 'vuex';

export default {
  name: 'PasswordUpdateForm',
  setup() {
    const store = useStore();
    const state = reactive({
      credentials: {
        oldpw: '',
        newpw1: '',
        newpw2: '',
      },
      isCorrect: false,
      currentUserId: '',
      currentUserEmail: '',
    });
    const checkStrNumPasswd1 = function () {
      if (!(/^(?=.*[a-z])(?=.*[0-9]).{8,16}$/.test(state.credentials.newpw1))) {
        state.isCorrect = false;
        console.log('비번1정규식통과ㄴㄴ');
        alert('8자리 이상 16자리 이하 영문+숫자.');
        state.credentials.newpw1 = '';
        document.getElementById('user_signup_pw1').focus();
      }
    };
    const checkStrNumPasswd2 = function () {
      if (/^(?=.*[a-z])(?=.*[0-9]).{8,16}$/.test(state.credentials.newpw2)) {
        if (state.credentials.newpw1 === state.credentials.newpw2) {
          state.isCorrect = true;
          console.log('비번2정규식통과&같음');
        } else {
          state.isCorrect = false;
          console.log('비번2정규식통과ㄴㄴ');
          alert('비밀번호가 올바르지 않습니다.');
        }
      } else {
        state.isCorrect = false;
        console.log('비번2정규식통과ㄴㄴ');
        alert('비밀번호가 올바르지 않습니다.');
      }
    };
    const isLoggedIn = computed(() => store.getters.isLoggedIn);
    const currentUser = computed(() => store.getters.currentUser);
    onBeforeMount(() => {
      state.currentUserEmail = currentUser.value.email;
      state.currentUserId = currentUser.value.id;
      console.log(state.currentUserEmail);
    });
    const passwordupdateform = function () {
      store.dispatch('changePassword', {
        id: state.currentUserId,
        password: state.credentials.oldpw,
        newPassword: state.credentials.newpw1,
        newPasswordCheck: state.credentials.newpw2,
      });
    };
    return {
      state,
      store,
      checkStrNumPasswd1,
      checkStrNumPasswd2,
      passwordupdateform,
      isLoggedIn,
      currentUser,
    };
  },
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
