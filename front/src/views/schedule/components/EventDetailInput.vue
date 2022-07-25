<template>
  <div>
    <div id="div-category">
      <label for="category">분 류: <select name="category" id="category">
        <option value="practice">1:1 모의 면접</option>
        <option value="consulting-job">직무 상담</option>
        <option value="consulting-introduction">자소서 상담</option>
        <option value="else">기타</option>
      </select></label>
    </div>
    <div>
      <label for="detail">상세정보: <input type="text" id="detail"></label>
    </div>
    <div id="add-button">
      <button>추 가</button>
    </div>
  </div>
</template>

<script>
export default {

};
</script>

<style scoped>
button {
  margin-top: 1.5vw;
  width: 6vw;
  height: 3vw;
  font-size: 1vw;
}

select {
  width: 12vw;
}

#detail {
  margin-top: 1.5vw;
}

#div-category {
  display: flex;
  justify-content: start;
}

#category {
  display: inline;
  text-align: center;
}

#add-button {
  display: flex;
  justify-content: center;
}

label {
  margin-left: 2vw;
}
</style>
