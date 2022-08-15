<template>
  <div v-if="streamManager">
    <ov-video :stream-manager="streamManager" @emotionRatioCount="emotionRatioCount"/>
  </div>
</template>

<script>
import OvVideo from './OvVideo.vue';

export default {
  name: 'UserVideo',

  components: {
    OvVideo,
  },

  props: {
    streamManager: Object,
    emotionCount: Number,
    happy: Number,
  },

  computed: {
    clientData() {
      const { clientData } = this.getConnectionData();
      return clientData;
    },
  },

  methods: {
    getConnectionData() {
      const { connection } = this.streamManager.stream;
      return JSON.parse(connection.data);
    },

    emotionRatioCount(happyRatio) {
      this.$emit('emotionRatioCount', happyRatio);
    },
  },
};
</script>
