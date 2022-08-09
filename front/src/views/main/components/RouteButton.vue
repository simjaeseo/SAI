<template>
  <div class="container" >
    <div class="row">
      <div class="col-lg-7" id='left-box'>
        <p id='schedule-text1'>오늘의 일정</p>
        <!-- <p id='schedule-text2'>예약된 일정이 없습니다.</p> -->
        <p id='schedule-text2'>11:00 류채윤 1:1 모의면접
          <button id='schedule-btn1' class="btn">입장하기</button></p>
      </div>
      <div class="col-lg-5" id='right-box'>
        <router-link to="/interview" style="text-decoration:none">
          <button id='schedule-btn2' class="btn">모의면접시작</button>
        </router-link>
        <router-link to="/schedule" style="text-decoration:none">
          <button id='schedule-btn3' class="btn">일정관리</button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'RouteButton',
};
</script>

<style scoped>
#schedule-btn3 {
  display: block;
  background-color: #ffffff;
  color: #5c6ac4;
  border: 1px solid #5c6ac4;
  font-size: 13px;
  width: 90%;
  padding-top: 15px;
  padding-bottom: 15px;
  margin-top: 30px;
}
#schedule-btn2 {
  display: block;
  background-color: #ffffff;
  color: #5c6ac4;
  border: 1px solid #5c6ac4;
  font-size: 13px;
  width: 90%;
  padding-top: 15px;
  padding-bottom: 15px;
}
#schedule-btn2:hover {
  background-color: #5c6ac4;
  color: white;
}
#schedule-btn3:hover {
  background-color: #5c6ac4;
  color: white;
}
#schedule-text1 {
  font-size: 30px;
  margin-top: 20px;
}
#schedule-btn1 {
  background-color: #5c6ac4;
  color: #ffffff;
  border: 1px solid #5c6ac4;
  font-size: 13px;
}
.container {
  margin: auto;
}
#left-box {
  text-align: center;
}
</style>
