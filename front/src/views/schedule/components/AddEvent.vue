<template>
  <div>

    <div id="btn-box">
      <select
      class="form-select"
      aria-label="Default select example"
      @change="pickMyConsultant"
      required>
        <option selected disabled>컨설턴트상담</option>
        <option
        v-for="myConsultant in myConsultants"
        :key="myConsultant"
        :value="myConsultant.id">
          {{ myConsultant.name }}
        </option>
      </select>
        <button
        class="btn"
        @click.prevent="noConsultant(null)">
          개인일정추가
        </button>
    </div>

    <div>
      <div id="time-picker">
        <time-picker></time-picker>
      </div>

      <div id="event-detail-input">
        <event-detail-input></event-detail-input>
      </div>

    </div>
  </div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';
import TimePicker from './TimePicker.vue';
import EventDetailInput from './EventDetailInput.vue';

export default {
  name: 'AddEvent',
  props: ['cKey'],
  components: {
    TimePicker,
    EventDetailInput,
  },
  setup() {
    const store = useStore();

    const selectedDate = computed(() => store.getters.selectedDate);
    const myConsultants = computed(() => store.getters.myConsultants);
    const fetchMyConsultants = () => {
      store.dispatch('fetchMyConsultants');
    };
    const pickMyConsultant = (e) => {
      store.dispatch('pickMyConsultant', e.target.value);
    };
    const noConsultant = (value) => {
      store.dispatch('pickMyConsultant', value);
    };
    return {
      selectedDate,
      myConsultants,
      fetchMyConsultants,
      pickMyConsultant,
      noConsultant,
    };
  },
  created() {
    this.fetchMyConsultants();
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
