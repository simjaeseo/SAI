<template>
  <div>
    <form @submit.prevent="userUpdate">
        <div class='container'>
          <div class='row'>
            <!-- 프로필이미지 -->
            <div id='profile_image_box' class='col-sm-2'>
              <img v-if="!!state.imgUrl" :src="state.imgUrl" alt="preview"
              id='user_profile_img'>
              <img v-else-if="currentUser.profilePicture"
              :src="require(`../../../../../image/${currentUser.profilePicture.fileName}`)"
              alt="preview"
              id='user_profile_img'>
              <img v-else src='@/assets/profile5.png' alt='basic-img' id='user_profile_img'> <br>
                <div class="filebox">
                    <label for='ex_file'><input type='file' id='ex_file' accept='image/*'
                    ref='image' class='upload-box' @change="onInputImage">파일선택</label>
                </div>
            </div>
            <!-- 프로필인적사항 -->
            <div id='personal-data-box1' class='col-sm-10'>
              <!-- 왼쪽 -->
              <div class='row mt-2' id='personal-data-box2'>
                <div class='col-lg-6'>
                    <p id='data-name'>이름</p>
                    <p id="user-data">{{ currentUser.name }}</p>
                    <p id='data-name'>소속</p><br>
                    <select class='form-select mb-3 mt-2' id='form-select-region'
                    aria-label='Default select example'
                    @change="changeCity">
                        <option selected disabled>{{ currentUser.campus.city }}</option>
                        <option value='서울'>서울</option>
                        <option value='대전'>대전</option>
                        <option value='광주'>광주</option>
                        <option value='구미'>구미</option>
                        <option value='부울경'>부울경</option>
                    </select><br>
                    <p id='data-name'>연락처</p><br>
                    <select class='form-select mt-2' id='form-select-first'
                    aria-label='Default select example'>
                        <option value='1'>010</option>
                        <option value='2'>011</option>
                        <option value='3'>016</option>
                        <option value='4'>017</option>
                        <option value='5'>018</option>
                        <option value='5'>019</option>
                    </select>
                    <label for="user_signup_number1">
                        <input type="text" class="form-control" id='form-select-second'
                        v-model="state.mobileSecond" maxlength="4">
                    </label>
                    <label for="user_signup_number2">
                        <input type="text" class="form-control" id='form-select-second'
                        v-model="state.mobileLast" maxlength="4">
                    </label>
                </div>
              <!-- 오른쪽 -->
                <div class='col-lg-6'>
                    <p id='data-name'>생년월일</p>
                    <p id="user-data">{{ currentUser.birthday }}</p>
                    <p id='data-name'>이메일</p>
                    <p id="user-data">{{ currentUser.email }}</p>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- 버튼 -->
        <div id='btn-box'>
          <router-link :to="{ name: 'Main' }">
            <button class='btn' id='cancel-btn' type="button">취소</button>
          </router-link>
            <button class='btn'
            type='submit'
            id='update-btn'>완료</button>
        </div>
    </form>
  </div>
</template>

<script>
import { computed, reactive, onBeforeMount } from 'vue';
import { useStore } from 'vuex';

export default {
  name: 'ProfileDataUpdate',
  setup() {
    const store = useStore();
    const state = reactive({
      mobileFirst: '',
      mobileSecond: '',
      mobileLast: '',
      file: '',
      url: '',
      imgUrl: '',
      userCity: '',
    });
    const isLoggedIn = computed(() => store.getters.isLoggedIn);
    const currentUser = computed(() => store.getters.currentUser);
    let img = null;
    onBeforeMount(() => {
      const userPhone = currentUser.value.phone;
      state.mobileFirst = userPhone.substr(0, 3);
      state.mobileSecond = userPhone.substr(3, 4);
      state.mobileLast = userPhone.substr(7);
      state.userCity = currentUser.value.campus.city;
      if (currentUser.value.profilePictrue != null) {
        img = currentUser.value.profilePictrue.fileName;
      }
    });
    const noChange = function () {
      alert('변경할 수 없는 값입니다.');
    };
    const userUpdate = () => {
      const data = {
        phone: state.mobileFirst + state.mobileSecond + state.mobileLast,
        campus: {
          city: state.userCity,
        },
      };
      console.log(data.phone);
      console.log(data.campus.city);
      console.log(img);
      const formData = new FormData();
      formData.append('file', img);
      formData.append('request', new Blob([JSON.stringify(data)], { type: 'application/json' }));
      store.dispatch('userUpdateCT', formData);
    };
    const onInputImage = (e) => {
      state.file = e.target.files;
      state.url = URL.createObjectURL(state.file[0]);
      state.imgUrl = state.url;
      console.log(state.imgUrl);
      [img] = e.target.files;
    };
    const changeCity = function (event) {
      console.log(event.target.value);
      state.userCity = event.target.value;
    };
    return {
      isLoggedIn,
      currentUser,
      state,
      noChange,
      onInputImage,
      userUpdate,
      changeCity,
    };
  },
};
</script>

<style scoped>
#user-data {
  font-size: 20px;
  margin-bottom: 10px;
  color: #707070;
  font-size: 15px;
}
#data-name {
  font-size: 12px;
  color: #707070;
  display: inline;
  padding: 3px;
  border-radius: 15px;
  background-color: #626db325;
}
.modal-body {
  height: 500px;
}
#box1 {
  display: flex;
  justify-content: space-between;
}
#for-inline {
  display: inline;
}
#add-btn {
  z-index: 1000;
  color: #5c6ac4;
  background-color: white;
  border: 1px solid #5c6ac4;
  display: inline-block;
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
  height: 40px;
  width: 90px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
  margin-bottom: 10px;
}

