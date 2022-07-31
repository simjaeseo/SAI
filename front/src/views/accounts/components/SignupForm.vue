<template>
  <div class='mt-5'>
    <div id='signup-text-wrap'>
      <p id='signup-text1'>회원가입 교육생용</p>
    </div>
    <div id='form-input-wrap'>
      <form @submit.prevent="signup(credentials)">
        <p id='login-text1' class="mb-4">로그인 정보</p>
        <div>
          <label for='user_signup_email'><p id="login-text2">이메일</p>
            <input type='text' id='user_signup_email'
            class='form-control'
            v-model="credentials.userEmail">
          </label>
          <button class="btn mx-2" id="double-check-btn">중복확인</button>
        </div>
        <div class='mt-5 mb-5' id='pw-input-wrap'>
          <label for='user_signup_pw1'><p id="login-text2">비밀번호</p>
            <input type='password' id='user_signup_pw1'
            class='form-control'
            v-model="credentials.userPassword1"
            placeholder="8자리 이상 16자리 이하 영문+숫자">
          </label>
          <label for='user_signup_pw2' class="mx-2"><p id="login-text2">비밀번호 확인</p>
            <input type='password' id='user_signup_pw2'
            class='form-control'
            v-model="credentials.userPassword2"
            placeholder="8자리 이상 16자리 이하 영문+숫자">
          </label>
          <div v-show='isInput'>
            <p v-if='passwordCorrect' id='correct-pw'>비밀번호가 일치합니다.</p>
            <p v-else id='incorrect-pw'>비밀번호가 일치하지않습니다. 다시 입력해주세요.</p>
          </div>
        </div>
        <hr>
        <div class='mb-5 mt-5'>
          <p id='login-text1'>관심 기업</p>
          <div id='search-bar-box'>
            <search-bar class="mb-5" @companies='companies'></search-bar>
            <!-- 예시 -->
          </div>
          <div id='search-bar-box2'>
            <p id='login-text1'>관심 직무</p>
            <div>
            <search-bar-duty-sign-up class="mb-5" @duties='duties'></search-bar-duty-sign-up>
              <!-- 예시 -->
            </div>
          </div>
        </div>
        <hr>
        <div class='mt-5 mb-5'>
          <p id='login-text1'>개인 정보</p>
          <div>
            <label for='user_signup_name'><p id='login-text2'>이름</p>
              <input type='text' id='user_signup_name'
              class='form-control'
              v-model="credentials.username">
            </label>
            <div id='form-select-wrap1'>
              <p id='login-text3'>생년월일</p>
              <select class='form-select' id='form-select-cardinal-number'
              aria-label='Default select example'
              @change="selectedUserYear">
                <option selected>년도</option>
                <option value='1990'>1990</option>
                <option value='1991'>1991</option>
                <option value='1992'>1992</option>
                <option value='1993'>1993</option>
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
              <select class='form-select' id='form-select-region'
              aria-label='Default select example'
              @change="selectedUserMonth">
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
              <select class='form-select' id='form-select-class'
              aria-label='Default select example'
              @change="selectedUserDay">
                <option value='1'>01</option>
                <option value='2'>02</option>
                <option value='3'>03</option>
                <option value='4'>04</option>
                <option value='5'>05</option>
                <option value='6'>06</option>
                <option value='7'>07</option>
                <option value='8'>08</option>
                <option value='9'>09</option>
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
            </div>
          </div>
          <div>
            <div id='form-select-wrap'>
              <p id='login-text3'>소속</p>
              <select class='form-select' id='form-select-cardinal-number'
              aria-label='Default select example'
              @change="selectedUserCardinalNumber">
                <option selected>기수</option>
                <option value='1기'>1기</option>
                <option value='2기'>2기</option>
                <option value='3기'>3기</option>
                <option value='4기'>4기</option>
                <option value='5기'>5기</option>
                <option value='6기'>6기</option>
                <option value='7기'>7기</option>
              </select>
              <select class='form-select' id='form-select-region'
              aria-label='Default select example'
              v-model="selected"
              @click='setOptions'
              @change="selectedUserRegion">
                <option value='서울'>서울</option>
                <option value='대전'>대전</option>
                <option value='광주'>광주</option>
                <option value='구미'>구미</option>
                <option value='부울경'>부울경</option>
              </select>
              <select class='form-select' id='form-select-class'
              aria-label='Default select example'
              @change="selectedUserClass">
                <option v-for='option in options' :key="option">{{option}}</option>
              </select>
            </div>
            <div id='form-select-wrap' class="mx-2">
              <p id='login-text4'>연락처</p>
              <select class='form-select' id='form-select3' aria-label='Default select example'
              @change="UserMobileFirst">
                <option selected>선택</option>
                <option value='010'>010</option>
                <option value='011'>011</option>
                <option value='016'>016</option>
                <option value='017'>017</option>
                <option value='018'>018</option>
                <option value='019'>019</option>
              </select>
              <label for="user_signup_number1">
                  <input type="text" class="form-control" id='user_signup_number1'
                @change="UserMobileSecond">
              </label>
              <label for="user_signup_number2">
                <input type="text" class="form-control" id='user_signup_number2'
                @change="UserMobileLast">
              </label>
            </div>
          </div>
        </div>
        <div id='user-signup-btn'>
          <button class='btn' id='signup-btn' type="submit">회원가입</button>
        </div>
      </form>
        <router-link to='/helpPassword' id='sign-up-text'>
          <button class='btn' id='signup-btn'>비밀번호 찾기</button>
        </router-link>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex';
