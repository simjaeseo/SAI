<template>
  <div class="container">
    <div class="d-flex">
      <div class="box1">면접 유형 선택</div>
      <div class="box2">연습할 질문 선택</div>
    </div>
    <div class="d-flex">
      <div class="box3">
        <button class="question-select-btn" data-bs-toggle="button" autocomplete="off">
          인성 면접 질문</button>
        <div class="dropdown">
          <button class="question-select-btn dropdown-toggle" type="button"
          id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
            직무 면접 질문
          </button>
          <ul class="dropdown-menu duties-select" aria-labelledby="dropdownMenuButton1">
            <li><a class="dropdown-item" href="#">Frontend</a></li>
            <li><a class="dropdown-item" href="#">Backend</a></li>
            <li><a class="dropdown-item" href="#">Android/iOS</a></li>
            <li><a class="dropdown-item" href="#">Data Scientist</a></li>
            <li><a class="dropdown-item" href="#">DevOps</a></li>
          </ul>
        </div>
        <button class="question-select-btn" data-bs-toggle="button" autocomplete="off">
          내가 만든 질문</button>
      </div>
      <div class="box4">
        <div class="question">
          <div>-개의 질문</div>
          <button class="question-btn" v-for="(duty,i) in duties" :key="i">{{ duty }}</button>
        </div>
      </div>
    </div>
    <div class="d-flex">
      <div class="box5">
      </div>
      <div class="box6">
        <div class="d-flex justify-content-between">
          <div class="d-flex align-items-center">
            <div class="form-check check">
              <label class="form-check-label" for="randomChecked">
                <input class="form-check-input" type="checkbox" value=""
              id="randomChecked" checked>
                질문 랜덤 기능
              </label>
              <label class="form-check-label" for="shuffleChecked">
                <input class="form-check-input" type="checkbox" value=""
              id="shuffleChecked" checked>
                질문 셔플 기능
              </label>
            </div>
          </div>
          <div class="d-flex align-items-center">
            <div>선택된 질문 -개</div>
            <button class="start-btn">시작하기</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      duties: ['인성1', '인성2'],
    };
  },
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
  overflow: auto;
  border-radius: 20px;
  border: 0.1px solid black;
  padding: 12px;
  width: 990px;
  max-height: 518px;
  display: flex;
  flex-direction: column;
  box-shadow: 2.4px 4.8px 4.8px hsl(0deg 0% 0% / 0.43);
}
.box5 {
  width: 366px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.box6 {
  overflow: auto;
  border-radius: 20px;
  width: 1000px;
  max-height: 100px;
  display: flex;
  flex-direction: column;
}
.question {
  display: flex;
  flex-direction: column;
  height: 650px;
}
.question-select-btn{
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
  width: 300px;
  height: 50px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
.question-btn{
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
  padding: 0.5rem;
  height: 48px;
  width: 960px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
label {
  width: 150px;
}

.duties-select {
  z-index: 2000;
  width: 300px;
  text-align: center;
}
.start-btn{
  margin: 20px;
  margin-right: 12px;
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
  width: 200px;
  height: 60px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
.btn-outline-dark {
  width: 900px;
}
</style>
