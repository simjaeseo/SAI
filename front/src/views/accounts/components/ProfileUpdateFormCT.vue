<template>
  <div>
    <form action="">
        <div class='container'>
          <div class='row'>
            <!-- 프로필이미지 -->
            <div id='profile_image_box' class='col-sm-2'>
              <img src='@/assets/profile4.png' alt='basic-img' id='user_profile_img'> <br>
                <div class="filebox">
                    <label for='ex_file'><input type='file' id='ex_file' accept='image/*'
                    ref='image' class='upload-box'>파일선택</label>
                </div>
            </div>
            <!-- 프로필인적사항 -->
            <div id='personal-data-box1' class='col-sm-10'>
              <!-- 왼쪽 -->
              <div class='row' id='personal-data-box2'>
                <div class='col-lg-6'>
                    <p id='data-name'>이름</p>
                    <label for='user-update-name'><input type='text' id='user-update-name'
                    class='form-control' v-model="currentUser.name"></label>
                    <p id='data-name'>소속</p>
                    <select class='form-select' id='form-select-region'
                    aria-label='Default select example'>
                        <option selected disabled>지역</option>
                        <option value='1'>서울</option>
                        <option value='2'>대전</option>
                        <option value='3'>광주</option>
                        <option value='4'>구미</option>
                        <option value='5'>부울경</option>
                    </select>
                    <p id='data-name'>연락처</p>
                    <select class='form-select' id='form-select-first'
                    aria-label='Default select example'>
                        <option value='1'>010</option>
                        <option value='2'>011</option>
                        <option value='3'>016</option>
                        <option value='4'>017</option>
                        <option value='5'>018</option>
                        <option value='5'>019</option>
                    </select>
                    <label for="user_signup_number1">
                        <input type="text" class="form-control" id='form-select-second'
                        v-model="state.mobileSecond">
                    </label>
                    <label for="user_signup_number2">
                        <input type="text" class="form-control" id='form-select-second'
                        v-model="state.mobileLast">
                    </label>
                </div>
              <!-- 오른쪽 -->
                <div class='col-lg-6'>
                    <p id='data-name'>생년월일</p>
                    <label for='user-update-name'><input type='text' id='user-update-name'
                    class='form-control' v-model="currentUser.birthday"
                    readonly @click="noChange"></label>
                    <p id='data-name'>이메일</p>
                    <label for='user-update-name'><input type='text' id='user-update-name'
                    class='form-control' v-model="currentUser.email"
                    readonly @click="noChange"></label>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- 버튼 -->
        <div id='btn-box'>
            <router-link to="profile"><button class='btn' id='cancel-btn'>취소</button></router-link>
            <router-link to="profile"><button class='btn'
            type='submit'
            id='update-btn'>완료</button></router-link>
        </div>
    </form>
  </div>
</template>

<script>
import { computed, reactive, onBeforeMount } from 'vue';
import { useStore } from 'vuex';

export default {
  name: 'ProfileDataUpdate',
  setup() {
    const store = useStore();
    const state = reactive({
      mobileSecond: '',
      mobileLast: '',
    });
    const isLoggedIn = computed(() => store.getters.isLoggedIn);
    const currentUser = computed(() => store.getters.currentUser);
    onBeforeMount(() => {
      const userPhone = currentUser.value.phone;
      state.mobileSecond = userPhone.substr(3, 4);
      state.mobileLast = userPhone.substr(7);
    });
    const noChange = function () {
      alert('변경할 수 없는 값입니다.');
    };
    return {
      isLoggedIn,
      currentUser,
      state,
      noChange,
    };
  },
};
</script>

<style scoped>
.modal-body {
  height: 500px;
}
#box1 {
  display: flex;
  justify-content: space-between;
}
#for-inline {
  display: inline;
}
#add-btn {
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
  height: 40px;
  width: 90px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
  margin-bottom: 10px;
}

@media (prefers-reduced-motion: reduce) {
  #add-btn {
    transition: none;
  }
}
#add-btn:hover {
  color: white;
  background-color: #5c6ac4;
}
.form-select:focus {
  color: #000000;
  background-color: rgb(255, 255, 255);
  border-color: #ffffff;
  outline: 0;
  box-shadow: 0 0 0 0.1rem #5c6ac4;
}
#update-btn {
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
  margin-left: 20px;
}

