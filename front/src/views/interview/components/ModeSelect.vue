<template>
  <main class="page-content">
    <div class="card">
        <div class="content">
            <h2 class="title">나 혼자 연습</h2>
            <router-link to='/interview/setting' id='routerlink'>
              <button class="start-btn">시작하기</button>
            </router-link>
        </div>
    </div>
    <div class="card">
        <div class="content">
            <h2 class="title">컨설턴트님과 1:1 면접</h2>
            <div class="d-flex flex-column align-items-center">
              <router-link to='#' id='routerlink'>
                <button class="start-btn">1:1 면접 신청</button>
              </router-link>
              <router-link to='/interview/setting' id='routerlink'>
               <button class="start-btn">--월 --일 --시 --분<br>1:1 면접 시작하기</button>
              </router-link>
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
  max-width: 1000px;
  margin: 0 auto;
  font-family: var(--font-sans);
}

.card {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 1rem;
  width: 400px;
  height: 600px;
  color: whitesmoke;
  background-color: white;
  box-shadow: 0 1px 1px #5c6ac4(0, 0, 0, 0.1), 0 2px 2px #5c6ac4(0, 0, 0, 0.1), 0 4px 4px
  #5c6ac4(0, 0, 0, 0.1), 0 8px 8px #5c6ac4(0, 0, 0, 0.1), 0 16px 16px #5c6ac4(0, 0, 0, 0.1);
}

.title {
  color: black;
  font-size: 36px;
  text-align: center;
}
.start-btn{
  margin-top: 20px;
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
  width: 300px;
  height: 60px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

#routerlink {
  color: white;
  text-decoration: none;
}
</style>
