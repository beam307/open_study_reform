<template>
  <v-toolbar dark fixed scroll-off-screen>
    <v-toolbar-title @click="goHome" class="home">OpenStudy</v-toolbar-title>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn flat to="/login">로그인</v-btn>
      <v-btn flat to="/join">회원가입</v-btn>
    </v-toolbar-items>
  </v-toolbar>
</template>

<script>
  export default {
    methods: {
      goHome() {
        window.location="/";
      }
    }
  }
</script>

<style scoped>
  .home {
    cursor: pointer;
  }
</style>
