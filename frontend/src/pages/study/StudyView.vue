<template>
  <v-container fluid class="px-0 study-view">
    <div v-if="study.status == 'FINISH'" class="finish">
      <div class="finish-text">
        완료
      </div>
    </div>
    <v-carousel height="400" hide-delimiters>
      <v-carousel-item
        v-for="(image,i) in study.meta.images"
        :key="i"
        v-if="image"
        :src="image"
      ></v-carousel-item>
      <div class="user">
        <div class="user-img">
          <img v-if="study.writer.image" :src="study.writer.image" :alt="study.writer.image">
        </div>
        <div class="user-info">
          <h3 class="title font-weight-black white--text">{{study.name}}</h3>
          <h4 class="subheading font-weight-bold white--text">{{study.writer.name}}</h4>
        </div>
        <div class="study-additional">
          <div class="view-icon py-0">
            <v-icon v-if="bookmark" large class="bookmark" @click="setBookmark">star</v-icon>
            <v-icon v-else large class="bookmark font-weight-light" @click="setBookmark">star_border</v-icon>
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
        <template v-if="study.studyWriterId == userId && study.status == 'ACTIVE'">
          <v-btn @click="finish" color="info">완료</v-btn>
          <v-btn @click="studyDelete" color="error">삭제</v-btn>
          <v-btn :to="{ name: 'studyEdit', params: { id: this.$route.params.id }}"
                 color="warning">수정
          </v-btn>
        </template>
        <template v-else>
          <v-btn @click="studyApply" color="error">신청</v-btn>
        </template>
        <v-btn to="/study/list" color="success" class="list-btn">목록</v-btn>
      </v-flex>
    </v-layout>
    <template v-if="study.status == 'ACTIVE'">
      <v-divider></v-divider>
      <ReplyReg></ReplyReg>
      <ReplyList :studyWriterId=study.studyWriterId></ReplyList>
    </template>
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
                    introduce: '',
                    writer: {
                        id: 0,
                        name: '',
                        image: ''
                    },
                    status: ''
                },
                bookmarkDone: true,
                bookmark: false,
            }
        },
        methods: {
            setBookmark() {
                if (this.bookmarkDone && this.study.status == 'ACTIVE') {
                    this.bookmarkDone = false;
                    this.$authorizedApi.post('/api/graph/like', {studyId: this.$route.params.id})
                        .then((r) => {
                            this.bookmark = r.data;
                            if (r.data) {
                                this.$swal.fire({
                                    type: "info",
                                    timer: 700,
                                    text: '해당스터디를 즐겨찾기하였습니다.',
                                    showConfirmButton: false
                                });
                            } else {
                                this.$swal.fire({
                                    type: "info",
                                    timer: 700,
                                    text: '해당스터디를 즐겨찾기를 취소하였습니다.',
                                    showConfirmButton: false
                                });
                            }
                            this.bookmarkDone = true;
                        })
                        .catch((e) => {
                            console.log(e);
                            this.bookmarkDone = true;
                        });
                }
            },
            finish() {
                this.$swal.fire({
                    title: '정말 완료하시겠습니까?',
                    text: "완료 후 복구는 불가능합니다.",
                    type: 'warning',
                    showCancelButton: true,
                }).then((result) => {
                    if (result.value) {
                        this.$authorizedApi.post(`/api/study/${this.$route.params.id}/finish`)
                            .then((r) => {
                                this.$swal.fire({
                                    type: "info",
                                    timer: 1500,
                                    text: '해당스터디를 완료했습니다.'
                                });
                                this.$router.push({name: 'studyList'});
                            })
                            .catch((e) => {
                                console.log(e);
                                this.$swal.fire({
                                    type: "error",
                                    timer: 1500,
                                    text: '완료하는데 에러가 발생하였습니다.'
                                });
                            });
                    }
                })

            },
            studyDelete() {
                this.$swal.fire({
                    title: '정말 완료하시겠습니까?',
                    text: "완료 후 복구는 불가능합니다.",
                    type: 'warning',
                    showCancelButton: true,
                }).then((result) => {
                    if (result.value) {
                        this.$authorizedApi.post(`/api/study/${this.$route.params.id}/delete`)
                            .then((r) => {
                                this.$swal.fire({
                                    type: "info",
                                    timer: 1500,
                                    text: '해당스터디를 삭제했습니다.'
                                });
                                this.$router.push({name: 'studyList'});
                            })
                            .catch((e) => {
                                console.log(e);
                                this.$swal.fire({
                                    type: "error",
                                    timer: 1500,
                                    text: '삭제하는데 에러가 발생하였습니다.'
                                });
                            });
                    }
                });

            },
            studyApply() {
                this.$swal.fire({
                    title: '정말 신청하시겠습니까?',
                    text: "스터디장에게 이름, 연락처 등이 전달 됩니다.",
                    type: 'info',
                    showCancelButton: true,
                }).then((result) => {
                    if (result.value) {
                        this.$authorizedApi.post(`/api/study/${this.$route.params.id}/apply`)
                            .then((r) => {
                                this.$swal.fire({
                                    type: "info",
                                    timer: 1500,
                                    title: '신청이 완료되었습니다.',
                                    text: '스터디장에게 연락 올때까지 기다리십시요.'
                                });
                            })
                            .catch((e) => {
                                console.log(e);
                                this.$swal.fire({
                                    type: "error",
                                    timer: 1500,
                                    text: '이미 스터디를 신청하였습니다.'
                                });
                            });
                    }
                });

            }
        },
        created() {
            this.$http.get(`/api/study/${this.$route.params.id}`)
                .then((result) => {
                    this.study = result.data;
                    this.study.meta = JSON.parse(this.study.meta);
                    if (!this.study.meta.images.length) {
                        this.study.meta.images.push('https://openstudy.s3.ap-northeast-2.amazonaws.com/img/study_default.jpg');
                    }
                    this.study.uniqueCategories = _.uniqBy(this.study.categories, (c) => c.title);
                })
                .catch((e) => {
                    console.log(e);
                });

            if (this.bookmarkDone) {
                this.bookmarkDone = false;
                this.$http.get('/api/graph/like', {
                    params: {
                        studyId: this.$route.params.id
                    }
                })
                    .then((r) => {
                        this.bookmark = r.data.like;
                        this.bookmarkDone = true;
                    })
                    .catch((e) => {
                        console.log(e);
                        this.bookmarkDone = true;
                    });
            }
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

<style lang="scss">
  .study-view {
    position: relative;

    .finish {
      position: absolute;
      z-index: 100;
      width: 100%;
      height: 100%;
      background: rgba(255, 255, 255, 0.4);

      .finish-text {
        position: absolute;
        left: 50%;
        top: 50%;
        z-index: 101;
        transform: translateX(-50%) translateY(-50%);
        display: inline-block;
        font-size: 90px;
        font-weight: bold;
        padding: 10px 35px;
        border: 10px solid;
        border-radius: 40px;
      }
    }

    .v-carousel__next .v-btn.v-btn--icon {
      background: #999;
    }

    .v-carousel__prev .v-btn.v-btn--icon {
      background: #999;
    }

    .v-image__image--cover {
      background-size: contain !important;
    }

    textarea {
      color: rgba(0, 0, 0, 0.54) !important;
      font-size: 14px;
      font-weight: 500;
    }

    .list-btn {
      z-index: 105;
    }

  }

</style>
<style scoped lang="scss">
  .user {
    position: absolute;
    display: flex;
    bottom: 0;
    width: 100%;
    background: rgba(2, 9, 31, 0.3);
    padding: 16px;
    z-index: 55;

    .user-img {
      height: auto;
      overflow: hidden;
    }

    .user-img img {
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

    .user-info {
      display: flex;
      flex-direction: column;
      justify-content: center;
      padding: 12px;
    }

    .user-info h4 {
      margin-top: 10px;
    }

    .study-additional {
      position: absolute;
      right: 20px;
      display: flex;
      flex-direction: column;
      justify-content: space-around;
      padding: 25px 0px;
      height: 100%;
      top: 0;
    }

    .view-icon {
      text-align: right;
      padding: 5px;
    }

    .uniqueCategory {
      color: white;
      border: 1px solid white;
      padding: 4px 15px;
      margin: 0 5px;
    }

    .bookmark {
      cursor: pointer;
    }
  }
</style>
