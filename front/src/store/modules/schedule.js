import drf from '@/api/api';
import router from '@/router';
import axios from 'axios';

export default {
  namespace: true,
  state: {
    selectedDate: null,
    endTime: null,
    schedules: [],
    upcomingSchedules: [],
    myConsultants: [],
    CTDaySchedules: [],
    daySchedules: [],
  },
  getters: {
    selectedDate: (state) => state.selectedDate,
    selectedEndTime: (state) => state.endTime,
    schedules: (state) => state.schedules,
    upcomingSchedules: (state) => state.upcomingSchedules,
    isUpcomingSchedules: (state) => state.upcomingSchedules.length,
    myConsultants: (state) => state.myConsultants,
    CTDaySchedules: (state) => state.CTDaySchedules,
    daySchedules: (state) => state.daySchedules,
  },
  mutations: {
    SET_DATE(state, date) {
      if (state.selectedDate !== date) {
        state.selectedDate = date;
      } else {
        state.selectedDate = false;
      }
    },
    // SET_START_TIME(state, startTime) {
    //   if (state.startTime !== startTime) {
    //     state.startTime = startTime;
    //     if (startTime.includes('30')) {
    //       state.endTime = `${Number(startTime.slice(0, -3)) + 1}:00`;
    //     } else {
    //       state.endTime = `${startTime.slice(0, -3)}:30`;
    //     }
    //   } else {
    //     state.startTime = false;
    //     state.endTime = false;
    //   }
    // },
    SET_SCHEDULES(state, schedules) {
      state.schedules = schedules;
    },
    SET_UPCOMING_SCHEDULES(state, upcomingSchedules) {
      state.upcomingSchedules = upcomingSchedules;
      console.log(state.upcomingSchedules);
    },
    SET_MY_CONSULTANTS(state, myConsultants) {
      state.myConsultants = myConsultants;
    },
    SET_CT_DAY_SCHEDULES(state, CTDaySchedules) {
      state.CTDaySchedules = [];
      for (let i = 0; i < CTDaySchedules.length; i += 1) {
        state.CTDaySchedules.push(CTDaySchedules[i].startTime);
      }
    },
    SET_DAY_SCHEDULES(state, daySchedules) {
      state.daySchedules = [];
      for (let i = 0; i < daySchedules.length; i += 1) {
        state.daySchedules.push(daySchedules[i].startTime);
      }
    },
    RESET_SELECTED_DATE(state) {
      state.selectedDate = null;
    },
    RESET_END_TIME(state) {
      state.endTime = null;
    },
  },
  actions: {
    pickDate({ commit, dispatch }, date) {
      commit('SET_DATE', date);
      dispatch('fetchDaySchedules', date);
    },
    pickTime({ commit }, startTime) {
      commit('SET_START_TIME', startTime);
    },
    fetchSchedules({ commit, getters }) {
      axios({
        url: drf.schedule.schedule(getters.currentUser.id),
        method: 'get',
        headers: getters.authHeader,
      })
        .then((res) => commit('SET_SCHEDULES', res.data.data));
      // .catch((err) => console.error(err));
    },
    fetchUpcomingSchedules({ commit, getters }) {
      axios({
        url: drf.schedule.upcomingSchedule(getters.currentUser.id),
        method: 'get',
        headers: getters.authHeader,
      })
        .then((res) => console.log(res.data))
        .then((res) => commit('SET_UPCOMING_SCHEDULES', res.data.data));
      // .catch((err) => console.error(err.response));
    },
    fetchMyConsultants({ commit, getters }) {
      axios({
        url: drf.schedule.myConsultant(getters.currentUser.id),
        method: 'get',
        headers: getters.authHeader,
      })
        .then((res) => commit('SET_MY_CONSULTANTS', res.data.data));
      // .catch((err) => console.error(err.response));
    },
    createSchedule({ dispatch, getters }, Credential) {
      if (getters.selectedDate
        && Credential.startTime
        && Credential.selectedConsultant !== null
        && Credential.scheduleDetail
        && Credential.selectedCategory) {
        axios({
          url: drf.schedule.schedule(getters.currentUser.id),
          method: 'post',
          data: {
            scheduleDate: getters.selectedDate,
            startTime: Credential.startTime,
            endTime: getters.selectedEndTime,
            category: Credential.selectedCategory,
            detail: Credential.scheduleDetail,
            memberStudentId: getters.currentUser.id,
            memberConsultantId: Credential.selectedConsultant,
          },
          headers: getters.authHeader,
        })
          .then(() => {
            dispatch('fetchSchedules');
            router.push({ name: 'Schedule' });
          })
          .catch((err) => console.log(err));
      } else {
        alert('빠짐없이 입력해주세요');
      }
    },
    createScheduleCT({ dispatch, getters, commit }) {
      if (getters.selectedDate
        && getters.selectedStartTime
        && getters.selectedCategory
        && getters.scheduleDetail) {
        axios({
          url: drf.schedule.schedule(getters.currentUser.id),
          method: 'post',
          data: {
            scheduleDate: getters.selectedDate,
            startTime: getters.selectedStartTime,
            endTime: getters.selectedEndTime,
            category: getters.selectedCategory,
            detail: getters.scheduleDetail,
            memberConsultantId: getters.currentUser.id,
          },
          headers: getters.authHeader,
        })
          .then(() => {
            dispatch('fetchSchedules');
            router.push({ name: 'ScheduleCT' });
          })
          .catch((err) => console.log(err));
      } else {
        alert('빠짐없이 입력해주세요');
      }
      commit('RESET_SELECTED_DATE');
      commit('RESET_SELECTED_CONSULTANT');
      commit('RESET_START_TIME');
      commit('RESET_END_TIME');
      commit('RESET_SELECTED_CATEGORY');
      commit('RESET_SCHEDULE_DETAIL');
    },
    deleteSchedule({ dispatch, getters }, scheduleId) {
      axios({
        url: drf.schedule.scheduleDelete(getters.currentUser.id, scheduleId),
        method: 'delete',
        headers: getters.authHeader,
      })
        .then(() => {
          dispatch('fetchSchedules');
          router.push({ name: 'Schedule' });
        });
    },
    fetchCTDaySchedules({ commit, getters }, data) {
      axios({
        url: drf.schedule.daySchedule(data.consultant, data.date),
        method: 'get',
        headers: getters.authHeader,
      })
        .then((res) => commit('SET_CT_DAY_SCHEDULES', res.data.data));
      // .catch((err) => console.error(err));
    },
    fetchDaySchedules({ commit, getters }, date) {
      axios({
        url: drf.schedule.daySchedule(getters.currentUser.id, date),
        method: 'get',
        headers: getters.authHeader,
      })
        .then((res) => commit('SET_DAY_SCHEDULES', res.data.data));
      // .catch((err) => console.error(err));
    },
  },
  modules: {
  },
};
