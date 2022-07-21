<template>
  <div id="wrap1" class="container">
    <div id="wrap2" class="row">
      <!-- 로그인이미지 -->
      <div id="login-image1" class="d-none d-md-block col-md-6">
      </div>
      <!-- 로그인폼 -->
      <div id="login-form" class="col-sm-12 col-md-6">
      </div>
    </div>
  </div>
</template>

<script>
export default {

};
</script>

<style>
#wrap1 {
  border: 1px solid black;
  height: 80vh;
  width: 80%;
  margin: 0 auto;
}
#wrap2 {
  height: 100%;
}
#login-image1 {
  background-color: red;
}
#login-form {
  background-color: green;
}
</style>
