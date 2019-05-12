<template>
  <v-toolbar dark fixed scroll-off-screen>
    <v-btn flat to="/" class="home">OpenStudy</v-btn>
    <v-spacer></v-spacer>
    <v-toolbar-items class="hidden-xs-only">
      <v-btn flat to="/study/register">스터디등록</v-btn>
      <v-btn flat to="/login">로그인</v-btn>
      <v-btn flat to="/user/signUp">회원가입</v-btn>
    </v-toolbar-items>
    <v-menu class="hidden-sm-and-up">
      <v-toolbar-side-icon slot="activator"></v-toolbar-side-icon>
      <v-list>
        <v-list-tile to="/study/register">
          <v-list-tile-content>
            <v-list-tile-title>스터디등록</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
        <v-list-tile to="/login">
          <v-list-tile-content>
            <v-list-tile-title>로그인</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
        <v-list-tile to="/user/signUp">
          <v-list-tile-content>
            <v-list-tile-title>회원가입</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-menu>
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
