<template>
  <div>
    <div class='container'>
      <div class='row'>
        <!-- 프로필이미지 -->
        <div id='profile_image_box' class='d-none d-md-block col-md-2'>
          <img src='@/assets/profile4.png' alt='basic-img' id='user_profile_img'> <br>
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
                <p id='data-name'>관심직무</p>
                <p id="user-job" v-for="(job, index) in currentUser.interestedJobs" :key="index">
                  #{{ job.jobName }}
                </p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 관심기업/직무 -->
    <div>
      <div id='personal-video-box1' class='container'>
        <p>관심기업</p>
          <div id='personal-video-box2'>
            <div v-if="currentUser.interestedEnterprises">
              <p v-for="(enterprise, index) in currentUser.interestedEnterprises"
              :key="index"
              id="user-enterprise">
                {{ enterprise.enterpriseName }}</p>
            </div>
            <p v-else id='none-data-text1'>관심기업/직무를 등록하세요!</p>
          </div>
      </div>
    </div>
    <!-- 동영상 -->
    <div>
      <div id='personal-video-box1' class="container">
        <p>내 동영상</p>
          <div id='personal-video-box2'>
            <p id='none-data-text1'>아직 저장된 영상이 없어요</p>
            <!-- 동영상 구현 필요 -->
          </div>
        <p>컨설팅 영상</p>
          <div id='personal-video-box3'>
            <p id='none-data-text1'>아직 저장된 영상이 없어요</p>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';

export default {
  name: 'ProfileView',
  setup() {
    const store = useStore();

    const isLoggedIn = computed(() => store.getters.isLoggedIn);
    const currentUser = computed(() => store.getters.currentUser);
    return {
      isLoggedIn,
      currentUser,
    };
  },
};
</script>

<style scoped>
#user-job {
  display: inline;
  margin-right: 10px;
  font-size: 20px;
  margin-bottom: 10px;
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
    border-radius: 10px;
}
#profile_image_box {
  text-align: center;
  display: inline-block;
}
#user_profile_img {
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

</style>
