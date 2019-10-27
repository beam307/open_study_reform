<template>
  <v-footer v-once dark absolute height="auto">
    <v-layout column align-center>
    <p>경기도 성남시</p>
    <p>beam307@naver.com <u></u> 010-xxxx-xxxx</p>
    <p>©2019 OpenStudy Corp</p>
    </v-layout>
  </v-footer>
</template>

<script>
  export default {}
</script>

<style scoped>

  p {
    color: #fff;
    text-align: center;
    font-size: 0.8em;
    margin-bottom: 0px;
  }

  u {
    display: inline-block;
    width: 1px;
    height: 10px;
    background: #ccc;
    margin: 0 4px -2px 6px;
  }
</style>
