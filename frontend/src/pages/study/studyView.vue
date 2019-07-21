<template>
  <v-container fluid>
    <v-carousel>
      <v-carousel-item
        v-for="(image,i) in study.meta.images"
        :key="i"
        dark
        :src="require(`../../assets/temp/${image}`)"
      ></v-carousel-item>
    </v-carousel>
  </v-container>
</template>

<script>
  export default {
    data() {
      return {
        study: {
          meta: {
            images : null
          }
        }
      }
    },
    methods: {},
    created() {
      this.$http.get(`${process.env.JAVA_API_URL}/api/study/${this.$route.params.id}`)
        .then((result) => {
          this.study = result.data;
          this.study.meta = JSON.parse(this.study.meta);
          console.log(this.study);
        })
        .catch((e) => {
          console.log(e);
        });
    }
  }
</script>

<style scoped>

</style>
