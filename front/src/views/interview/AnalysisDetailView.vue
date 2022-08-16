<template>
  <div>
    <div class="container" v-if="setVideos">
      <!-- 묶음
      {{ userVideo }}
      <hr>
      개별비디오들
      {{ setVideos }}
      <hr>
      비디오 주소
      {{ videoArray }}
      <hr>
      오디오 주소
      {{ audioArray }}
      <hr>
      피드백 결과
      {{ isFeedBackCompleted }}
      <hr>
      stt
      {{ sttArray }}
      <hr>
      티쳐블
      {{ teachableArray }}
      <hr>
      질문들
      {{ qArray }}
      <hr>
      이모션
      {{ emotionArray }} -->
      {{ audioArray }}
      {{ teachableArray }}
      <h3>{{ currentUser.name }}님의 {{ videoId }} 번째 영상 분석 결과</h3>
      <hr>
      <p id="date-text">
        &#128204;{{ userVideo[videoId-1].interviewDate}}일자 연습영상을 분석한 결과입니다
      </p>
      <div class="container">
        <div class="row">
          <div class="col-lg-8" id="video-box">
            <embed :src="`${videoLink}`" type="" v-if="videoLink" width="680px" height="400px">
            <embed :src="`${videoArray[0]}`" type="" v-else width="680px" height="400px">
          </div>
          <div id="stt-box" class="col-lg-4">
            <div v-if="order != null">
              <p>{{order+1}} 번째 영상의 스크립트</p>
              <p>{{ sttArray[order]}}</p>
            </div>
          </div>
            <div class="dropdown">
              <button class="question-select-btn dropdown-toggle" type="button"
              id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                질문
              </button>
              <ul class="dropdown-menu duties-select" aria-labelledby="dropdownMenuButton1">
                <li id="q-text" v-for="(q, index) in qArray" :key="index" @click="getIndex(index)"
                @keydown="getIndex(index)">
                  {{ q.question }}
                </li>
              </ul>
            </div>
        </div>
        <h5>컨설턴트 피드백</h5>
        <div id="ct-box" v-if="currentUser.memberStatus === 'TRAINEE'">
            <p v-if="isFeedBackCompleted[order] === 'false'" id="no-feedback">
              해당 영상에 대한 피드백이 없습니다.
            </p>
        </div>
        <div class="container">
          <div class="row">
            <div class="col-lg-6" id="audio-box">
              <h5>오디오 분석</h5>
              <div id="audio-box-inner">
                <canvas></canvas>
              </div>
            </div>
            <div class="col-lg-6" id="teachable-box">
              <h5>자세 분석</h5>
              <div id="teachable-box-inner">
                <p v-if="teachableArray[order] > 5"></p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div v-else>
      데이터를 분석중이에요. 조금만 기다려주세요.
    </div>
  </div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';
import axios from 'axios';
import drf from '@/api/api';

