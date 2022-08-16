<template>
  <div class="container">
    묶음
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
    {{ emotionArray }}
    <h1>{{ currentUser.name }}님의 {{ videoId }} 번째 영상 분석 결과</h1>
    <hr>
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
            <li id="q-text" v-for="(q, index) in qArray" :key="index" @click="getIndex(index)"
            @keydown="getIndex(index)">
              {{ q.question }}
            </li>
          </ul>
        </div>
        <!-- <p>AI 피드백</p>
        <div class="aifeedback"></div> -->
        <div class="d-flex justify-content-between">
          <p>컨설턴트님 피드백</p>
        </div>
        <div class="ctfeedback"></div>
        <p></p>
      </div>
      <div class="right">
        <div class="mb-3">
          <p>표정</p>
          <div class="graph">
          </div>
        </div>
        <div class="mb-3">
          <p>머리 움직임</p>
          <div class="graph">
          </div>
        </div>
        <div class="mb-3">
          <p>음성 크기</p>
          <div class="graph"></div>
        </div>
        <div class="mb-3">
          <p>STT</p>
          <div class="graph">
          </div>
        </div>
      </div>
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
    let order = null;
    const getIndex = function (index) {
      order = index;
    };
    return {
      currentUser,
      userVideo,
      videoDetail,
      getVideoDetail,
      getIndex,
      order,
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
          this.isFeedBackCompleted = JSON.parse(JSON.stringify(res.data.data[i].feedback));
          this.sttArray.push(JSON.parse(JSON.stringify(res.data.data[i].stt)));
          this.teachableArray.push(JSON.parse(JSON.stringify(res.data.data[i].wrongPostureCount)));
          this.qArray.push(JSON.parse(JSON.stringify(res.data.data[i].usedInterviewQuestion)));
          this.emotionArray.push(JSON.parse(JSON.stringify(res.data.data[i].emotionRatio)));
        }
      });
  },
};
</script>

<style scoped>
#q-text {
  cursor: pointer;
  padding-bottom: 10px;
  padding-top: 10px;
}
#q-text:hover {
  cursor: pointer;
  padding-bottom: 10px;
  background-color: rgba(228, 228, 228, 0.514);
}
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
