<template>
  <div class="container" v-if="!session">
    <div>
      <div class="d-flex">
        <div class="box1">면접 유형 선택</div>
        <div class="box2">연습할 질문 선택</div>
      </div>
      <div class="d-flex">
        <div class="box3">
          <button @click="fetchQuestionList(['인성', '공통']), selected=''" class="question-select-btn"
          data-bs-toggle="button">인성 면접 질문</button>
          <div class="dropdown">
            <button class="question-select-btn dropdown-toggle" type="button"
            id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
              직무 면접 질문
            </button>
            <ul class="dropdown-menu duties-select" aria-labelledby="dropdownMenuButton1">
              <li @click="fetchQuestionList(['직무', 'frontend']), selected=''"
              @keydown.enter="s">
                <a class="dropdown-item" href="#">Frontend</a></li>
              <li @click="fetchQuestionList(['직무', 'backend']), selected=''"
              @keydown.enter="s">
                <a class="dropdown-item" href="#">Backend</a></li>
              <li @click="fetchQuestionList(['직무', 'Android/iOS']), selected=''"
              @keydown.enter="s">
                <a class="dropdown-item" href="#">Android/iOS</a></li>
              <li @click="fetchQuestionList(['직무', 'Data Scientist']), selected=''"
              @keydown.enter="s">
                <a class="dropdown-item" href="#">Data Scientist</a></li>
              <li @click="fetchQuestionList(['직무', 'DevOps']), selected=''"
              @keydown.enter="s">
                <a class="dropdown-item" href="#">DevOps</a></li>
            </ul>
          </div>
          <button @click="selected='myQuestion'" class="question-select-btn"
          data-bs-toggle="button" autocomplete="off">내가 만든 질문</button>
          <div v-if="selectedQuestionList.length" id="user-select-box">
            <h5>
              선택된 질문
            </h5>
            <p v-for="(pick, index) in selectedQuestionList" :key="index" id="text-select">
                #{{ pick }}</p>
          </div>
        </div>
        <div class="box4" v-show="selected==''">
          <div class="question">
            <div>{{ questionList.length }}개의 질문</div>
            <button class="question-btn" data-bs-toggle="button" autocomplete="off"
            v-for="(data, i) in questionList" :key="i"
            :style="[selectedQuestionList.includes(data.question) == '' ?
            {background:'#ffffff'} : {background:'#5c6ac4', color:'#ffffff'}]"
            @click="selectQuestion(data)">
              {{ data.question }}</button>
            <div v-if="selectedQuestionList">
              <p v-for="(pick, index) in selectedQuestionList" :key="index">
                {{ pick }}</p>
            </div>
          </div>
        </div>
        <div class="box4" v-show="selected=='myQuestion'">
          <div>
            <div class="d-flex">
              <input type="text" class='form-control'
              v-model="myQuestion"
              @keydown.enter="addQuestion()"
              aria-labelledby="myQuestion">
              <button id="double-check-btn" @click="addQuestion()">등록</button>
            </div>
            <div v-for="myQ in myQuestionList" :key="myQ">
              <button class="question-btn" data-bs-toggle="button" autocomplete="off"
              :style="[selectedQuestionList.includes(myQ) == '' ?
              {background:'#ffffff'} : {background:'#5c6ac4', color:'#ffffff'}]"
              @click="selectMyQuestion(myQ)">
                {{ myQ }}
              </button>
            </div>
          </div>
        </div>
      </div>
      <div class="d-flex">
        <div class="box5">
        </div>
        <div class="box6">
          <div class="d-flex justify-content-between">
            <div class="d-flex align-items-center">
              <!-- <div class="form-check check">
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
              </div> -->
            </div>
            <div class="d-flex align-items-center">
              <div>선택된 질문 {{ selectedQuestionList.length }}개</div>
              <router-link to="room">
                <button class="start-btn"
                @click="selectQuestionList(selectedQuestionList)">시작하기</button>
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useStore } from 'vuex';
import { computed } from 'vue';

export default {
  components: {
  },
  data() {
    return {
      selected: '',
      selectedQuestionList: [],
      myQuestion: '',
      myQuestionList: [],

      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      mySessionId: 'SessionA',
      myUserName: `Participant${Math.floor(Math.random() * 100)}`,
      isSelected: false,
    };
  },
  setup() {
    const store = useStore();

    const questionList = computed(() => store.getters.questionList);
    const fetchQuestionList = (params) => {
      store.dispatch('fetchQuestionList', params);
    };
    const selectQuestionList = (data) => store.commit('SET_SELECTED_QUESTION_LIST', data);

    return {
      fetchQuestionList,
      questionList,
      selectQuestionList,
    };
  },
  computed() {},
  methods: {
    selectQuestion(data) {
      const index = this.selectedQuestionList.indexOf(data.question, 0);
      if (index >= 0) {
        this.selectedQuestionList.splice(index, 1);
        this.isSelected = false;
      } else {
        this.selectedQuestionList.push(data.question);
        this.isSelected = true;
      }
      console.log(this.selectedQuestionList);
    },
    selectMyQuestion(data) {
      const index = this.selectedQuestionList.indexOf(data, 0);
      if (index >= 0) {
        this.selectedQuestionList.splice(index, 1);
      } else {
        this.selectedQuestionList.push(data);
      }
      console.log(this.selectedQuestionList);
    },
    addQuestion() {
      console.log(this.myQuestion);
      this.selectedQuestionList.push(this.myQuestion);
      this.myQuestionList.push(this.myQuestion);
      this.clearInput();
    },
    clearInput() {
      this.myQuestion = '';
    },
  },
};

</script>

<style scoped>
#q-list {
  width: 100%;
  color: #5c6ac4;
  border: 1px solid #5c6ac4;
  margin-bottom: 10px;
}
#q-list:hover {
  width: 100%;
  color: #ffffff;
  border: 1px solid #5c6ac4;
  background-color: #5c6ac4;
  margin-bottom: 10px;
}
#text-select {
  color: #4d4d4d;
}
#user-select-box {
  background: #5c6ac40c;
  width: 300px;
  max-height: 350px;
  border-radius: 10px;
  overflow: auto;
}
/* .container {
  max-width: 1600px;
  padding: 0;
  width: 1366px;
  height: 768px;
  border: 1px solid black;
  border-radius: 20px;
} */

.container2 {
  max-width: 2000px;
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
  height: 100px;
  font-size: 36px;
  font-weight: 500;
  color: #4d4d4d;
}
.box2 {
  display: flex;
  align-items: center;
  width: 1000px;
  height: 100px;
  font-size: 48px;
  font-weight: 900;
  color: #4d4d4d;
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
  width: 920px;
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
