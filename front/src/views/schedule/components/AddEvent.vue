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
  </div>
</template>

<script>
import TimePicker from './TimePicker.vue';
import EventDetailInput from './EventDetailInput.vue';

export default {
  name: 'AddEvent',
  components: {
    TimePicker,
    EventDetailInput,
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
