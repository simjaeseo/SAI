<template>
  <div id="detail">
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
      <div>
        <h2>{{ userVideo[0].studentName }}님의 {{ videoId }} 번째 영상 분석 결과 &#128064;</h2>
        <div id="btn-box">
          <button class="btn"
          id="completed-btn" @click="completedPB"
          v-if="currentUser.memberStatus === 'CONSULTANT'">피드백 완료</button>
        </div>
      </div>
      <hr>
      <p id="date-text">
        &#128204;{{ userVideo[videoId-1].interviewDate}}
      </p>
      <div class="container">
        <div class="row">
          <div class="col-lg-8" id="video-box">
            <embed :src="`${videoLink}`" type="" v-if="videoLink" width="680px" height="400px">
            <embed :src="`${ videoArray[order] }`" type="" v-else width="680px" height="400px">
          </div>
          <div id="stt-box" class="col-lg-4">
            <div v-if="order != null">
              <p>{{order+1}} 번째 영상의 답변내용</p>
              <p>{{ sttArray[order]}}</p>
            </div>
          </div>
            <div class="dropdown">
              <button class="question-select-btn dropdown-toggle" type="button"
              id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                질문
              </button>
              <ul class="dropdown-menu duties-select" aria-labelledby="dropdownMenuButton1">
                <li id="q-text" v-for="(q, index) in qArray" :key="index"
                @click="getIndex(index), getId(q.id)"
                @keydown="getIndex(index), getId(q.id)">
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
            <p v-else class="mx-2 mt-2">
              {{ isFeedBackCompleted[order] }}
            </p>
        </div>
        <div v-if="currentUser.memberStatus === 'CONSULTANT' && order === 0 &&
        isFeedBackCompleted[order] !== 'false'">
          <form @submit.prevent="feedBackPost">
            <label for="feedback">
              현재 질문 : {{ qArray[order].question }}
              <textarea id="feedback" cols="120" rows="15" class="form-control"
              v-model="isFeedBackCompleted[0]"></textarea>
            </label>
            <div id="btn-box">
              <button class="btn" type="submit" id="feed-back-btn">등록</button>
            </div>
          </form>
        </div>
        <div v-else-if="currentUser.memberStatus === 'CONSULTANT'">
          <form @submit.prevent="feedBackPost">
            <label for="feedback">
              현재 질문 : {{ qArray[order].question }}
              <textarea id="feedback" cols="120" rows="15" class="form-control"
              v-model="feedbackData"></textarea>
            </label>
            <div id="btn-box">
              <button class="btn" type="submit" id="feed-back-btn">등록</button>
            </div>
          </form>
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
                <p v-if="teachableArray[order]" id="result-text">
                  해당 답변이 진행되는 동안 잘못된 자세가 {{ teachableArray[order] }}번 감지되었습니다.
                  안정된 자세는 지원자의 면접 태도에 좋은 영향을 끼칩니다. 안정된 자세를 유지하여, 편안하게 면접을 응시하시기 바랍니다.
                  몸의 흔들림이 잦을수록 집중도, 신뢰감, 소통능력 평가에 영향을 줄 수 있으니, 유의하여 면접에 응시하시기 바랍니다.
                </p>
              </div>
            </div>
            <div class="col-lg-6" id="teachable-box">
              <h5>표정 변화</h5>
              <div id="teachable-box-inner">
                <p v-if="emotionArray[order] != null" id="result-text">
                  긍정적인 표정의 비율이 {{ emotionArray[order] }}입니다.
                  긍정적인 표정이 많을경우, 면접관에게 좋은 인상을 남길 수 있습니다. 또한 표정 변화는 활기참, 호감도, 친절함, 유쾌함
                  평가에 영향을 줄 수 있습니다. 표정 변화에 유의해서 긍정적 표정을 지으며 면접에 응시하기 바랍니다.
                </p>
              </div>
            </div>
            <div class="col-lg-6" id="teachable-box">
              <h5>총평</h5>
              <div id="teachable-box-inner">
                <p v-if="teachableSub > 1" id="result-text">
                  평균적으로 감지되는 움직임보다 다소 많은 흔들림이 감지되었습니다.
                  AI면접에서는 답변 내용보다 외적 요소가 중요합니다. 말할 때 표정과 자세, 목소리 톤, 음색,
                  시선 처리 등이 가장 많은 점수를 차지합니다. 따라서 {{ userVideo[0].studentName }}
                  님의 경우 올바른 자세를 유지하는
                  연습이 필요하다는 점을 알려드립니다. 또한
                  AI면접에서는 목소리에 대한 요소가 가장 중요합니다. 우선 크고, 또렷한 목소리로 대답을 해야 하며
                  목소리의 크기 역시 중요한데 약간은 큰 소리로 대답하는것이 좋습니다. 또한, 정확한 발음을 통해
                  음성이 텍스트로 올바르게 전환될 수 있도록 해야합니다. 주의할 것은 웅얼거리듯이 말하는 것입니다.
                  웅얼거리지 않도록 신경써서 대답하는 것을 연습할 필요가 있습니다.
                </p>
                <p v-else-if="emotionSub < 10" id="result-text">
                  영상에서 분석된 님의 표정변화가 다소 부족한것으로 판단됩니다.
                  AI면접에서는 답변 내용보다 외적 요소가 중요합니다. 말할 때 표정과 자세, 목소리 톤, 음색,
                  시선 처리 등이 가장 많은 점수를 차지합니다. 따라써 {{ userVideo[0].studentName }}님의 경우 미소를 유지하는
                  연습이 필요하다는 점을 알려드립니다. 또한
                  AI면접에서는 목소리에 대한 요소가 가장 중요합니다. 우선 크고, 또렷한 목소리로 대답을 해야 하며
                  목소리의 크기 역시 중요한데 약간은 큰 소리로 대답하는것이 좋습니다. 또한, 정확한 발음을 통해
                  음성이 텍스트로 올바르게 전환될 수 있도록 해야합니다. 주의할 것은 웅얼거리듯이 말하는 것입니다.
                  웅얼거리지 않도록 신경써서 대답하는 것을 연습할 필요가 있습니다.
                </p>
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
// eslint-disable-next-line
import router from '@/router/index';
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
      order: 0,
      videoLink: null,
      teachableSub: null,
      emotionSub: null,
      len: null,
      feedbackData: null,
      feedbackId: null,
    };
  },
  created() {
    this.videoId = this.$route.params.videoid;
    this.userId = this.$route.params.userid;
    this.$store.dispatch('getUserVideo', this.userId);
    axios({
      url: drf.interview.videoDetailPage(this.userId, this.videoId),
      method: 'get',
    })
      .then((res) => {
        this.setVideos = JSON.parse(JSON.stringify(res.data.data));
        this.len = this.setVideos.length;
        for (let i = 0; i < res.data.data[0].videoUrl.length; i += 1) {
          this.videoArray.push(JSON.parse(JSON.stringify(res.data.data[i].videoUrl)));
          this.audioArray.push(JSON.parse(JSON.stringify(res.data.data[i].audioUrl)));
          this.isFeedBackCompleted.push(JSON.parse(JSON.stringify(res.data.data[i].feedback)));
          this.sttArray.push(JSON.parse(JSON.stringify(res.data.data[i].stt)));
          this.teachableArray.push(JSON.parse(JSON.stringify(res.data.data[i].wrongPostureCount)));
          this.qArray.push(JSON.parse(JSON.stringify(res.data.data[i].usedInterviewQuestion)));
          this.emotionArray.push(JSON.parse(JSON.stringify(res.data.data[i].emotionRatio)));
          this.teachableSub += JSON.parse(JSON.stringify(res.data.data[i].wrongPostureCount));
          this.emotionSub += JSON.parse(JSON.stringify(res.data.data[i].emotionRatio));
        }
      });
  },
  methods: {
    getIndex(index) {
      this.order = index;
      this.videoLink = this.videoArray[this.order];
    },
    getId(id) {
      this.feedbackId = id;
      if (this.isFeedBackCompleted[this.order] !== 'false') {
        this.feedbackData = this.isFeedBackCompleted[this.order];
      } else if (this.isFeedBackCompleted[this.order] === 'false') {
        this.feedbackData = null;
      }
    },
    feedBackPost() {
      console.log(this.feedbackData);
      axios({
        url: drf.interview.feedBackPost(this.currentUser.id, this.feedbackId),
        method: 'post',
        data: {
          feedback: this.feedbackData,
        },
      })
        .then(
          alert('피드백이 등록되었습니다.'),
        );
    },
    completedPB() {
      // eslint-disable-next-line
      if (confirm("해당 영상을 '피드백 완료' 처리 하시겠습니까?")) {
        axios({
          url: drf.interview.completedPB(this.currentUser.id, this.$route.params.videoid),
          method: 'put',
        })
          .then(router.push({ name: 'Management' }));
      }
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
    drawAudio(this.audioArray.order);
  },
};
</script>

