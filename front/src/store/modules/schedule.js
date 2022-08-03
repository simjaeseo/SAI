import drf from '@/api/api';
import router from '@/router';
import axios from 'axios';

export default {
  namespace: true,
  state: {
    selectedDate: null,
    startTime: null,
    endTime: null,
    schedules: [],
    upcomingSchedules: [],
    myConsultants: [],
    selectedConsultant: false,
    scheduleDetail: '',
    selectedCategory: null,
    CTDaySchedules: [],
  },
  getters: {
    selectedDate: (state) => state.selectedDate,
    selectedStartTime: (state) => state.startTime,
    selectedEndTime: (state) => state.endTime,
    schedules: (state) => state.schedules,
    upcomingSchedules: (state) => state.upcomingSchedules,
    isUpcomingSchedules: (state) => state.upcomingSchedules.length,
    myConsultants: (state) => state.myConsultants,
    selectedConsultant: (state) => state.selectedConsultant,
    scheduleDetail: (state) => state.scheduleDetail,
    selectedCategory: (state) => state.selectedCategory,
    CTDaySchedules: (state) => state.CTDaySchedules,
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
      state.selectedConsultant = consultant;
    },
    SET_SCHEDULE_DETAIL(state, datail) {
      state.scheduleDetail = datail;
    },
    SET_SELECTED_CATEGORY(state, category) {
      state.selectedCategory = category;
    },
    SET_CT_DAY_SCHEDULES(state, CTDaySchedules) {
      state.CTDaySchedules = [];
      for (let i = 0; i < CTDaySchedules.length; i += 1) {
        state.CTDaySchedules.push(CTDaySchedules[i].startTime);
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
    pickMyConsultant({ commit, dispatch, getters }, consultant) {
      commit('SET_MY_CONSULTANT', consultant);
      const data = {
        consultant,
        date: getters.selectedDate,
      };
      dispatch('fetchCTDaySchedules', data);
    },
    entryScheduleDetail({ commit }, detail) {
      commit('SET_SCHEDULE_DETAIL', detail.target.value);
    },
    selectCategory({ commit }, category) {
      commit('SET_SELECTED_CATEGORY', category);
    },
    createSchedule({ dispatch, getters }) {
      if (getters.selectedDate
        && getters.selectedStartTime
        && getters.selectedCategory
        && getters.scheduleDetail
        && getters.selectedConsultant !== false) {
        axios({
          url: drf.schedule.schedule(getters.currentUser.id),
          method: 'post',
          data: {
            scheduleDate: getters.selectedDate,
            startTime: getters.selectedStartTime,
            endTime: getters.selectedEndTime,
            category: getters.selectedCategory,
            detail: getters.scheduleDetail,
            memberStudentId: getters.currentUser.id,
            memberConsultantId: getters.selectedConsultant,
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
  },
  modules: {
  },
};
