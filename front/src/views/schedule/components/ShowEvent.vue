<template>
  <div>
    <div id="next-schedule">
        <h3>1:1 모의 화상 면접</h3>
        <h4>7월 30일 11 : 30</h4>
        <br><br>
        <p>담당 컨설턴트 : 류채윤<br>타겟 기업 : 카카오<br>면접 분류 : 직무</p>
        <br>
        <button class="btn btn-primary">면접 바로가기</button>
    </div>
    <div id="upcoming-schedule">
        <p>다가오는 일정</p>
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
#next-schedule {
    position: relative;
    border: 1px solid black;
    margin-top: 3%;
    padding-top: 1.3vw;
    padding-bottom: 1.5vw;
    width: 25vw;
    height: 22vw;
    left: 50%;
    transform: translate(-50%);
}

#upcoming-schedule {
    position: relative;
    border: 1px solid black;
    padding-top: 1.5vw;
    padding-bottom: 1.5vw;
    margin-top: 5%;
    width: 25vw;
    left: 50%;
    transform: translate(-50%);
    text-align: start;
    text-indent: 1em;
    line-height: 1em;
}
</style>
