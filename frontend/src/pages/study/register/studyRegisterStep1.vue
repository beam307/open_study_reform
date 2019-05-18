<template>
  <v-card class="mb-5" color="grey lighten-4">
    <Category/>
    <v-layout wrap>
      <v-flex xs4 sm2 px-2 py-1>
        <v-subheader style="align-items:flex-end">스터디명</v-subheader>
      </v-flex>
      <v-flex xs8 sm10 px-2 py-1>
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
</template>

<script>
  import Category from '../../../components/common/Category'

  export default {
    data() {
      return {
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
