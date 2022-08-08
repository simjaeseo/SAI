import drf from '@/api/api';
import axios from 'axios';
// import router from '@/router/index';

export default {
  state: {
    questionList: {},
    // selectedQuestionList: [],
  },
  getters: {
    questionList: (state) => state.questionList,
    // selectedQuestionList: (state) => state.selectedQuestionList,
  },
  mutations: {
    SET_QUESTION_LIST(state, questionList) {
      state.questionList = questionList;
    },
    // SET_SELECTED_QUESTION_LIST(state, questionList) {
    //   state.selectedQuestionList = questionList;
    // },
  },
  actions: {
    fetchQuestionList({ getters, commit }, params) {
      console.log(params[0], params[1]);
      axios({
        url: drf.interview.questionList(params[0], params[1]),
        method: 'get',
        header: getters.authHeader,
      })
        .then((res) => {
          commit('SET_QUESTION_LIST', res.data);
          console.log(res);
          console.log('1234');
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