@media (prefers-reduced-motion: reduce) {
  #update-btn {
    transition: none;
  }
}
#update-btn:hover {
  color: white;
  background-color: #5c6ac4;
}
#update-btn2 {
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
  height: 40px;
  width: 90px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
  margin-left: 10px;
}

@media (prefers-reduced-motion: reduce) {
  #update-btn2 {
    transition: none;
  }
}
#update-btn2:hover {
  color: white;
  background-color: #5c6ac4;
}
#cancel-btn {
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
  #cancel-btn {
    transition: none;
  }
}
#cancel-btn:hover {
  color: white;
  background-color: #5c6ac4;
}
#cancel-btn2 {
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
  height: 40px;
  width: 90px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

@media (prefers-reduced-motion: reduce) {
  #cancel-btn2 {
    transition: none;
  }
}
#cancel-btn2:hover {
  color: white;
  background-color: #5c6ac4;
}
#btn-box {
    text-align: center;
    margin-top: 40px;
}
#form-select-region {
    width: 300px;
    display: inline;
}
#form-select-region:hover {
    width: 300px;
    display: inline;
    box-shadow: 0 0 0 0.1rem #5c6ac496;
}
#form-select-class {
    width: 95px;
    display: inline;
    margin-left: 7.5px;
}
#form-select-class:hover {
    width: 95px;
    display: inline;
    margin-left: 7.5px;
    box-shadow: 0 0 0 0.1rem #5c6ac496;
}
#form-select-cardinal-number {
  width: 95px;
  display: inline;
}
#form-select-cardinal-number:focus {
  color: #000000;
  background-color: rgb(255, 255, 255);
  border-color: #ffffff;
  outline: 0;
  box-shadow: 0 0 0 0.1rem #5c6ac496;
}
#form-select-first {
    display: inline;
    width: 95px;
}
#form-select-first:focus {
    color: #000000;
    background-color: rgb(255, 255, 255);
    border-color: #ffffff;
    outline: 0;
    box-shadow: 0 0 0 0.1rem #5c6ac496;
}
#form-select-second {
    width: 95px;
    margin-left: 7.5px;
}
#form-select-second:focus {
    color: #000000;
    background-color: rgb(255, 255, 255);
    border-color: #ffffff;
    outline: 0;
    box-shadow: 0 0 0 0.1rem #5c6ac496;
}
.form-control {
    width: 300px;
}
.form-control:focus {
    color: #000000;
    background-color: rgb(255, 255, 255);
    border-color: #ffffff;
    outline: 0;
    box-shadow: 0 0 0 0.1rem #5c6ac496;
}
#data-name {
  font-size: 12px;
}
#user-data {
  font-size: 20px;
  margin-bottom: 10px;
}
p {
  margin-bottom: 5px;
  margin-top: 5px;
}
#personal-video-box1 {
    margin-top: 60px;
    width: 100%;
}
#personal-video-box2 {
    background-color: #5c6ac40c;
    width: 100%;
    height: 250px;
    margin-bottom: 40px;
    border-radius: 10px;
}
#personal-video-box3 {
    background-color: #5c6ac40c;
    width: 100%;
    height: 250px;
    border-radius: 10px;
}
#personal-data-box1 {
    background-color: #5c6ac40c;
    text-align: start;
    height: 230px;
    border-radius: 10px;
}
#profile_image_box {
  text-align: center;
  display: inline-block;
}
#user_profile_img {
  width: 100%;
  height: 80%;
  border: 1px solid #d0d0d0;
  display: inline-block;
}

body {margin: 10px}
.where {
  display: block;
  margin: 25px 15px;
  font-size: 11px;
  color: #000;
  text-decoration: none;
  font-family: verdana;
  font-style: italic;
}
.filebox {display:inline-block; margin: auto;}

.filebox label {
  display: inline-block;
  padding: .3em .7em;
  color:#5c6ac4;
  font-size: inherit;
  line-height: normal;
  vertical-align: middle;
  background-color: #ffffff;
  cursor: pointer;
  border: 1px solid #5c6ac4;
  border-radius: .25em;
  margin-top:10px;
}
.filebox label:hover {
  display: inline-block;
  padding: .3em .7em;
  color:#ffffff;
  font-size: inherit;
  line-height: normal;
  vertical-align: middle;
  background-color: #5c6ac4;
  cursor: pointer;
  border: 1px solid #5c6ac4;
  border-radius: .25em;
  margin-top:10px;
}

.filebox input[type="file"] {  /* 파일 필드 숨기기 */
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip:rect(0,0,0,0);
  border: 0;
}

</style>
