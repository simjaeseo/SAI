<template>
  <div>
    <select
    id="select-ct"
    class="form-select"
    aria-label="Default select example"
    @change="pickMyConsultant"
    :style="[selectedConsultant ?
    {background:'#5c6ac4', color:'#ffffff'} : {background:'#ffffff'}]"
    required>
      <option selected disabled>상담 예약</option>
      <option
      v-for="myConsultant in myConsultants"
      :key="myConsultant"
      :value="myConsultant.id">
        {{ myConsultant.name }} 컨설턴트
      </option>
    </select>
  </div>
</template>

<script>
import { computed, onMounted } from 'vue';
import { useStore } from 'vuex';

export default {
  props: {
    selectedConsultant: String,
  },
  setup() {
    const store = useStore();

    const myConsultants = computed(() => store.getters.myConsultants);
    const fetchMyConsultants = () => {
      store.dispatch('fetchMyConsultants');
    };

    onMounted(() => {
      fetchMyConsultants();
    });
    return {
      myConsultants,
      fetchMyConsultants,
    };
  },
  methods: {
    pickMyConsultant(e) {
      this.$emit('pickMyConsultant', e.target.value);
    },
  },
};
</script>

<style scoped>
/* #select-ct {
  height: 40px;
  border-radius: 10px;
  border-color: #5c6ac4;
  text-align: center;
  padding: 0;
  color: #5c6ac4;
}
#select-ct:hover {
  height: 40px;
  border-radius: 10px;
  border-color: #5c6ac4;
  text-align: center;
  padding: 0;
  color: #5c6ac4;
  font-weight: 500;
  cursor: pointer;
} */
</style>