<style scoped>
#completed-btn {
  background-color: #ffffff;
  color: #5c6ac4;
  border: 1px solid #5c6ac4;
}
#completed-btn:hover {
  background-color: #5c6ac4;
  color: white;
}
h2 {
  font-weight: 700;
  color: #494949;
  margin-top: 50px;;
}
#feed-back-btn {
  border: 1px solid #5c6ac4;
  color: #5c6ac4;
}
#feed-back-btn:hover {
  background-color: #5c6ac4;
  color: white;
}
#btn-box {
  text-align: end;
}
#teachable-box-inner {
  display: flex;
  justify-content: center;
  align-items: center;
}
#result-text {
  padding-left: 10px;
  padding-right: 10px;
}
canvas {
  width: 800px;
  height: 20vw;
  margin: 2rem auto;
}
#audio-box-inner {
  border: 2px solid rgb(221, 221, 221);
  border-radius: 8px;
  height: 300px;
  box-shadow: 2px 2px 2px 2px rgb(224, 224, 224);
}
#teachable-box-inner {
  border: 2px solid rgb(221, 221, 221);
  border-radius: 8px;
  height: 300px;
  box-shadow: 2px 2px 2px 2px rgb(224, 224, 224);
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
  margin-bottom: 50px;
  text-align: end;
  color: gray;
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
  box-shadow: 2px 2px 2px 2px rgb(224, 224, 224);
}
#ct-box {
  height: 400px;
  border: 2px solid rgb(221, 221, 221);
  border-radius: 8px;
  box-shadow: 2px 2px 2px 2px rgb(224, 224, 224);
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
  height: 120vh;
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
