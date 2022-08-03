<template>
  <div>
    <div id="div-category">
      <label for="category">분류
        <select
        name="category"
        id="category"
        class="form-select mb-3"
        @change.prevent="selectCategory">
          <option value="block">상담 신청 막기</option>
          <option value="else">기타</option>
        </select>
      </label>
    </div>
    <div id="div-category">
      <label for="detail">상세정보
        <input @change="entryScheduleDetail" type="text" id="detail" class="form-control">
      </label>
    </div>
    <div id="add-button">
      <button class="btn" @click.prevent="createSchedule">등록</button>
    </div>
  </div>
</template>

<script>
import { useStore } from 'vuex';

export default {
  name: 'EventDetailInput',
  setup() {
    const store = useStore();

    const entryScheduleDetail = (detail) => {
      store.dispatch('entryScheduleDetail', detail);
    };
    const selectCategory = (e) => {
      store.dispatch('selectCategory', e.target.value);
    };
    const createSchedule = () => {
      store.dispatch('createSchedule');
    };
    return {
      entryScheduleDetail,
      selectCategory,
      createSchedule,
    };
  },
};
</script>

<style scoped>
#add-button {
  text-align: end;
}
label {
  display: inline;
}
.form-control {
  height: 270px;
}
.form-control:focus {
  border: 1px solid #5c6ac4;
  box-shadow: none;
}
.btn {
  border: 1px solid #5c6ac4;
  color: #5c6ac4;
  margin-top: 10px;
  margin-bottom: 10px;
}
.btn:hover{
  border: 1px solid #5c6ac4;
  background-color: #5c6ac4;
  color: #ffffff;
  margin-top: 10px;
  margin-bottom: 10px;
}
.form-select:focus {
  border: 1px solid #5c6ac4;
  box-shadow: none;
}
</style>
