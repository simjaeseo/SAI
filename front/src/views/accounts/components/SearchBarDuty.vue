<template>
  <div>
    <div class="max-w-xs relative space-y-3">
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
        <p v-for="country in selectedCountries" :key="country" class='btn'
        id='selected-item'
        @keydown="selectedDeleteItem(country.name)"
        @click="[selectedDeleteItem(country.name), deleteplus(country.name)]">
        #{{ country.name }}<span id='delete'> x</span></p>

        <p v-for="(user, index) in Jobs" :key="index" class='btn'
        id='selected-item'
        @click="selectedDeleteItem2(user)"
        @keydown="selectedDeleteItem2(user)">
        #{{ user.jobName }}<span id='delete'> x</span></p>
      </div>
    </div>
  </div>
</template>

<script>
import countries from '@/data/duty.json';
import {
  ref,
  computed,
  // onBeforeMount,
} from 'vue';
import { useStore } from 'vuex';

export default {
  name: 'SearchBar',
  setup() {
    const store = useStore();
    const plusJob = [];
    const isLoggedIn = computed(() => store.getters.isLoggedIn);
    const currentUser = computed(() => store.getters.currentUser);
    const Jobs = computed(() => store.getters.userJob);
    const searchTerm = ref('');

    // const Jobs = [];
    // onBeforeMount(() => {
    //   Job.value.forEach((el) => {
    //     if (!Jobs.includes(el)) {
    //       Jobs.push(el);
    //     }
    //   });
    // });
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
    };
    const deleteplus = function (event) {
      for (let i = 0; i < plusJob.length; i += 1) {
        if (plusJob[i].name === event) {
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
      for (let i = 0; i < this.selectedCountries.length; i += 1) {
        if (this.selectedCountries[i].name === this.deleteItem) {
          this.selectedCountries.splice(i, 1);
          i -= 1;
        } this.$forceUpdate();
      }
    },
    selectedDeleteItem2(event) {
      const data = event.jobName;
      for (let i = 0; i < this.Jobs.length; i += 1) {
        if (this.Jobs[i].jobName === data) {
          this.Jobs.splice(i, 1);
          i -= 1;
        } this.$forceUpdate();
      }
      const newJobs = JSON.parse(JSON.stringify(this.Jobs));
      this.$store.dispatch('updateJob', newJobs);
    },
  },
  // updated() {
  //   // 유저가 추가를 하면 즉 추가선택한 리스트의 길이가 0이 아니라면
  //   this.$nextTick(function () {
  //     if (this.selectedCountries.length !== 0) {
  //       for (let i = 0; i < this.selectedCountries.length; i += 1) {
  //         this.allEnterprises.push(this.selectedCountries[i].name);
  //       }
  //       const unique = {};
  //       this.allEnterprises.forEach((el) => {
  //         unique[el] = true;
  //       });
  //       for (let i = 0; i < this.userPickList.length; i += 1) {
  //         this.allEnterprises.push(this.userPickList[i]);
  //       }
  //       const unique3 = {};
  //       this.allEnterprises.forEach((el) => {
  //         unique3[el] = true;
  //       });
  //     }
  //     this.$emit('enterprises', this.allEnterprises);
  //   });
  // },
  // // 유저가 관심기업 업데이트 안했을때 자동 에밋
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
