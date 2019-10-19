<template>
  <v-container class="px-0 user-manage white">
    <v-tabs centered grow>
      <v-tabs-slider></v-tabs-slider>
      <v-tab href="#profile">
        프로필 변경
      </v-tab>
      <v-tab href="#additional">
        부가 정보
      </v-tab>
      <v-tab href="#password">
        비밀번호 변경
      </v-tab>
      <v-tab href="#leave">
        회원 탈퇴
      </v-tab>
      <v-tab-item value="profile">
        <v-subheader class="font-weight-bold">프로필 사진</v-subheader>
        <v-divider></v-divider>
        <v-layout mt-3 mb-3 justify-center wrap>
          <v-flex xs12 sm4>
            <ProfileUpload :image="user.image" v-on:image="imageChange"></ProfileUpload>
          </v-flex>
          <v-flex xs12 sm8 align-self-center mt-3>
            <p class="subheading my-0 red--text px-3">회원님의 정면사진을 올려주세요!</p>
            <p class="subheading my-0 red--text px-3">상대방이 신뢰를 갖고 연락할 확률이 높아질 거에요</p>
          </v-flex>
        </v-layout>
        <v-subheader class="font-weight-bold">계정 정보</v-subheader>
        <v-divider></v-divider>
        <v-subheader class="body-2">이메일</v-subheader>
        <v-layout wrap>
          <v-flex xs12 sm4 px-3>
            <p class="body-1">{{user.email}}</p>
          </v-flex>
        </v-layout>
        <v-subheader class="body-2">이름</v-subheader>
        <v-layout wrap>
          <v-flex xs12 sm4 px-3>
            <v-text-field single-line v-model="user.name"></v-text-field>
          </v-flex>
        </v-layout>
        <v-subheader class="body-2">닉네임</v-subheader>
        <v-layout wrap>
          <v-flex xs12 sm4 px-3>
            <v-text-field single-line v-model="user.nickname"></v-text-field>
          </v-flex>
        </v-layout>
        <v-subheader class="body-2">생년월일</v-subheader>
        <v-layout wrap>
          <v-flex xs12 sm4 px-3>
            <v-text-field single-line v-model="user.birth"></v-text-field>
          </v-flex>
        </v-layout>
        <v-subheader class="body-2">성별</v-subheader>
        <v-layout wrap>
          <v-flex xs12 sm4 px-3>
            <v-radio-group row v-model="user.gender">
              <v-radio label="남자" value="M"></v-radio>
              <v-radio label="여자" value="F"></v-radio>
            </v-radio-group>
          </v-flex>
        </v-layout>
        <v-subheader class="body-2">휴대전화</v-subheader>
        <v-layout wrap>
          <v-flex xs12 sm4 px-3>
            <v-text-field single-line mask="###-####-####" v-model="user.phone"></v-text-field>
          </v-flex>
        </v-layout>
        <v-layout wrap>
          <v-flex xs12 class="text-xs-right">
            <v-btn outline @click="saveUser">저장하기</v-btn>
          </v-flex>
        </v-layout>
      </v-tab-item>
      <v-tab-item value="additional">
        <v-container>
          <v-subheader class="font-weight-bold">관심카테고리</v-subheader>
          <Category v-model="userAdditional.categories"></Category>
          <v-subheader class="font-weight-bold">관심 지역</v-subheader>
          <v-layout wrap>
            <v-flex xs2 px-2 py-1>
              <v-subheader>지역</v-subheader>
            </v-flex>
            <v-flex xs5 px-2 py-1>
              <v-select
                :items="majorRegions"
                label="지역 대분류"
                solo
                v-model="userAdditional.majorRegion"
                v-on:change="minorRegionChoice(userAdditional.majorRegion)"
              ></v-select>
            </v-flex>
            <v-flex xs5 px-2 py-1>
              <v-select
                :items="minorRegions"
                label="지역 소분류"
                solo
                v-model="userAdditional.minorRegion"
              ></v-select>
            </v-flex>
          </v-layout>
          <v-layout wrap>
            <v-flex xs4 sm2 px-2 py-1>
              <v-subheader style="align-items:flex-end">소개글</v-subheader>
            </v-flex>
            <v-flex xs8 sm10 px-2 py-1>
              <v-textarea
                outline
                rows="10"
                no-resize
                v-model="userAdditional.introduce"
              ></v-textarea>
            </v-flex>
          </v-layout>
          <v-layout wrap>
            <v-flex xs12 class="text-xs-right">
              <v-btn outline @click="saveUserAdditional">저장하기</v-btn>
            </v-flex>
          </v-layout>
        </v-container>
      </v-tab-item>
      <v-tab-item value="password">
        비밀번호
      </v-tab-item>
      <v-tab-item value="leave">
        탈퇴
      </v-tab-item>
    </v-tabs>
  </v-container>
</template>

<script>
    import ProfileUpload from "../../components/common/ProfileUpload";
    import Category from "../../components/common/Category";

    export default {
        data() {
            return {
                user: {
                    id: 0,
                    name: "",
                    email: "",
                    gender: "",
                    nickname: "",
                    birth: null,
                    image: "",
                    phone: ""
                },
                majorRegions: [],
                minorRegions: [],
                minorRegionAll: [],
                userAdditional: {
                    majorRegion: 0,
                    minorRegion: 0,
                    categories: [],
                    introduce: '',
                    userId: 0,
                }
            };
        },
        components: {
            ProfileUpload,
            Category
        },
        methods: {
            imageChange(fileName) {
                this.user.image = fileName ? fileName : '';
            },
            saveUser() {
                this.$http.post('/api/user/update', this.user)
                    .then(() => alert("저장 되었습니다."))
                    .catch(e => console.log(e));
            },
            saveUserAdditional() {
                this.$http.post('/api/user/additional', this.userAdditional)
                    .then(() => alert("저장 되었습니다."))
                    .catch(e => console.log(e));
            },
            minorRegionChoice(code) {
                if (code == 40) {
                    this.minorRegions = [{text: '전국', value: 4000}];
                    return;
                }
                let minor = this.minorRegionAll[code];
                this.minorRegions = minor.map(r => {
                    return {
                        text: r.name,
                        value: r.id
                    }
                });
            },
        },
        created() {
            this.$http.get("/api/user/me")
                .then(user => {
                    this.user = user.data;
                    this.userAdditional.userId = this.user.id;
                }).catch(e => {
                console.log(e);
            });

            this.$http.get("/api/user/additional")
                .then(user => {
                    if (user.data) {
                        this.userAdditional = user.data;
                    }
                }).catch(e => {
                console.log(e);
            });

            this.$http.get('/api/study/majorRegion')
                .then((result) => {
                    this.majorRegions = result.data.map(r => {
                        return {
                            text: r.name,
                            value: r.id
                        }
                    });
                })
                .catch(e => {
                    console.log(e);
                });

            this.$http.get('/api/study/minorRegion')
                .then((result) => {
                    this.minorRegionAll = result.data;
                    let minor = this.minorRegionAll[this.userAdditional.majorRegion];
                    this.minorRegions = minor.map(r => {
                        return {
                            text: r.name,
                            value: r.id
                        }
                    });
                })
                .catch(e => {
                    console.log(e);
                });
        }
    }
</script>

<style>
  .user-manage .v-text-field {
    padding-top: 0;
    margin-top: 0;
  }
</style>
