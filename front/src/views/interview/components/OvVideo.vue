<template>
  <video id="video" autoplay>
    <track default
      kind="captions"
      srclang="en"
      src="/media/examples/friday.vtt" />
  </video>
</template>

<script>
// import { store } from 'vuex';
import { onMounted } from 'vue';
// import { onMounted, reactive, computed } from 'vue';
// import * as faceapi from 'face-api.js';
import * as faceapi from '../../../assets/face-api.min';

export default {
  name: 'OvVideo',

  props: {
    streamManager: Object,
  },

  setup() {
    // const state = reactive({
    //   detections: null,
    //   myEmotion: '',
    //   webCamWidth: 0,
    //   webCamHeight: 0,
    //   hide: false,
    //   timerId: 0,
    //   emotions: {
    //     angry: 0,
    //     disgusted: 0,
    //     fearful: 0,
    //     happy: 0,
    //     neutral: 0,
    //     sad: 0,
    //     surprised: 0,
    //   },
    // });

    // const startExpressDetection = async () => {
    //   console.log('1');
    //   state.timerId = setInterval(async () => {
    //     console.log('2');
    //     console.log(video);
    //     eslint-disable-next-line
    //     state.detections = await faceapi.detectSingleFace(video.value, new faceapi.TinyFaceDetectorOptions()).withFaceExpressions();
    //     console.log('3');
    //     if (state.detections) {
    //       console.log('4');
    //       console.log('4');
    //       let maxVal = 0;
    //       let maxEmotion = '';
    //       // eslint-disable-next-line
    //       for (let emotion in state.detections.expressions) {
    //         console.log('55555');
    //         if (state.detections.expressions[emotion] > maxVal) {
    //           maxVal = state.detections.expressions[emotion];
    //           maxEmotion = emotion;
    //         }
    //       }
    //       state.emotions[maxEmotion] += 1;
    //       console.log(state.emotions);
    //     }
    //   }, 250);
    // };
    // Promise.all([
    //   faceapi.nets.tinyFaceDetector.load('./models'),
    //   // faceapi.nets.faceLandmark68Net.load('./models'),
    //   // faceapi.nets.faceRecognitionNet.load('./models'),
    //   faceapi.nets.faceExpressionNet.load('./models'),
    //   // faceapi.nets.tinyYolov2.loadFromDisk('./models'),
    //   // faceapi.nets.tinyFaceDetector.loadFromUri(faceDetector),
    //   // faceapi.nets.faceLandmark68Net.loadFromUri(faceLandmark),
    //   // faceapi.nets.faceRecognitionNet.loadFromUri(faceRecognition),
    //   // faceapi.nets.faceExpressionNet.loadFromUri(faceExpression),
    // // ]);
    // ])
    const MODEL_URL = '/models';
    Promise.all([
      faceapi.nets.tinyFaceDetector.loadFromUri(MODEL_URL),
      faceapi.nets.faceLandmark68Net.loadFromUri(MODEL_URL),
      faceapi.nets.faceRecognitionNet.loadFromUri(MODEL_URL),
      faceapi.nets.faceExpressionNet.loadFromUri(MODEL_URL),
    ]);

    // window.onload = function () {
    onMounted(() => {
      const video = document.getElementById('video');
      video.addEventListener('play', () => {
        const canvas = faceapi.createCanvasFromMedia(video);
        document.body.append(canvas);
        const displaySize = { width: video.width, height: video.height };
        faceapi.matchDimensions(canvas, displaySize);
        setInterval(async () => {
          // eslint-disable-next-line
          const detections = await faceapi.detectAllFaces(video, new faceapi.TinyFaceDetectorOptions()).withFaceLandmarks().withFaceExpressions();
          console.log(detections[0].expressions);
          // const resizedDetections = faceapi.resizeResults(detections, displaySize);
          // canvas.getContext('2d').clearRect(0, 0, canvas.width, canvas.height);
          // faceapi.draw.drawDetections(canvas, resizedDetections);
          // faceapi.draw.drawFaceLandmarks(canvas, resizedDetections);
          // faceapi.draw.drawFaceExpressions(canvas, resizedDetections);
        }, 100);
      });
    });

    // startExpressDetection();

    onMounted(async () => {
      // const video = document.getElementById('video');

      // // await this.streamManager.addVideoElement(this.$el);
      // // console.log(faceapi);
      // // await faceapi.nets.tinyFaceDetector.load('./models');
      // // await faceapi.nets.faceExpressionNet.load('./models');
      // await startExpressDetection();
    });
    return {
    };
  },

  mounted() {
    this.streamManager.addVideoElement(this.$el);
  },
};
</script>

<style>
</style>
