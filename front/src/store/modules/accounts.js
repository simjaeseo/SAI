import drf from '@/api/api';
import axios from 'axios';
import router from '@/router/index';

export default {
  state: {
    token: localStorage.getItem('token') || '',
    currentUser: {},
  },
  getters: {
    isLoggedIn: (state) => !!state.token,
    currentUser: (state) => state.currentUser,
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.token = token;
    },
    SET_CURRENT_USER(state, user) {
      state.currentUser = user;
    },
    REMOVE_CURRENT_USER(state) {
      state.currentUser = {};
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
      axios({
        url: drf.member.studentSignup(),
        method: 'post',
        data: credentials,
      })
        .then((res) => {
          const token = res.data.key;
          dispatch('saveToken', token);
          alert('회원가입이 완료되었습니다.');
          router.push({ name: 'Login' });
        })
        .catch((err) => {
          alert('에러가 발생했습니다.');
          console.log(err);
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
            console.log(err);
          });
      }
    },
    logout({ getters, dispatch, commit }) {
      console.log('일단오긴왔음?0');
      axios({
        url: drf.member.logout(),
        method: 'post',
        headers: getters.authHeader,
      })
        .then(() => {
          console.log('요청왔니?');
          dispatch('removeToken');
          commit('REMOVE_CURRENT_USER');
          alert('로그아웃 되었습니다.');
          router.push({ name: 'Login' });
        })
        .catch((err) => {
          console.log('요청안왔다!');
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
          console.log('에러발생');
          console.log(err.response.data);
        });
    },
  },
  modules: {},
};
