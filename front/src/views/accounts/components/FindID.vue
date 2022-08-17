<template>
  <div>
    <form @submit.prevent="findId">
      <div>
        <label for='find_user_input_name'>
          <p id='find_user_input_text'>이름</p>
          <input type='text' id='find_user_input_name'
          class='form-control'
          v-model="state.credentials.username"
          required>
        </label>
      </div>
      <div id='select-box-div' class="mt-3">
        <label for='find_user_input_email'>
          <p id='find_user_input_text'>생년월일</p>
          <select name="find_user_input_emil" id="select-year"
          class="form-select"
          @change="selectedUserYear"
          required>
            <option selected disabled>연도</option>
            <option value='1990'>1990</option>
            <option value='1991'>1991</option>
            <option value='1992'>1992</option>
            <option value='1992'>1993</option>
            <option value='1994'>1994</option>
            <option value='1995'>1995</option>
            <option value='1996'>1996</option>
            <option value='1997'>1997</option>
            <option value='1998'>1998</option>
            <option value='1999'>1999</option>
            <option value='2000'>2000</option>
            <option value='2001'>2001</option>
            <option value='2002'>2002</option>
            <option value='2003'>2003</option>
          </select>
          <select name="find_user_input_emil" id="select-month"
          class="form-select"
          @change="selectedUserMonth"
          required>
            <option selected disabled>월</option>
            <option value='01'>01</option>
            <option value='02'>02</option>
            <option value='03'>03</option>
            <option value='04'>04</option>
            <option value='05'>05</option>
            <option value='06'>06</option>
            <option value='07'>07</option>
            <option value='08'>08</option>
            <option value='09'>09</option>
            <option value='10'>10</option>
            <option value='11'>11</option>
            <option value='12'>12</option>
          </select>
          <select name="find_user_input_emil" id="select-day"
          class="form-select"
          @change="selectedUserDay"
          required>
              <option selected disabled>일</option>
              <option value='01'>01</option>
              <option value='02'>02</option>
              <option value='03'>03</option>
              <option value='04'>04</option>
              <option value='05'>05</option>
              <option value='06'>06</option>
              <option value='07'>07</option>
              <option value='08'>08</option>
              <option value='09'>09</option>
              <option value='10'>10</option>
              <option value='11'>11</option>
              <option value='12'>12</option>
              <option value='13'>13</option>
              <option value='14'>14</option>
              <option value='15'>15</option>
              <option value='16'>16</option>
              <option value='17'>17</option>
              <option value='18'>18</option>
              <option value='19'>19</option>
              <option value='20'>20</option>
              <option value='21'>21</option>
              <option value='22'>22</option>
              <option value='23'>23</option>
              <option value='24'>24</option>
              <option value='25'>25</option>
              <option value='26'>26</option>
              <option value='27'>27</option>
              <option value='28'>28</option>
              <option value='29'>29</option>
              <option value='30'>30</option>
              <option value='31'>31</option>
          </select>
        </label>
      </div>
      <div v-if="state.result" class="mt-3 mb-3">
        회원님의 아이디는 {{ state.result }} 입니다.
      </div>
      <div>
        <button id='find-pw-btn' type='submit'>아이디 찾기</button>
      </div>
    </form>
      <div>
        <router-link :to="{ name: 'Login' }">
        <button class='btn' id='login-btn'>
        로그인 페이지</button></router-link>
      </div>
  </div>
</template>

<script>
import { reactive } from 'vue';
import { useStore } from 'vuex';
import axios from 'axios';
import drf from '@/api/api';

export default {
  name: 'FindID',
  setup() {
    const store = useStore();
    const state = reactive({
      credentials: {
        userBirth: '',
        username: '',
      },
      result: '',
    });
    const selectedUserYear = function (event) {
      const dash = '-';
      if (state.credentials.userBirth.length === 0) {
        state.credentials.userBirth += event.target.value.concat(dash);
      } else {
        state.credentials.userBirth = '';
        state.credentials.userBirth += event.target.value.concat(dash);
      }
    };
    const selectedUserMonth = function (event) {
      const dash = '-';
      if (state.credentials.userBirth.length === 0) {
        state.credentials.userBirth += event.target.value.concat(dash);
      } else {
        const updateMonth = state.credentials.userBirth.substr(0, 5);
        state.credentials.userBirth = updateMonth;
        state.credentials.userBirth += event.target.value.concat(dash);
      }
    };
    const selectedUserDay = function (event) {
      state.credentials.userBirth += event.target.value;
      if (state.credentials.userBirth.length === 0) {
        state.credentials.userBirth += event.target.value;
      } else {
        const updateDay = state.credentials.userBirth.substr(0, 8);
        state.credentials.userBirth = updateDay;
        state.credentials.userBirth += event.target.value;
      }
    };
    const findId = function () {
      axios({
        url: drf.member.findId(),
        method: 'post',
        data: {
          name: state.credentials.username,
          birthday: state.credentials.userBirth,
        },
      })
        .then((res) => { state.result = res.data.data.email; })
        .catch(() => alert('존재하지 않는 회원 정보입니다.'));
    };
    return {
      state,
      store,
      selectedUserYear,
      selectedUserMonth,
      selectedUserDay,
      findId,
    };
  },
};
</script>

<style scoped>
.form-select {
  display: inline;
  width: 93px;
  margin: 5px;
  height: 45px;
}
.form-select:focus {
  color: #000000;
  background-color: rgb(255, 255, 255);
  border-color: #ffffff;
  outline: 0;
  box-shadow: 0 0 0 0.1rem #5c6ac496;
}
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
