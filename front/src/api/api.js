const HOST = 'http://localhost:8081/';

const MEMBER = 'members/';
const SCHEDULE = 'schedule/';

export default {
  member: {
    login: () => `${HOST}login`,
    logout: () => `${HOST}logout`,
    studentSignup: () => `${HOST}signup/member`,
    currentUserInfo: (id) => `${HOST + MEMBER}member/${id}`,
    ctSignup: () => `${HOST}signup/consultant`,
    profile: (id) => HOST + MEMBER + id,
    updatePassword: () => `${HOST + MEMBER}password`,
    updateProfile: (id) => `${HOST + MEMBER + id}/modify`,
    findId: () => `${HOST + MEMBER}find/id`,
    findPassword: () => `${HOST + MEMBER}find/password`,
  },
  schedule: {
    schedule: (id) => HOST + SCHEDULE + id,
    scheduleDelete: (id, scheduleId) => `${HOST + SCHEDULE + id}/${scheduleId}`,
    myConsultant: (id) => `${HOST + SCHEDULE + id}/consultant`,
    upcomingSchedule: (id) => `${HOST + SCHEDULE + id}/recent`,
  },
};
