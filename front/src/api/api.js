const HOST = 'http://localhost:8080/';

const MEMBER = 'member/';
const SCHEDULE = 'schedule/';

export default {
  member: {
    login: () => `${HOST + MEMBER}login`,
    studentSignup: () => `${HOST + MEMBER}studentsignup`,
    ctSignup: () => `${HOST + MEMBER}consultantsignup`,
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
