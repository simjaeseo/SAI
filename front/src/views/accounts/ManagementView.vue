<template>
  <div class='container' id='management-page1'>
    <div class="d-flex justify-content-between">
      <p id='management-text1'>교육생관리{{ searchName }}</p>
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
          v-if="finded.length != 0"
          class="border border-gray-300"
        >
          <li>
            <p id='result-count'>
              {{ finded.length }}개가 검색되었습니다.
            </p>
          </li>
          <li
            v-for="student in finded"
            :key="student.id"
            @click="selectedStudent(student.name)"
            @keydown="selectedStudent(student.name)">
            <p>{{ student.name }}</p>
          </li>
        </ul>
      </div>
    </div>
    <hr>
    <div class="container">
      <searched-student></searched-student>
    </div>
        <div id='home-btn-box'>
      <router-link to='/'>
        <button class='btn' id='home-btn'>홈으로</button>
      </router-link>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';
import axios from 'axios';
import drf from '@/api/api';
// import { useStore } from 'vuex';
import SearchedStudent from './components/SearchedStudentCT.vue';

export default {
  components: { SearchedStudent },
  name: 'ManagenmentView',
  setup() {
    // const store = useStore();
    const finded = [];
    const searchName = ref('');
    const searchStudent = function () {
      axios({
        url: drf.member.getAllUser(),
        method: 'post',
        data: {
          name: searchName.value,
        },
      })
        .then((res) => finded.push(res.data.data))
        .then(console.log(finded))
        .catch((err) => console.log(err.config.data));
      // for (let i = 0; i < finded.length;)
    };
    return {
      searchName,
      searchStudent,
      finded,
    };
  },
};
</script>

<style scoped>
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
