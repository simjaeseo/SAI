<template>
  <div>
    <form @submit.prevent="userUpdate" enctyle="multipart/form-data" id="form">
        <div class='container'>
          <div class='row'>
            <!-- 프로필이미지 -->
            <div id='profile_image_box' class='col-sm-2'>
              <img v-if="!!state.imgUrl" :src="state.imgUrl" alt="preview"
              id='user_profile_img'>
              <img v-else-if="currentUser.profilePicture"
              :src="`https://i7c206.p.ssafy.io/img/${currentUser.profilePicture.fileName}`"
              alt="preview"
              id='user_profile_img'>
              <img v-else src='@/assets/profile5.png' alt='basic-img' id='user_profile_img'> <br>
                <div class="filebox">
                    <label for='ex_file'><input type='file' id='ex_file' accept='image/*'
                    ref='image' class='upload-box' @change="onInputImage">파일선택</label>
                </div>
            </div>
            <!-- 프로필인적사항 -->
            <div id='personal-data-box1' class='col-sm-10'>
              <!-- 왼쪽 -->
              <div class='row' id='personal-data-box2'>
                <div class='col-lg-6'>
                    <p id='data-name'>이름</p>
                    <p id="user-data">{{ currentUser.name }}</p>
                    <p id='data-name'>소속</p><br>
                    <select class='form-select mb-2' id='form-select-cardinal-number'
                    aria-label='Default select example'>
                        <option selected disabled>
                        {{ currentUser.year}}기</option>
                    </select>
                    <select class='form-select' id='form-select-region'
                    aria-label='Default select example'
                    >
                        <option selected disabled>{{ currentUser.campus.city }}</option>
                    </select>
                    <select class='form-select' id='form-select-class'
                    aria-label='Default select example'
                    @click="selectedUserClass"
                    v-model="state.userClass"
                    required>
                      <option selected>{{ currentUser.campus.classNumber}}</option>
                      <option v-for='option in state.options' :key="option">{{option}}</option>
                    </select><br>
                    <p id='data-name'>연락처</p><br>
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
                        v-model="state.mobileSecond"
                         maxlength="4">
                    </label>
                    <label for="user_signup_number2">
                        <input type="text" class="form-control" id='form-select-second'
                        v-model="state.mobileLast"
                         maxlength="4">
                    </label>
                </div>
              <!-- 오른쪽 -->
                <div class='col-lg-6'>
                    <p id='data-name'>생년월일</p>
                    <p id="user-data">{{ currentUser.birthday }}</p>
                    <p id='data-name'>이메일</p>
                    <p id="user-data">{{ currentUser.email }}</p>
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
              <p id='for-inline'>
                {{ currentUser.name }}님의 {{ Enters.length }}개의 관심기업 &#128064;</p>
              <!-- Button trigger modal -->
              <button type="button" class="btn btn-primary" data-bs-toggle="modal"
              data-bs-target="#staticBackdrop" id='add-btn'>
                수정하기
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
                      aria-label="Close" data-bs-dismiss="modal" @click="trigger"
                      >확인</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div v-if="Enters.length" id='personal-video-box' class="mt-5">
              <div id="myCarousel" class="carousel slide"
              data-bs-touch="false" data-bs-interval="false">
                <div class="carousel-inner mb-5">
                  <div class="carousel-item active" id="caro"
                  v-for="(enter, index) in Enters" :key="index">
                    <div class="d-block"></div>
                    <img :src="require(`@/assets/enter/${enter.name}.jpg`)"
                    class="d-block mx-auto" alt="로고" style="width:300px; height:130px;">
                    <h5>{{ enter.name }}</h5>
                  </div>
                </div>
                <button class="carousel-control-prev" type="button"
                data-bs-target="#myCarousel" data-bs-slide="prev">
                  <span id="next">&#60;</span>
                </button>
                <button class="carousel-control-next" type="button"
                data-bs-target="#myCarousel" data-bs-slide="next"
                id="next-btn" @click="trigger"
                >
                  <span id="next">&#62;</span>
                </button>
              </div>
            </div>
            <div v-else>
              <div id='personal-video-box2'>
                <p id='none-data-text1'>아직 추가한 기업이 없어요 :(</p>
              </div>
            </div>
          </div>
        </div>
    <!-- 개인 동영상 -->
        <div class="mt-5 pt-5">
          <p>{{ currentUser.name }}님의 {{ userVideo.length }}개의 영상 &#127916;</p>
          <div class="box">
            <div id="tab">
              <ul :nav="false" :dots="false" class="marginTop50">
                <li class="card" style="width: 16.792rem; margin-top:70px;"
                v-for="(video, index) in userVideo" :key="index">
                  <div class="card-body">
                    <div id="delete-personal-btn-box">
                      <button class="btn"
                      id="delete-personal-btn" @click="deletePersonalVideo(video.id)">x</button>
                    </div>
                    <div id="badge-box">
                      <button id="card-text-badge-request" class="btn"
                      v-if="video.feedbackCompleteStatus === 'FALSE'
                      && video.feedbackRequestStatus === 'TRUE'">
                        REQUEST</button>
                      <button id="card-text-badge-completed" class="btn"
                      v-if="video.feedbackCompleteStatus === 'TRUE'">
                        COMPLETED</button>
                      <button id="card-text-badge-none" class="btn"
                      v-if="video.feedbackRequestStatus === 'FALSE'">
                        TEST</button>
                    </div>
                    <h5 class="card-title mt-3">#{{ video.id }} 개인 모의 면접</h5>
                    <p class="card-text">녹화일: {{ video.interviewDate }}</p> <br>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <!-- 컨설팅영상 추가하심됩니다-->
        <div class="mt-5 pt-5">
          <p class="mt-5">{{ currentUser.name }}님의 컨설팅 영상 &#127916;</p>
          <div class="box">
            <div id="tab">
              <ul :nav="false" :dots="false" class="marginTop50">
                <li class="card" style="width: 16.792rem; margin-top:70px;"
                v-for="(video, index) in userVideo" :key="index">
                  <div class="card-body">
                    <div id="badge-box">
                      <button id="card-text-badge-request" class="btn"
                      v-if="video.feedbackCompleteStatus === 'FALSE'
                      && video.feedbackRequestStatus === 'TRUE'">
                        REQUEST</button>
                      <button id="card-text-badge-completed" class="btn"
                      v-if="video.feedbackCompleteStatus === 'TRUE'">
                        COMPLETED</button>
                      <button id="card-text-badge-none" class="btn"
                      v-if="video.feedbackRequestStatus === 'FALSE'">
                        TEST</button>
                    </div>
                    <h5 class="card-title mt-3">#{{ video.id }} 개인 모의 면접</h5>
                    <p class="card-text">녹화일: {{ video.interviewDate }}</p> <br>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <!-- 버튼 -->
        <div id='btn-box'>
            <router-link :to="{ name: 'Profile', params: { id: `${ currentUser.id }`} }">
              <button class='btn' id='cancel-btn'>돌아가기</button>
            </router-link>
            <button class='btn' type='submit' id='update-btn' @click="updateAllEnter">수정완료</button>
        </div>
    </form>
  </div>
</template>

<script>
import {
  computed,
  reactive,
  onBeforeMount,
} from 'vue';
import axios from 'axios';
import drf from '@/api/api';
import { useStore } from 'vuex';
import _uniq from 'lodash/uniq';
import router from '@/router/index';
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
      userClass: '',
      file: '',
      url: '',
      imgUrl: '',
    });

    // store에서 넘어온 원래 직무 (변경사항 있을수도, 없을수도)
    const Jobs = computed(() => store.getters.userJob);
    const Enters = computed(() => store.getters.userEnter);
    const isLoggedIn = computed(() => store.getters.isLoggedIn);
    const currentUser = computed(() => store.getters.currentUser);
    const userVideo = computed(() => store.getters.userVideo);
    let img = null;
    const trigger = function () {
      if (Enters.value.length) {
        for (let i = 0; i < currentUser.value.interestedEnterprises.length + 1; i += 1) {
          document.getElementById('next').click();
        }
      }
    };
    onBeforeMount(() => {
      const userPhone = currentUser.value.phone;
      state.mobileFirst = userPhone.substr(0, 3);
      state.mobileSecond = userPhone.substr(3, 4);
      state.mobileLast = userPhone.substr(7);
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
      state.userClass = currentUser.value.campus.classNumber;
      if (currentUser.value.profilePicture != null) {
        img = currentUser.value.profilePicture.fileName;
        console.log('이미지있다!');
        console.log(img);
      } else {
        img = null;
      }
    });
    const selectedUserClass = function (event) {
      state.userClass = event.target.value;
    };
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
    const userUpdate = () => {
      const data = {
        id: currentUser.value.id,
        campus: {
          city: currentUser.value.campus.city,
          classNumber: state.userClass,
        },
        phone: state.mobileFirst + state.mobileSecond + state.mobileLast,
        interestedJobs:
          _uniq(Jobs.value),
        interestedEnterprises:
          _uniq(Enters.value),
      };
      const formData = new FormData();
      formData.append('file', img);
      console.log(img);
      formData.append('request', new Blob([JSON.stringify(data)], { type: 'application/json' }));
      store.dispatch('userUpdate', formData);
    };
    const onInputImage = (e) => {
      state.file = e.target.files;
      state.url = URL.createObjectURL(state.file[0]);
      state.imgUrl = state.url;
      console.log(state.imgUrl);
      [img] = e.target.files;
    };
    const deletePersonalVideo = function (info) {
      if (window.confirm('정말 삭제하시겠습니까? 삭제된 영상은 복구할 수 없습니다.')) {
        const userId = currentUser.value.id;
        console.log(userId);
        axios({
          url: drf.interview.deletePersonalVideo(userId, info),
          method: 'delete',
        })
          .then(() => {
            router.go();
          });
      }
    };
    return {
      isLoggedIn,
      currentUser,
      state,
      noChange,
      setOptions,
      userUpdate,
      onInputImage,
      Enters,
      Jobs,
      selectedUserClass,
      userVideo,
      deletePersonalVideo,
      trigger,
    };
  },
  created() {
    this.$store.dispatch('getUserVideo', this.currentUser.id);
  },
  mounted() {
    this.trigger();
  },
};
</script>

