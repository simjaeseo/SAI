import drf from '@/api/api';
import axios from 'axios';

export default {
  namespace: true,
  state: {
    selectedDate: false,
    startTime: false,
    endTime: false,
    schedules: [],
    upcomingSchedules: [],
  },
  getters: {
    selectedDate: (state) => state.selectedDate,
    selectedStartTime: (state) => state.startTime,
    selectedEndTime: (state) => state.endTime,
    schedules: (state) => state.schedules,
    upcomingSchedules: (state) => state.upcomingSchedules,
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
    SET_SCHEDULES(state, schedules) {
      state.schedules = schedules;
    },
    SET_UPCOMING_SCHEDULES(state, upcomingSchedules) {
      state.upcomingSchedules = upcomingSchedules;
    },
  },
  actions: {
    pickDate({ commit }, date) {
      commit('SET_DATE', date);
    },
    pickTime({ commit }, startTime) {
      commit('SET_START_TIME', startTime);
    },
    fetchSchedules({ commit, getters }, id) {
      axios({
        url: drf.schedule.schedule(id),
        method: 'get',
        headers: getters.authHeader,
      })
        .then((res) => commit('SET_ARTICLES', res.data))
        .catch((err) => console.error(err.response));
    },
    fetchUpcomingSchedules({ commit, getters }, date) {
      axios({
        url: drf.schedule.upcomingSchedule(date),
        method: 'get',
        headers: getters.authHeader,
      })
        .then((res) => commit('SET_UPCOMING_ARTICLES', res.data))
        .catch((err) => console.error(err.response));
    },
  },
  modules: {
  },
};
