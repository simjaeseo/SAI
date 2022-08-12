<template>
<div id="background">
  <div class="card">
    <div class="imgBox">
      <div class="bark"></div>
    <img src="@/assets/커버.png" alt="img" style="width:300px; height:400px;">
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
      <p class="mt-5">지근지근ㅋㅅㅋ~!안냥?</p>
      <p>지금 내가 이거 쓰고있는데 자꾸 보지말라고해도!</p>
      <p>뒤에서 훔쳐보는 당신... 안써주려다가</p>
      <p>선심썼읍니다..!! 호호 이건줄은 몰랐죠?</p>
      <p>낮프밤백 하느라 7주동안 몸이 쓰뤠기가 되어버린</p>
      <p>지근ㅠ다음 플젝은 건강챙기면서 하자구요! 약속!</p>
      <p>이번 플젝 진짜진짜진짜루 고생 많았어요</p>
      <p>새벽반 같이해줘서 넘 고마웠구</p>
      <p>앞으로도 같이 잘해보좌! 고생했다요!!</p>
      <p></p>
      <p class="text-right">지근님이랑 친해지기 성공(?)</p>
      <p class="text-right">지수가</p>
    </div>
    <div class="details" v-if="currentUser.name === '심재서'">
      <h4 class="color1">To.재서님</h4>
      <h4 class="color2 mt-4">(우주최강 스윗남!)</h4>
      <p>재서님~! 안녕하세용</p>
      <p>당쉰 더운날 밖에 나가지마요</p>
      <p>너무 스윗해서 녹아 내리면 어떡해요!?</p>
      <p>ㅋㅅㅋ제가 만나본 사람들중에서 젤 스윗하신 재서님</p>
      <p>항상 모르는거 있으면 세상 친절하게 알려주셔서 감사해요</p>
      <p>덕분에 비전공자가 이만큼이나 해낼수있었습니다!</p>
      <p>프로젝트 같이 할 수 있어서 넘 좋았어요!!</p>
      <p>재서님만 괜찮으시다면..</p>
      <p>다음프로젝트도 같이 가면 좋겠습니다!!!</p>
      <p></p>
      <p class="text-right">배홍동 엠버서더 재서(?)</p>
      <p class="text-right">지수가</p>
    </div>
    <div class="details" v-if="currentUser.name === '윤형준'">
      <h4 class="color1">To.형준님</h4>
      <h4 class="color2 mt-4">(aka.힘숨찐)</h4>
      <p>형준님쓰~! 안녕하세요</p>
      <p>우리은행 면접은 잘 보고 오셨나요..?</p>
      <p>맨날 안될것처럼 말해놓고 세상 혼자사는 맛이 어때요?</p>
      <p>흥. 재쉅서. -ㅅ-! 장난이구</p>
      <p>이번에 형준님이랑 프로젝트 같이하게돼서 좋았습니다 ㅎㅎ..</p>
      <p>1학기때 친해지고싶었는데,,,!! 조금은 가까워지기 성공?</p>
      <p>꼭 좋은 소식 오실거라 믿어요!</p>
      <p>항상 힘든일 뒤에는 좋은일이 가득하니까요!</p>
      <p>이번 프로젝트 고생 많으셨습니다 :) </p>
      <p></p>
      <p class="text-right">취뽀턱 기다린다.</p>
      <p class="text-right">지수가</p>
    </div>
    <div class="details" v-if="currentUser.name === '최인호'">
      <h4 class="color1">To.인호님</h4>
      <h4 class="color2 mt-4">()</h4>
      <p>팀장님,, 아녕하십니까,,</p>
      <p>접때 말한 이스터에그입니다 하하하</p>
      <p>부족한게 많은 저인데 믿고 함께해주셔서 감사했어요</p>
      <p>덕분에 좋은 사람들도 많이 알게 되었고</p>
      <p>프로젝트 하면서 정말 많은것을 배웠습니다!</p>
      <p>처음 기획했던만큼은 아니지만</p>
      <p>저희는 최선을 다했잖아요?!</p>
      <p>팀장님 고생 진짜진짜 많으셨습니다!!!</p>
      <p>다음 프로젝트 기대할게요 ~_~!!</p>
      <p></p>
      <p class="text-right">happy inno!</p>
      <p class="text-right">지수가</p>
    </div>
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
@import url('https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap');

#background {
  width: 100%;
  height: 100vh;
}
body {
font-family: 'Hi Melody', cursive;
background: #FDE3A7; /*CAPE HONEY*/
margin: 0px;
padding: 0px;
}
p {
font-family: 'Gamja Flower', cursive;
}
::selection {
background: transparent;
}

h4 {
font-size: 26px;
line-height: 1px;
font-family: 'Gamja Flower', cursive;
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
background: #e1dfdc;
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
