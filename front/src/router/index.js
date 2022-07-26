import { createRouter, createWebHistory } from 'vue-router';
// 메인페이지 라우터
import MainPageView from '@/views/main/MainPageView.vue';
// 인터뷰 라우터
import AnalysisComprehensiveView from '@/views/interview/AnalysisComprehensiveView.vue';
import AnalysisDetailView from '@/views/interview/AnalysisDetailView.vue';
import InterviewView from '@/views/interview/InterviewView.vue';
import InterviewSoloView from '@/views/interview/InterviewSoloView.vue';
import InterviewCtView from '@/views/interview/InterviewCtView.vue';
// 유저 라우터
import LoginView from '@/views/accounts/LoginView.vue';
import SignupView from '@/views/accounts/SignupView.vue';
import SignupCTView from '@/views/accounts/SignupCTView.vue';
import HelpPasswordView from '@/views/accounts/HelpPasswordView.vue';
import HelpIdView from '@/views/accounts/HelpIdView.vue';
import ProfileView from '@/views/accounts/ProfileView.vue';
import UpdateUserView from '@/views/accounts/UpdateUserView.vue';
import ManagementView from '@/views/accounts/ManagementView.vue';
import PasswordUpdateView from '@/views/accounts/PasswordUpdateView.vue';
// 스케줄 라우터
import ScheduleView from '@/views/schedule/ScheduleView.vue';

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
    component: UpdateUserView,
  },
  {
    path: '/management',
    name: 'Management',
    component: ManagementView,
  },
  {
    path: '/schedule',
    name: 'ScheduleHome',
    component: ScheduleView,
  },
  {
    path: '/interview',
    name: 'Interview',
    component: InterviewView, // 인터뷰 페이지
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
    path: '/password/update',
    name: 'PasswordUpdate',
    component: PasswordUpdateView,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
