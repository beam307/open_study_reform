<template>
  <v-card class="mb-5" color="grey lighten-4">
    <v-layout wrap>
      <v-flex xs4 sm2 px-2 py-1>
        <v-subheader class="align-content-end">소개글</v-subheader>
      </v-flex>
      <v-flex xs8 sm10 px-2 py-1>
        <v-textarea
          outline
          rows="10"
          no-resize
          v-model="study.introduce"
        ></v-textarea>
      </v-flex>
    </v-layout>
    <v-layout wrap>
      <v-flex xs4 sm2 px-2 py-1>
        <v-subheader class="align-content-end">이미지</v-subheader>
      </v-flex>
      <v-flex xs8 sm10 px-2 py-1 class="image-upload">
        <image-upload v-if="this.$route.params.id == 'new'" :images="study.meta.images"></image-upload>
        <v-subheader v-else class="font-weight-bold">이미지는 수정할 수 없습니다.</v-subheader>
      </v-flex>
    </v-layout>
  </v-card>
</template>

<script>
    import imageUpload from '../../../components/common/ImageUpload';

    export default {
        props: ['study'],
        components: {
            imageUpload
        },
    }
</script>

<style scoped>

</style>
