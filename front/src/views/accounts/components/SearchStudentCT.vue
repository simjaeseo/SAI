<template>
  <div class='container' id='search-bar-box1'>
    <div class='pt-3' id='components-box'>
      <select class='form-select' id='form-select-cardinal-number'
      aria-label='Default select example'>
        <option selected>년도</option>
        <option value='1'>1기</option>
        <option value='2'>2기</option>
        <option value='3'>3기</option>
        <option value='4'>4기</option>
        <option value='5'>5기</option>
        <option value='6'>6기</option>
        <option value='7'>7기</option>
        <option value='7'>8기</option>
      </select>
      <search-bar-student></search-bar-student>
    </div>
  </div>
</template>

<script>
import SearchBarStudent from './SearchBarStudentCT.vue';

export default {
  components: { SearchBarStudent },
  name: 'SearchStudent',
};
</script>

<style scoped>
#search-bar-box1 {
  background-color: #5c6ac40c;
  width: 100%;
  height: 250px;
  margin-bottom: 40px;
  border-radius: 10px;
}
#form-select-cardinal-number {
  width: 100px;
  float: left;
  margin-right: 10px;
}
</style>
