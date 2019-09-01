<template>
  <v-toolbar dark fixed scroll-off-screen class="nav">
    <v-btn flat to="/" class="home">OpenStudy</v-btn>
    <v-spacer></v-spacer>
    <v-toolbar-items class="hidden-xs-only">
      <v-btn flat to="/study/register">스터디등록</v-btn>
      <template v-if="!this.authenticated">
        <v-btn flat to="/login">로그인</v-btn>
        <v-btn flat to="/user/signUp">회원가입</v-btn>
      </template>
      <template v-else>
        <v-btn flat @click="logout()">로그아웃</v-btn>
      </template>
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
    import {mapGetters, mapActions} from 'vuex'

    export default {
        data() {
            return {}
        },
        computed: {
            ...mapGetters({authenticated: 'user/authenticated'}),
        },
        methods: {
            ...mapActions({
                setAuthenticated: 'user/setAuthenticated',
                setAccessToken: 'user/setAccessToken',
                setRefreshToken: 'user/setRefreshToken',
            }),
            logout() {
                this.setAuthenticated(false);
                this.setAccessToken(null);
                this.setRefreshToken(null);
                window.localStorage.removeItem("access-token");
                window.localStorage.removeItem("refresh-token");
                alert("로그아웃하셨습니다.");
                this.$router.push("/");
            }
        }
    }
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
  .nav {
    z-index: 999;
  }
</style>
