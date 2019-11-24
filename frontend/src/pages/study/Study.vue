<template>
  <v-container mt-5 mb-5>
    <v-layout mt-3 mb-5 justify-center>
      <v-flex xs12>
        <Header/>
        <router-view :key="$route.fullPath"></router-view>
        <Footer/>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  import Header from '../../components/layouts/Header'
  import Footer from '../../components/layouts/Footer'

  export default {
    components: {
      Header,
      Footer,
    },
  }
</script>

<style scoped>

</style>
