<template>
  <div class="body">
    <video id="video" width="720" height="560" autoplay muted></video>
  </div>
</template>

<script>
// // eslint-disable-next-line
import faceExpression from '@/assets/models/face_expression_model-weights_manifest.json';
import faceLandmark from '@/assets/models/face_landmark_68_model-weights_manifest.json';
import faceRecognition from '@/assets/models/face_recognition_model-weights_manifest.json';
import faceDetector from '@/assets/models/tiny_face_detector_model-weights_manifest.json';
import * as faceapi from '../../assets/face-api.min';

export default {
  setup() {
    const video = document.getElementById('video');

    function startVideo() {
      navigator.getUserMedia(
        { video: {} },
        (stream) => { video.srcObject = stream; },
        (err) => console.error(err),
      );
    }

    Promise.all([
      faceapi.nets.tinyFaceDetector.loadFromUri(faceDetector),
      faceapi.nets.faceLandmark68Net.loadFromUri(faceLandmark),
      faceapi.nets.faceRecognitionNet.loadFromUri(faceRecognition),
      faceapi.nets.faceExpressionNet.loadFromUri(faceExpression),
    ]).then(startVideo);

    video.addEventListener('play', () => {
      const canvas = faceapi.createCanvasFromMedia(video);
      document.body.append(canvas);
      const displaySize = { width: video.width, height: video.height };
      faceapi.matchDimensions(canvas, displaySize);
      setInterval(async () => {
      // eslint-disable-next-line
        const detections = await faceapi.detectAllFaces(video, new faceapi.TinyFaceDetectorOptions()).withFaceLandmarks().withFaceExpressions();
        console.log(detections);
        const resizedDetections = faceapi.resizeResults(detections, displaySize);
        canvas.getContext('2d').clearRect(0, 0, canvas.width, canvas.height);
        faceapi.draw.drawDetections(canvas, resizedDetections);
        faceapi.draw.drawFaceLandmarks(canvas, resizedDetections);
        faceapi.draw.drawFaceExpressions(canvas, resizedDetections);
      }, 100);
    });
  },
};
</script>

<style>
.body {
  margin: 0;
  padding: 0;
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

canvas {
  position: absolute;
}
</style>
