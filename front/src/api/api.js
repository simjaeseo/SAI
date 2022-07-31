const HOST = 'http://localhost:8081/';

const MEMBER = 'members/';
const SCHEDULE = 'schedule/';

export default {
  member: {
    login: () => `${HOST + MEMBER}login`,
    studentSignup: () => `${HOST + MEMBER}/member`,
    ctSignup: () => `${HOST + MEMBER}/consultant`,
    profile: (id) => HOST + MEMBER + id,
    updatePassword: (id) => `${HOST + MEMBER + id}/password`,
    updateProfile: (id) => `${HOST + MEMBER + id}/modify`,
    findId: () => `${HOST + MEMBER}find/id`,
    findPassword: () => `${HOST + MEMBER}find/password`,
  },
  schedule: {
    schedule: (id) => HOST + SCHEDULE + id,
    scheduleDelete: (id, scheduleId) => `${HOST + SCHEDULE + id}/${scheduleId}/`,
  },
};
