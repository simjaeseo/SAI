<template>
  <div>
    <div class="max-w-xs relative space-y-3 mt-2">
      <label
        for="search"
        class="text-gray-900"
      >
      <input
        type="text"
        id="search"
        v-model="searchTerm"
        placeholder="&#128269;  직무를검색하세요"
        class="p-3 mb-0.5 w-full form-control"
        style="width:300px; height:36px"
        @keydown.enter.prevent
      >
      </label>

      <ul
        v-if="searchCountries.length"
        class="border border-gray-300"
      >
        <li>
          <p id='result-count'>
            {{ countries.length }} 개의 직무중 {{ searchCountries.length }}개가 검색되었습니다.
          </p>
        </li>
        <li
            v-for="country in searchCountries"
            :key="country.name"
            @click="selectCountry(country.name)"
            @keydown="selectCountry(country.name)"
            @keyup="selectCountry(country.name)"
        >
          <p id='result' @click="addJob(country.name)" @keydown="addJob(country.name)">
            {{ country.name }}
          </p>
        </li>
      </ul>
      <div v-if="selectedCountry">
        <p v-for="(user, index) in Jobs" :key="index" class='btn'
        id='selected-item'
        @click="selectedDeleteItem2(user)"
        @keydown="selectedDeleteItem2(user)">
        {{ user.name }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import {
  ref,
  computed,
} from 'vue';
import { useStore } from 'vuex';
import countries from '@/data/duty.json';

export default {
  name: 'SearchBar',
  setup() {
    const store = useStore();
    let plusJob = [];
    const isLoggedIn = computed(() => store.getters.isLoggedIn);
    const currentUser = computed(() => store.getters.currentUser);
    // eslint-disable-next-line
    const Jobs = computed(() => [...new Set(store.getters.userJob.map(JSON.stringify))].map(JSON.parse));
    const searchTerm = ref('');

    const searchCountries = computed(() => {
      if (searchTerm.value === '') {
        return [];
      }

      let matches = 0;

      return countries.filter((country) => {
        if (country.name.toLowerCase().includes(searchTerm.value.toLowerCase()) && matches < 10) {
          matches += 1;
          return country;
        }
        return false;
      });
    });
    let selectedCountry = {};
    const selectedCountries = [];
    const selectCountry = (country) => {
      selectedCountry = {
        name: country,
      };
      selectedCountries.push(selectedCountry);
      searchTerm.value = '';
    };
    // 이름을 클릭하면
    const addJob = function (event) {
      // { name : '직업명' } 으로 state의 plusjob에 저장
      const name = event;
      plusJob.push({ name });
      store.dispatch('newJob', {
        plusJob,
      });
      plusJob = [];
    };
    const deleteplus = function (event) {
      console.log(event);
      console.log(plusJob);
      for (let i = 0; i < plusJob.length; i += 1) {
        if (plusJob[i].name === event.name) {
          plusJob.splice(i, 1);
          i -= 1;
        }
        store.dispatch('newJob', {
          plusJob,
        });
      }
    };
    return {
      isLoggedIn,
      currentUser,
      countries,
      searchTerm,
      searchCountries,
      selectCountry,
      selectedCountry,
      selectedCountries,
      addJob,
      deleteplus,
      Jobs,
    };
  },
  methods: {
    selectedDeleteItem(event) {
      this.deleteItem = event;
      const index = this.selectedCountries.findIndex((item) => item.name === this.deleteItem);
      this.selectedCountries.splice(index, 1);
      this.$forceUpdate();
    },
    selectedDeleteItem2(event) {
      console.log(event.name);
      const dataIndex = this.Jobs.findIndex((item) => item.name === event.name);
      console.log(dataIndex);
      this.Jobs.splice(dataIndex, 1);
      this.$forceUpdate();
      const newJobs = JSON.parse(JSON.stringify(this.Jobs));
      console.log(newJobs);
      this.$store.dispatch('updateJob', newJobs);
    },
  },
  created() {
    const originalJobs = JSON.parse(JSON.stringify(this.Jobs));
    this.$store.dispatch('updateJob', originalJobs);
  },
};
</script>

<style scoped>
#delete {
  font-size: 5px;
  vertical-align: top;
}
ul {
  z-index: 1000;
  position: absolute;
  overflow: scroll;
}
#selected-item{
  z-index: 1000;
  color: #5c6ac4;
  font-weight: 400;
  line-height: 1.5;
  text-align: center;
  text-decoration: none;
  vertical-align: middle;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
  font-size: 15px;
  border-radius: 0.25rem;
  padding: 0.5rem;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
  border: none;
}

@media (prefers-reduced-motion: reduce) {
  #selected-item {
    transition: none;
  }
}
#selected-item:hover {
  color: #5c6ac4;
  background-color: none;
  border: none;
}
ul {
  background-color: white;
  width: 300px;
  list-style:none;
  padding-left: 0;
}
#result-count {
  color: gray;
  font-size: 13px;
  text-align: center;
  margin-top: 10px;
}
li:hover {
 cursor: pointer;
 background-color: rgb(233, 233, 233);
}
#result {
  margin-bottom: 0;
  padding: 15px;
  text-align: center;
}
.form-control:focus {
  color: #000000;
  background-color: rgb(255, 255, 255);
  border-color: #ffffff;
  outline: 0;
  box-shadow: 0 0 0 0.1rem #5c6ac496;
}
</style>
