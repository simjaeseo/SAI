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
    RESET_SCHEDULE_STATE(state) {
      state.selectedDate = null;
      state.endTime = null;
      state.schedules = [];
      state.upcomingSchedules = [];
      state.myConsultants = [];
      state.CTDaySchedules = [];
      state.daySchedules = [];
    },
    RESET_DATE(state) {
      state.selectedDate = null;
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
    RESET_CT_DAY_SCHEDULES(state) {
      state.CTDaySchedules = [];
    },
    SET_DAY_SCHEDULES(state, daySchedules) {
      state.daySchedules = [];
      for (let i = 0; i < daySchedules.length; i += 1) {
        state.daySchedules.push(daySchedules[i].startTime);
      }
    },
  },
  actions: {
    pickDate({ commit, dispatch }, date) {
      commit('SET_DATE', date);
      dispatch('fetchDaySchedules', date);
    },
    async fetchSchedules({ commit, getters }) {
      await axios({
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
    createSchedule({ dispatch, getters, commit }, Credential) {
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
            commit('RESET_DATE');
            dispatch('fetchSchedules');
            dispatch('fetchUpcomingSchedules');
            router.push({ name: 'Schedule' });
          })
          .catch((err) => console.log(err));
      } else {
        alert('빠짐없이 입력해주세요');
      }
    },
    createScheduleCT({ dispatch, getters, commit }, Credential) {
      if (getters.selectedDate
        && Credential.startTime
        && Credential.scheduleDetail
        && Credential.selectedCategory) {
        axios({
          url: drf.schedule.schedule(getters.currentUser.id),
          method: 'post',
          data: {
            scheduleDate: getters.selectedDate,
            startTime: Credential.startTime,
            category: Credential.selectedCategory,
            detail: Credential.scheduleDetail,
            memberConsultantId: getters.currentUser.id,
          },
          headers: getters.authHeader,
        })
          .then(() => {
            commit('RESET_DATE');
            dispatch('fetchSchedules');
            dispatch('fetchUpcomingSchedules');
            router.push({ name: 'ScheduleCT' });
          })
          .catch((err) => console.log(err));
      } else {
        alert('빠짐없이 입력해주세요');
      }
    },
    deleteSchedule({ dispatch, getters }, scheduleId) {
      axios({
        url: drf.schedule.scheduleDelete(getters.currentUser.id, scheduleId),
        method: 'delete',
        headers: getters.authHeader,
      })
        .then(() => {
          dispatch('fetchSchedules');
          dispatch('fetchUpcomingSchedules');
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
