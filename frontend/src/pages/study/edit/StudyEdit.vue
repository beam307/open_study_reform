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
          <StudyEditStep1 :study.sync="study"></StudyEditStep1>
          <v-layout justify-end>
            <v-btn color="secondary" @click="step++">다음</v-btn>
          </v-layout>
        </v-stepper-content>

        <v-stepper-content step="2">
          <StudyEditStep2 :study.sync="study"></StudyEditStep2>
          <v-layout justify-space-between>
            <v-btn color="secondary" @click="step--">이전</v-btn>
            <v-btn color="secondary" @click="step++">다음</v-btn>
          </v-layout>
        </v-stepper-content>

        <v-stepper-content step="3">
          <StudyEditStep3 :study.sync="study"></StudyEditStep3>
          <v-layout justify-space-between>
            <v-btn color="secondary" @click="step--">이전</v-btn>
            <v-btn color="secondary" @click="saveStudy">완료</v-btn>
          </v-layout>
        </v-stepper-content>
      </v-stepper-items>
    </v-stepper>
  </v-container>
</template>

<script>
    import StudyEditStep1 from './StudyEditStep1'
    import StudyEditStep2 from './StudyEditStep2'
    import StudyEditStep3 from './StudyEditStep3'
    import {mapGetters} from 'vuex'

    export default {
        data() {
            return {
                step: 0,
                study: {
                    name: '',
                    majorRegionId: 0,
                    minorRegionId: 0,
                    categoryIds: [],
                    meta: {
                        age: [],
                        time: [],
                        images: [],
                    },
                    startDate: null,
                    maxMemberCnt: 0,
                    introduce: ''
                }
            }
        },
        created() {
            if (this.$route.params.id != 'new') {
                this.$http.get(`/api/study/${this.$route.params.id}`)
                    .then((result) => {
                        this.study = result.data;
                        this.study.meta = JSON.parse(this.study.meta);
                        if (!this.study.meta.images.length) {
                            this.study.meta.images.push('study_default.jpg');
                        }
                        this.study.categoryIds = this.study.categories.map(c => c.id);
                        delete this.study.categories;
                    })
                    .catch((e) => {
                        console.log(e);
                    });
            }
        },
        computed: {
            ...mapGetters({userId: 'user/id'}),
        },
        methods: {
            saveStudy() {
                this.study.meta = JSON.stringify(this.study.meta);
                this.study.studyWriterId = this.userId;
                this.$http.post('/api/study/create', this.study)
                    .then(() => {
                        this.$router.push({name: 'studyList'});
                    })
                    .catch((e) => {
                        console.log(e);
                    })
            },
        },
        components: {
            StudyEditStep1,
            StudyEditStep2,
            StudyEditStep3,
        }
    }
</script>

<style scoped>

</style>
