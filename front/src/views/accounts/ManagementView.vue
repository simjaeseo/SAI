<template>
  <div class='container' id='management-page1'>
    <div class="d-flex justify-content-between">
      <p id='management-text1'>교육생관리</p>
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
          v-if="searchName.length"
          class="border border-gray-300"
        >
          <li>
            <p id='result-count'>
              {{ students.length }}개가 검색되었습니다.
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
    <hr>

    <!-- <div class="container" id='searched-bar-box1'>
      <div v-if="studentData.studentName">
        <img v-if="studentData.img" id='user_profile_img'
        :src="require(`../../../../image/${studentData.img.fileName}`)" alt="img">
        <img v-else src='@/assets/profile5.png' alt="no-img" id='user_profile_img'>
        <h6>이름</h6>
        <p>{{ studentData.studentName }}</p>
        <h6 class="mt-4">생년월일</h6>
        <p>{{ studentData.birthday }}</p>
        <h6 class="mt-4">지역/반</h6>
        <p>{{ studentData.year}}기 {{ studentData.city }} {{ studentData.classNumber }}반</p>
        <h6 class="mt-4">이메일</h6>
        <p>{{ studentData.email }}</p>
        <h6 class="mt-4">연락처</h6>
        <p>{{ studentData.phone }}</p>
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
    </div> -->
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
          console.log(finded);
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
      console.log(searchName.value);
      searchName = ref('');
      console.log(searchName.value);
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
    };
  },
};
</script>

<style scoped>
h6 {
  display: inline;
}
p {
  display: inline;
}
#user_profile_img {
  margin-top: 10px;
  width: 132px;
  height: 170px;
  display: inline;
}
#for-li {
  background-color: none;
  list-style: none;
  display: inline;
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
  height: 520px;
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
