import drf from '@/api/api';
import axios from 'axios';
// import router from '@/router/index';

export default {
  state: {
    questionList: {},
  },
  getters: {
    questionList: (state) => state.questionList,
  },
  mutations: {
    SET_QUESTION_LIST(state, questionList) {
      state.questionList = questionList;
    },
  },
  actions: {
    fetchQuestionList({ getters, commit }) {
      axios({
        url: drf.interview.questionList(),
        method: 'get',
        header: getters.authHeader,
      })
        .then((res) => commit('SET_QUESTION_LIST', res.data))
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
