<template>
  <div>
    <div class='container'>
      <div class='row'>
        <!-- 프로필이미지 -->
        <div id='profile_image_box' class='d-none d-md-block col-md-2'>
          <img v-if="!currentUser.profilePicture"
          src="@/assets/profile5.png" alt="profile" id='user_img'>
          <img v-else
          :src="require(`../../../../../image/${currentUser.profilePicture.fileName}`)"
          id='user_img' alt="profile">
        </div>
        <!-- 프로필인적사항 -->
        <div id='personal-data-box1' class='col-xs-12 col-sm-12 col-md-10'>
          <!-- 왼쪽 -->
          <div class='row' id='personal-data-box2'>
            <div class='col-lg-6 mt-4'>
                <p id='data-name'>이름</p>
                <p id="user-data">{{ currentUser.name }}</p>
                <p id='data-name'>소속</p>
                <p id="user-data">{{ currentUser.year}}기 {{ currentUser.campus.city }}
                  {{ currentUser.campus.classNumber }}반</p>
                <p id='data-name'>연락처</p>
                <p id="user-data">{{ currentUser.phone }}</p>
            </div>
          <!-- 오른쪽 -->
            <div class='col-lg-6 mt-4'>
                <p id='data-name'>생년월일</p>
                <p id="user-data">{{ currentUser.birthday }}</p>
                <p id='data-name'>이메일</p>
                <p id="user-data">{{ currentUser.email }}</p>
                <div v-if="currentUser.interestedJobs.length">
                  <p id='data-name2'>관심직무</p><br>
                    <p id="user-job" v-for="(job, index) in currentUser.interestedJobs"
                    :key="index">
                        #{{ job.jobName }}
                    </p>
                </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 관심기업/직무 -->
    <div class="mt-5">
        <p>{{ currentUser.name }}님의
          {{ currentUser.interestedEnterprises.length }}개의 관심기업 &#128064;</p>
          <div v-if="currentUser.interestedEnterprises.length">
            <div id="personal-caro-box" class="mt-5 mb-5">
              <div id='personal-video-box'>
                <Carousel :autoplay="2000">
                  <Slide v-for="enter in currentUser.interestedEnterprises" :key="enter">
                    <div class="carousel__item">
                    <img :src="require(`@/assets/enter/${enter.enterpriseName}.jpg`)"
                    class="d-block mx-auto" alt="로고" style="width:300px; height:130px;">
                      {{ slide }}
                      <div>
                        {{ enter.enterpriseName }}
                      </div>
                    </div>
                  </Slide>
                  <template #addons>
                    <Navigation />
                    <Pagination />
                  </template>
                </Carousel>
              </div>
            </div>
          </div>
          <div v-else id='personal-video-box2'>
            <p id='none-data-text1'>아직 추가한 기업이 없어요 :(</p>
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
  </div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';
import {
  Carousel, Slide, Pagination, Navigation,
} from 'vue3-carousel';
import 'vue3-carousel/dist/carousel.css';

export default {
  name: 'ProfileView',
  components: {
    Carousel,
    Slide,
    Pagination,
    Navigation,
  },
  setup() {
    const store = useStore();

    const isLoggedIn = computed(() => store.getters.isLoggedIn);
    const currentUser = computed(() => store.getters.currentUser);
    const profileImg = computed(() => store.getters.profileImg);
    const userVideo = computed(() => store.getters.userVideo);
    return {
      isLoggedIn,
      currentUser,
      profileImg,
      userVideo,
    };
  },
  created() {
    this.$store.dispatch('getUserVideo', this.currentUser.id);
  },
};
</script>

<style scoped>
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
#next {
  color: black;
}
#caro {
  text-align: center;
}
#personal-caro-box{
  height: 162px;
}
#user-job {
  margin-right: 10px;
  font-size: 15px;
  margin-bottom: 10px;
  display: inline;
  color: #707070;
}
#user-enterprise {
  display: inline;
}
#none-data-text1{
  color: rgb(167, 167, 167);
  line-height: 250px;
  text-align: center;
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
#btn-box {
    text-align: center;
    margin-top: 40px;
}
#form-select-region {
    width: 95px;
    display: inline;
    margin-left: 7.5px;
}
#form-select-class {
    width: 95px;
    display: inline;
    margin-left: 7.5px;
}
#form-select-cardinal-number {
  width: 95px;
  display: inline;
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
    box-shadow: 0 0 0 0.1rem #5c6ac4;
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
    box-shadow: 0 0 0 0.1rem #5c6ac4;
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
  color: #707070;
  display: inline;
  padding: 3px;
  border-radius: 15px;
  background-color: #626db325;
}
#data-name2 {
  font-size: 12px;
  color: #707070;
  padding: 3px;
  border-radius: 15px;
  display: inline;
  background-color: #626db325;
}
#user-data {
  font-size: 20px;
  margin-bottom: 10px;
  color: #707070;
  font-size: 15px;
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
    border-radius: 10px;
    height: 100%;
}
#profile_image_box {
  text-align: center;
  display: inline-block;
}
#user_img {
  width: 100%;
  height: 100%;
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

/* 캐러셀 */
.carousel__item {
  min-height: 200px;
  width: 100%;
  justify-content: center;
  align-items: center;
}

.carousel__slide {
  padding: 10px;
}

.carousel__prev,
.carousel__next {
  box-sizing: content-box;
  background: white;
  border: 5px solid white;
}

.carousel__pagination {
  padding: 0;
}
</style>
