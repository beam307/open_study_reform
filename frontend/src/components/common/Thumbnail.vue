<template>
  <v-card>
    <v-img :src="require(`../../assets/temp/${study.meta.images[0]}`)"contain max-width="300" max-height="150"></v-img>
    <v-card-title primary-title>
      {{study.name}}
    </v-card-title>
    <v-card-actions>
      <v-btn flat color="orange" @click="test">자세히보기</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
  export default {
    props: ['study'],
    data() {
      return {

      }
    },
    methods: {
      test() {
        console.log(this.study);
      }
    }
  }
</script>

<style>

</style>
