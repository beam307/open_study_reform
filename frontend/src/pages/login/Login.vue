<template>
  <v-container mt-5 mb-5>
    <v-layout mt-5 mb-5 justify-center>
      <v-flex xs12 sm8 md5>
        <Header/>
        <v-card class="pa-4">
          <div>
            <a href="/login/kakao" class="kakao-login sns"> <img src="/resources/images/kakao-icon.jpg" alt="">
              카카오계정으로로그인</a>
            <a href="/login/naverLogin" class="naver-login sns"> <img src="/resources/images/naver-icon.jpg" alt="">
              네이버계정으로로그인</a>
          </div>
          <v-card-text class="pt-2">
            <v-form v-model="valid" ref="form">
              <v-text-field class="caption"
                            label="이메일주소"
                            v-model="email"
                            :rules="emailRules"
                            required
              ></v-text-field>
              <v-text-field class="caption"
                            label="비밀번호"
                            v-model="password"
                            min="8"
                            type="password"
                            :rules="passwordRules"
                            required
              ></v-text-field>
              <v-checkbox
                label="아이디 저장"
                color="secondary"
                value="secondary"
                hide-details
                class="mt-0 caption"
              ></v-checkbox>
              <v-layout justify-space-between>
                <v-btn block @click="submit" :class=" { 'blue darken-4 white--text' : valid, disabled: !valid }">로그인
                </v-btn>
              </v-layout>
            </v-form>
          </v-card-text>
          <v-card-text class="py-1 caption">
            아이디/비밀번호를 잊어버리셨나요?
            <a class="link" data-toggle="modal" @click.stop="findId=true">아이디찾기</a>
            <a class="link" data-toggle="modal" @click.stop="findPwd=true">비밀번호찾기</a>
            <v-dialog v-model="findId" max-width="400">
              <v-card>
                <v-card-title class="headline">
                  <span class="title">아이디찾기</span>
                  <v-spacer></v-spacer>
                  <v-btn icon @click="findId = false">
                    <v-icon>close</v-icon>
                  </v-btn>
                </v-card-title>

                <v-card-text>
                  <v-text-field label="전화번호"></v-text-field>
                  <v-text-field label="임시이메일"></v-text-field>
                </v-card-text>
                <v-card-actions>
                  <v-btn color="green darken-1" flat="flat">
                    아이디 찾기
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
            <v-dialog v-model="findPwd" max-width="400">
              <v-card>
                <v-card-title class="headline">
                  <span class="title">비밀번호찾기</span>
                  <v-spacer></v-spacer>v
                  <v-btn icon @click="findPwd = false">
                    <v-icon>close</v-icon>
                  </v-btn>
                </v-card-title>
                <v-card-text>
                  <v-text-field label="이메일"></v-text-field>
                  <v-text-field label="이름"></v-text-field>
                  <v-text-field label="생년월일"></v-text-field>
                </v-card-text>
                <v-card-actions>
                  <v-btn color="green darken-1" flat="flat">
                    비밀번호 찾기
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-card-text>
          <v-card-text class="py-1 caption">
            아직 오픈스터디를 가입하시지않으셨나요?
            <router-link class="link" to="/join">회원가입</router-link>
          </v-card-text>
        </v-card>
        <Footer/>
      </v-flex>
    </v-layout>
  </v-container>
</template>


<script>
  import Header from '../../components/layouts/Header'
  import Footer from '../../components/layouts/Footer'

  export default {
    data() {
      return {
        findId: false,
        findPwd: false,
        valid: false,
        password: '',
        passwordRules: [
          (v) => !!v || '비밀번호가 필요합니다.',
        ],
        email: '',
        emailRules: [
          (v) => !!v || '이메일이 필요합니다.',
          (v) => /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(v) || '이메일 양식이 잘못되었습니다.'
        ],
      }
    },
    methods: {
      submit() {
        if (this.$refs.form.validate()) {
          this.$refs.form.$el.submit()
        }
      },
      clear() {
        this.$refs.form.reset()
      }
    },
    components: {
      Header,
      Footer,
    },
  }
</script>

<style scoped>
  .sns {
    display: block;
    padding: 10px;
    margin: 10px 0;
    color: #331a19;
    background: #ffeb00;
    border: 1px solid #ffeb00;
    text-align: center;
    font-size: 13px;
    font-weight: 600;
    border-radius: 5px;
    text-decoration: none;
  }

  .sns:hover {
    background: rgba(255, 235, 0, 0.7);
    border: 1px solid rgba(255, 235, 0, 0.7);
    color: #331a19;
  }

  .kakao-login img {
    width: 18px;
    margin-left: 5px;
    float: left;
  }

  .naver-login {
    background: #44b035;
    border: 1px solid #44b035;
  }

  .naver-login:hover {
    background: rgba(68, 176, 53, 0.9);
    border: 1px solid rgba(68, 176, 53, 0.9);
    color: #fff;
  }

  .naver-login img {
    width: 18px;
    margin-left: 5px;
    float: left;
  }

  .link {
    font-weight: bold;
    padding-left: 10px;
    text-decoration: none;
    color: #ccc;
  }

  @media (max-width: 600px) {
    .link {
      display: block;
      padding-left: 0px;
    }

  }
</style>
