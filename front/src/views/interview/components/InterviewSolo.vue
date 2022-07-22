<template>
  <div class="container">
    <!-- <button class="btn btn-primary">면접 시작</button> -->
    <div class="d-flex justify-content-end" id="x">
      <button>종료</button>
    </div>
    <div>
      <div class="questionbox">자기소개 부탁드립니다.</div>
      <div class="interviewer">
        <div class="interviewee">interviewee</div>
      </div>
    </div>
  </div>
  <button class="btn btn-primary">답변 완료</button>
</template>

<script>
export default {

};
</script>

<style>
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

.interviewer{
  display: flex;
  justify-content: center;
  align-items: center;
}

.interviewee{
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  left: 267px;
  top: 649px;
  width: 400px;
  height: 275px;
  border: 1px solid black;
}

.questionbox {
  position: absolute;
  top: 150px;
  width: 1600px;
  height: 100px;
  background: black;
  color: white;
  font-size: 55px;
  opacity: 80%;
  line-height: 100px;
  text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
}
</style>
