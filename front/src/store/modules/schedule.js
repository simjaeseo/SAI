export default {
  namespace: true,
  state: {
    selectedDate: false,
    startTime: false,
    endTime: false,
  },
  getters: {
    selectedDate: (state) => state.selectedDate,
    selectedStartTime: (state) => state.startTime,
    selectedEndTime: (state) => state.endTime,
  },
  mutations: {
    SET_DATE(state, date) {
      if (state.selectedDate !== date) {
        state.selectedDate = date;
      } else {
        state.selectedDate = false;
      }
      console.log(state.selectedDate);
    },
    SET_START_TIME(state, startTime) {
      if (state.startTime !== startTime) {
        state.startTime = startTime;
        if (startTime.includes('30')) {
          state.endTime = `${Number(startTime.slice(0, -3)) + 1}:00`;
        } else {
          state.endTime = `${startTime.slice(0, -3)}:30`;
        }
      } else {
        state.startTime = false;
        state.endTime = false;
      }
    },
  },
  actions: {
    pickDate({ commit }, date) {
      commit('SET_DATE', date);
    },
    pickTime({ commit }, startTime) {
      commit('SET_START_TIME', startTime);
    },
  },
  modules: {
  },
};
