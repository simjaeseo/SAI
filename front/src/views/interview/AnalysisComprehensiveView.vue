<template>
  <div class="container">
    <h1>ㅇㅇㅇ님의 분석 결과</h1>
    <div class="d-flex">
        <div>종합 분석</div>
        <div>세부 분석</div>
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
    height: 2000px;
}
.box {
    border: 1px solid black;
    border-radius: 15px;
    height: 1400px;
    padding: 16px;
}

h1 {
    text-align: left;
}

p {
    text-align: left;
}

</style>
