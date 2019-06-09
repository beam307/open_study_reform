<template>
  <div>
    <file-pond
      name="image_upload"
      ref="pond"
      label-idle="드래그 또는 클릭하세요.(최대 4개)"
      allow-multiple="true"
      maxFiles="4"
      accepted-file-types="image/jpeg, image/png"
      v-bind:files="thumbnail"
      v-on:init="handleFilePondInit"/>
  </div>
</template>

<script>
  import vueFilePond from 'vue-filepond';
  import FilePondPluginFileValidateType from 'filepond-plugin-file-validate-type';
  import FilePondPluginImagePreview from 'filepond-plugin-image-preview';

  const FilePond = vueFilePond(FilePondPluginFileValidateType, FilePondPluginImagePreview);

  export default {
    data: function () {
      return {
        thumbnail: [],
      };
    },
    methods: {
      handleFilePondInit: function () {
        console.log('FilePond has initialized');
      }
    },
    components: {
      FilePond
    }
  };
</script>

<style>
  .image-upload .filepond--panel-root {
    border: 2px solid #424242;
  }

  .image-upload .filepond--item {
    width: calc(25% - .5em);
  }

  @media (max-width: 600px) {
    .image-upload .filepond--item {
      width: calc(100% - .5em);
    }
  }
</style>
