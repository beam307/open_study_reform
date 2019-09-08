<template>
  <v-layout wrap id="reply-reg">
    <v-flex xs4 sm2>
      <v-subheader class="font-weight-black">작성자</v-subheader>
      <v-subheader class="body-2">테스트</v-subheader>
    </v-flex>
    <v-flex xs8 sm7>
      <v-subheader class="font-weight-black">내용</v-subheader>
      <v-textarea no-resize solo light rows="3"></v-textarea>
    </v-flex>
    <v-flex xs12 sm3>
      <v-layout fill-height>
        <v-layout align-end fill-height>
          <v-flex>
            <v-checkbox
              v-model="secret"
              label="비밀글"
            ></v-checkbox>
          </v-flex>
        </v-layout>
        <v-layout wrap align-center fill-height>
          <v-flex>
            <v-btn block dark>작성하기</v-btn>
          </v-flex>
        </v-layout>
      </v-layout>
    </v-flex>
  </v-layout>
</template>

<script>
    export default {
        data() {
            return {
                secret: false
            }
        }
    }
</script>

<style>
  #reply-reg .v-label {
    font-size: 14px !important;
  }
</style>
