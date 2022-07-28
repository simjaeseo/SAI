export default {
  namespace: true,
  state: {
    selectedDate: false,
    startTime: false,
    endTime: false,
  },
  getters: {
    selectedDate: (state) => state.selectedDate,
    selectedStartTime: (state) => state.selectedStartTime,
    selectedEndTime: (state) => state.selectedEndTime,
  },
  mutations: {
    SET_DATE(state, date) {
      if (state.selectedDate !== date) {
        state.selectedDate = date;
      } else {
        state.selectedDate = false;
      }
    },
    SET_START_TIME(state, startTime)
  },
  actions: {
    pickDate({ commit }, date) {
      commit('SET_DATE', date);
    },
  },
  modules: {
  },
};
