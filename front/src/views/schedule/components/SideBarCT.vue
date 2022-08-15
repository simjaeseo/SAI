<template>
  <div>
    <show-event-CT v-if="isUpcomingSchedules" v-show="!selectedDate"></show-event-CT>
    <add-event-CT
    v-show="selectedDate || !isUpcomingSchedules"
    @forceRerender="forceRerender">
    </add-event-CT>
  </div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';
import AddEventCT from './AddEventCT.vue';
import ShowEventCT from './ShowEventCT.vue';

export default {
  components: {
    AddEventCT,
    ShowEventCT,
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
