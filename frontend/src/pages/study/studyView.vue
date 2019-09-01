<template>
  <v-container fluid class="study-view">
    <v-carousel height="400">
      <v-carousel-item
        v-for="(image,i) in study.meta.images"
        :key="i"
        :src="require(`../../assets/temp/${image}`)"
      ></v-carousel-item>
    </v-carousel>
    <v-layout wrap mt-4>
      <v-flex xs4 sm2>
        <v-subheader class="font-weight-black">카테고리</v-subheader>
      </v-flex>
      <v-flex xs8 sm10>
        <v-subheader>
          <span class="mr-1" v-for="(category,index) in study.categories" :key="category.id">{{index == (study.categories.length - 1) ? category.name : (category.name + ', ')}}</span>
        </v-subheader>
      </v-flex>
    </v-layout>
    <v-layout wrap>
      <v-flex xs4 sm2>
        <v-subheader class="font-weight-black">현재인원</v-subheader>
      </v-flex>
      <v-flex xs8 sm10>
        <v-subheader>0</v-subheader>
      </v-flex>
    </v-layout>
    <v-layout wrap>
      <v-flex xs4 sm2>
        <v-subheader class="font-weight-black">최대인원</v-subheader>
      </v-flex>
      <v-flex xs8 sm10>
        <v-subheader>{{study.maxMemberCnt}}</v-subheader>
      </v-flex>
    </v-layout>
    <v-layout wrap>
      <v-flex xs4 sm2>
        <v-subheader class="font-weight-black">지역</v-subheader>
      </v-flex>
      <v-flex xs8 sm10>
        <v-subheader>{{study.majorRegion.name}} {{study.minorRegion.name}}</v-subheader>
      </v-flex>
    </v-layout>
    <v-layout wrap>
      <v-flex xs4 sm2>
        <v-subheader class="font-weight-black">대상</v-subheader>
      </v-flex>
      <v-flex xs8 sm10>
        <v-subheader>
          <span class="mr-1" v-for="(age,i) in study.meta.age" :key="age">{{i == (study.meta.age.length - 1) ? age+'대' : (age + '대, ')}}</span>
        </v-subheader>
      </v-flex>
    </v-layout>
    <v-layout wrap>
      <v-flex xs4 sm2>
        <v-subheader class="font-weight-black">시작날짜</v-subheader>
      </v-flex>
      <v-flex xs8 sm10>
        <v-subheader>{{study.startDate | dateFormat}}</v-subheader>
      </v-flex>
    </v-layout>
    <v-layout wrap>
      <v-flex xs4 sm2>
        <v-subheader class="font-weight-black">일정</v-subheader>
      </v-flex>
      <v-flex xs8 sm10>
        <v-subheader v-for="time in study.meta.time" :key="time">{{time}}</v-subheader>
      </v-flex>
    </v-layout>
    <v-layout wrap>
      <v-flex xs4 sm2>
        <v-subheader class="font-weight-black">소개글</v-subheader>
      </v-flex>
      <v-flex xs8 sm10>
        <v-textarea readonly no-resize solo flat background-color="transparent" :value="study.introduce"></v-textarea>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
    import moment from 'moment'

    export default {
        data() {
            return {
                study: {
                    name: '',
                    majorRegion: 0,
                    minorRegion: 0,
                    categories: [],
                    meta: {
                        age: [],
                        time: [],
                        images: [],
                    },
                    startDate: null,
                    maxMemberCnt: 0,
                    introduce: ''
                }
            }
        },
        methods: {},
        created() {
            this.$http.get(`/api/study/${this.$route.params.id}`)
                .then((result) => {
                    this.study = result.data;
                    this.study.meta = JSON.parse(this.study.meta);
                })
                .catch((e) => {
                    console.log(e);
                });
        },
        filters: {
            dateFormat: function (value) {
                if (!value) return '';
                return moment(value).format('YYYY-MM-DD');
            }
        }
    }
</script>

<style>
  .study-view .v-carousel__next .v-btn.v-btn--icon {
    background: #999;
  }

  .study-view .v-carousel__prev .v-btn.v-btn--icon {
    background: #999;
  }

  .study-view .v-image__image--cover {
    background-size: contain !important;
  }

  .study-view textarea {
    color: rgba(0,0,0,0.54) !important;
    font-size: 14px;
    font-weight: 500;
  }
</style>
