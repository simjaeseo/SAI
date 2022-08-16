<template>
  <div class="container">
    <h1>ㅇㅇㅇ님의 분석 결과</h1>
    <div class="d-flex align-items-center bar">
        <router-link to="/analysis/detail">
          <div id="detail">세부 분석</div>
          <!-- <p> {{ userVideo[$route.params.index].id }}</p> -->
        </router-link>
    </div>
    <!-- <div class="d-flex align-items-center detail-bar">
      <router-link to="/analysis/detail">
        <div class="selected" id="sight">시선 처리</div>
      </router-link>
      <router-link to="/analysis/detail/head">
        <div id="head">머리 움직임</div>
      </router-link>
      <router-link to="/analysis/detail/pitch">
        <div id="pitch">음성 높낮이</div>
      </router-link>
      <router-link to="/analysis/detail/volume">
        <div id="volume">음성 크기</div>
      </router-link>
      <router-link to="/analysis/detail/stt">
        <div id="stt">답변 내용</div>
      </router-link>
    </div> -->
    <div class="box">
      <div class="left">
        <video class="mb-3" controls width="460">
          <source :src="this.videoUrl"
                  type="video/mp4">
          <track src="captions_en.vtt" kind="captions" srclang="en" label="english_captions">
        </video>
        <div class="dropdown">
          <button class="question-select-btn dropdown-toggle" type="button"
          id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
            질문
          </button>
          <ul class="dropdown-menu duties-select" aria-labelledby="dropdownMenuButton1">
            <li @click="this.emotionRatio=video.emotionRatio,
            this.stt=video.stt,
            this.videoUrl=video.videoUrl,
            this.wrongPostureCount=video.wrongPostureCount"
            v-for="(video, index) in videoDetail" :key="index"
            @keydown.enter="s">
              <a class="dropdown-item">{{ video.usedInterviewQuestion.question }}</a>
            </li>
          </ul>
        </div>
        <!-- <p>AI 피드백</p>
        <div class="aifeedback"></div> -->
        <div class="d-flex justify-content-between">
          <p>컨설턴트님 피드백</p>
        </div>
        <div class="ctfeedback"></div>
        <p> {{ videoDetail }}</p>
      </div>
      <div class="right">
        <div class="mb-3">
          <p>표정</p>
          <div class="graph">
            {{ this.emotionRatio }}
          </div>
        </div>
        <div class="mb-3">
          <p>머리 움직임</p>
          <div class="graph">
            {{ this.wrongPostureCount }}
          </div>
        </div>
        <div class="mb-3">
          <p>음성 크기</p>
          <div class="graph"></div>
        </div>
        <div class="mb-3">
          <p>STT</p>
          <div class="graph">
            {{ this.stt }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';

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
    };
  },
  // methods: {
  //   dataChange(),
  // },
  created() {
    this.$store.dispatch('getUserVideo', this.currentUser.id);
    this.getVideoDetail([this.currentUser.id, this.userVideo[this.$route.params.index].id]);
  },
};
</script>

<style scoped>
.container {
    margin-top: 50px;
    padding: 0;
    width: 1000px;
    height: 2050px;
}

#comprehensive {
  padding-left: 2px;
  padding-right: 2px;
}
#detail {
  border-bottom: 3px solid #5c6ac4;
  padding-top: 19px;
  padding-bottom: 17px;
  padding-left: 2px;
  padding-right: 2px;
  color: #5c6ac4;
}
.bar{
  z-index: 1000;
  /* background-color: #5c6ac4; */
  color: white;
  display: inline-block;
  font-weight: 400;
  line-height: 20px;
  text-decoration: none;
  vertical-align: middle;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
  border-top: 0.1px solid lightgrey;
  border-bottom: 0.1px solid lightgrey;
  font-size: 20px;
  opacity: 0.8;
  width: 1000px;
  height: 60px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

.detail-bar{
  z-index: 1000;
  /* background-color: #5c6ac4; */
  color: white;
  display: inline-block;
  font-weight: 400;
  line-height: 20px;
  text-decoration: none;
  vertical-align: middle;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
  font-size: 20px;
  opacity: 0.8;
  width: 1000px;
  height: 40px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
.selected {
  border-bottom: 3px solid #5c6ac4;
  padding-top: 12px;
  padding-bottom: 10px;
  padding-left: 2px;
  padding-right: 2px;
  color: #5c6ac4;
}
.box {
    display: flex;
    justify-content: space-between;
    margin-top: 1px;
    border: 1px solid transparent;
    border-radius: 15px;
    height: 1400px;
    padding: 1rem;
}

.left {
  display: flex;
  flex-direction: column;
  width: 460px;
}

.right {
  display: flex;
  flex-direction: column;
  width: 460px;
}
.player {
  border: 1px solid black;
  height: 350px;
  margin-bottom: 1rem;
  background-color: black;
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
  width: 460px;
  height: 40px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
.duties-select {
  z-index: 2000;
  width: 450px;
  text-align: center;
}

.aifeedback {
  margin-bottom: 1rem;
  border: 0.1px solid black;
  height: 350px;
  border-radius: 15px;
  box-shadow: 2.4px 4.8px 4.8px hsl(0deg 0% 0% / 0.43);
}

.ctfeedback {
  border: 0.1px solid black;
  height: 350px;
  border-radius: 15px;
  box-shadow: 2.4px 4.8px 4.8px hsl(0deg 0% 0% / 0.43);
}

.graph {
  border: 1px solid black;
  border-radius: 15px;
  height: 400px;
  box-shadow: 2.4px 4.8px 4.8px hsl(0deg 0% 0% / 0.43);
}
h1 {
    text-align: left;
}

a {
    text-decoration: none;
    color: black;
    margin-left: 1rem;
}

p {
  font-size: 24px;
  color: #5c6ac4;
}

.start-btn{
  margin-bottom: 3px;
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
  width: 150px;
  height: 40px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
#alluser {
    display: flex;
    flex-direction: column;
    align-items: center;
}
</style>
