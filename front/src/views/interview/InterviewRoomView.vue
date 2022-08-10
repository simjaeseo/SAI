<template>
    <div class="container mt-5">
      <div class="modal fade" id="exampleModalToggle" aria-hidden="true"
      aria-labelledby="exampleModalToggleLabel" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalToggleLabel">마이 페이지에 저장하시겠습니까?</h5>
              <button type="button" class="btn-close"
              data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <div class="col-12">
                <div class="form-check">
                  <div class="form-check">
                  <label class="form-check-label" for="gridRadios1">
                  <input class="form-check-input" type="radio" name="gridRadios"
                  id="gridRadios1" value="true" checked @change="myConfirm($event)">
                    예
                  </label>
                </div>
                <div class="form-check">
                  <label class="form-check-label" for="gridRadios2">
                  <input class="form-check-input" type="radio" name="gridRadios"
                  id="gridRadios2" value="false" @change="myConfirm($event)">
                    아니오
                  </label>
                </div>
                </div>
              </div>
            </div>
            <div class="modal-footer">
              <button class="btn btn-primary" data-bs-target="#exampleModalToggle2"
              data-bs-toggle="modal">다음</button>
            </div>
          </div>
        </div>
      </div>
      <div class="modal fade" id="exampleModalToggle2" aria-hidden="true"
      aria-labelledby="exampleModalToggleLabel2" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalToggleLabel2">컨설턴트님께 피드백을 요청하겠습니까?</h5>
              <button type="button" class="btn-close"
              data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <div class="col-12">
                <div class="form-check">
                  <div class="form-check">
                  <label class="form-check-label" for="gridRadios1">
                  <input class="form-check-input" type="radio" name="gridRadios"
                  id="gridRadios1" value="true" checked @change="ctConfirm($event)">
                    예
                  </label>
                </div>
                <div class="form-check">
                  <label class="form-check-label" for="gridRadios2">
                  <input class="form-check-input" type="radio" name="gridRadios"
                  id="gridRadios2" value="false" @change="ctConfirm($event)">
                    아니오
                  </label>
                </div>
                </div>
              </div>
            </div>
            <div class="modal-footer">
              <router-link to="/">
                <button class="btn btn-primary" data-bs-dismiss="modal"
                >창 닫기</button>
              </router-link>
            </div>
          </div>
        </div>
      </div>
      <div id="session" v-if="session">
        <div id="session-header">
        </div>
        <div id="main-video">
          <div id="video-box">
            <div v-if="question.length">
              <span v-if="!isFinished"> 질문 : </span>
              <span>{{question}}</span>
            </div>
            <user-video :stream-manager="mainStreamManager"/>
            <div id="video-text" v-if="isAnimationStart">
              <p id="video-start"> 모의 면접을 시작합니다.</p>
              <p id="video-start"> 질문에 답변해주세요. </p>
              <div class="cd-number-wrapper">
                <span class="cd-number-five">5</span>
                <span class="cd-number-four">4</span>
                <span class="cd-number-three">3</span>
                <span class="cd-number-two">2</span>
                <span class="cd-number-one">1</span>
              </div>
            </div>
          </div>
          <div class="d-flex justify-content-end">
            <input class="btn btn-light me-2" type="button"
            id="buttonLeaveSession" @click="[answerCompleted(), stopRecoding()]" value="답변 완료">
            <div> <input class="btn btn-light me-2" type="button"
              id="buttonLeaveSession" @click="startRecoding" value="녹화!"> </div>
            <div><button class="btn btn-primary" data-bs-toggle="modal" @keydown="leaveSession"
            data-bs-target="#exampleModalToggle" @click="leaveSession">면접 종료</button></div>
          </div>
        </div>
        <!-- <div id="video-container" class="col-md-6">
          <user-video :stream-manager="publisher"
          @click="updateMainVideoStreamManager(publisher)"/>
          <user-video v-for="sub in subscribers"
          :key="sub.stream.connection.connectionId"
          :stream-manager="sub"
          @click="updateMainVideoStreamManager(sub)"/>
        </div> -->
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import { computed } from 'vue';
import { useStore } from 'vuex';
import UserVideo from './components/UserVideo.vue';

axios.defaults.headers.post['Content-Type'] = 'application/json';

const OPENVIDU_SERVER_URL = 'https://i7c206.p.ssafy.io:8083';
const OPENVIDU_SERVER_SECRET = 'MY_SECRET';