@media (prefers-reduced-motion: reduce) {
  #add-btn {
    transition: none;
  }
}
#add-btn:hover {
  color: white;
  background-color: #5c6ac4;
}
.form-select:focus {
  color: #000000;
  background-color: rgb(255, 255, 255);
  border-color: #ffffff;
  outline: 0;
  box-shadow: 0 0 0 0.1rem #5c6ac4;
}
#update-btn {
  z-index: 1000;
  color: #5c6ac4;
  background-color: white;
  border: 1px solid #5c6ac4;
  display: inline-block;
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
  height: 50px;
  width: 90px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
  margin-left: 20px;
}

@media (prefers-reduced-motion: reduce) {
  #update-btn {
    transition: none;
  }
}
#update-btn:hover {
  color: white;
  background-color: #5c6ac4;
}
#update-btn2 {
  z-index: 1000;
  color: #5c6ac4;
  background-color: white;
  border: 1px solid #5c6ac4;
  display: inline-block;
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
  height: 40px;
  width: 90px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
  margin-left: 10px;
}

@media (prefers-reduced-motion: reduce) {
  #update-btn2 {
    transition: none;
  }
}
#update-btn2:hover {
  color: white;
  background-color: #5c6ac4;
}
#cancel-btn {
  z-index: 1000;
  color: #5c6ac4;
  background-color: white;
  border: 1px solid #5c6ac4;
  display: inline-block;
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
  height: 50px;
  width: 90px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

@media (prefers-reduced-motion: reduce) {
  #cancel-btn {
    transition: none;
  }
}
#cancel-btn:hover {
  color: white;
  background-color: #5c6ac4;
}
#cancel-btn2 {
  z-index: 1000;
  color: #5c6ac4;
  background-color: white;
  border: 1px solid #5c6ac4;
  display: inline-block;
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
  height: 40px;
  width: 90px;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
  border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

@media (prefers-reduced-motion: reduce) {
  #cancel-btn2 {
    transition: none;
  }
}
#cancel-btn2:hover {
  color: white;
  background-color: #5c6ac4;
}
#btn-box {
    text-align: center;
    margin-top: 40px;
}
#form-select-region {
    width: 300px;
    display: inline;
}
#form-select-region:hover {
    width: 300px;
    display: inline;
    box-shadow: 0 0 0 0.1rem #5c6ac496;
}
#form-select-class {
    width: 95px;
    display: inline;
    margin-left: 7.5px;
}
#form-select-class:hover {
    width: 95px;
    display: inline;
    margin-left: 7.5px;
    box-shadow: 0 0 0 0.1rem #5c6ac496;
}
#form-select-cardinal-number {
  width: 95px;
  display: inline;
}
#form-select-cardinal-number:focus {
  color: #000000;
  background-color: rgb(255, 255, 255);
  border-color: #ffffff;
  outline: 0;
  box-shadow: 0 0 0 0.1rem #5c6ac496;
}
#form-select-first {
    display: inline;
    width: 95px;
}
#form-select-first:focus {
    color: #000000;
    background-color: rgb(255, 255, 255);
    border-color: #ffffff;
    outline: 0;
    box-shadow: 0 0 0 0.1rem #5c6ac496;
}
#form-select-second {
    width: 95px;
    margin-left: 7.5px;
}
#form-select-second:focus {
    color: #000000;
    background-color: rgb(255, 255, 255);
    border-color: #ffffff;
    outline: 0;
    box-shadow: 0 0 0 0.1rem #5c6ac496;
}
.form-control {
    width: 300px;
}
.form-control:focus {
    color: #000000;
    background-color: rgb(255, 255, 255);
    border-color: #ffffff;
    outline: 0;
    box-shadow: 0 0 0 0.1rem #5c6ac496;
}
#data-name {
  font-size: 12px;
}
#user-data {
  font-size: 20px;
  margin-bottom: 10px;
}
p {
  margin-bottom: 5px;
  margin-top: 5px;
}
#personal-video-box1 {
    margin-top: 60px;
    width: 100%;
}
#personal-video-box2 {
    background-color: #5c6ac40c;
    width: 100%;
    height: 250px;
    margin-bottom: 40px;
    border-radius: 10px;
}
#personal-video-box3 {
    background-color: #5c6ac40c;
    width: 100%;
    height: 250px;
    border-radius: 10px;
}
#personal-data-box1 {
    background-color: #5c6ac40c;
    text-align: start;
    height: 250px;
    border-radius: 10px;
}
#profile_image_box {
  text-align: center;
  display: inline-block;
}
#user_profile_img {
  width: 100%;
  height: 80%;
  border: 1px solid #d0d0d0;
  display: inline-block;
}

body {margin: 10px}
.where {
  display: block;
  margin: 25px 15px;
  font-size: 11px;
  color: #000;
  text-decoration: none;
  font-family: verdana;
  font-style: italic;
}
.filebox {display:inline-block; margin: auto;}

.filebox label {
  display: inline-block;
  padding: .3em .7em;
  color:#5c6ac4;
  font-size: inherit;
  line-height: normal;
  vertical-align: middle;
  background-color: #ffffff;
  cursor: pointer;
  border: 1px solid #5c6ac4;
  border-radius: .25em;
  margin-top:10px;
}
.filebox label:hover {
  display: inline-block;
  padding: .3em .7em;
  color:#ffffff;
  font-size: inherit;
  line-height: normal;
  vertical-align: middle;
  background-color: #5c6ac4;
  cursor: pointer;
  border: 1px solid #5c6ac4;
  border-radius: .25em;
  margin-top:10px;
}

.filebox input[type="file"] {  /* 파일 필드 숨기기 */
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip:rect(0,0,0,0);
  border: 0;
}

</style>
