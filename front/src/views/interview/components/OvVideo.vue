<template>
  <video autoplay>
    <track default
      kind="captions"
      srclang="en"
      src="/media/examples/friday.vtt" />
  </video>
</template>

<script>
export default {
  name: 'OvVideo',

  props: {
    streamManager: Object,
  },

  mounted() {
    this.streamManager.addVideoElement(this.$el);
  },
};
</script>
