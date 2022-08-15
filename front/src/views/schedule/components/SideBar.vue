<template>
  <div>
    <show-event v-if="isUpcomingSchedules" v-show="!selectedDate"></show-event>
    <add-event
    v-show="selectedDate || !isUpcomingSchedules"
    @forceRerender="forceRerender">
    </add-event>
  </div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';
import AddEvent from './AddEvent.vue';
import ShowEvent from './ShowEvent.vue';

export default {
  components: {
    AddEvent,
    ShowEvent,
  },
  props: {
    cKey: Number,
  },
  setup() {
    const store = useStore();

    const isUpcomingSchedules = computed(() => store.getters.isUpcomingSchedules);
    const selectedDate = computed(() => store.getters.selectedDate);
    const fetchUpcomingSchedules = () => {
      store.dispatch('fetchUpcomingSchedules');
    };
    return {
      isUpcomingSchedules,
      selectedDate,
      fetchUpcomingSchedules,
    };
  },
  methods: {
    forceRerender() {
      this.$emit('forceRerender');
    },
  },
};
</script>

<style>

</style>
