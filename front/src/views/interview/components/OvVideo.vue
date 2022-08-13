<template>
  <video id="video" autoplay>
    <track default
      kind="captions"
      srclang="en"
      src="/media/examples/friday.vtt" />
  </video>
</template>

<script>
import { onMounted } from 'vue';
import * as faceapi from '../../../assets/face-api.min';

export default {
  name: 'OvVideo',

  props: {
    streamManager: Object,
  },

  setup() {
    const MODEL_URL = '/models';
    Promise.all([
      faceapi.nets.tinyFaceDetector.loadFromUri(MODEL_URL),
      faceapi.nets.faceLandmark68Net.loadFromUri(MODEL_URL),
      faceapi.nets.faceRecognitionNet.loadFromUri(MODEL_URL),
      faceapi.nets.faceExpressionNet.loadFromUri(MODEL_URL),
    ]);

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
        }, 100);
      });
    });
  },

  mounted() {
    this.streamManager.addVideoElement(this.$el);
  },
};
</script>

<style>
</style>
