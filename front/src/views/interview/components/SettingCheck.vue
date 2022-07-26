<template>
  <div class="container">
    <p>카메라, 마이크를 체크합니다.</p>
    <div class="box"></div>
    <div class="check">
      <router-link to='/interview/question' id='routerlink'>
       <button class="start-btn">시작하기</button>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {

};
</script>

<style scoped>
.container {
  align-items: center;
  max-width: 1366px;
  padding: 0;
}
p {
  font-size: 32px;
  margin: 3px;
  text-align: center;
  align-items: center;
}
.box {
  border: 1px solid black;
  background: grey;
  width: 1366px;
  height: 768px;
  margin-bottom: 1rem;
}
.check {
  display: flex;
  justify-content: flex-end;
}
.start-btn{
  margin: 3px;
  z-index: 1000;
  background-color: #5c6ac4;
  color: white;
  display: inline-block;
  font-weight: 400;
  line-height: 20px;
  text-align: center;
  text-decoration: none;
  vertical-align: middle;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
  border: 1px solid transparent;
  font-size: 20px;
  border-radius: 0.25rem;
  padding: 0;
  width: 200px;
  height: 60px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
</style>
