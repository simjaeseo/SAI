import drf from '@/api/api';
import axios from 'axios';
import router from '@/router/index';

export default {
  state: {
    token: localStorage.getItem('token') || '',
    currentUser: {},
    userEnter: {},
    userJob: {},
    setNewEnter: {},
    setJob: {},
  },
  getters: {
    isLoggedIn: (state) => !!state.token,
    currentUser: (state) => state.currentUser,
    userEnter: (state) => state.userEnter,
    userJob: (state) => state.userJob,
    setNewEnter: (state) => state.setNewEnter,
    setJob: (state) => state.setJob,
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.token = token;
    },
    SET_CURRENT_USER(state, user) {
      state.currentUser = user;
      state.userEnter = user.interestedEnterprises;
      state.userJob = user.interestedJobs;
    },
    REMOVE_CURRENT_USER(state) {
      state.currentUser = {};
    },
    SET_NEW_ENTER(state, enter) {
      state.setNewEnter = enter;
    },
    SET_NEW_JOB(state, job) {
      state.setJob = job;
    },
    CHANGE_USER_JOB(state, update) {
      state.userJob = update;
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
          dispatch('fetchCurrentUser', res.data.id);
          router.push({ name: 'Main' });
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
    fetchCurrentUser({ getters, commit }, userid) {
      if (getters.isLoggedIn) {
        axios({
          url: drf.member.currentUserInfo(userid),
          method: 'get',
          header: getters.authHeader,
        })
          .then((res) => commit('SET_CURRENT_USER', res.data.data))
          .catch(() => {
            alert('오류발생');
          });
      }
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
        .catch(() => {
          alert('오류발생');
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
    userUpdate({ getters }, credentials) {
      const userId = getters.currentUser.id;
      axios({
        url: drf.member.updateProfile(userId),
        method: 'put',
        data: credentials,
      })
        .then(() => console.log('성공'))
        .catch((err) => console.log(err));
    },
    updateJob({ commit }, data) {
      commit('CHANGE_USER_JOB', data);
    },
  },
  modules: {},
};
