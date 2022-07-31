import accounts from '@/store/modules/accounts';
import interview from '@/store/modules/interview';
import schedule from '@/store/modules/schedule';
import { createStore } from 'vuex';

export default createStore({
  modules: {
    accounts,
    interview,
    schedule,
  },
});
