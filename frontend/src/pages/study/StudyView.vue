<template>
  <v-container fluid class="px-0 study-view">
    <v-carousel height="400" cycle hide-delimiters>
      <v-carousel-item
        v-for="(image,i) in study.meta.images"
        :key="i"
        :src="require(`../../assets/temp/${image}`)"
      ></v-carousel-item>
      <div class="user">
        <div class="user-img">
          <img :src="require(`@/assets/${study.writer.image}`)">
        </div>
        <div class="user-info">
          <h3 class="title font-weight-black white--text">{{study.name}}</h3>
          <h4 class="subheading font-weight-bold white--text">{{study.writer.name}}</h4>
        </div>
        <div class="study-additional">
          <div class="view-icon">
            <v-icon>star</v-icon>
          </div>
          <div class="uniqueCategories">
            <span class="uniqueCategory" v-for="category in study.uniqueCategories" :key="category.id">{{category.title}}</span>
          </div>
        </div>
      </div>
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
        <v-subheader>{{study.startDate | date('YYYY-MM-DD')}}</v-subheader>
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
    <v-layout wrap>
      <v-flex xs12 sm12 class="text-xs-right">
        <v-btn :to="{ name: 'studyEdit', params: { id: this.$route.params.id }}" :key="this.$route.fullPath"
               v-if="study.studyWriterId == userId"
               color="error">수정
        </v-btn>
        <v-btn to="/study/list" color="success">목록</v-btn>
      </v-flex>
    </v-layout>
    <v-divider></v-divider>
    <ReplyReg></ReplyReg>
    <ReplyList :studyWriterId=study.studyWriterId></ReplyList>
  </v-container>
</template>

<script>
    import ReplyReg from '../../components/reply/ReplyReg'
    import ReplyList from '../../components/reply/ReplyList'
    import {mapGetters} from 'vuex'
    import _ from 'lodash'

    export default {
        data() {
            return {
                study: {
                    name: '',
                    majorRegion: {},
                    minorRegion: {},
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
                    if (!this.study.meta.images.length) {
                        this.study.meta.images.push('study_default.jpg');
                    }
                    this.study.uniqueCategories = _.uniqBy(this.study.categories, (c) => c.title);
                })
                .catch((e) => {
                    console.log(e);
                });
        },
        computed: {
            ...mapGetters({userId: 'user/id'}),
        },
        components: {
            ReplyReg,
            ReplyList
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
    color: rgba(0, 0, 0, 0.54) !important;
    font-size: 14px;
    font-weight: 500;
  }
</style>
<style scoped>
  .user {
    position: absolute;
    display: flex;
    bottom: 0;
    width: 100%;
    background: rgba(2, 9, 31, 0.3);
    padding: 16px;
    z-index: 55;
  }

  .user .user-img {
    height: auto;
    overflow: hidden;
  }

  .user .user-img img {
    z-index: 5;
    width: 100px;
    height: 100px;
    max-width: 100px;
    max-height: 100px;
    -webkit-border-radius: 50%;
    -moz-border-radius: 50%;
    border-radius: 50%;
    border: 2px solid #fff;
  }

  .user .user-info {
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding: 12px;
  }

  .user .user-info h4 {
    margin-top: 10px;
  }

  .user .study-additional {
    position: absolute;
    right: 20px;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    padding: 25px 0px;
    height: 100%;
    top: 0;
  }

  .user .view-icon {
    text-align: right;
    padding: 5px;
  }

  .user .uniqueCategory {
    color: white;
    border: 1px solid white;
    padding: 4px 15px;
    margin: 0 5px;
  }
</style>