export default {
  // name: '',
  components: {
    UserVideo,
  },
  data() {
    return {
      isAnimationStart: false,
      myRecodingId: undefined,
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      mySessionId: 'SessionAD',
      myUserName: `Participant${Math.floor(Math.random() * 100)}`,
      isStart: false,
      question: '',
      questions: [],
      isFinished: false,
      myConfirms: true,
      ctConfirms: true,
      savedUrls: [],
    };
  },
  setup() {
    const store = useStore();
    const selectedQuestionList = computed(() => store.getters.selectedQuestionList);
    const currentUser = computed(() => store.getters.currentUser);
    return {
      selectedQuestionList,
      currentUser,
    };
  },
  created() {
    this.questions = this.selectedQuestionList;
    console.log(this.questions);
  }, // 해당 vue 파일이 실행 되는 순간
  mounted() {
    this.joinSession();
    // this.mySessionId = this.currentUser.id;
  }, // 템플릿 내 HTML DOM이 화면에 로딩이 되는 순간, 마운트가 다 끝난 순간 실행
  unmounted() { }, // 컴포넌트 이동 시 unmount가 일어나면서 해당 코드 자동 실행
  methods: {
    myConfirm(event) {
      if (event.target.value === 'true') {
        this.myConfirms = true;
      } else {
        this.myConfirms = false;
      }
      console.log(this.myConfirms);
    },
    ctConfirm(event) {
      if (event.target.value === 'true') {
        this.ctConfirms = true;
      } else {
        this.ctConfirms = false;
      }
      console.log(this.ctConfirms);
    },
    answerCompleted() {
      this.question = '';
      if (!this.questions.length) {
        this.question = '면접이 종료되었습니다.';
        this.isFinished = true;
      }
      // 답변완료 버튼을 누르면 타임라인이 생성되고 다음질문이 3초뒤에 TTS.
    },
    startRecoding() {
      this.isAnimationStart = true;
      this.question = this.questions.shift();
      console.log(this.question);
      console.log(this.questions);
      axios
        .post(`${OPENVIDU_SERVER_URL}/openvidu/api/recordings/start`, JSON.stringify({
          session: this.mySessionId,
        }), {
          auth: {
            username: 'OPENVIDUAPP',
            password: OPENVIDU_SERVER_SECRET,
          },
        })
        .then((res) => {
          this.myRecodingId = res.data.id;
          console.log(res);
        });
    },
    stopRecoding() {
      axios
        .post(`${OPENVIDU_SERVER_URL}/openvidu/api/recordings/stop/${this.myRecodingId}`, JSON.stringify({
          recoding: this.myRecodingId,
        }), {
          auth: {
            username: 'OPENVIDUAPP',
            password: OPENVIDU_SERVER_SECRET,
          },
        })
        .then((res) => {
          this.savedUrls.push(res.data.url);
          console.log(this.savedUrls);
        });
    },
    joinSession() {
      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu();

      // --- Init a session ---
      this.session = this.OV.initSession();

      // --- Specify the actions when events take place in the session ---

      // On every new Stream received...
      this.session.on('streamCreated', ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
      });

      // On every Stream destroyed...
      this.session.on('streamDestroyed', ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });

      // On every asynchronous exception...
      this.session.on('exception', ({ exception }) => {
        console.warn(exception);
      });

      // --- Connect to the session with a valid user token ---

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      this.getToken(this.mySessionId).then((token) => {
        this.session.connect(token, { clientData: this.myUserName })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---

            const publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true,
              // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true,
              // Whether you want to start publishing with your video enabled or not
              resolution: '1280x720', // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: 'APPEND', // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            this.mainStreamManager = publisher;
            this.publisher = publisher;

            // --- Publish your stream ---

            this.session.publish(this.publisher);
          })
          .catch((error) => {
            console.log('There was an error connecting to the session:', error.code, error.message);
          });
      });

      window.addEventListener('beforeunload', this.leaveSession);
    },

    leaveSession() {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect();

      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;

      window.removeEventListener('beforeunload', this.leaveSession);
    },

    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
    },

    getToken(mySessionId) {
      return this.createSession(mySessionId).then((sessionId) => this.createToken(sessionId));
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-session
    createSession(sessionId) {
      return new Promise((resolve, reject) => {
        const data = JSON.stringify({ customSessionId: sessionId });
        axios
          .post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, data, {
            headers: {
              Authorization: `Basic ${btoa(
                `OPENVIDUAPP:${OPENVIDU_SERVER_SECRET}`,
              )}`,
              'Content-type': 'application/json',
            },
          })
          .then((response) => {
            resolve(response.data.id);
          })
          .catch((error) => {
            if (error.response.status === 409) {
              resolve(sessionId);
            } else {
              console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
              if (window.confirm(`No connection to OpenVidu Server.This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
                window.location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
              }
              reject(error.response);
            }
          });
      });
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-connection
    createToken(sessionId) {
      return new Promise((resolve, reject) => {
        const data = {};
        axios
          .post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, data, {
            headers: {
              Authorization: `Basic ${btoa(
                `OPENVIDUAPP:${OPENVIDU_SERVER_SECRET}`,
              )}`,
              'Content-type': 'application/json',
            },
          })
          .then((response) => {
            resolve(response.data.token);
          })
          .catch((error) => reject(error.response));
      });
    },
  },
}; // 함수 정의해서 컴포넌트 내에서 사용 가능하게 함
</script>

<style scoped>
#hi{
  position: absolute; top: 30%; width: 50%;
  color: white;
}

#video-box {
  width: 100%;
  overflow: hidden;
  margin: 0px auto;
  position: relative;
}

user-video {
  width: 100%
}

#video-text {
  display: hidden;
  position: absolute;
  top: 25%;
  width: 100%;
  color: white;
  opacity: 5;
}

#video-start {
  opacity: 5;
  animation: fadeOutText 5s 2s ease-out forwards;
}

#video-text p {
  margin-top: -24px;
  text-align: center;
  font-size: 48px;
  color: #ffffff;
}

@keyframes fadeOutText {
  100% {
    opacity: 0;
  }
}

.cd-number-wrapper {
width: 80px;
height: 189px;
top: 50%;
margin: 80px auto 0 auto;
font-size: 10em;
font-family: 'Londrina Outline'; /* Bowlby One SC */
}

.cd-number-five {
position: absolute;
opacity: 0;
margin: 0 auto 0 auto;
-webkit-animation: cd-number-five-anim 1.2s ease 0s 1 normal;
-moz-animation: cd-number-five-anim 1.2s ease 0s 1 normal;
-ms-animation: cd-number-five-anim 1.2s ease 0s 1 normal;
-o-animation: cd-number-five-anim 1.2s ease 0s 1 normal;
animation: cd-number-five-anim 1.2s ease 0s 1 normal;
}

@-webkit-keyframes cd-number-five-anim {
from {-webkit-transform: scale(0.5); opacity: 0;}
to {  -webkit-transform: scale(1.3); opacity: 1;}}

@-moz-keyframes cd-number-five-anim {
from {-moz-transform: scale(0.5); opacity: 0;}
to {  -moz-transform: scale(1.3); opacity: 1;}}

@-o-keyframes cd-number-five-anim {
from {-o-transform: scale(0.5); opacity: 0;}
to {  -o-transform: scale(1.3); opacity: 1;}}

@-ms-keyframes cd-number-five-anim {
from {-ms-transform: scale(0.5); opacity: 0;}
to {  -ms-transform: scale(1.3); opacity: 1;}}

@keyframes cd-number-five-anim {
from {transform: scale(0.5); opacity: 0;}
to {  transform: scale(1.3); opacity: 1;}}

.cd-number-four {
position: absolute;
opacity: 0;
-webkit-animation: cd-number-four-anim 1.2s ease 1.2s 1 normal;
-moz-animation: cd-number-four-anim 1.2s ease 1.2s 1 normal;
-ms-animation: cd-number-four-anim 1.2s ease 1.2s 1 normal;
-o-animation: cd-number-four-anim 1.2s ease 1.2s 1 normal;
animation: cd-number-four-anim 1.2s ease 1.2s 1 normal;
}

@-webkit-keyframes cd-number-four-anim {
from {-webkit-transform: scale(0.5); opacity: 0;}
to {  -webkit-transform: scale(1.3); opacity: 1;}}

@-moz-keyframes cd-number-four-anim {
from {-moz-transform: scale(0.5); opacity: 0;}
to {  -moz-transform: scale(1.3); opacity: 1;}}

@-o-keyframes cd-number-four-anim {
from {-o-transform: scale(0.5); opacity: 0;}
to {  -o-transform: scale(1.3); opacity: 1;}}

@-ms-keyframes cd-number-four-anim {
from {-ms-transform: scale(0.5); opacity: 0;}
to {  -ms-transform: scale(1.3); opacity: 1;}}

@keyframes cd-number-four-anim {
from {transform: scale(0.5); opacity: 0;}
to {  transform: scale(1.3); opacity: 1;}}

.cd-number-three {
position: absolute;
opacity: 0;
-webkit-animation: cd-number-three-anim 1.2s ease 2.4s 1 normal;
-moz-animation: cd-number-three-anim 1.2s ease 2.4s 1 normal;
-ms-animation: cd-number-three-anim 1.2s ease 2.4s 1 normal;
-o-animation: cd-number-three-anim 1.2s ease 2.4s 1 normal;
animation: cd-number-three-anim 1.2s ease 2.4s 1 normal;
}

@-webkit-keyframes cd-number-three-anim {
from {-webkit-transform: scale(0.5); opacity: 0;}
to {  -webkit-transform: scale(1.3); opacity: 1;}}

@-moz-keyframes cd-number-three-anim {
from {-moz-transform: scale(0.5); opacity: 0;}
to {  -moz-transform: scale(1.3); opacity: 1;}}

@-o-keyframes cd-number-three-anim {
from {-o-transform: scale(0.5); opacity: 0;}
to {  -o-transform: scale(1.3); opacity: 1;}}

@-ms-keyframes cd-number-three-anim {
from {-ms-transform: scale(0.5); opacity: 0;}
to {  -ms-transform: scale(1.3); opacity: 1;}}

@keyframes cd-number-three-anim {
from {transform: scale(0.5); opacity: 0;}
to {  transform: scale(1.3); opacity: 1;}}

.cd-number-two {
position: absolute;
opacity: 0;
-webkit-animation: cd-number-two-anim 1.2s ease 3.6s 1 normal;
-moz-animation: cd-number-two-anim 1.2s ease 3.6s 1 normal;
-ms-animation: cd-number-two-anim 1.2s ease 3.6s 1 normal;
-o-animation: cd-number-two-anim 1.2s ease 3.6s 1 normal;
animation: cd-number-two-anim 1.2s ease 3.6s 1 normal;
}

@-webkit-keyframes cd-number-two-anim {
from {-webkit-transform: scale(0.5); opacity: 0;}
to {  -webkit-transform: scale(1.3); opacity: 1;}}

@-moz-keyframes cd-number-two-anim {
from {-moz-transform: scale(0.5); opacity: 0;}
to {  -moz-transform: scale(1.3); opacity: 1;}}

@-o-keyframes cd-number-two-anim {
from {-o-transform: scale(0.5); opacity: 0;}
to {  -o-transform: scale(1.3); opacity: 1;}}

@-ms-keyframes cd-number-two-anim {
from {-ms-transform: scale(0.5); opacity: 0;}
to {  -ms-transform: scale(1.3); opacity: 1;}}

@keyframes cd-number-two-anim {
from {transform: scale(0.5); opacity: 0;}
to {  transform: scale(1.3); opacity: 1;}}

.cd-number-one {
position: absolute;
opacity: 0;
-webkit-animation: cd-number-one-anim 1.2s ease 4.8s 1 normal;
-moz-animation: cd-number-one-anim 1.2s ease 4.8s 1 normal;
-ms-animation: cd-number-one-anim 1.2s ease 4.8s 1 normal;
-o-animation: cd-number-one-anim 1.2s ease 4.8s 1 normal;
animation: cd-number-one-anim 1.2s ease 4.8s 1 normal;
}

@-webkit-keyframes cd-number-one-anim {
from {-webkit-transform: scale(0.5); opacity: 0;}
to {  -webkit-transform: scale(1.3); opacity: 1;}}

@-moz-keyframes cd-number-one-anim {
from {-moz-transform: scale(0.5); opacity: 0;}
to {  -moz-transform: scale(1.3); opacity: 1;}}

@-o-keyframes cd-number-one-anim {
from {-o-transform: scale(0.5); opacity: 0;}
to {  -o-transform: scale(1.3); opacity: 1;}}

@-ms-keyframes cd-number-one-anim {
from {-ms-transform: scale(0.5); opacity: 0;}
to {  -ms-transform: scale(1.3); opacity: 1;}}

@keyframes cd-number-one-anim {
from {transform: scale(0.5); opacity: 0;}
to {  transform: scale(1.3); opacity: 1;}}

</style>
