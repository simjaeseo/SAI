import axios from 'axios';
import drf from '@/api/api';
// import router from '@/router/index';

export default {
  state: {
    questionList: {},
    selectedQuestionList: [],
    selectedQuestionListTTS: [],
    videoDetail: [],
    customQuestionList: [],
  },
  getters: {
    questionList: (state) => state.questionList,
    selectedQuestionList: (state) => state.selectedQuestionList,
    selectedQuestionListTTS: (state) => state.selectedQuestionListTTS,
    videoDetail: (state) => state.videoDetail,
    customQuestionList: (state) => state.customQuestionList,
  },
  mutations: {
    SET_QUESTION_LIST(state, questionList) {
      state.questionList = questionList;
    },
    SET_SELECTED_QUESTION_LIST(state, questionList) {
      state.selectedQuestionList = questionList;
    },
    SET_SELECTED_QUESTION_LIST_TTS(state, questionListTTS) {
      state.selectedQuestionListTTS = questionListTTS;
    },
    SET_VIDEO_DEATIL(state, videoDetail) {
      state.videoDetail = videoDetail;
    },
    SET_CUSTOM_QUESTION_LIST(state, customQuestionList) {
      state.customQuestionList = customQuestionList;
    },
  },
  actions: {
    fetchQuestionList({ getters, commit }, params) {
      axios({
        url: drf.interview.questionList(params[0], params[1]),
        method: 'get',
        header: getters.authHeader,
      })
        .then((res) => {
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
    fetchCustomQuestionList({ commit, getters }) {
      axios({
        // url: drf.interview.customQuestionList(getters.currentUser.id),
        url: drf.interview.customQuestionList(getters.currentUser.id),
        method: 'get',
        // data: {
        //   memberId: getters.currentUser.id,
        // },
        header: getters.authHeader,
      })
        .then((res) => {
          console.log(res.data);
          commit('SET_CUSTOM_QUESTION_LIST', res.data.data);
        });
    },
  },
};
