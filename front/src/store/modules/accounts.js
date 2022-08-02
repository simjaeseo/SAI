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
  },
  mutations: {
    SET_TOKEN: (state, token) => state.toekn === token,
    SET_CURRENT_USER: (state, user) => state.currentUser === user,
  },
  actions: {
    login({ dispatch }, credential) {
      axios({
        url: drf.member.login(),
        method: 'post',
        data: credential,
      })
        .then((res) => {
          const token = res.data.accessToken;
          console.log(res);
          dispatch('saveToken', token);
          dispatch('fetchCurrentUser', credential.id);
          router.push({ name: 'Main' });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    saveToken({ commit }, token) {
      commit('SET_TOKEN', token);
      localStorage.setItem('token', token);
    },
    signup(credentials) {
      console.log('엑시오스전');
      axios({
        url: drf.member.studentSignup(),
        method: 'post',
        data: credentials,
      })
        .then((res) => {
          console.log(res.data);
          alert('회원가입이 완료되었습니다.');
          router.push({ name: 'Login' });
        })
        .catch((err) => {
          console.log(err);
          console.log(err.response.data);
          alert('에러가 발생했습니다.');
        });
    },
    fetchCurrentUser({ getters }, userid) {
      if (getters.isLoggenIn) {
        axios({
          url: drf.member.currentUserInfo(),
          method: 'get',
          header: getters.authHeader,
          params: {
            id: userid,
          },
        })
          .then((r) => console.log(r))
          .catch((err) => {
            console.log(err);
            console.log('오류발생');
          });
      }
    },
  },
  modules: {},
};
