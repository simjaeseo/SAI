<template>
  <div class="container">
    <div class="d-flex">
      <div class="box1">면접 유형 선택</div>
      <div class="box2">연습할 질문 선택</div>
    </div>
    <div class="d-flex">
      <div class="box3">
        <button @click="selected=tenacity" class="question-select-btn"
        data-bs-toggle="button" autocomplete="off">인성 면접 질문</button>
        <div class="dropdown">
          <button class="question-select-btn dropdown-toggle" type="button"
          id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
            직무 면접 질문
          </button>
          <ul class="dropdown-menu duties-select" aria-labelledby="dropdownMenuButton1">
            <li @click="selected=fequestions" @keydown.enter="selected=fequestions">
              <a class="dropdown-item" href="#">Frontend</a></li>
            <li @click="selected=bequestions" @keydown.enter="selected=bequestions">
              <a class="dropdown-item" href="#">Backend</a></li>
            <li @click="selected=androidquestions" @keydown.enter="selected=androidquestions">
              <a class="dropdown-item" href="#">Android/iOS</a></li>
            <li @click="selected=dataquestions" @keydown.enter="selected=dataquestions">
              <a class="dropdown-item" href="#">Data Scientist</a></li>
            <li @click="selected=devopsquestions" @keydown.enter="selected=devopsquestions">
              <a class="dropdown-item" href="#">DevOps</a></li>
          </ul>
        </div>
        <button @click="selected=myQuestion" class="question-select-btn"
        data-bs-toggle="button" autocomplete="off">내가 만든 질문</button>
      </div>
      <div class="box4">
        <div class="question" v-show="selected!=myQuestion">
          <div>{{ selected.length }}개의 질문</div>
          <button class="question-btn" data-bs-toggle="button" autocomplete="off"
          v-for="(category, i) in selected" :key="i"
          @click="selectQuestion(category)">
            {{ category.question }}</button>
          <label class="btn btn-outline-primary" for="btn-check-outlined">
            <input type="checkbox" class="btn-check" id="btn-check-outlined" autocomplete="off">
            Single toggle</label><br>
        </div>
        <!-- <div v-show="selected==myQuestion">
          <label for="myQuestion"><input type="text" class='form-control'></label>
          <button id="double-check-btn">등록</button>
        </div> -->
        <div class="inputBox shadow" v-show="selected==myQuestion">
          <label for="s">
            <input type="text" v-model="newQuestion" @keyup.enter="addTodo"></label>
          <span class="addContainer" @click="addTodo" @keyup.enter="addTodo">
            <i class="far fa-plus-square addBtn"></i>
          </span>
        </div>
        <p>{{ selectedQuestionList }}</p>
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
            <div>선택된 질문 {{ selectedQuestionList.length }}개</div>
            <router-link to='/interview/room' id='routerlink'>
              <button class="start-btn">시작하기</button>
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import tenacity from '@/data/tenacity.json';
import dataquestions from '@/data/dataquestions.json';
import bequestions from '@/data/bequestions.json';
import fequestions from '@/data/fequestions.json';
import androidquestions from '@/data/androidquestions.json';
import devopsquestions from '@/data/devopsquestions.json';

export default {
  data() {
    return {
      selected: tenacity,
      tenacity,
      fequestions,
      bequestions,
      dataquestions,
      androidquestions,
      devopsquestions,
      selectedQuestionList: [],
      myquestion: '',
      newQuestion: '',
    };
  },
  computed() {},
  methods: {
    selectQuestion(category) {
      const index = this.selectedQuestionList.indexOf(this.category, 0);
      if (index >= 0) {
        this.selectedQuestionList.splice(index, 1);
      } else {
        this.selectedQuestionList.push(category);
      }
    },
    addTodo() {
      console.log(this.newTodoItem);
      localStorage.setItem(this.newTodoItem, this.newTodoItem);
      this.clearInput();
    },
    clearInput() {
      this.newTodoItem = '';
    },
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
  height: 518px;
  /* display: flex; */
  /* flex-direction: column; */
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

@media (prefers-reduced-motion: reduce) {
  .question-btn {
    transition: none;
  }
}

.question-btn:hover {
  color: white;
  background-color: #5c6ac4;
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

.form-control {
  margin-bottom: 3px;
  width: 342px;
  height: 50px;
  font-size: 1rem;
  line-height: 7;
  color: #000000;
  background-clip: padding-box;
  border: 1px solid #ced4da;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  border-radius: 0.25rem;
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
.form-control:focus {
  color: #000000;
  background-color: rgb(255, 255, 255);
  border-color: #ffffff;
  outline: 0;
  box-shadow: 0 0 0 0.1rem #5c6ac496;
}
#double-check-btn{
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
  height: 50px;
  width: 90px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

input:focus {
  outline: none;
}
.inputBox {
  background: white;
  height: 50px;
  line-height: 50px;
  border-radius: 5px;
}
.inputBox input {
  border-style: none;
  font-size: 0.9rem;
}
.addContainer {
  float: right;
  background: linear-gradient(to right, #62EAC6, #32CEE6);
  display: block;
  width: 3rem;
  border-radius: 0 5px 5px 0;
}
.addBtn {
  color: white;
  vertical-align: middle;
}
.btn-outline-dark {
  width: 900px;
}
</style>
