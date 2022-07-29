<template>
  <div class="container" >
    <div class="row">
      <div class="col-lg-8" id='left-box'>
        <p id='schedule-text1'>오늘의 일정</p>
        <p id='schedule-text2'>예약된 일정이 없습니다.</p>
        <p id='schedule-text2'>11:00 류채윤 1:1 모의면접
          <button id='schedule-btn1' class="btn">입장하기</button></p>
      </div>
      <div class="col-lg-4" id='right-box'>
        <button id='schedule-btn2'>모의면접시작</button>
        <button id='schedule-btn3'>일정관리</button>
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
#schedule-text1 {
  font-size: 30px;
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
