import accounts from '@/store/modules/accounts';
import interview from '@/store/modules/interview';
import schedule from '@/store/modules/schedule';
import { createStore } from 'vuex';
import createPersistedState from 'vuex-persistedstate';

const persistedstate = createPersistedState({
  paths: ['token', 'id', 'name', 'email', 'birthday', 'role', 'phone', 'memberStatus'],
});

export default createStore({
  modules: {
    accounts,
    interview,
    schedule,
  },
  plugins: [persistedstate],
});
