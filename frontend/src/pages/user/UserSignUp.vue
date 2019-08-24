<template>
    <v-layout justify-center wrap>
      <v-flex xs12 sm8 md6>
        <Header/>
        <v-card class="pa-4">
          <v-toolbar class="pt-5 secondary darken-4">
            <v-toolbar-title class="white--text"><h4>오픈스터디에오신걸 환영합니다.</h4></v-toolbar-title>
          </v-toolbar>
          <v-card-text class="pt-2">
            <v-form v-model="valid" ref="form">
              <v-text-field class="caption"
                            label="이메일주소"
                            v-model="userInfo.email"
                            :rules="emailRules"
                            required
              ></v-text-field>
              <v-text-field class="caption"
                            label="이름"
                            v-model="userInfo.name"
                            :rules="nameRules"
                            required
              ></v-text-field>
              <v-text-field class="caption"
                            label="비밀번호"
                            v-model="userInfo.pwd"
                            min="8"
                            type="password"
                            :rules="pwdRules"
                            required
              ></v-text-field>
              <v-text-field class="caption"
                            label="비밀번호확인"
                            v-model="pwd_check"
                            min="8"
                            type="password"
                            :rules="pwdCheckRules"
                            required
              ></v-text-field>
              <v-text-field class="caption"
                            label="닉네임"
                            v-model="userInfo.nickname"
                            :rules="nicknameRules"
                            required
              ></v-text-field>
              <v-text-field class="caption"
                            label="생년월일"
                            v-model="userInfo.birth"
                            :rules="birthRules"
                            required
              ></v-text-field>
              <v-radio-group v-model="userInfo.gender" row>
                <v-radio
                  v-for="n in gender"
                  :key="n.value"
                  :label="n.label"
                  :value="n.value"
                ></v-radio>
              </v-radio-group>
              <v-checkbox
                label="개인정보를 동의하시겠습니까?"
                color="secondary"
                value="secondary"
                hide-details
                class="mt-0"
              ></v-checkbox>
              <v-layout justify-space-between>
                <v-btn block @click="submit" :class=" { 'secondary darken-4 white--text' : valid, disabled: !valid }">회원가입
                </v-btn>
              </v-layout>
            </v-form>
          </v-card-text>
        </v-card>
        <Footer/>
      </v-flex>
    </v-layout>
</template>

<script>
  import Header from '../../components/layouts/Header'
  import Footer from '../../components/layouts/Footer'

  export default {
    data() {
      return {
        userInfo: {
          email: '',
          name: '',
          pwd: '',
          nickname: '',
          birth: '',
          gender: 'M',
        },
        pwd_check: '',
        valid: false,
        nameRules: [
          (v) => !!v || '이름이 필요합니다.',
        ],
        pwdRules: [
          (v) => !!v || '비밀번호가 필요합니다.',
        ],
        pwdCheckRules: [
          (v) => !!v || '비밀번호가 필요합니다.',
          (v) => v == this.userInfo.pwd || '비밀번호가 일치하지 않습니다.'
        ],
        emailRules: [
          (v) => !!v || '이메일이 필요합니다.',
          (v) => /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(v) || '이메일 양식이 잘못되었습니다.'
        ],
        nicknameRules: [],
        birthRules: [],
        gender: [{value:'M',label:'남성'},{value:'F',label:'여성'}],
      }
    },
    methods: {
      submit() {
        let userJson = JSON.stringify(this.userInfo);
        if (this.$refs.form.validate()) {
          this.$http.post('/api/user/save', userJson, {
            headers : {
              'Content-Type': 'application/json',
            }
          })
            .then((result) => {
              console.log(result);
              this.$router.push("/");
            })
            .catch((e) => {
              console.log(e);
            })
        }
      }
    },
    components: {
      Header,
      Footer,
    },
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