export default {
  name: 'ProfileView',
  setup() {
    const store = useStore();

    const currentUser = computed(() => store.getters.currentUser);
    const userVideo = computed(() => store.getters.userVideo);
    const videoDetail = computed(() => store.getters.videoDetail);
    const getVideoDetail = (params) => {
      store.dispatch('getVideoDetail', params);
    };
    return {
      currentUser,
      userVideo,
      videoDetail,
      getVideoDetail,
    };
  },
  data() {
    return {
      videoUrl: '',
      stt: '',
      emotionRatio: 0,
      wrongPostureCount: 0,
      setVideos: null,
      videoId: null,
      videoArray: [],
      audioArray: [],
      isFeedBackCompleted: [],
      sttArray: [],
      teachableArray: [],
      qArray: [],
      emotionArray: [],
      feedback: [],
      order: null,
      videoLink: null,
    };
  },
  created() {
    this.videoId = this.$route.params.videoid;
    console.log(this.videoId);
    this.$store.dispatch('getUserVideo', this.currentUser.id);
    axios({
      url: drf.interview.videoDetailPage(this.currentUser.id, this.videoId),
      method: 'get',
    })
      .then((res) => {
        this.setVideos = JSON.parse(JSON.stringify(res.data.data));
        for (let i = 0; i < res.data.data[0].videoUrl.length; i += 1) {
          this.videoArray.push(JSON.parse(JSON.stringify(res.data.data[i].videoUrl)));
          this.audioArray.push(JSON.parse(JSON.stringify(res.data.data[i].audioUrl)));
          this.isFeedBackCompleted.push(JSON.parse(JSON.stringify(res.data.data[i].feedback)));
          this.sttArray.push(JSON.parse(JSON.stringify(res.data.data[i].stt)));
          this.teachableArray.push(JSON.parse(JSON.stringify(res.data.data[i].wrongPostureCount)));
          this.qArray.push(JSON.parse(JSON.stringify(res.data.data[i].usedInterviewQuestion)));
          this.emotionArray.push(JSON.parse(JSON.stringify(res.data.data[i].emotionRatio)));
        }
      });
  },
  methods: {
    getIndex(index) {
      this.order = index;
      console.log(this.order);
      this.videoLink = this.videoArray[this.order];
      console.log(this.videoLink);
    },
  },
  mounted() {
    window.AudioContext = window.AudioContext || window.webkitAudioContext;
    const audioContext = new AudioContext();

    const drawLineSegment = (ctx, x, height, width, isEven) => {
      const tmp = -height;
      const tmp2 = ctx;
      tmp2.lineWidth = 1;
      tmp2.strokeStyle = '#000';
      tmp2.beginPath();
      // tmp = isEven ? height : -height;
      tmp2.moveTo(x, 0);
      tmp2.lineTo(x, tmp);
      tmp2.arc(x + width / 2, tmp, width / 2, Math.PI, 0, isEven);
      tmp2.lineTo(x + width, 0);
      tmp2.stroke();
    };

    const draw = (normalizedData) => {
      const canvas = document.querySelector('canvas');
      const dpr = window.devicePixelRatio || 1;
      const padding = 20;
      canvas.width = canvas.offsetWidth * dpr;
      canvas.height = (canvas.offsetHeight + padding * 2) * dpr;
      const ctx = canvas.getContext('2d');
      ctx.scale(dpr, dpr);
      ctx.translate(0, canvas.offsetHeight / 2 + padding);

      const width = canvas.offsetWidth / normalizedData.length;
      for (let i = 0; i < normalizedData.length; i += 1) {
        const x = width * i;
        let height = normalizedData[i] * canvas.offsetHeight - padding;
        if (height < 0) {
          height = 0;
        } else if (height > canvas.offsetHeight / 2) {
          height = height > canvas.offsetHeight / 2;
        }
        drawLineSegment(ctx, x, height, width, (i + 1) % 2);
      }
    };

    const filterData = (audioBuffer) => {
      const rawData = audioBuffer.getChannelData(0);
      const samples = 5;
      const blockSize = Math.floor(rawData.length / samples);
      const filteredData = [];
      for (let i = 0; i < samples; i += 1) {
        const blockStart = blockSize * i;
        let sum = 0;
        for (let j = 0; j < blockSize; j += 1) {
          sum += Math.abs(rawData[blockStart + j]);
        }
        filteredData.push(sum / blockSize);
      }
      return filteredData;
    };

    const normalizeData = (filteredData) => {
      const multiplier = Math.max(...filteredData) ** -1;
      return filteredData.map((n) => n * multiplier);
    };

    const drawAudio = (url) => {
      fetch(url)
        .then((response) => response.arrayBuffer())
        .then((arrayBuffer) => audioContext.decodeAudioData(arrayBuffer))
        .then((audioBuffer) => draw(normalizeData(filterData(audioBuffer))));
    };

    // drawAudio(sample);
    drawAudio('@/assets/sample.mp3');
  },
};
</script>

<style scoped>
canvas {
  width: 800px;
  height: 20vw;
  margin: 2rem auto;
}
#audio-box-inner {
  border: 2px solid rgb(221, 221, 221);
  border-radius: 8px;
  height: 300px;
}
#teachable-box-inner {
  border: 2px solid rgb(221, 221, 221);
  border-radius: 8px;
  height: 300px;
}
h5 {
  font-weight: 500;
  margin-top: 50px;
}
#no-feedback {
  color: gray;
  text-align: center;
  line-height: 400px;
}
#date-text {
  margin: 0;
  text-align: end;
}
.dropdown {
  padding: 0;
}
#video-box {
  padding: 0;
}
#stt-inner {
  border: 2px solid rgb(221, 221, 221);
  height: 365px;
  border-radius: 8px;
}
#stt-box {
  height: 400px;
  width: 330px;
  padding: 0;
  border: 2px solid rgb(221, 221, 221);
  border-radius: 8px;
}
#ct-box {
  height: 400px;
  border: 2px solid rgb(221, 221, 221);
  border-radius: 8px;
}
ul {
  width: 100%;
}
#q-text {
  cursor: pointer;
  padding-bottom: 10px;
  padding-top: 10px;
  text-align: center;
}
#q-text:hover {
  cursor: pointer;
  padding-bottom: 10px;
  background-color: rgba(228, 228, 228, 0.514);
}
.container {
  margin-top: 20px;
  padding: 0;
  width: 1000px;
  height: 2050px;
}
.question-select-btn{
  margin-bottom: 1rem;
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
  width: 99%;
  height: 40px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
</style>
