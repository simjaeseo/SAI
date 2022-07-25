import { createRouter, createWebHistory } from 'vue-router';
import MainPageView from '@/views/main/MainPageView.vue';
import AnalysisComprehensiveView from '@/views/interview/AnalysisComprehensiveView.vue';
import AnalysisDetailView from '@/views/interview/AnalysisDetailView.vue';
import LoginView from '@/views/accounts/LoginView.vue';
import SignupView from '@/views/accounts/SignupView.vue';
import HelpPasswordView from '@/views/accounts/HelpPasswordView.vue';
import HelpIdView from '@/views/accounts/HelpIdView.vue';
import ProfileView from '@/views/accounts/ProfileView.vue';
import UpdateUserView from '@/views/accounts/UpdateUserView.vue';
import ManagementView from '@/views/accounts/ManagementView.vue';
import ScheduleView from '@/views/schedule/ScheduleView.vue';
import InterviewView from '@/views/interview/InterviewView.vue';
import InterviewSoloView from '@/views/interview/InterviewSoloView.vue';
import InterviewCtView from '@/views/interview/InterviewCtView.vue';

const routes = [
  {
    path: '/',
    name: 'Main',
    component: MainPageView,
  },
  {
    path: '/login',
    name: 'Mogin',
    component: LoginView,
  },
  {
    path: '/signup',
    name: 'Mignup',
    component: SignupView,
  },
  {
    path: '/helpPassword',
    name: 'MelpPassword',
    component: HelpPasswordView,
  },
  {
    path: '/helpid',
    name: 'Melpid',
    component: HelpIdView,
  },
  {
    path: '/profile',
    name: 'Mrofile',
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
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
