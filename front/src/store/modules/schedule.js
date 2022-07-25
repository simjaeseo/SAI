export default {
  state: {
    selectedDate: null,
  },
  getters: {
    dateToggle: (state) => state.selectedDate,
  },
  mutations: {
    SET_DATE: (state, selectedDate) => selectedDate,
  },
  actions: {
  },
  modules: {
  },
};
