<template>
  <div>
    <div id="div-category">
      <label for="category">분류
        <select
        v-if="selectedConsultant"
        name="category"
        id="category"
        class="form-select mb-3"
        @change.prevent="selectCategory">
          <option value="practice">1:1 모의 면접</option>
          <option value="consulting-job">직무 상담</option>
          <option value="consulting-introduction">자소서 상담</option>
          <option value="else">기타</option>
        </select>
        <select
        v-else
        name="category"
        id="category"
        class="form-select mb-3"
        @change.prevent="selectCategory">
          <option value="document">서류 지원</option>
          <option value="coding-test">코딩 테스트</option>
          <option value="interview">면접</option>
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
import { computed } from 'vue';
import { useStore } from 'vuex';

export default {
  name: 'EventDetailInput',
  setup() {
    const store = useStore();

    const selectedConsultant = computed(() => store.getters.selectedConsultant);
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
      selectedConsultant,
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
