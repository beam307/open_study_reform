<template>
  <v-timeline align-top dense>
    <v-timeline-item color="black" small>
      <v-layout pt-3>
        <v-flex xs6 sm3>
          <strong>작성자</strong>
          <span>테스트</span>
        </v-flex>
        <v-spacer class="hidden-xs-only"></v-spacer>
        <v-flex xs6 sm3 class="text-sm-right text-xs-right">
          <strong>2019-09-23 11:00</strong>
        </v-flex>
      </v-layout>
      <v-layout>
        <v-flex>
          <v-textarea readonly no-resize solo flat background-color="transparent" rows="3" :value="test"></v-textarea>
        </v-flex>
      </v-layout>
    </v-timeline-item>
    <v-timeline-item color="black" small>
      <v-layout pt-3>
        <v-flex xs6 sm3>
          <v-icon size="20">lock</v-icon>
          <strong>작성자</strong>
          <span>테스트</span>
        </v-flex>
        <v-spacer class="hidden-xs-only"></v-spacer>
        <v-flex xs6 sm3 class="text-sm-right text-xs-right">
          <strong>2019-09-23 11:00</strong>
        </v-flex>
      </v-layout>
      <v-layout>
        <v-flex>
          <v-textarea readonly no-resize solo flat background-color="transparent" rows="3" :value="test2"></v-textarea>
        </v-flex>
      </v-layout>
    </v-timeline-item>
  </v-timeline>
</template>

<script>
    export default {
        data() {
            return {
              test:"zzzz",
              test2:"zzsdfsdf안녕하세요zz"
            }
        }
    }
</script>

<style scoped>

</style>
