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
    videoUrl: '',
    stt: '',
    emotionRatio: 0,
    wrongPostureCount: 0,
    setVideos: null,
    videoArray: [],
    audioArray: [],
    isFeedBackCompleted: [],
    sttArray: [],
    teachableArray: [],
    qArray: [],
    emotionArray: [],
    feedback: [],
    order: 0,
    videoLink: null,
    audioLink: null,
    teachableSub: null,
    emotionSub: null,
    feedbackData: null,
    feedbackId: null,
  },
  getters: {
    questionList: (state) => state.questionList,
    selectedQuestionList: (state) => state.selectedQuestionList,
    selectedQuestionListTTS: (state) => state.selectedQuestionListTTS,
    videoDetail: (state) => state.videoDetail,
    customQuestionList: (state) => state.customQuestionList,
    videoUrl: (state) => state.videoUrl,
    stt: (state) => state.stt,
    emotionRatio: (state) => state.emotionRatio,
    wrongPostureCount: (state) => state.wrongPostureCount,
    setVideos: (state) => state.setVideos,
    videoArray: (state) => state.videoArray,
    audioArray: (state) => state.audioArray,
    isFeedBackCompleted: (state) => state.isFeedBackCompleted,
    sttArray: (state) => state.sttArray,
    teachableArray: (state) => state.teachableArray,
    qArray: (state) => state.qArray,
    emotionArray: (state) => state.emotionArray,
    feedback: (state) => state.feedback,
    order: (state) => state.order,
    videoLink: (state) => state.videoLink,
    audioLink: (state) => state.audioLink,
    teachableSub: (state) => state.teachableSub,
    emotionSub: (state) => state.emotionSub,
    feedbackData: (state) => state.feedbackData,
    feedbackId: (state) => state.feedbackId,
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
    SET_FEEDBACK_CREDENTIALS(state, credentials) {
      state.setVideos = credentials;
      credentials.forEach((element) => {
        state.videoArray.push(element.videoUrl);
        state.audioArray.push(element.audioUrl);
        state.isFeedBackCompleted.push(element.feedback);
        state.sttArray.push(element.stt);
        state.teachableArray.push(element.wrongPostureCount);
        state.qArray.push(element.usedInterviewQuestion);
        state.emotionArray.push(element.emotionRatio);
        state.teachableSub += element.wrongPostureCount;
        state.emotionSub += element.emotionRatio;
      });
    },
    RESET_FEEDBACK_CREDENTIALS(state) {
      state.videoUrl = '';
      state.stt = '';
      state.emotionRatio = 0;
      state.wrongPostureCount = 0;
      state.setVideos = null;
      state.videoArray = [];
      state.audioArray = [];
      state.isFeedBackCompleted = [];
      state.sttArray = [];
      state.teachableArray = [];
      state.qArray = [];
      state.emotionArray = [];
      state.feedback = [];
      state.order = 0;
      state.teachableSub = null;
      state.emotionSub = null;
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
      console.log('??');
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
          commit('SET_CUSTOM_QUESTION_LIST', res.data.data);
        });
    },
    fetchFeedbackCredentials({ commit, getters }, data) {
      axios({
        url: drf.interview.videoDetailPage(data[0], data[1]),
        method: 'get',
        header: getters.authHeader,
      })
        .then((res) => commit('SET_FEEDBACK_CREDENTIALS', res.data.data))
        .catch((err) => console.log(err));
    },
  },
};
