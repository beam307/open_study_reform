<template>
  <v-card class="thumbnail" @click="goToStudy">
    <div class="image-wrapper">
      <div class="overlay">
        <v-subheader class="subtitle-1 font-weight-bold white--text study-title">{{study.name}}</v-subheader>
      </div>
      <img class="thumbnail-image" v-if="study.meta.images.length > 0"
           :src="study.meta.images[0]">
      <img class="thumbnail-image" v-else src="https://openstudy.s3.ap-northeast-2.amazonaws.com/img/study_default.jpg">
    </div>
    <div class="icon">
      <v-icon class="i">remove_red_eye</v-icon>
      <span class="thumbnail-info"> {{study.viewCnt}}</span></div>
    <div class="icon">
      <v-icon class="i">person</v-icon>
      <span class="thumbnail-info"> {{study.maxMemberCnt}}</span></div>
    <div class="preview">
      <img :src="study.writer.image"/>
    </div>
    <v-card-title primary-title class="pt-4 pb-0 justify-center thumbnail-description">
      <h3 class="body-2 font-weight-bold">{{study.writer.name}}</h3>
      <h3 class="body-2 font-weight-bold">{{study.majorRegion.name}} {{study.minorRegion.name}}</h3>
      <h3 class="body-2">시작일 : {{study.startDate | date('YYYY-MM-DD')}}</h3>
    </v-card-title>
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

<style scoped lang="scss">

  .thumbnail {

    &:hover {
      .thumbnail-image {
        transform: scale( 1.5 );
      }
    }

    .image-wrapper {
      overflow: hidden;
      max-height: 150px;
    }

    .thumbnail-image {
      width: 100%;
      height: 150px;
      max-height: 150px;
      transition: all linear .3s;
    }

    .preview {
      position: absolute;
      left: 50%;
      top: 50%;
      z-index: 5;
      webkit-transform: translateX(-50%) translateY(-50%);
      transform: translateX(-50%) translateY(-50%);

      img {
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
    }

    .overlay {
      z-index: 1;
      position: absolute;
      height: 150px;
      width: 100%;
      background-color: rgba(0, 0, 0, 0.5);
    }

    .icon {
      position: relative;
      top: 0;
      right: 5px;
      font-size: 15px;
      padding-left: 8px;
      color: #555;
      float: right;
    }

    .icon .i {
      font-size: 20px !important;
    }

    .icon .thumbnail-info {
      font-size: 15px;
    }

    .study-title {
      justify-content: center;
      align-items: center;
      height: 100%;
      line-height: 1;
    }

    .thumbnail-description {
      flex-direction: column;
      width: 100%;
    }
  }

</style>
