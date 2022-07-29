<template>
  <div>
    <div id="next-schedule">
      <h3>1:1 모의 화상 면접</h3>
      <h4>7월 30일 11 : 30</h4>
      <button class="btn">면접 바로가기</button>
      <br><br>
      <p>담당 컨설턴트 : 류채윤<br>타겟 기업 : 카카오<br>면접 분류 : 직무</p>
      <br>
    </div>
    <div id='layer'>
      <h5>다가오는 일정</h5>
    </div>
    <div>
      <p>7월 20일  카카오 서류 마감</p>
      <p>7월 21일  마카오 서류 마감</p>
      <p>7월 22일  코카오 서류 마감</p>
      <p>7월 23일  킹카오 서류 마감</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ShowEvent',
};
</script>

<style scoped>
.btn {
  margin-top: 10px;
  background-color: #5c6ac4;
  color: white;
  padding-top: 10px;
  padding-bottom: 10px;
}
#next-schedule {
  text-align: center;
  margin-top: 50px;
}

h3 h4 h5 {
  color: #5a5a5a;
}
h5 {
  text-align: center;
  margin-top: 50px;
  margin-bottom: 30px;
}
p {
  color: #5a5a5a;
  text-align: center
};
</style>
