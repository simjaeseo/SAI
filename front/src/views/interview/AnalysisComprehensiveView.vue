<template>
  <div class="container">
    <h1>ㅇㅇㅇ님의 분석 결과</h1>
    <div class="d-flex align-items-center bar">
        <router-link to="/analysis/comprehensive">
          <div id="comprehensive">종합 분석</div>
        </router-link>
        <router-link to="/analysis/detail">
          <div id="detail">세부 분석</div>
        </router-link>
    </div>
    <div class="box">
        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum
            has been the industry's standard dummy text ever since the 1500s, when an unknown
            printer took a galley of type and scrambled it to make a type specimen book.
             It has survived not only five centuries, but also the leap into electronic typesetting,
             remaining essentially unchanged. It was popularised
             in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and
             more recently with desktop publishing software like Aldus PageMaker including versions
             of Lorem Ipsum.</p>
        <div class="d-flex justify-content-evenly">
            <div>
                합격 가능성
            </div>
                <div class="d-flex flex-column">
                <h1>면접 준비 우수</h1>
                <h1>상위 %</h1>
            </div>
        </div>
        <hr>
        <div id="alluser">
            <div>{{ alluser }}명중 {[ }}위입니다.</div>
            <div>전체 사용자 평균 횟수 : {{}}회</div>
            <div>1인 최다 연습 횟수 : {{}}회</div>
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
    margin-top: 50px;
    padding: 0;
    width: 1000px;
    height: 1500px;
}

#comprehensive {
  border-bottom: 3px solid #5c6ac4;
  padding-top: 19px;
  padding-bottom: 17px;
  padding-left: 2px;
  padding-right: 2px;
  color: #5c6ac4;
}

#detail {
  padding-left: 2px;
  padding-right: 2px;
}
.bar{
  z-index: 1000;
  /* background-color: #5c6ac4; */
  color: white;
  display: inline-block;
  font-weight: 400;
  line-height: 20px;
  text-decoration: none;
  vertical-align: middle;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
  border-top: 0.1px solid lightgrey;
  border-bottom: 0.1px solid lightgrey;
  font-size: 20px;
  opacity: 0.8;
  width: 1000px;
  height: 60px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

.box {
    border: 1px solid transparent;
    border-radius: 15px;
    height: 1400px;
}

h1 {
    text-align: left;
}

p {
    text-align: left;
    padding: 1rem;
}

a {
    text-decoration: none;
    color: black;
    margin-left: 1rem;
}
#alluser {
    display: flex;
    flex-direction: column;
    align-items: center;
}

</style>
