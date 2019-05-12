<template>
  <v-container fluid>
    <v-card>
      <v-card-text>
        <v-layout wrap>
          <v-flex sm9 xs8 pa-2>
            <v-text-field height="36" autofocus label="검색어를 입력하세요."></v-text-field>
          </v-flex>
          <v-flex sm3 xs4 pa-2>
            <v-btn block large type="submit">검색</v-btn>
          </v-flex>
        </v-layout>
        <v-layout wrap>
          <v-flex xs6 px-2 py-1>
            <v-select
              :items="majorRegion"
              label="지역 대분류"
              solo
            ></v-select>
          </v-flex>
          <v-flex xs6 px-2 py-1>
            <v-select
              :items="minorRegion"
              label="지역 소분류"
              solo
            ></v-select>
          </v-flex>
        </v-layout>
        <v-layout wrap>
          <v-flex xs6 px-2 py-1>
            <v-select
              :items="majorCategory"
              label="카테고리 대분류"
              solo
            ></v-select>
          </v-flex>
          <v-flex xs6 px-2 py-1>
            <v-select
              :items="minorCategory"
              label="카테고리 소분류"
              solo
            ></v-select>
          </v-flex>
        </v-layout>
      </v-card-text>
    </v-card>
    <v-card class="mt-3">
      <v-card-text>
        <v-layout wrap>
          <v-flex sm3 xs6>
            <v-select
              :items="filter"
              label="정렬"
              solo
            ></v-select>
          </v-flex>
        </v-layout>
        <v-layout wrap>
          <Thumbnail></Thumbnail>
          <Thumbnail></Thumbnail>
          <Thumbnail></Thumbnail>
          <Thumbnail></Thumbnail>
          <Thumbnail></Thumbnail>
          <Thumbnail></Thumbnail>
          <Thumbnail></Thumbnail>
          <Thumbnail></Thumbnail>
          <Thumbnail></Thumbnail>
        </v-layout>
        <v-layout justify-center>
          <v-pagination
            v-model="page"
            :length="5"
          ></v-pagination>
        </v-layout>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
  import Thumbnail from '../../components/common/Thumbnail'

  export default {
    data() {
      return {
        majorRegion: ['경기도'],
        minorRegion: ['성남시'],
        majorCategory: ['컴퓨터'],
        minorCategory: ['웹개발'],
        filter: ['최신순', '인기순', '조회수'],
        page: 1
      }
    },
    components: {
      Thumbnail
    }
  }
</script>

<style scoped>

</style>
