<template>
  <div>
    <form @submit.prevent="userUpdate">
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
                    <select class='form-select' id='form-select-cardinal-number'
                    aria-label='Default select example'>
                        <option selected disabled>
                        {{ currentUser.year}}기</option>
                    </select>
                    <select class='form-select' id='form-select-region'
                    aria-label='Default select example'>
                        <option selected disabled>{{ currentUser.campus.city }}</option>
                    </select>
                    <select class='form-select' id='form-select-class'
                    aria-label='Default select example'
                    @click="setOptions">
                      <option selected>{{ currentUser.campus.classNumber}}</option>
                      <option v-for='option in state.options' :key="option">{{option}}</option>
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
                    class='form-control' v-model="currentUser.birthday" readonly
                    @click="noChange"></label>
                    <p id='data-name'>이메일</p>
                    <label for='user-update-name'><input type='text' id='user-update-name'
                    class='form-control' v-model="currentUser.email" readonly
                    @click="noChange"></label>
                    <p id='data-name'>관심직무</p>
                    <search-bar-duty></search-bar-duty>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div>
          <div id='personal-video-box1' class='container'>
            <div id='box1'>
              <p id='for-inline'>관심기업</p>
              <!-- Button trigger modal -->
              <button type="button" class="btn btn-primary" data-bs-toggle="modal"
              data-bs-target="#staticBackdrop" id='add-btn'>
                추가하기
              </button>

              <!-- Modal -->
              <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
              data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel"
              aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title" id="staticBackdropLabel">관심기업명을 검색하세요</h5>
                      <button type="button" class="btn-close" data-bs-dismiss="modal"
                      aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                      <search-bar-update></search-bar-update>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn" id='cancel-btn2'
                      data-bs-dismiss="modal">닫기</button>
                      <button type="button" class="btn" id='update-btn2'
                      aria-label="Close" data-bs-dismiss="modal">확인</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div id='personal-video-box2'>
            </div>
          </div>
        </div>
        <!-- 동영상 -->
        <div>
          <div id='personal-video-box1' class="container">
            <p>내 동영상</p>
              <div id='personal-video-box2'>
              </div>
            <p>컨설팅 영상</p>
              <div id='personal-video-box3'>
              </div>
          </div>
        </div>
        <!-- 버튼 -->
        <div id='btn-box'>
            <router-link to="profile"><button class='btn' id='cancel-btn'>취소</button></router-link>
            <button class='btn' type='submit' id='update-btn'>완료</button>
        </div>
    </form>
    <div>
      {{ oldJobs }}
      {{ newJobs.plusJob }}
    </div>
  </div>
</template>

<script>
import {
  computed,
  reactive,
  onBeforeMount,
  onUpdated,
} from 'vue';
import { useStore } from 'vuex';
import SearchBarDuty from './SearchBarDuty.vue';
import SearchBarUpdate from './SearchBarUpdate.vue';

