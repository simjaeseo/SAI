import drf from '@/api/api';
import axios from 'axios';
import router from '@/router/index';
import _uniqBy from 'lodash/uniqBy';

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
  },
  getters: {
    authHeader: (state) => ({ Authorization: `Token ${state.token}` }),
    isLoggedIn: (state) => !!state.token,
    currentUser: (state) => state.currentUser,
    // 원래회사
    userEnter: (state) => state.userEnter,
    // 원래직무
    userJob: (state) => state.userJob,
    // 새로운회사
    setNewEnter: (state) => state.setNewEnter,
    // 새로운직무
    setNewJob: (state) => state.setNewJob,
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.token = token;
    },
    SET_CURRENT_USER(state, user) {
      state.currentUser = user;
      const arr = user.interestedEnterprises;
      const array1 = _uniqBy(arr, 'name');
      state.userEnter = array1;
      const arr2 = user.interestedJob;
      const array2 = _uniqBy(arr2, 'name');
      state.userJob = array2;
    },
    REMOVE_CURRENT_USER(state) {
      state.currentUser = {};
    },
    SET_NEW_ENTER(state, enter) {
      state.setNewEnter = enter;
    },
    SET_NEW_JOB(state, job) {
      state.setNewJob = job;
    },
    CHANGE_USER_JOB(state, update) {
      state.userJob = update;
    },
    CHANGE_USER_ENTER(state, update) {
      state.userEnter = update;
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
          dispatch('fetchCurrentUser', res.data.data.id);
          router.push({ name: 'Main' });
        })
        .catch(() => {
          alert('아이디와 비밀번호를 확인해주세요.');
        });
    },
    signup({ dispatch }, credentials) {
      console.log(credentials);
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
          .catch((err) => {
            console.log('에러베러');
            console.log(err);
            console.log('에러베러');
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
    updateJob({ commit }, data) {
      commit('CHANGE_USER_JOB', data);
    },
    updateEnter({ commit }, data) {
      commit('CHANGE_USER_ENTER', data);
    },
    userUpdate({ dispatch, getters }, credentials) {
      console.log(credentials);
      const userId = getters.currentUser.id;
      axios({
        url: drf.member.updateProfile(userId),
        method: 'put',
        data: credentials,
      })
        .then(() => {
          alert('수정되었습니다.');
          dispatch('fetchCurrentUser', userId);
          router.push({ name: 'Profile' });
        })
        .catch((err) => console.log(err));
    },
  },
  modules: {},
};
