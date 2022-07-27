<template>
  <div class='container' id='search-bar-box1'>
    <div class='pt-3'>
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
      <search-bar-student id='component-bar'></search-bar-student>
    </div>
  </div>
</template>

<script>
import SearchBarStudent from './SearchBarStudent.vue';

export default {
  components: { SearchBarStudent },
  name: 'SearchStudent',
};
</script>

<style scoped>
#component-bar {
  margin-left: 10px;
}
#search-bar-box1 {
  background-color: #5c6ac40c;
  width: 100%;
  height: 250px;
  margin-bottom: 40px;
  border-radius: 10px;
}
#form-select-cardinal-number {
  width: 100px;
  display: inline;
}
</style>
