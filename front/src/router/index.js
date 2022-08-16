import { createRouter, createWebHistory } from 'vue-router';
// eslint-disable-next-line
import store from '@/store';

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
import ManualView from '@/views/interview/ManualView.vue';

// 유저 라우터
import ThanksToView from '@/views/accounts/ThanksToView.vue';
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

// 스케줄 라우터
import ScheduleView from '@/views/schedule/ScheduleView.vue';
import ScheduleCTView from '@/views/schedule/ScheduleCTView.vue';

// test
// import Test from '@/views/chat/TTSSTT2.vue';

// page not found
import PageNotFound from '@/views/main/PageNotFoundView.vue';

const routes = [
  {
    path: '/manual',
    name: 'Manual',
    component: ManualView,
  },
  {
    path: '/thanks',
    name: 'C206',
    component: ThanksToView,
  },
  {
    path: '/main',
    name: 'Main',
    component: MainPageView,
  },
  {
    path: '/',
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
    path: '/profile/:id',
    name: 'Profile',
    component: ProfileView,
  },
  {
    path: '/update',
    name: 'UpdateUser',
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
    path: '/profile/update/ct/:id',
    name: 'ProfileUpdateCTView',
    component: ProfileUpdateCTView,
  },
  // {
  //   path: '/test',
  //   name: 'Test',
  //   component: Test,
  // },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/404',
  },
  {
    path: '/404',
    component: PageNotFound,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

router.beforeEach((to, from, next) => {
  const { isLoggedIn } = store.getters;
  const authPages = [
    'Manual',
    'C206',
    'Main',
    'Profile',
    'UpdateUser',
    'Management',
    'Schedule',
    'ScheduleCT',
    'Interview',
    'InterviewSetting',
    'InterviewQuestion',
    'InterviewRoom',
    'InterviewSolo',
    'InterviewCt',
    'AnalysisComprehensive',
    'AnalysisDetail',
    'AnalysisDetailHead',
    'AnalysisDetailPitch',
    'AnalysisDetailVolume',
    'AnalysisDetailStt',
    'PasswordUpdate',
    'ProfileUpdateCTView',
  ];
  const unAuthPages = [
    'Login',
    'Signup',
    'SignupCT',
    'HelpPassword',
    'Helpid',
  ];

  const isAuthRequired = authPages.includes(to.name);
  const NotAuthentication = unAuthPages.includes(to.name);

  if (NotAuthentication && isLoggedIn) {
    next({ name: 'Main' });
  } else {
    next();
  }

  if (isAuthRequired && !isLoggedIn) {
    next({ name: 'Login' });
  } else {
    next();
  }
});
export default router;
