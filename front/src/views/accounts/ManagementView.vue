<template>
  <div class='container' id='management-page1'>
    <div class="d-flex justify-content-between">
      <p id='management-text1'>교육생 관리</p>
      <div class="max-w-xs relative space-y-3">
        <label
          for="search"
          class="text-gray-900"
        >
        <input
          type="text"
          id="search"
          v-model="searchName"
          placeholder="&#128269;  이름을검색하세요"
          class="p-3 mb-0.5 w-full form-control"
          style="width:300px; height:50px"
          @keydown.enter="searchStudent"
        >
        </label>

        <ul
          v-if="searchName"
          class="border border-gray-300"
        >
          <li>
            <p id='result-count'>
              {{ students.length }}명이 검색되었습니다.
            </p>
          </li>
          <li
            v-for="student in students"
            :key="student.id"
            @click="selectedStudent(student), closeUl()"
            @keydown="selectedStudent(student), closeUl()">
            <p>{{student.year}}기 {{ student.name }} {{ student.campus.city }}
              {{student.campus.classNumber }}반</p>
          </li>
        </ul>
      </div>
    </div>
    <!-- {{ getList }} -->
    <hr>
    <div class="container mb-5">
      <h5>{{ getList.length }}개의 피드백 요청 &#128172;</h5>
      <div>
      <carousel :nav="false" :dots="false" class="marginTop50">
        <div class="card mt-4" style="width: 19rem;"
        v-for="(request, index) in getList" :key="index">
          <div class="card-body">
            <h5 class="card-title">{{ request.name }}교육생의 피드백 요청</h5>
            <p class="card-text">요청일: {{ request.interviewDate }}</p>
          </div>
        </div>
      </carousel>
      </div>
    </div>
    <div class="container">
      <div class="row">
        <div v-if="studentData.studentName" id="student-profile">
          <img v-if="studentData.img" id='user_profile_img'
          :src="require(`../../../../image/${studentData.img.fileName}`)" alt="img">
          <img v-else src='@/assets/profile5.png' alt="no-img" id='user_profile_img'>
          <div id="user-profile-inner">
            <h6>이름</h6>
            <p id='student-data'>{{ studentData.studentName }}</p>
            <h6 class="mt-4">생년월일</h6>
            <p id='student-data'>{{ studentData.birthday }}</p>
            <h6 class="mt-4">지역/반</h6>
            <p id='student-data'>{{ studentData.year}}기
              {{ studentData.city }} {{ studentData.classNumber }}반</p>
            <h6 class="mt-4">이메일</h6>
            <p id='student-data'>{{ studentData.email }}</p>
            <h6 class="mt-4">연락처</h6>
            <p id='student-data'>{{ studentData.phone }}</p>
            <h6 v-if="studentData.enters.length" class="mt-4">관심기업</h6>
            <li v-for="(enter, index) in studentData.enters" :key="index" id="for-li"
            class="mb-4">
              #{{ enter.enterpriseName }}
            </li>
            <h6 v-if="studentData.jobs.length" class="mt-4">관심직무</h6>
            <li v-for="(job, index) in studentData.jobs" :key="index" id="for-li">
              #{{ job.jobName }}
            </li>
          </div>
        </div>
        <div v-if="studentData.studentName" id="student-profile2">
        <carousel :nav="false" :dots="false" class="marginTop50">
          <div class="card mt-4" style="width: 16.792rem;"
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
              <h5 class="card-title">#{{ video.id }} 개인 모의 면접</h5>
              <p class="card-text">녹화일: {{ video.interviewDate }}</p> <br>
            </div>
          </div>
        </carousel>
        </div>
      </div>
      </div>
        <div id='home-btn-box'>
      <router-link to='/'>
        <button class='btn' id='home-btn'>홈으로</button>
      </router-link>
    </div>
  </div>
</template>

<script>
import { ref, computed } from 'vue';
import axios from 'axios';
import drf from '@/api/api';
import { useStore } from 'vuex';