export default {
  components: { SearchBarDuty, SearchBarUpdate },
  name: 'ProfileDataUpdate',
  setup() {
    const store = useStore();
    const state = reactive({
      mobileFirst: '',
      mobileSecond: '',
      mobileLast: '',
      options: [],
    });

    // store에서 넘어온 새로 추가 된 직무
    const newJobs = computed(() => store.getters.setNewJob);
    const newEnters = computed(() => store.getters.setNewEnter);
    // store에서 넘어온 원래 직무 (변경사항 있을수도, 없을수도)
    const oldJobs = computed(() => store.getters.userJob);
    const oldEnters = computed(() => store.getters.userEnter);
    const isLoggedIn = computed(() => store.getters.isLoggedIn);
    const currentUser = computed(() => store.getters.currentUser);

    onBeforeMount(() => {
      // const originalJobs = JSON.parse(JSON.stringify(oldJobs));
      // console.log(originalJobs);
      const userPhone = currentUser.value.phone;
      state.mobileFirst = userPhone.substr(0, 3);
      state.mobileSecond = userPhone.substr(3, 4);
      state.mobileLast = userPhone.substr(7);
    });

    let updateJobs = [];
    let newUpdateJobs = [];
    let updateEnters = [];
    let newUpdateEnters = [];
    let jobData = null;
    let enterData = null;
    onUpdated(() => {
      // allJobs 배열에 모든 직무 객체 추가
      const allJobs = [];
      // 추가된 직무 길이
      if (!newJobs.value.plusJob) {
        for (let i = 0; i < newJobs.value.length; i += 1) {
          allJobs.push(newJobs.value.plusJob[i].name);
        }
      }
      // 유저가 원래 가지고있던 직무 길이 (수정 됐을수도 안됐을수도)
      if (oldJobs.value.length !== 0) {
        for (let i = 0; i < oldJobs.value.length; i += 1) {
          allJobs.push(oldJobs.value[i].jobName);
        }
      }
      updateJobs = allJobs;
      newUpdateJobs = updateJobs.map((item) => ({ name: item }));
      // proxy 변환 후 전송해도 오류
      const jobProxy = new Proxy(newUpdateJobs, {});
      jobData = jobProxy;
      console.log(jobData);
      console.log(jobProxy);
      const allEnters = [];
      // 추가된 회사 길이
      if (!newEnters.value.plusEnter) {
        for (let i = 0; i < newEnters.value.length; i += 1) {
          allEnters.push(newEnters.value.plusEnter[i].name);
        }
      }
      // 유저가 원래 가지고있던 회사 길이 (수정 됐을수도 안됐을수도)
      if (oldEnters.value.length !== 0) {
        for (let i = 0; i < oldEnters.value.length; i += 1) {
          allEnters.push(oldEnters.value[i].enterpriseName);
        }
      }
      updateEnters = allEnters;
      newUpdateEnters = updateEnters.map((item) => ({ name: item }));
      // proxy 변환 후 전송해도 오류
      const enterProxy = new Proxy(newUpdateEnters, {});
      enterData = enterProxy;
      console.log(enterData);
      console.log(enterProxy);
    });

    const noChange = function () {
      alert('변경할 수 없는 값입니다.');
    };
    const setOptions = function () {
      if (currentUser.value.campus.city === '서울') {
        state.options.push('1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16');
      } else if (currentUser.value.campus.city === '대전') {
        state.options = [];
        state.options.push('1', '2', '3', '4', '5', '6', '7', '8', '9');
      } else if (currentUser.value.campus.city === '광주') {
        state.options = [];
        state.options.push('1', '2', '3', '4', '5', '6');
      } else if (currentUser.value.campus.city === '구미') {
        state.options = [];
        state.options.push('1', '2', '3', '4', '5', '6');
      } else if (currentUser.value.campus.city === '부울경') {
        state.options = [];
        state.options.push('1', '2', '3', '4', '5', '6');
      }
    };

    const userUpdate = function () {
      store.dispatch('userUpdate', {
        campus: {
          city: currentUser.value.campus.city,
          classNumber: currentUser.value.campus.classNumber,
        },
        phone: state.mobileFirst + state.mobileSecond + state.mobileLast,
        profilePicture: 'none',
        interestedJobs: {
          name: jobData,
        },
        interestedEnterprises: {
          name: enterData,
        },
      });
    };
    return {
      isLoggedIn,
      currentUser,
      state,
      noChange,
      setOptions,
      newJobs,
      oldJobs,
      userUpdate,
      updateJobs,
      newEnters,
      oldEnters,
      updateEnters,
      newUpdateEnters,
      jobData,
      enterData,
    };
  },
  updated() {
  },
};
</script>

<style scoped>
#nocheck {
  color: gray;
}
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
    width: 95px;
    display: inline;
    margin-left: 7.5px;
}
#form-select-region:hover {
    width: 95px;
    display: inline;
    margin-left: 7.5px;
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
