<template>
  <div>
    <div id="btn-box">
      <button class="btn"
      @click.prevent="noConsultant(null)">
        개인일정추가
      </button>
    </div>
    <!-- <p>{{ selectedDate }}</p> -->
    <div>
      <div id="time-picker">
        <time-picker-CT></time-picker-CT>
      </div>
      <div id="event-detail-input">
        <event-detail-input-CT></event-detail-input-CT>
      </div>
    </div>
  </div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';
import TimePickerCT from './TimePickerCT.vue';
import EventDetailInputCT from './EventDetailInputCT.vue';

export default {
  name: 'AddEvent',
  components: {
    TimePickerCT,
    EventDetailInputCT,
  },
  setup() {
    const store = useStore();

    const selectedDate = computed(() => store.getters.selectedDate);
    const noConsultant = (value) => {
      store.dispatch('pickMyConsultant', value);
    };
    return {
      selectedDate,
      noConsultant,
    };
  },
};
</script>

<style scoped>
#btn-box {
  display: flex;
  justify-content: space-evenly;
  margin-top: 20px;
}
.btn {
  width: 150px;
  border: 1px solid #ced4da;
  color: #141414;
}
.btn:focus{
  width: 150px;
  border: 1px solid #5c6ac4;
  color: #141414;
}

.form-select {
  width: 150px;
  display: inline;
}
.form-select:focus {
  box-shadow: none;
  border: 1px solid #5c6ac4;
}
</style>
