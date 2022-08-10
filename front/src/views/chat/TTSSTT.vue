<template>
  <div><canvas></canvas></div>
</template>

<script>
import sample from '@/assets/sample.mp3';

export default {
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
      const samples = 700;
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

    drawAudio(sample);
  },
};
</script>

<style scoped>
canvas {
  width: 800px;
  height: 20vw;
  margin: 2rem auto;
}
</style>