<style scoped>
#data-name {
  font-size: 12px;
  color: #707070;
  display: inline;
  padding: 3px;
  border-radius: 15px;
  background-color: #626db325;
}
#user-data {
  font-size: 20px;
  margin-bottom: 10px;
  color: #707070;
  font-size: 15px;
}
#delete-personal-btn-box {
  text-align: end;
}
#delete-personal-btn {
  padding-left: 10px;
  padding-right: 10px;
  color: gray;
  border: none;
}
#delete-personal-btn:hover {
  padding-left: 10px;
  padding-right: 10px;
  color: #f0506e;
  border: none;
}
ul{
  list-style:none;
}
a{
  text-decoration:none;
  color:inherit;
}
.box{
  width:100%;
  height:300px;
  margin:0 auto;
  background-color: #5c6ac40c;
  border-radius: 10px;
}
#tab ul{
  white-space:nowrap;
  overflow-x: auto;
  text-align:center;
  height: 300px;
}

#tab ul li{
  display:inline-block;
  margin-right:10px;
}
.card-body:hover {
  cursor: pointer;
  background-color: #5c6ac41a;
  text-align: center;
}
.card {
  display: inline-block;
  margin-right: 10px;
  text-align: center;
}
#card-text-badge-request {
  display: inline;
  background: #f0506e;
  color: #ffffff;
  font-size: 10px;
  padding: 2px 3px 2px 3px;
}
#card-text-badge-completed {
  display: inline;
  background: #32d296;
  color: #ffffff;
  font-size: 10px;
  padding: 2px 3px 2px 3px;
}
#card-text-badge-none {
  display: inline;
  background: #faa05a;
  color: #ffffff;
  font-size: 10px;
  padding: 2px 3px 2px 3px;
}
#none-data-text1 {
  color: rgb(167, 167, 167);
  line-height: 250px;
  text-align: center;
}
#caro {
  text-align: center;
}
#next {
  color: black;
}
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
    text-align: center;
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
