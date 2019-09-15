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
          <StudyRegisterStep1></StudyRegisterStep1>
          <v-layout justify-end>
            <v-btn color="secondary" @click="step++;">다음</v-btn>
          </v-layout>
        </v-stepper-content>

        <v-stepper-content step="2">
          <StudyRegisterStep2></StudyRegisterStep2>
          <v-layout justify-space-between>
            <v-btn color="secondary" @click="step--">이전</v-btn>
            <v-btn color="secondary" @click="step++">다음</v-btn>
          </v-layout>
        </v-stepper-content>

        <v-stepper-content step="3">
          <StudyRegisterStep3></StudyRegisterStep3>
          <v-layout justify-space-between>
            <v-btn color="secondary" @click="step--">이전</v-btn>
            <v-btn color="secondary" @click="createStudy">완료</v-btn>
          </v-layout>
        </v-stepper-content>
      </v-stepper-items>
    </v-stepper>
  </v-container>
</template>

<script>
    import StudyRegisterStep1 from './studyRegisterStep1'
    import StudyRegisterStep2 from './studyRegisterStep2'
    import StudyRegisterStep3 from './studyRegisterStep3'
    import {mapGetters, mapState, mapMutations} from 'vuex'

    export default {
        data() {
            return {
                step: 0,
            }
        },
        components: {
            StudyRegisterStep1,
            StudyRegisterStep2,
            StudyRegisterStep3,
        },
        computed: {
            ...mapGetters({meta: 'study/meta', userId: 'user/id'}),
            ...mapState(['study'])
        },
        methods: {
            ...mapMutations({resetStudy: 'study/resetState'}),

            createStudy() {
                let study = _.cloneDeep(this.study);
                study.meta = JSON.stringify(this.meta);
                study.studyWriterId = this.userId;
                this.$http.post('/api/study/create', study)
                    .then((result) => {
                        this.resetStudy();
                        this.$router.push({name:'studyList'});
                    })
                    .catch((e) => {
                        console.log(e);
                    })
            }
        }
    }
</script>

<style scoped>

</style>
