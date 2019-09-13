<template>
  <v-layout wrap id="reply-reg" v-if="this.authenticated">
    <v-flex xs4 sm2>
      <v-subheader class="font-weight-black">작성자</v-subheader>
      <v-subheader class="body-2">{{this.name}}</v-subheader>
    </v-flex>
    <v-flex xs8 sm7>
      <v-subheader class="font-weight-black">내용</v-subheader>
      <v-textarea no-resize solo light rows="3" v-model="reply.text"></v-textarea>
    </v-flex>
    <v-flex xs12 sm3>
      <v-layout fill-height>
        <v-layout align-end fill-height>
          <v-flex>
            <v-checkbox
              v-model="reply.secret"
              label="비밀글"
            ></v-checkbox>
          </v-flex>
        </v-layout>
        <v-layout wrap align-center fill-height>
          <v-flex>
            <v-btn block dark @click="submit">작성하기</v-btn>
          </v-flex>
        </v-layout>
      </v-layout>
    </v-flex>
  </v-layout>
</template>

<script>
    import {mapGetters} from 'vuex'

    export default {
        data() {
            return {
                submitFlag: true,
                reply: {
                    studyId: this.$route.params.id,
                    text: '',
                    secret: false
                }

            }
        },
        computed: {
            ...mapGetters({
                authenticated: 'user/authenticated',
                isProfile: 'user/isProfile',
                userId: 'user/id',
                name: 'user/name',
            }),
        },
        methods: {
            submit() {
                if (this.submitFlag) {
                    this.submitFlag = false;
                    this.$http.post("/api/reply", this.reply)
                        .then(r => {
                            this.reply.text = "";
                        }).catch(e => {
                        console.log(e);
                        alert("오류가 발생하였습니다. 잠시 후 다시 이용해주세요.");
                    }).finally(() => {
                        this.submitFlag = true;
                    });
                }

            }
        }
    }
</script>

<style>
  #reply-reg .v-label {
    font-size: 14px !important;
  }
</style>
