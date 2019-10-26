<template>
  <v-card class="thumbnail" @click="goToStudy">
    <div>
      <div class="overlay"></div>
      <v-img v-if="study.meta.images.length > 0" :src="require(`@/assets/temp/${study.meta.images[0]}`)" contain
             max-width="100%" max-height="150"></v-img>
      <v-img v-else :src="require('@/assets/temp/study_default.jpg')" contain max-width="100%" max-height="150"></v-img>
    </div>
    <v-card-title primary-title class="pt-5">
      <h3 class="subtitle-1">{{study.writer.name}}</h3>
      <v-subheader>{{study.name}}</v-subheader>
    </v-card-title>
    <div class="preview">
      <img :src="require(`@/assets/${study.writer.image}`)"/>
    </div>
    <v-card-actions class="justify-center">
      <v-btn class="caption" dark small color="orange" @click="goToStudy">자세히보기</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
    export default {
        props: ['study'],
        data() {
            return {}
        },
        methods: {
            goToStudy() {
                this.$router.push({name: 'studyView', params: {id: this.study.id}})
            }
        }
    }
</script>

<style scoped>
  .thumbnail .preview {
    position: absolute;
    left: 50%;
    top: 55%;
    z-index: 5;
    webkit-transform: translateX(-50%) translateY(-50%);
    transform: translateX(-50%) translateY(-50%);
  }

  .thumbnail .preview img {
    z-index: 5;
    width: 100px;
    height: 100px;
    max-width: 100px;
    max-height: 100px;
    -webkit-border-radius: 50%;
    -moz-border-radius: 50%;
    border-radius: 50%;
    border: 5px solid rgba(255, 255, 255, 0.5);
  }

  .thumbnail .overlay {
    z-index: 1;
    position: absolute;
    height: 150px;
    width: 100%;
    background-color: rgba(0, 0, 0, 0.3);
  }
</style>
