import axios from 'axios';
import { uniq } from 'lodash';
import drf from '@/api/api';
// eslint-disable-next-line
import router from '@/router/index';

export default {
  state: {
    token: localStorage.getItem('token') || '',
    currentUser: {},
    // 원래회사
    userEnter: {},
    // 원래직무
    userJob: {},
    // 새로추가한회사
    setNewEnter: {},
    // 새로추가한직무
    setNewJob: {},
    profileImg: '',
    students: [],
    selectedStudent: [],
    feedBackList: [],
    userVideo: [],
  },
  getters: {
    authHeader: (state) => ({ Authorization: `Token ${state.token}` }),
    isLoggedIn: (state) => !!state.token,
    currentUser: (state) => state.currentUser,
    // 원래회사
    userEnter: (state) => uniq(state.userEnter),
    // 원래직무
    userJob: (state) => uniq(state.userJob),
    // 새로운회사
    setNewEnter: (state) => state.setNewEnter,
    // 새로운직무
    setNewJob: (state) => state.setNewJob,
    profileImg: (state) => state.profileImg,
    students: (state) => state.students,
    selectedStudent: (state) => state.selectedStudent,
    feedbackList: (state) => state.feedBackList,
    userVideo: (state) => state.userVideo,
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.token = token;
    },
    SET_CURRENT_USER(state, user) {
      state.currentUser = user;
      state.userJob = user.interestedJobs.map((item) => ({ name: item.jobName }));
      state.userEnter = user.interestedEnterprises.map((item) => ({ name: item.enterpriseName }));
    },
    REMOVE_CURRENT_USER(state) {
      state.currentUser = {};
    },
    SET_NEW_ENTER(state, enter) {
      for (let i = 0; i < enter.plusEnter.length; i += 1) {
        state.userEnter.push(...enter.plusEnter);
      }
    },
    SET_NEW_JOB(state, job) {
      for (let i = 0; i < job.plusJob.length; i += 1) {
        state.userJob.push(...job.plusJob);
      }
    },
    CHANGE_USER_JOB(state, update) {
      state.userJob = update;
    },
    CHANGE_USER_ENTER(state, update) {
      state.userEnter = update;
    },
    // COMBINE_USER_JOB(state) {
    //   state.userJob = state.user
    // },
    SET_USER_PROFILE_IMG(state, img) {
      state.profileImg = img;
    },
    SET_STUDENTS(state, student) {
      state.students = student;
    },
    SELECTED_STUDENTS(state, data) {
      state.selectedStudent = data;
    },
    RESET_SELECTED_STUDENTS(state) {
      state.selectedStudent = [];
    },
    SET_FEEDBACK_LIST(state, list) {
      state.feedBackList = list;
    },
    SET_USER_VIDEO(state, data) {
      state.userVideo = data;
    },
  },
  actions: {
    saveToken({ commit }, token) {
      commit('SET_TOKEN', token);
      localStorage.setItem('token', token);
    },
    removeToken({ commit }) {
      commit('SET_TOKEN', '');
      localStorage.setItem('token', '');
    },
    login({ dispatch }, credential) {
      axios({
        url: drf.member.login(),
        method: 'post',
        data: credential,
      })
        .then((res) => {
          const token = res.headers.accesstoken;
          dispatch('saveToken', token);
          dispatch('fetchCurrentUser', res.data.data.id).then(() => {
            router.push({ name: 'Main' });
          });
        })
        .catch(() => {
          alert('아이디와 비밀번호를 확인해주세요.');
        });
    },
    signup({ dispatch }, credentials) {
      axios({
        url: drf.member.studentSignup(),
        method: 'post',
        data: credentials,
      })
        .then((res) => {
          const token = res.data.key;
          dispatch('saveToken', token);
          alert('교육생님의 회원가입이 완료되었습니다. 로그인 페이지로 이동합니다.');
          router.push({ name: 'Login' });
        })
        .catch(() => {
          alert('올바르지 않은 요청입니다. 회원가입을 다시 시도하세요.');
        });
    },
    async fetchCurrentUser({ getters, commit, dispatch }, userid) {
      if (getters.isLoggedIn) {
        await axios({
          url: drf.member.currentUserInfo(userid),
          method: 'get',
          header: getters.authHeader,
        })
          .then((res) => {
            commit('SET_CURRENT_USER', res.data.data);
            dispatch('fetchMyConsultants');
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    getUserVideo({ commit }, userid) {
      axios({
        url: drf.interview.getUserVideo(userid),
        method: 'get',
      })
        .then((res) => {
          commit('SET_USER_VIDEO', res.data.data);
        });
    },
    logout({ getters, dispatch, commit }) {
      const userId = getters.currentUser.id;
      axios({
        url: drf.member.logout(),
        method: 'post',
        headers: getters.authHeader,
        data: userId,
      })
        .then(() => {
          dispatch('removeToken');
          commit('REMOVE_CURRENT_USER');
          commit('RESET_SCHEDULE_STATE');
          alert('로그아웃 되었습니다.');
          router.push({ name: 'Login' });
        })
        .catch((err) => {
          console.log(err.response);
        });
    },
    changePassword({ getters, dispatch }, credential) {
      axios({
        url: drf.member.updatePassword(),
        method: 'post',
        headers: getters.authHeader,
        data: credential,
      })
        .then(() => {
          alert('비밀번호가 변경되었습니다. 로그인창으로 이동합니다.');
          dispatch('logout');
        })
        .catch((err) => {
          console.log(err);
        });
    },
    signupformCT({ dispatch }, credentials) {
      axios({
        url: drf.member.ctSignup(),
        method: 'post',
        data: credentials,
      })
        .then((res) => {
          const token = res.data.key;
          dispatch('saveToken', token);
          alert('컨설턴트님의 회원가입이 완료되었습니다. 로그인 페이지로 이동합니다.');
          router.push({ name: 'Login' });
        })
        .catch(() => {
          alert('올바르지 않은 요청입니다. 회원가입을 다시 시도하세요.');
        });
    },
    newEnter({ commit }, enter) {
      commit('SET_NEW_ENTER', enter);
    },
    newJob({ commit }, enter) {
      commit('SET_NEW_JOB', enter);
    },
    updateJob({ commit }, data) {
      commit('CHANGE_USER_JOB', data);
    },
    updateEnter({ commit }, data) {
      commit('CHANGE_USER_ENTER', data);
    },
    userUpdate({ dispatch, getters }, credentials) {
      const userId = getters.currentUser.id;
      axios({
        url: drf.member.updateProfile(userId),
        method: 'put',
        data: credentials,
      })
        .then(() => {
          alert('수정되었습니다.');
          dispatch('fetchCurrentUser', userId);
          router.push({ name: 'Profile', params: { id: userId } });
        });
    },
    userUpdateCT({ dispatch, getters }, credentials) {
      const userId = getters.currentUser.id;
      axios({
        url: drf.member.updateProfileCT(userId),
        method: 'put',
        data: credentials,
      })
        .then(() => {
          alert('수정되었습니다.');
          dispatch('fetchCurrentUser', userId);
          router.push({ name: 'Main' });
        })
        .catch((err) => console.log(err));
    },
    findedStudents({ commit }, student) {
      commit('SET_STUDENTS', student);
    },
    selectedStudentData({ commit }, studentData) {
      commit('SELECTED_STUDENTS', studentData);
    },
  },
  modules: {},
};
