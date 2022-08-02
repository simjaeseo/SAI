<template>
  <div>
    <div id="div-category">
      <label for="category">상담분야<select name="category" id="category" class="form-select mb-3">
        <option value="practice">1:1 모의 면접</option>
        <option value="consulting-job">직무 상담</option>
        <option value="consulting-introduction">자소서 상담</option>
        <option value="else">기타</option>
      </select></label>
    </div>
    <div id="div-category">
      <label for="detail">상세정보 <input type="text" id="detail" class="form-control"></label>
    </div>
    <div id="add-button">
      <button class="btn">등록</button>
    </div>
  </div>
</template>

<script>
export default {

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
