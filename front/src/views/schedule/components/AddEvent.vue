<template>
  <div>
    <div id="select-button">
        <label for="select-CT"><select name="select-CT" id="select-CT">
          <option value="">컨설턴트 선택</option>
          <option value="kim-CT">김씨티</option>
          <option value="choi-CT">최씨티</option>
          <option value="gang-CT">강씨티</option>
        </select></label>
        <button>일정 추가</button>
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
#time-picker {
    position: relative;
    border: 1px solid black;
    margin-top: 3%;
    padding-top: 1.5vw;
    padding-bottom: 1.5vw;
    width: 25vw;
    height: 20vw;
    left: 50%;
    transform: translate(-50%);
}

#event-detail-input {
    position: relative;
    border: 1px solid black;
    margin-top: 7%;
    padding-top: 1.5vw;
    padding-bottom: 1.5vw;
    width: 25vw;
    height: 13vw;
    left: 50%;
    transform: translate(-50%);
}

#select-button {
  display: flex;
  justify-content: center;
}
</style>
