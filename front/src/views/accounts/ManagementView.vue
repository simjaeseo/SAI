<template>
  <div class='container' id='management-page1'>
    <p id='management-text1'>교육생관리</p>
    <hr>
    <div class="container">
      <search-bar-student></search-bar-student>
    </div>
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
import SearchedStudent from './components/SearchedStudent.vue';
import SearchBarStudent from './components/SearchStudent.vue';

export default {
  components: { SearchBarStudent, SearchedStudent },
  name: 'ManagenmentView',
};
</script>

<style>
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
  text-align: start;
}
</style>
