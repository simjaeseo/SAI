<template>
<div class="card">
<div class="imgBox">
<div class="bark"></div>
<img src="https://image.ibb.co/fYzTrb/lastofus.jpg" alt="img">
</div>
<div class="details" v-if="currentUser.name === '조덕희'">
<h4 class="color1">To.덕희님</h4>
<h4 class="color2 mt-4">(당좡 탑건 4D 가즈아~!!!)</h4>
<p>덕희님 안뇽!</p>
<p>첨에 만났을때 엄청 야무지게...?</p>
<p>말을 조리있게 하신다고 생각했는데 ㅎㅎㅎ</p>
<p>실제로도 그런 모습이 멋있었습니다!!</p>
<p>JPA 공부하면서 개발하느라 많이 힘드셨을텐데</p>
<p>마지막까지 최선을 다 해주셔서 너무 감사해요!</p>
<p>새로운 팀원들과는 더 멋진 프로젝트</p>
<p>더더! 완성도 높게 만드시고 취뽀 갑시드아!!!!</p>
<p>우리 나중에 회식한번 해요오ㅎㅅㅎ</p>
<p>고생많으셨어요 :) 그럼 안녕! bye!</p>
<p class="text-right">아참 밀린코인 모으기 시작!!</p>
<p class="text-right">지수가</p>
</div>
<div class="details" v-if="currentUser.name === '지근'">
<h4 class="color1">To.지근님</h4>
<h4 class="color2 mt-4">(칼바람 가즈앙~!)</h4>
<p>지근지근ㅋㅅㅋ~!안냥</p>
<p>지금 내가 이거 쓰고있는데 자꾸 보지말라고해도!</p>
<p>뒤에서 훔쳐보는 당신... 안써주려다가</p>
<p>선심썼읍니다..!! 호호 이건줄은 몰랐죠?</p>
<p>낮프밤백 하느라 7주동안 몸이 쓰뤠기가 되어버린</p>
<p>지근님 ㅠ 이제 다음 플젝은 건강챙기면서 하자구요! 약속</p>
<p>이번 플젝 진짜진짜진짜 고생 많았어요</p>
<p>새벽반 같이해줘서 넘 고마웠구</p>
<p>앞으로도 잘하좌! 고생했다요!!</p>
<p></p>
<p class="text-right">지근님이랑 친해지기 성공(?)</p>
<p class="text-right">지수가</p>
</div>
<div class="details" v-if="currentUser.name === '지근'">
<h4 class="color1">To.지근님</h4>
<h4 class="color2 mt-4">(칼바람 가즈앙~!)</h4>
<p>지근지근ㅋㅅㅋ~!안냥</p>
<p>지금 내가 이거 쓰고있는데 자꾸 보지말라고해도!</p>
<p>뒤에서 훔쳐보는 당신... 안써주려다가</p>
<p>선심썼읍니다..!! 호호 이건줄은 몰랐죠?</p>
<p>낮프밤백 하느라 7주동안 몸이 쓰뤠기가 되어버린</p>
<p>지근님 ㅠ 이제 다음 플젝은 건강챙기면서 하자구요! 약속</p>
<p>이번 플젝 진짜진짜진짜 고생 많았어요</p>
<p>새벽반 같이해줘서 넘 고마웠구</p>
<p>앞으로도 잘하좌! 고생했다요!!</p>
<p></p>
<p class="text-right">지근님이랑 친해지기 성공(?)</p>
<p class="text-right">지수가</p>
</div>
</div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';

export default {
  name: 'ThanksToView',
  setup() {
    const store = useStore();
    const currentUser = computed(() => store.getters.currentUser);

    return {
      currentUser,
    };
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css?family=Indie+Flower');
@import url('https://fonts.googleapis.com/css2?family=Hi+Melody&display=swap');

body {
font-family: 'Hi Melody', cursive;
background: #FDE3A7; /*CAPE HONEY*/
margin: 0px;
padding: 0px;
}
p {
font-family: 'Hi Melody', cursive;
}
::selection {
background: transparent;
}

h4 {
font-size: 26px;
line-height: 1px;
font-family: 'Hi Melody', cursive;
}

.color1{color:#1BBC9B}/*MOUNTAIN MEADOW*/
.color2{color:#C0392B/*TALL POPPY*/}

.card {
color: #013243; /*SHERPA BLUE*/
position: absolute;
top: 50%;
left: 50%;
width: 300px;
height: 400px;
background: #e0e1dc;
transform-style: preserve-3d;
transform: translate(-50%,-50%) perspective(2000px);
box-shadow: inset 300px 0 50px rgba(0,0,0,.5), 20px 0 60px rgba(0,0,0,.5);
transition: 1s;
}

.card:hover {
transform: translate(-50%,-50%) perspective(2000px) rotate(15deg) scale(1.2);
box-shadow: inset 20px 0 50px rgba(0,0,0,.5), 0 10px 100px rgba(0,0,0,.5);
}

.card:before {
content:'';
position: absolute;
top: -5px;
left: 0;
width: 100%;
height: 5px;
background: #BAC1BA;
transform-origin: bottom;
transform: skewX(-45deg);
}

.card:after {
content: '';
position: absolute;
top: 0;
right: -5px;
width: 5px;
height: 100%;
background: #92A29C;
transform-origin: left;
transform: skewY(-45deg);
}

.card .imgBox {
width: 100%;
height: 100%;
position: relative;
transform-origin: left;
transition: .7s;
}

.card .bark {
position: absolute;
background: #e0e1dc;
width: 100%;
height: 100%;
opacity: 0;
transition: .7s;
}

.card .imgBox img {
min-width: 250px;
max-height: 400px;
}

.card:hover .imgBox {
transform: rotateY(-135deg);
}

.card:hover .bark {
opacity: 1;
transition: .6s;
box-shadow: 300px 200px 100px rgba(0, 0, 0, .4) inset;
}

.card .details {
position: absolute;
top: 0;
left: 0;
box-sizing: border-box;
padding: 0 0 0 10px;
z-index: -1;
margin-top: 70px;
}

.card .details p {
font-size: 15px;
line-height: 5px;
transform: rotate(-10deg);
padding: 0 0 0 20px;
}

.card .details h4 {
text-align: center;
}

.text-right {
text-align: right;
}
</style>
