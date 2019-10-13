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
        부가정보
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
                }
            };
        },
        components: {
            ProfileUpload
        },
        methods: {
            imageChange(fileName) {
                this.user.image = fileName ? fileName : '';
            },
            saveUser() {
                this.$http.post('/api/user/update', this.user)
                    .then(() => alert("저장 되었습니다."))
                    .catch(e => console.log(e));
            }
        },
        created() {
            this.$http.get("/api/user/me").then(user => {
                this.user = user.data;
            }).catch(e => {
                console.log(e);
            })
        }
    }
</script>

<style>
  .user-manage .v-text-field {
    padding-top: 0;
    margin-top: 0;
  }
</style>
