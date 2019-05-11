<template>
  <v-toolbar dark fixed scroll-off-screen>
    <v-btn flat to="/" class="home">OpenStudy</v-btn>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn flat to="/login">로그인</v-btn>
      <v-btn flat to="/join">회원가입</v-btn>
    </v-toolbar-items>
  </v-toolbar>
</template>

<script>
  export default {}
</script>

<style scoped>
  .home {
    cursor: pointer;
    font-size: 20px;
    font-weight: 500;
    letter-spacing: 0.02em;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .home:before {
    background-color: #212121;
  }
</style>
