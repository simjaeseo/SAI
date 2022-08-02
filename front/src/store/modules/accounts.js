import drf from '@/api/api';
import axios from 'axios';
import router from '@/router/index';

export default {
  state: {
    token: localStorage.getItem('token') || '',
    currentUser: {},
  },
  getters: {
    isLoggenIn: (state) => !!state.token,
    currentUser: (state) => state.currentUser,
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.token = token;
    },
    SET_CURRENT_USER(state, user) {
      state.currentUser = user;
    },
  },
  actions: {
    login({ dispatch }, credential) {
      axios({
        url: drf.member.login(),
        method: 'post',
        data: credential,
      })
        .then((res) => {
          dispatch('fetchCurrentUser', res.data.id);
          router.push({ name: 'Main' });
        })
        .catch(() => {
          alert('아이디와 비밀번호를 확인해주세요.');
        });
    },
    saveToken({ commit }, token) {
      commit('SET_TOKEN', token);
      localStorage.setItem('token', token);
    },
    signup({ dispatch }, credentials) {
      axios({
        url: drf.member.studentSignup(),
        method: 'post',
        data: credentials,
      })
        .then((res) => {
          const token = res.data.accessToken;
          dispatch('saveToken', token);
          alert('회원가입이 완료되었습니다.');
          router.push({ name: 'Login' });
        })
        .catch((err) => {
          console.log(err);
          alert('에러가 발생했습니다.');
        });
    },
    fetchCurrentUser({ getters, commit }, userid) {
      console.log(userid);
      if (getters.isLoggenIn) {
        console.log(userid);
        axios({
          url: drf.member.currentUserInfo(userid),
          method: 'get',
          header: getters.authHeader,
        })
          .then((res) => commit('SET_CURRENT_USER', res.data.data))
          .catch((err) => {
            console.log(err);
            console.log('오류발생');
          });
      }
    },
  },
  modules: {},
};
