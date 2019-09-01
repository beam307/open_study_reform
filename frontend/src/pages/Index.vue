<template>
  <v-container>
    <v-layout>
      <v-flex>
        <Header/>
        <div id="main">
          <div id="visual">
            <div class="visual_contents">
              <h3>"오픈스터디는 쉽고 빠른 스터디 매칭 서비스입니다"</h3>
              <v-form>
                <v-layout wrap align-center justify-center>
                  <v-flex md4 sm6 xs8>
                    <v-text-field height="36" autofocus label="검색어를 입력하세요." color="#f2f2f2"></v-text-field>
                  </v-flex>
                  <v-flex md2 sm3 xs4>
                    <v-btn light type="submit">검색</v-btn>
                  </v-flex>
                </v-layout>
              </v-form>
            </div>
            <div class="visual_bg"></div>
          </div>
        </div>
        <!--최신스터디-->
        <div class="container main-thumbnail">
          <div class="header">
            <h1>
              NEW STUDY
              <router-link to="/study/list" class="more">더보기</router-link>
            </h1>
          </div>
          <h2 class="line"></h2>
          <v-layout wrap>
            <v-flex xs12 sm4 pa-2 mb-2 v-for="study in studies" :key="study.id">
              <Thumbnail :study="study"></Thumbnail>
            </v-flex>
          </v-layout>
        </div>
        <!--베스트스터디-->
        <div class="container main-thumbnail">
          <div class="header">
            <h1>
              BEST STUDY
              <router-link to="/study/list" class="more">더보기</router-link>
            </h1>
          </div>
          <h2 class="line"></h2>
          <v-layout wrap>
            <v-flex xs12 sm4 pa-2 mb-2 v-for="study in studies" :key="study.id">
              <Thumbnail :study="study"></Thumbnail>
            </v-flex>
          </v-layout>
        </div>
        <!--추천스터디-->
        <div class="container main-thumbnail">
          <div class="header">
            <h1>
              RECOMMENDING STUDY
              <router-link to="/study/list" class="more">더보기</router-link>
            </h1>
          </div>
          <h2 class="line"></h2>
          <v-layout wrap>
            <v-flex xs12 sm4 pa-2 mb-2 v-for="study in studies" :key="study.id">
              <Thumbnail :study="study"></Thumbnail>
            </v-flex>
          </v-layout>
        </div>
        <Footer/>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  import Header from '../components/layouts/Header'
  import Footer from '../components/layouts/Footer'
  import Thumbnail from '../components/common/Thumbnail'

  export default {

    data() {
      return {
        studies: [],
      }
    },
    created() {
      this.$http.get('/api/study/list')
        .then((result) => {
          this.studies = result.data;
          this.studies.map(s => s.meta = JSON.parse(s.meta));
        })
        .catch((e) => {
          console.log(e);
        });
    },
    components: {
      Header,
      Footer,
      Thumbnail
    },
  }
</script>
<style>

  #visual .v-label {
    color: #f2f2f2 !important;
  }

  #visual .v-input__slot:before {
    border-color: #f2f2f2 !important;
  }
</style>
<style scoped>
  #visual {
    position: relative;
    height: 700px;
    text-align: center;
  }

  .visual_contents {
    text-align: center;
    position: absolute;
    top: 20%;
    left: 50%;
    webkit-transform: translateX(-50%) translateY(-50%);
    transform: translateX(-50%) translateY(-50%);
    width: 100%;
    z-index: 99;
  }

  .visual_contents h3 {
    color: #fff;
    font-size: 35px;
  }

  .visual_bg {
    position: absolute;
    top: 54%;
    left: 50%;
    -webkit-transform: translate(-50%, -50%);
    transform: translate(-50%, -50%);
    min-width: 100vw;
    min-height: 100%;
    background: url("../assets/img/main_bg.jpg");
    background-size: cover;
    cursor: default;
  }

  .main-thumbnail {
    padding-bottom: 70px;
  }

  .main-thumbnail .header {
    position: relative;
  }

  .main-thumbnail h1 {
    text-align: center;
    font-weight: 600;
    color: #620017;
    margin-top: 50px;
  }

  .main-thumbnail h1 a {
    position: absolute;
    right: 8px;
    top: 0px;
    font-size: 13px;
    border: 1px solid #aaa;
    padding: 5px 10px;
    margin-top: 10px;
    color: #888;
    font-weight: 300;
  }

  .main-thumbnail h1 a:hover {
    background: #888;
    color: #fff;
    border: 1px solid #888;
    font-weight: 300;
    transition: 0.5s all;
  }

  .main-thumbnail .line {
    background: #620017;
    padding: 1px;
    width: 30px;
    display: block;
    margin: 0 auto;
    margin-top: 20px;
    margin-bottom: 30px;
  }

  @media (max-width: 960px) {
    .visual_contents h3 {
      font-size: 1.7em;
    }
  }

  @media (max-width: 600px) {
    .visual_contents h3 {
      font-size: 1em;
    }

    .main-thumbnail h1 {
      font-size: 1.2rem;
    }

    .main-thumbnail h1 a {
      top: 15px;
    }

    #visual {
      height: 450px;
    }

    .visual_contents {
      top: 30%;
    }
  }
</style>
