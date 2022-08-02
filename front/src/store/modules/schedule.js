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
    myConsultants: [],
    selectedConsultant: false,
    scheduleDetail: '',
    selectedCategory: false,
  },
  getters: {
    selectedDate: (state) => state.selectedDate,
    selectedStartTime: (state) => state.startTime,
    selectedEndTime: (state) => state.endTime,
    schedules: (state) => state.schedules,
    upcomingSchedules: (state) => state.upcomingSchedules,
    myConsultants: (state) => state.myConsultants,
    selectedConsultant: (state) => state.selectedConsultant,
    scheduleDetail: (state) => state.scheduleDetail,
    selectedCategory: (state) => state.selectedCategory,
  },
  mutations: {
    SET_DATE(state, date) {
      if (state.selectedDate !== date) {
        state.selectedDate = date;
      } else {
        state.selectedDate = false;
      }
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
    SET_MY_CONSULTANTS(state, myConsultants) {
      state.myConsultants = myConsultants;
    },
    SET_MY_CONSULTANT(state, consultant) {
      state.myConsultant = consultant;
    },
    SET_SCHEDULE_DETAIL(state, datail) {
      state.scheduleDetail = datail;
    },
    SET_SELECTED_CATEGORY(state, category) {
      state.selectedCategory = category;
    },
  },
  actions: {
    pickDate({ commit }, date) {
      commit('SET_DATE', date);
    },
    pickTime({ commit }, startTime) {
      commit('SET_START_TIME', startTime);
    },
    fetchSchedules({ commit, state, getters }) {
      axios({
        url: drf.schedule.schedule(state.currentUser.id),
        method: 'get',
        headers: getters.authHeader,
      })
        .then((res) => commit('SET_SCHEDULES', res.data))
        .catch((err) => console.error(err.response));
    },
    fetchUpcomingSchedules({ commit, getters }, date) {
      axios({
        url: drf.schedule.upcomingSchedule(date),
        method: 'get',
        headers: getters.authHeader,
      })
        .then((res) => commit('SET_UPCOMING_SCHEDULES', res.data))
        .catch((err) => console.error(err.response));
    },
    fetchMyConsultants({ commit, state, getters }) {
      axios({
        url: drf.schedule.myConsultants(state.currentUser.id),
        method: 'get',
        headers: getters.authHeader,
      })
        .then((res) => commit('SET_MY_CONSULTANTS', res.data))
        .catch((err) => console.error(err.response));
    },
    pickMyconsultant({ commit }, consultant) {
      commit('SET_START_TIME', consultant);
    },
    entryScheduleDetail({ commit }, detail) {
      commit('SET_SCHEDULE_DETAIL', detail.target.value);
    },
    selectCategory({ commit }, category) {
      commit('SET_SELECTED_CATEGORY', category);
    },
    createSchedule({ dispatch, state, getters }) {
      axios({
        url: drf.schedule.schedule(state.currentUser.id),
        method: 'post',
        data: {
          scheduleDate: getters.selectedDate,
          startTime: getters.selectedStartTime,
          endTime: getters.selectedEndTime,
          category: getters.selectCategory,
          detail: getters.scheduleDetail,
          memberStudentId: state.currentUser.id,
          memberConsultantId: getters.selectedConsultant,
        },
        headers: getters.authHeader,
      })
        .then(() => {
          dispatch(this.fetchSchedules);
        });
    },
    deleteSchedule({ dispatch, state, getters }, scheduleId) {
      axios({
        url: drf.schedule.scheduleDelete(state.currentUser.id, scheduleId),
        method: 'delete',
        headers: getters.authHeader,
      })
        .then(() => {
          dispatch(this.fetchSchedules);
          // 안되면 라우터푸시?
        });
    },
  },
  modules: {
  },
};
