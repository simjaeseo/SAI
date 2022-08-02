<template>
  <div>
    <div id="btn-box">
      <select class="form-select" aria-label="Default select example">
        <option selected>컨설턴트상담</option>
        <option value="1">One</option>
        <option value="2">Two</option>
        <option value="3">Three</option>
      </select>
        <button class="btn">개인일정추가</button>
    </div>
    <div>
      <div id="time-picker">
        <time-picker></time-picker>
      </div>
      <div id="event-detail-input">
        <event-detail-input></event-detail-input>
      </div>
    </div>
    <!-- 주석! -->
    <!-- <div id="btn-box">
      <select class="form-select" aria-label="Default select example">
        <option selected>컨설턴트상담</option>
        <option
        v-for="myConsultant in myConsultants"
        :key="myConsultant"
        :value="myConsultant.member_id"
        @click="pickMyConsultant(myConsultant.member_id)">
          {{ myConsultant.name }}
        </option>
      </select>
        <button
        class="btn"
        @click="pickMyConsultant(false)">
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
    </div> -->
  </div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';
import TimePicker from './TimePicker.vue';
import EventDetailInput from './EventDetailInput.vue';

export default {
  name: 'AddEvent',
  components: {
    TimePicker,
    EventDetailInput,
  },
  setup() {
    const store = useStore();

    const myConsultants = computed(() => store.getters.myConsultants);
    const fetchMyConsultants = () => {
      store.dispatch('fetchMyConsultants');
    };
    const pickMyConsultant = (date) => {
      store.dispatch('pickMyConsultant', date);
    };
    return {
      myConsultants,
      fetchMyConsultants,
      pickMyConsultant,
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
