<template>
  <main class="page-content">
    <div class="card">
        <div class="content">
            <h2 class="title">나 혼자 연습</h2>
            <p class="copy"></p><button class="btn">시작하기</button>
        </div>
    </div>
    <div class="card">
        <div class="content">
            <h2 class="title">컨설턴트님과 1:1 면접</h2>
            <p class="copy"></p>
            <div class="d-flex flex-column align-items-center">
              <button class="btn">1:1 면접 신청</button>
              <button class="btn">--월 --일 --시 --분<br>1:1 면접 시작하기</button>
            </div>
        </div>
    </div>

  </main>
</template>

<script>
export default {

};
</script>

<style scoped>

.page-content {
  display: flex;
  justify-content: space-between;
  grid-gap: 1rem;
  padding: 1rem;
  max-width: 1024px;
  margin: 0 auto;
  font-family: var(--font-sans);
}

.card {
  position: relative;
  display: flex;
  padding: 1rem;
  width: 400px;
  height: 600px;
  color: whitesmoke;
  background-color: whitesmoke;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1), 0 2px 2px rgba(0, 0, 0, 0.1), 0 4px 4px
  rgba(0, 0, 0, 0.1), 0 8px 8px rgba(0, 0, 0, 0.1), 0 16px 16px rgba(0, 0, 0, 0.1);
}

.title {
  color: black;
  font-size: 48px;
}
.btn {
  color: white;
  background-color: rgb(33, 150, 243);
}
</style>
