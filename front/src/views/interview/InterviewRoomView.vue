<template>
    <div class="container">
      <div id="session" v-if="session">
        <div id="session-header">
        </div>
        <div id="main-video">
          <user-video :stream-manager="mainStreamManager"/>
          <div class="d-flex flex-row-reverse">
            <input class="btn btn-light me-2" type="button"
            id="buttonLeaveSession" @click="leaveSession" value="면접 종료하기">
          </div>
        </div>
        <div>{{ questionList }}</div>
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
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      mySessionId: 'SessionA',
      myUserName: `Participant${Math.floor(Math.random() * 100)}`,
    };
  },
  setup() {
    const store = useStore();

    const selectedQuestionList = computed(() => store.getters.selectedQuestionList);

    return {
      selectedQuestionList,
    };
  },
  created() {}, // 해당 vue 파일이 실행 되는 순간
  mounted() {
    this.joinSession();
  }, // 템플릿 내 HTML DOM이 화면에 로딩이 되는 순간, 마운트가 다 끝난 순간 실행
  unmounted() {}, // 컴포넌트 이동 시 unmount가 일어나면서 해당 코드 자동 실행
  methods: {
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
      this.$router.push('/');
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
        axios
          .post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
            customSessionId: sessionId,
          }), {
            auth: {
              username: 'OPENVIDUAPP',
              password: OPENVIDU_SERVER_SECRET,
            },
          })
          .then((response) => response.data)
          .then((data) => resolve(data.id))
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
        axios
          .post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
            auth: {
              username: 'OPENVIDUAPP',
              password: OPENVIDU_SERVER_SECRET,
            },
          })
          .then((response) => response.data)
          .then((data) => resolve(data.token))
          .catch((error) => reject(error.response));
      });
    },
  },
}; // 함수 정의해서 컴포넌트 내에서 사용 가능하게 함
</script>

<style scoped>

</style>
