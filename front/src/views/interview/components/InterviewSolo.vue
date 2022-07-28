<template>
  <div class="container">
    <!-- <button class="btn btn-primary">면접 시작</button> -->
    <!-- <div class="d-flex justify-content-end" id="x"> -->
    <div class="d-flex flex-column align-items-end">
      <button class="btn btn-outline-transparent">X</button>
      <div class="questionbox">자기소개 부탁드립니다.</div>
    </div>
    <div class="interviewer">
      <div class="interviewee"></div>
      <button button class="finish-btn">답변 완료</button>
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
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  /* align-items: space-between; */
  border: 1px solid black;
  width: 1600px;
  height: 900px;
  padding: 0;
  background-image: url("../../../assets/interviewer.jpeg");
  background-size: cover;
}
.finish-btn{
  margin: 3px;
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
  justify-content: space-between;
  align-items: flex-end;
}

.interviewee{
  display: flex;
  justify-content: center;
  align-items: center;
  width: 400px;
  height: 275px;
  border: 1px solid black;
  background-image: url("../../../assets/interviewee.png");
  background-size: cover;
}
.questionbox{
  z-index: 1000;
  color: #5c6ac4;
  background-color: white;
  border: 1px solid #5c6ac4;
  display: inline-block;
  font-weight: 400;
  line-height: 60px;
  text-align: center;
  text-decoration: none;
  vertical-align: middle;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
  font-size: 24px;
  border-radius: 0.25rem;
  padding: 0rem;
  height: 60px;
  width: 1598px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

.btn {
  opacity: 0.2;
  font-size: 2rem;
}
</style>
