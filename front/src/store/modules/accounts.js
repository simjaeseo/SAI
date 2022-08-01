import drf from '@/api/api';
import axios from 'axios';

export default {
  state: {
    token: localStorage.getItem('token') || '',
  },
  getters: {

  },
  mutations: {
    SET_TOKEN: (state, token) => state.toekn === token,
  },
  actions: {
    saveToken({ commit }, token) {
      commit('SET_TOKEN', token);
      localStorage.setItem('token', token);
    },
    signup({ dispatch }, credentials) {
      // const newForm = {
      //   userSelectedCompanies: credentials.userSelectedCompanies,
      //   userSelectedDuties: credentials.userSelectedDuties,
      //   userBirth: credentials.userBirth,
      //   userPhoneNumber: credentials.userPhoneNumber,
      //   userEmail: credentials.userEmail,
      //   userPassword1: credentials.userPassword1,
      //   userPassword2: credentials.userPassword2,
      //   username: credentials.username,
      //   selectedUserCardinalNumber: credentials.selectedUserCardinalNumber,
      //   selectedUserRegion: credentials.selectedUserRegion,
      //   selectedUserClass: credentials.selectedUserClass,
      // };
      console.log('엑시오스전');
      axios({
        url: drf.member.studentSignup(),
        method: 'post',
        data: credentials,
      })
        .then((res) => {
          console.log(res.data);
          const token = res.data.accessToken;
          dispatch('saveToken', token);
          alert('회원가입이 완료되었습니다.');
        })
        .catch((err) => {
          console.log(err);
          alert('에러가 발생했습니다.');
        });
    },
  },
  modules: {},
};
