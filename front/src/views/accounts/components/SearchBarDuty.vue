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
        placeholder="&#128269;  직무를 검색하세요"
        class="p-3 mb-0.5 w-full form-control"
        style="width:460px"
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
          <p id='result'>
            {{ country.name }}
          </p>
        </li>
      </ul>
      <div v-if="selectedCountry">
        <button v-for="country in selectedCountries" :key="country" class='btn'
        id='selected-item'
        @click.prevent="selectedDeleteItem(country)">
        #{{ country }}<span id='delete'> x</span></button>

      </div>
    </div>
  </div>
</template>

<script>
import countries from '@/data/duty.json';
import { ref, computed } from 'vue';

export default {
  name: 'SearchBar',
  data() {
    return {
      deleteItem: '',
      isClicked: '',
    };
  },
  setup() {
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
    const selectedCountry = ref('');
    const selectedCountries = [];
    const selectCountry = (country) => {
      selectedCountry.value = country;
      selectedCountries.push(selectedCountry.value);
      searchTerm.value = '';
    };

    return {
      countries,
      searchTerm,
      searchCountries,
      selectCountry,
      selectedCountry,
      selectedCountries,
    };
  },
  updated() {
    this.$emit('companies', this.selectedCountries);
  },
  methods: {
    selectedDeleteItem(event) {
      this.deleteItem = event;
      for (let i = 0; i < this.selectedCountries.length; i += 1) {
        if (this.selectedCountries[i] === this.deleteItem) {
          this.selectedCountries.splice(i, 1);
          i -= 1;
        }
      } this.$forceUpdate();
    },
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
  height: 300px;
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
}

@media (prefers-reduced-motion: reduce) {
  #selected-item {
    transition: none;
  }
}
#selected-item:hover {
  color: #5c6ac4;
  background-color: #ffffff;
  border: 1px solid white;
}
ul {
  background-color: white;
  width: 460px;
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
