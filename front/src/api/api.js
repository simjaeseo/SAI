const HOST = 'https://i7c206.p.ssafy.io:8081/';
// const HOST = 'http://localhost:8081/';

const MEMBER = 'members/';
const SCHEDULE = 'schedule/';
const INTERVIEW = 'interview/';

export default {
  member: {
    login: () => `${HOST}login`,
    logout: () => `${HOST}logout`,
    studentSignup: () => `${HOST}signup/member`,
    currentUserInfo: (id) => `${HOST + MEMBER}member/${id}`,
    ctSignup: () => `${HOST}signup/consultant`,
    profile: (id) => HOST + MEMBER + id,
    updatePassword: () => `${HOST + MEMBER}password`,
    updateProfile: (id) => `${HOST + MEMBER}member/${id}`,
    updateProfileCT: (id) => `${HOST + MEMBER}consultant/${id}`,
    findId: () => `${HOST + MEMBER}`,
    findPassword: () => `${HOST + MEMBER}find/password`,
    getUserImg: (id) => `${HOST + MEMBER + id}/profile`,
    getAllUser: () => `${HOST + MEMBER}search`,
  },
  schedule: {
    schedule: (id) => HOST + SCHEDULE + id,
    scheduleDelete: (id, scheduleId) => `${HOST + SCHEDULE + id}/${scheduleId}`,
    myConsultant: (id) => `${HOST + SCHEDULE + id}/consultant`,
    upcomingSchedule: (id) => `${HOST + SCHEDULE + id}/recent`,
    daySchedule: (id, date) => `${HOST + SCHEDULE + id}/${date}`,
  },
  interview: {
    questionList: (type, detail) => `${HOST + INTERVIEW}question/list?questionType=${type}&questionDetailType=${detail}`,
    saveVideo: (id) => `${HOST + INTERVIEW + id}`,
    getUserVideo: (id) => `${HOST + INTERVIEW + id}`,
    getVideoDetail: (id, index) => `${HOST + INTERVIEW + id}/request/${index}`,
    getFeedbackRequestVideo: (id) => `${HOST + INTERVIEW + id}/request`,
    deletePersonalVideo: (id, info) => `${HOST + INTERVIEW + id}/${info}`,
    customQuestion: () => `${HOST + INTERVIEW}customQuestion/create`,
    customQuestionDelete: (QID) => `${HOST + INTERVIEW}customQuestion/delete/${QID}`,
    customQuestionList: (id) => `${HOST + INTERVIEW}customQuestion/list/${id}`,
    // 비디오 가져오기
    videoDetailPage: (memberId, videoId) => `${HOST + INTERVIEW}${memberId}/request/${videoId}`,
    // 교육생 피드백 작성
    feedBackPost: (ctId, videoId) => `${HOST + INTERVIEW}${ctId}/feedback/${videoId}`,
    completedPB: (ctId, InfoId) => `${HOST + INTERVIEW}${ctId}/request/${InfoId}`,
  },
};
