import { createRouter, createWebHistory } from 'vue-router';

// 메인페이지 라우터
import MainPageView from '@/views/main/MainPageView.vue';

// 인터뷰 라우터
import AnalysisComprehensiveView from '@/views/interview/AnalysisComprehensiveView.vue';
import AnalysisDetailView from '@/views/interview/AnalysisDetailView.vue';
import AnalysisDetailHeadView from '@/views/interview/AnalysisDetailHeadView.vue';
import AnalysisDetailPitchView from '@/views/interview/AnalysisDetailPitchView.vue';
import AnalysisDetailVolumeView from '@/views/interview/AnalysisDetailVolumeView.vue';
import AnalysisDetailSttView from '@/views/interview/AnalysisDetailSttView.vue';
import InterviewView from '@/views/interview/InterviewView.vue';
import InterviewSettingView from '@/views/interview/InterviewSettingView.vue';
import InterviewQuestionView from '@/views/interview/InterviewQuestionView.vue';
import InterviewRoomView from '@/views/interview/InterviewRoomView.vue';
import InterviewSoloView from '@/views/interview/InterviewSoloView.vue';
import InterviewCtView from '@/views/interview/InterviewCtView.vue';

// 유저 라우터
import LoginView from '@/views/accounts/LoginView.vue';
import SignupView from '@/views/accounts/SignupView.vue';
import SignupCTView from '@/views/accounts/SignupCTView.vue';
import HelpPasswordView from '@/views/accounts/HelpPasswordView.vue';
import HelpIdView from '@/views/accounts/HelpIdView.vue';
import ProfileView from '@/views/accounts/ProfileView.vue';
import ProfileUpdateCTView from '@/views/accounts/ProfileUpdateCTView.vue';
import ProfileUpdateView from '@/views/accounts/ProfileUpdateView.vue';
import ManagementView from '@/views/accounts/ManagementView.vue';
import PasswordUpdateView from '@/views/accounts/PasswordUpdateView.vue';

// 채팅 라우터
import ChatView from '@/views/chat/ChatView.vue';

// 스케줄 라우터
import ScheduleView from '@/views/schedule/ScheduleView.vue';
import ScheduleCTView from '@/views/schedule/ScheduleCTView.vue';

// test
import TTSSTT from '@/views/chat/TTSSTT.vue';

const routes = [
  {
    path: '/',
    name: 'Main',
    component: MainPageView,
  },
  {
    path: '/login',
    name: 'Login',
    component: LoginView,
  },
  {
    path: '/signup',
    name: 'Signup',
    component: SignupView,
  },
  {
    path: '/signupCT',
    name: 'SignupCT',
    component: SignupCTView,
  },
  {
    path: '/helpPassword',
    name: 'HelpPassword',
    component: HelpPasswordView,
  },
  {
    path: '/helpid',
    name: 'Helpid',
    component: HelpIdView,
  },
  {
    path: '/profile',
    name: 'Profile',
    component: ProfileView,
  },
  {
    path: '/update',
    name: 'MpdateUser',
    component: ProfileUpdateView,
  },
  {
    path: '/management',
    name: 'Management',
    component: ManagementView,
  },
  {
    path: '/schedule',
    name: 'Schedule',
    component: ScheduleView,
  },
  {
    path: '/scheduleCT',
    name: 'ScheduleCT',
    component: ScheduleCTView,
  },
  {
    path: '/interview',
    name: 'Interview',
    component: InterviewView, // 인터뷰 페이지
  },
  {
    path: '/interview/setting',
    name: 'InterviewSetting',
    component: InterviewSettingView,
  },
  {
    path: '/interview/question',
    name: 'InterviewQuestion',
    component: InterviewQuestionView,
  },
  {
    path: '/interview/room',
    name: 'InterviewRoom',
    component: InterviewRoomView,
  },
  {
    path: '/interview/solo',
    name: 'InterviewSolo',
    component: InterviewSoloView,
  },
  {
    path: '/interview/ct',
    name: 'InterviewCt',
    component: InterviewCtView,
  },
  {
    path: '/analysis/comprehensive',
    name: 'AnalysisComprehensive',
    component: AnalysisComprehensiveView,
  },
  {
    path: '/analysis/detail',
    name: 'AnalysisDetail',
    component: AnalysisDetailView,
  },
  {
    path: '/analysis/detail/head',
    name: 'AnalysisDetailHead',
    component: AnalysisDetailHeadView,
  },
  {
    path: '/analysis/detail/pitch',
    name: 'AnalysisDetailPitch',
    component: AnalysisDetailPitchView,
  },
  {
    path: '/analysis/detail/volume',
    name: 'AnalysisDetailVolume',
    component: AnalysisDetailVolumeView,
  },
  {
    path: '/analysis/detail/stt',
    name: 'AnalysisDetailStt',
    component: AnalysisDetailSttView,
  },
  {
    path: '/password/update',
    name: 'PasswordUpdate',
    component: PasswordUpdateView,
  },
  {
    path: '/chat',
    name: 'Chat',
    component: ChatView,
  },
  {
    path: '/profile/update/ct',
    name: 'ProfileUpdateCTView ',
    component: ProfileUpdateCTView,
  },
  {
    path: '/test',
    name: 'Test',
    component: TTSSTT,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
