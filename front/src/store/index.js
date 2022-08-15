import { createStore } from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import accounts from '@/store/modules/accounts';
import interview from '@/store/modules/interview';
import schedule from '@/store/modules/schedule';

export default createStore({
  modules: {
    accounts,
    interview,
    schedule,
  },
  plugins: [
    createPersistedState({
      paths: [
        'accounts',
        'interview',
        'schedule',
      ],
    }),
  ],
});
