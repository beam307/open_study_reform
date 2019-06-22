<template>
  <v-container fluid>
    <v-stepper v-model="step">
      <v-stepper-header>
        <v-stepper-step :complete="step > 1" step="1" color="secondary">기본정보</v-stepper-step>
        <v-divider></v-divider>
        <v-stepper-step :complete="step > 2" step="2" color="secondary">상세정보</v-stepper-step>
        <v-divider></v-divider>
        <v-stepper-step step="3" color="secondary">소개</v-stepper-step>
      </v-stepper-header>

      <v-stepper-items>
        <v-stepper-content step="1">
          <step1></step1>
          <v-layout justify-end>
            <v-btn color="secondary" @click="step++">다음</v-btn>
          </v-layout>
        </v-stepper-content>

        <v-stepper-content step="2">
          <step2></step2>
          <v-layout justify-space-between>
            <v-btn color="secondary" @click="step--">이전</v-btn>
            <v-btn color="secondary" @click="step++">다음</v-btn>
          </v-layout>
        </v-stepper-content>

        <v-stepper-content step="3">
          <step3></step3>
          <v-layout justify-space-between>
            <v-btn color="secondary" @click="step--">이전</v-btn>
            <v-btn color="secondary" @click="step = 1">완료</v-btn>
          </v-layout>
        </v-stepper-content>
      </v-stepper-items>
    </v-stepper>
  </v-container>
</template>

<script>
  import step1 from './studyRegisterStep1'
  import step2 from './studyRegisterStep2'
  import step3 from './studyRegisterStep3'

  export default {
    data() {
      return {
        step: 0,
      }
    },
    components: {
      step1,
      step2,
      step3,
    }
  }
</script>

<style scoped>

</style>
