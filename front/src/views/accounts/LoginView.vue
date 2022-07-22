<template>
  <div id="wrap1" class="container">
    <div id="wrap2" class="row">
      <!-- 로그인이미지 -->
      <div id="login-image1" class="d-none d-md-block col-md-6">
        <img src="@/assets/loginpageimage1.jpg" alt="interview-image" id="interview-image">
        <h4>면접관과 나와의 거리 42CM</h4>
      </div>
      <!-- 로그인폼 -->
      <div id="login-form" class="col-sm-12 col-md-6">
        <login-form class="login-form"></login-form>
      </div>
    </div>
  </div>
</template>

<script>
import LoginForm from '@/views/accounts/components/LoginForm.vue';

export default {
  name: 'LoginView',
  components: { LoginForm },
};
</script>

<style>
#wrap1 {
  height: 80vh;
  margin: 0 auto;
}
#wrap2 {
  height: 100%;
}
#login-image1 {
  width: 50%;
  margin-top: auto;
  margin-bottom: auto;
  text-align: center;
}
#login-form {
  margin-top: auto;
  margin-bottom: auto;
}
.container {
  margin-right: auto;
  margin-left: auto;
}
@media (min-width: 768px) {
  .container {
    width: 900px;
  }
}
@media (min-width: 992px) {
  .container {
    width: 1100px;
  }
}
@media (min-width: 1200px) {
  .container {
    width: 1400px;
  }
}
#interview-image {
  max-width: 100%;
}
</style>
