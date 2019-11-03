<template>
  <v-container class="px-0 user-manage white">
    <v-tabs centered grow>
      <v-tabs-slider></v-tabs-slider>
      <v-tab href="#bookmark">
        즐겨찾기
      </v-tab>
      <v-tab href="#recruit">
        모집
      </v-tab>
      <v-tab href="#apply">
        신청
      </v-tab>
      <v-tab href="#finish">
        완료
      </v-tab>
      <v-tab-item value="bookmark">
        <v-layout wrap>
          <v-flex xs12 sm4 pa-2 mb-2 v-for="study in likeStudies" :key="study.id">
            <Thumbnail :study="study"></Thumbnail>
          </v-flex>
        </v-layout>
      </v-tab-item>

      <v-tab-item value="recruit">
        <v-layout wrap>
          <v-flex xs12 sm4 pa-2 mb-2 v-for="study in recruitStudies" :key="study.id">
            <Thumbnail :study="study"></Thumbnail>
          </v-flex>
        </v-layout>
      </v-tab-item>

      <v-tab-item value="apply">ee
      </v-tab-item>

      <v-tab-item value="finish">ff
      </v-tab-item>
    </v-tabs>
  </v-container>
</template>

<script>
    import Thumbnail from '../../components/common/Thumbnail'
    export default {
        data() {
            return {
                likeStudies: [],
                recruitStudies: [],
            }
        },
        created() {
            this.$http.get('/api/study/like')
                .then((result) => {
                    this.likeStudies = result.data;
                    this.likeStudies.map(s => s.meta = JSON.parse(s.meta));
                })
                .catch((e) => {
                    console.log(e);
                });
            this.$http.get('/api/study/recruit')
                .then((result) => {
                    this.recruitStudies = result.data;
                    this.recruitStudies.map(s => s.meta = JSON.parse(s.meta));
                })
                .catch((e) => {
                    console.log(e);
                });
        },
        components: {
            Thumbnail
        },
    }
</script>

<style scoped>

</style>
