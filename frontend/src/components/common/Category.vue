<template>
  <v-tabs v-model="active" dark grow show-arrows>
    <v-tab v-for="category in categorys" :key="category.title" ripple>{{ category.title }}</v-tab>
    <v-tab-item v-for="category in categorys" :key="category.title">
      <v-card flat>
        <v-layout wrap>
          <v-flex xs12 sm4 v-for="item in category.items">
            <v-checkbox :label="item"></v-checkbox>
          </v-flex>
        </v-layout>
      </v-card>
    </v-tab-item>
  </v-tabs>
</template>

<script>
  export default {
    data() {
      return {
        categorys: [
          {title: '영어', items: ['토익', '토플', '공무원', '회화', '독해']},
          {title: '외국어', items: ['중국어', '일본어', '독일어', '프랑스어', '스페인어']},
          {title: 'IT', items: ['웹개발', '게임개발', '자격증', '프론트엔드', '백엔드']},
          {title: '교양', items: ['피아노', '보컬']},
          {title: '공무원', items: ['행정직', '사회복지직', '운전직', '세무직', '9급', '7급', '임용고시']},
        ]
      }
    }
  }
</script>

<style scoped>

</style>