import SearchBar from './SearchBar.vue';
import SearchBarDutySignUp from './SearchBarDutySignUp.vue';

export default {
  components: { SearchBar, SearchBarDutySignUp },
  name: 'SignupForm',
  data() {
    return {
      credentials: {
        userSelectedCompanies: '',
        userSelectedDuties: '',
        userBirth: '',
        userPhoneNumber: '',
        userEmail: '',
        userPassword1: '',
        userPassword2: '',
        username: '',
        selectedUserCardinalNumber: '',
        selectedUserRegion: '',
        selectedUserClass: '',
      },
      selected: '',
      options: [],
      passwordCorrect: false,
      isInput: false,
    };
  },
  methods: {
    companies(data) {
      this.userSelectedCompanies = data;
      console.log(this.userSelectedCompanies);
    },
    duties(data) {
      this.userSelectedDuties = data;
      console.log(this.userSelectedDuties);
    },
    setOptions() {
      if (this.selected === '서울') {
        this.options.push('1반', '2반', '3반', '4반', '5반', '6반', '7반', '8반', '9반', '10반', '11반', '12반', '13반', '14반', '15반', '16반');
        console.log(this.options);
      } else if (this.selected === '대전') {
        this.options = [];
        this.options.push('1반', '2반', '3반', '4반', '5반', '6반', '7반');
      } else if (this.selected === '광주') {
        this.options = [];
        this.options.push('1반', '2반', '3반', '4반', '5반', '6반');
      } else if (this.selected === '구미') {
        this.options = [];
        this.options.push('1반', '2반', '3반', '4반', '5반', '6반', '7반');
      } else if (this.selected === '부울경') {
        this.options = [];
        this.options.push('1반', '2반', '3반', '4반', '5반', '6반');
      }
    },
    selectedUserYear(event) {
      const dash = '-';
      this.credentials.userBirth = event.target.value.concat(dash);
    },
    selectedUserMonth(event) {
      const dash = '-';
      this.credentials.userBirth += event.target.value.concat(dash);
    },
    selectedUserDay(event) {
      this.credentials.userBirth += event.target.value;
    },
    UserMobileFirst(event) {
      this.credentials.userPhoneNumber = event.target.value;
    },
    UserMobileSecond(event) {
      this.credentials.userPhoneNumber += event.target.value;
    },
    UserMobileLast(event) {
      this.credentials.userPhoneNumber += event.target.value;
    },
    ...mapActions(['signup']),
  },
  updated() {
    if (this.credentials.userPassword1.length > 0) {
      this.isInput = true;
    } else {
      this.isInput = false;
    }
    if (this.credentials.userPassword1 === this.credentials.userPassword2) {
      this.passwordCorrect = true;
    } else {
      this.passwordCorrect = false;
    }
  },
};
</script>

<style scoped>
#incorrect-pw {
  color: #fd0606;
}
#correct-pw {
  color: #5c6ac4;
}
#search-bar-box {
  height: 110px;
  position: relative;
  z-index: 1000;
  margin-top: 20px;
}
#search-bar-box2 {
  height: 110px;
  position: relative;
  z-index: 900;
}
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
  border: 1px solid #ced4da;
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
  border: 1px solid #ced4da;
}
#user_signup_number1:focus {
  color: #000000;
  background-color: rgb(255, 255, 255);
  border-color: #ffffff;
  outline: 0;
  box-shadow: 0 0 0 0.1rem #5c6ac496;
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
  border: 1px solid #ced4da;
 transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
#user_signup_number2:focus {
  color: #000000;
  background-color: rgb(255, 255, 255);
  border-color: #ffffff;
  outline: 0;
  box-shadow: 0 0 0 0.1rem #5c6ac496;
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
  width: 105px;
  height: 50px;
  display: inline-block;
}
#form-select-cardinal-number{
  width: 107px;
  height: 50px;
  display: inline-block;

}
#form-select-region{
  width: 107px;
  height: 50px;
  display: inline-block;
  margin-left: 10px;
}
#form-select-class{
  width: 107px;
  height: 50px;
  display: inline-block;
  margin-left: 10px;
  margin-right: 1px;
}
#form-select:focus {
  color: #000000;
  background-color: rgb(255, 255, 255);
  border-color: #ffffff;
  outline: 0;
  box-shadow: 0 0 0 0.1rem #5c6ac496;
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
  box-shadow: 0 0 0 0.1rem #5c6ac496;
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
.form-select:focus {
  color: #000000;
  background-color: rgb(255, 255, 255);
  border-color: #ffffff;
  outline: 0;
  box-shadow: 0 0 0 0.1rem #5c6ac496;
}
#form-select-wrap{
  display: inline-block;
}
#form-select-wrap1{
  display: inline-block;
  margin-left: 9px;
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
