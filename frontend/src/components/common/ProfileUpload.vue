<template>
  <div class="profile-upload">
    <file-pond
      label-idle="Drag & Drop your picture "
      imagePreviewHeight="170"
      imageCropAspectRatio="1:1"
      imageResizeTargetWidth=200
      imageResizeTargetHeight=200
      stylePanelLayout="compact circle"
      styleLoadIndicatorPosition="center bottom"
      styleProgressIndicatorPosition="right bottom"
      styleButtonRemoveItemPosition="left bottom"
      styleButtonProcessItemPosition="right bottom"
    ></file-pond>
  </div>
</template>

<script>
    import vueFilePond from 'vue-filepond';
    import FilePondPluginFileValidateType from 'filepond-plugin-file-validate-type';
    import FilePondPluginImagePreview from 'filepond-plugin-image-preview';
    import FilePondPluginFileEncode from 'filepond-plugin-file-encode';
    import FilePondPluginImageExifOrientation from 'filepond-plugin-image-exif-orientation';
    import FilePondPluginImageCrop from 'filepond-plugin-image-crop';
    import FilePondPluginImageResize from 'filepond-plugin-image-resize';
    import FilePondPluginImageTransform from 'filepond-plugin-image-transform';

    vueFilePond(FilePondPluginFileEncode,
        FilePondPluginFileValidateType,
        FilePondPluginImageExifOrientation,
        FilePondPluginImagePreview,
        FilePondPluginImageCrop,
        FilePondPluginImageResize,
        FilePondPluginImageTransform
    );

    export default {
        props: ['images'],
        data: function () {
            return {
                saveTempURL: '/api/study/saveTempImage',
                deleteTempURL: '/api/study/deleteTempImage',
            };
        },
        methods: {},
        components: {}
    };
</script>

<style>
  .profile-upload .filepond--root {
    width: 170px;
    margin: 0 auto;
  }

  .profile-upload .filepond--drop-label {
    color: #4c4e53;
  }

  .profile-upload .filepond--label-action {
    text-decoration-color: #babdc0;
  }

  .profile-upload .filepond--panel-root {
    background-color: #edf0f4;
  }
</style>
