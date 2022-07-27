export default {
  namespace: true,
  state: {
    selectedDate: false,
  },
  getters: {
    selectedDate: (state) => state.selectedDate,
  },
  mutations: {
    SET_DATE(state, date) {
      if (state.selectedDate !== date) {
        state.selectedDate = date;
      } else {
        state.selectedDate = false;
      }
    },
  },
  actions: {
    pickDate({ commit }, date) {
      commit('SET_DATE', date);
    },
  },
  modules: {
  },
};
