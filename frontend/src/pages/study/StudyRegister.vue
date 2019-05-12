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
          <v-card class="mb-5" color="grey lighten-4">
            <Category/>
            <v-layout wrap>
              <v-flex xs4 sm3 px-2 py-1>
                <v-subheader style="align-items:flex-end">스터디명</v-subheader>
              </v-flex>
              <v-flex xs8 sm9 px-2 py-1>
                <v-text-field label="스터디명"></v-text-field>
              </v-flex>
            </v-layout>
            <v-layout wrap>
              <v-flex xs2 px-2 py-1>
                <v-subheader>지역</v-subheader>
              </v-flex>
              <v-flex xs5 px-2 py-1>
                <v-select
                  :items="majorRegion"
                  label="지역 대분류"
                  solo
                ></v-select>
              </v-flex>
              <v-flex xs5 px-2 py-1>
                <v-select
                  :items="minorRegion"
                  label="지역 소분류"
                  solo
                ></v-select>
              </v-flex>
            </v-layout>
          </v-card>
          <v-btn color="secondary" @click="step++">다음</v-btn>
          <v-btn flat to="/study/list">취소</v-btn>
        </v-stepper-content>

        <v-stepper-content step="2">
          <v-card
            class="mb-5"
            color="grey lighten-1"
            height="200px"
          ></v-card>
          <v-btn color="secondary" @click="step++">다음</v-btn>
          <v-btn flat to="/study/list">취소</v-btn>
        </v-stepper-content>

        <v-stepper-content step="3">
          <v-card
            class="mb-5"
            color="grey lighten-1"
            height="200px"
          ></v-card>

          <v-btn color="secondary" @click="step = 1">완료</v-btn>
          <v-btn flat to="/study/list">취소</v-btn>
        </v-stepper-content>
      </v-stepper-items>
    </v-stepper>
  </v-container>
</template>

<script>
  import Category from '../../components/common/Category'

  export default {
    data() {
      return {
        step: 0,
        majorRegion: ['서울특별시', '경기도', '인천광역시'],
        minorRegion: ['강남구', '성남시'],
        majorCategory: ['컴퓨터'],
        minorCategory: ['웹개발'],
      }
    },
    components: {
      Category
    }
  }
</script>

<style scoped>

</style>
