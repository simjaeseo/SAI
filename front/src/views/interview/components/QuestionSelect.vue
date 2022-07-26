<template>
  <div class="container">
    <div class="d-flex">
      <div class="box1">면접 유형 선택</div>
      <div class="box2">연습할 질문 선택</div>
    </div>
    <div class="d-flex">
      <div class="box3">
        <button type="button" class="btn btn-primary" data-bs-toggle="button" autocomplete="off">
          인성 면접 질문</button>
        <button type="button" class="btn btn-primary" data-bs-toggle="button" autocomplete="off">
          직무 면접 질문</button>
        <button type="button" class="btn btn-primary" data-bs-toggle="button" autocomplete="off">
          내가 만든 질문</button>
      </div>
      <div class="box4">
        <div class="question">
          <div>-개의 질문</div>
          <button type="button" class="btn btn-outline-dark">질문을 선택해주세요.</button>
          <button type="button" class="btn btn-outline-dark">질문을 선택해주세요.</button>
          <button type="button" class="btn btn-outline-dark">질문을 선택해주세요.</button>
          <button type="button" class="btn btn-outline-dark">질문을 선택해주세요.</button>
          <button type="button" class="btn btn-outline-dark">질문을 선택해주세요.</button>
          <button type="button" class="btn btn-outline-dark">질문을 선택해주세요.</button>
        </div>
        <div class="d-flex justify-content-between">
          <div class="d-flex align-items-center">
            <div class="form-check">
              <label class="form-check-label" for="flexCheckChecked">
                <input class="form-check-input" type="checkbox" value=""
              id="flexCheckChecked" checked>
                질문 랜덤 기능
              </label>
            </div>
          </div>
          <div class="d-flex align-items-center">
            <div>선택된 질문 -개</div>
            <button class="btn btn-primary">시작하기</button>
          </div>
        </div>
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
  padding: 0;
  width: 1366px;
  height: 768px;
  border: 1px solid black;
  border-radius: 20px;
}

.box1 {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 366px;
  height: 150px;
  font-size: 36px;
}
.box2 {
  display: flex;
  align-items: center;
  width: 1000px;
  height: 150px;
  font-size: 48px;
}
.box3 {
  width: 366px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.box4 {
  border-radius: 20px;
  width: 1000px;
  height: 618px;
  display: flex;
  flex-direction: column;
}
.question {
  display: flex;
  flex-direction: column;
  height: 650px;
}
.btn {
  margin: 12px;
  font-size: 24px;
}

.btn-outline-dark {
  width: 900px;
}
</style>
