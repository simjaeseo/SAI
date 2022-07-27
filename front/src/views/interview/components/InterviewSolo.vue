<template>
  <div class="container">
    <!-- <button class="btn btn-primary">면접 시작</button> -->
    <div class="d-flex justify-content-end" id="x">
      <button class="btn btn-dark">종료</button>
    </div>
    <div>
      <div class="questionbox">자기소개 부탁드립니다.</div>
      <div class="interviewer">
        <div class="interviewee">interviewee</div>
        <button button class="finish-btn">답변 완료</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {

};
</script>

<style scoped>
.container {
  max-width: 1600px;
  /* display: flex;
  justify-content: center;
  align-items: center; */
  border: 1px solid black;
  width: 1600px;
  height: 900px;
  padding: 0;
}
.finish-btn{
  margin-bottom: 3px;
  z-index: 1000;
  background-color: #5c6ac4;
  color: white;
  display: inline-block;
  font-weight: 400;
  line-height: 20px;
  text-align: center;
  text-decoration: none;
  vertical-align: middle;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
  border: 1px solid transparent;
  font-size: 20px;
  border-radius: 0.25rem;
  padding: 0;
  width: 150px;
  height: 50px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
.interviewer{
  display: flex;
}

.interviewee{
  display: flex;
  justify-content: center;
  align-items: center;
  width: 400px;
  height: 275px;
  margin-top: 576px;
  border: 1px solid black;
}
.questionbox{
  margin-top: 10px;
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
  padding: 0rem;
  height: 60px;
  width: 1598px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
.btn {
  margin: 0.3rem;
}
</style>
