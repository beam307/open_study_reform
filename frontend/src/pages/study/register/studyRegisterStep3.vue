<template>
  <v-card class="mb-5" color="grey lighten-4">
    <v-layout wrap>
      <v-flex xs4 sm2 px-2 py-1>
        <v-subheader style="align-items:flex-end">소개글</v-subheader>
      </v-flex>
      <v-flex xs8 sm10 px-2 py-1>
        <v-textarea
          outline
          rows="10"
        ></v-textarea>
      </v-flex>
    </v-layout>
  </v-card>
</template>

<script>

  export default {
    data() {
      return {
      }
    },
    components: {
    }
  }
</script>

<style scoped>

</style>
