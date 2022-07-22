<template>
  <div class="container">
    <p>카메라, 마이크를 체크합니다.</p>
    <div class="box"></div>
    <button class="btn btn-primary">시작하기</button>
  </div>
</template>

<script>
export default {

};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  max-width: 1600px;
  padding: 0;
}
p {
  font-size: 32px;
}
.box {
  border: 1px solid black;
  background: grey;
  width: 1366px;
  height: 768px;
  margin-bottom: 1rem;
}

.btn {
  font-size: 24px;
}
</style>
