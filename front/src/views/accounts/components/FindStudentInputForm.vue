<template>
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
        @change="searchStudent()"
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
          @click.prevent="selectedStudent(student), forceRerender()"
          @keydown="selectedStudent(student), forceRerender()">
          <p>{{student.year}}기 {{ student.name }} {{ student.campus.city }}
            {{student.campus.classNumber }}반</p>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { ref, computed } from 'vue';
import axios from 'axios';
import drf from '@/api/api';
import { useStore } from 'vuex';

export default {
  name: 'FindStudentInputForm',
  props: {
    cKey: Number,
  },
  setup() {
    const store = useStore();
    const students = computed(() => store.getters.students);
    let finded = [];
    const searchName = ref('');
    const value = function (event) {
      console.log(event);
    };
    const searchStudent = function () {
      axios({
        url: drf.member.getAllUser(),
        method: 'post',
        data: {
          name: searchName.value,
        },
      })
        .then((res) => {
          console.log(res);
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
    const selectedStudent = function (student) {
      console.log(student.id);
      searchName.value = '';
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
      finded,
      searchStudent,
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
      value,
    };
  },
  methods: {
    forceRerender() {
      this.$emit('forceRerender');
    },
  },
};
</script>

<style scoped>
#search {
  margin-top: 60px;
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
</style>
