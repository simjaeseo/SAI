import axios from 'axios';
import drf from '@/api/api';
// import router from '@/router/index';

export default {
  state: {
    questionList: {},
    selectedQuestionList: [],
    videoDetail: [],
  },
  getters: {
    questionList: (state) => state.questionList,
    selectedQuestionList: (state) => state.selectedQuestionList,
    videoDetail: (state) => state.videoDetail,
  },
  mutations: {
    SET_QUESTION_LIST(state, questionList) {
      state.questionList = questionList;
    },
    SET_SELECTED_QUESTION_LIST(state, questionList) {
      state.selectedQuestionList = questionList;
    },
    SET_VIDEO_DEATIL(state, videoDetail) {
      state.videoDetail = videoDetail;
    },
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
          console.log(res.data);
          commit('SET_QUESTION_LIST', res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getVideoDetail({ commit }, params) {
      axios({
        url: drf.interview.getVideoDetail(params[0], params[1]),
        method: 'get',
      })
        .then((res) => {
          console.log('getVideoDetail');
          console.log(res.data.data);
          commit('SET_VIDEO_DEATIL', res.data.data);
        });
    },
  },
};