export default {
  name: 'ManagenmentView',
  setup() {
    const store = useStore();
    const students = computed(() => store.getters.students);
    const studentData = computed(() => store.getters.selectedStudent);
    const currentUser = computed(() => store.getters.currentUser);
    const feedbackList = [];
    const getList = computed(() => store.getters.feedbackList);
    const userVideo = computed(() => store.getters.userVideo);

    let finded = [];
    let searchName = ref('');
    const searchStudent = function () {
      axios({
        url: drf.member.getAllUser(),
        method: 'post',
        data: {
          name: searchName.value,
        },
      })
        .then((res) => {
          for (let i = 0; i < res.data.data.length; i += 1) {
            finded.push(res.data.data[i]);
          }
          store.dispatch('findedStudents', finded);
          finded = [];
        })
        .catch((err) => console.log(err.config.data));
    };
    let birthday = '';
    let city = '';
    let classNumber = '';
    let email = '';
    let enters = '';
    let jobs = '';
    let studentName = '';
    let phone = '';
    let year = '';
    let img = '';

    const closeUl = function () {
      searchName.value = ref('');
      finded = [];
    };
    const selectedStudent = function (student) {
      console.log(student.id);
      searchName = ref('');
      console.log('선택했다!');
      console.log(searchName);
      finded = [];
      birthday = student.birthday;
      city = student.campus.city;
      classNumber = student.campus.classNumber;
      email = student.email;
      enters = student.interestedEnterprises;
      jobs = student.interestedJobs;
      studentName = student.name;
      phone = student.phone;
      year = student.year;
      img = student.profilePicture;
      store.dispatch('selectedStudentData', {
        birthday, city, classNumber, email, enters, jobs, studentName, phone, year, img,
      });
      store.dispatch('getUserVideo', student.id);
    };
    return {
      searchName,
      searchStudent,
      finded,
      students,
      selectedStudent,
      birthday,
      city,
      classNumber,
      email,
      enters,
      jobs,
      studentName,
      phone,
      year,
      img,
      studentData,
      closeUl,
      currentUser,
      feedbackList,
      getList,
      userVideo,
    };
  },
  mounted() {
    axios({
      url: drf.interview.getFeedbackRequestVideo(this.currentUser.id),
      method: 'get',
    })
      .then((res) => {
        // for (let i = 0; i < res.data.data.length; i += 1) {
        //   // this.feedbackList.push(res.data.data[i]);
        //   // console.log(this.feedbackList);
        //   this.feedbackList.push(res.data.data[0]);
        // }
        this.$store.commit('SET_FEEDBACK_LIST', res.data.data);
      });
  },

};
</script>

<style scoped>
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
.card-body:hover {
  cursor: pointer;
  background-color: #5c6ac41a;
}
.card {
  display: inline-block;
  margin-right: 10px;
  text-align: center;
}
h5 {
  color: #555555;
  padding-top: 20px;
  padding-left: 10px;
}
#feedback-box {
  height: 25%;
  width: 100%;
  background-color: #5c6ac40c;
}
#student-profile2 {
  background-color: #5c6ac40c;
  border-radius: 10px;
  height: 750px;
  width: 860px;
  margin-left: 30px;
}
h6 {
  color: #555555;
  font-weight: 500;
}
#student-data:hover {
  border-radius: 5px;
  background-color: #626db325;
  list-style: none;
  display: inline;
}
#student-profile {
  text-align: center;
  background-color: #5c6ac40c;
  border-radius: 10px;
  height: 750px;
  width: 400px;
}
p {
  display: inline;
  color: #555555;
}
#user_profile_img {
  margin-top: 20px;
  margin-bottom: 20px;
  width: 132px;
  height: 170px;
  display: inline;
}
#for-li {
  background-color: none;
  list-style: none;
  display: inline;
  color: #555555;
}
#for-li:hover {
  border-radius: 5px;
  background-color: #626db325;
  list-style: none;
  display: inline;
}
#searched-bar-box1 {
  background-color: #5c6ac40c;
  width: 100%;
  height: 730px;
  margin-bottom: 40px;
  border-radius: 10px;
}
#search {
  margin-top: 60px;
}
#components1 {
  display: inline;
}
#home-btn{
  z-index: 1000;
  background-color: #ffffff;
  color:  #5c6ac4;
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
  border: 1px solid  #5c6ac4;
  font-size: 15px;
  border-radius: 0.25rem;
  padding: 0.5rem;
  width: 200px;
  height: 50px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

#home-btn:hover{
  color: white;
  background-color:  #5c6ac4;;
}
@media (prefers-reduced-motion: reduce) {
  #home-btn {
    transition: none;
  }
}
#home-btn-box {
  text-align: center;
  margin-top: 70px;
}
#management-page1 {
  height: 100vh;
}
#management-text1 {
  font-size: 30px;
  font-weight: 900;
  line-height: 2;
  font-stretch: normal;
  letter-spacing: normal;
  margin-top: 50px;
  display: inline;
  text-align: start;
}
#delete {
  font-size: 5px;
  vertical-align: top;
}
ul {
  z-index: 1000;
  position: absolute;
  overflow: scroll;
}
#selected-item{
  z-index: 1000;
  color: #5c6ac4;
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
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
  border: none;
}

@media (prefers-reduced-motion: reduce) {
  #selected-item {
    transition: none;
  }
}
#selected-item:hover {
  color: #5c6ac4;
  background-color: none;
  border: none;
}
ul {
  background-color: white;
  width: 300px;
  list-style:none;
  padding-left: 0;
}
#result-count {
  color: gray;
  font-size: 13px;
  text-align: center;
  margin-top: 10px;
}
li:hover {
 cursor: pointer;
 background-color: rgb(233, 233, 233);
}
#result {
  margin-bottom: 0;
  padding: 15px;
  text-align: center;
}
.form-control:focus {
  color: #000000;
  background-color: rgb(255, 255, 255);
  border-color: #ffffff;
  outline: 0;
  box-shadow: 0 0 0 0.1rem #5c6ac496;
}
</style>
