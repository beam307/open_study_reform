<template>
  <div class="profile-upload">
    <file-pond
      label-idle="Drag & Drop OR Click"
      imagePreviewHeight="170"
      imageCropAspectRatio="1:1"
      imageResizeTargetWidth=200
      imageResizeTargetHeight=200
      :server="{process, load, revert}"
      :files="files"
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
        props: ['image'],
        data: function () {
            return {
                saveURL: '/api/upload/image',
                deleteURL: '/api/upload/delete',
                files: [{
                    source: this.image,
                    options: {
                        type: 'local'
                    }
                }],
            };
        },
        methods: {
            process(fieldName, file, metadata, load, error, progress, abort) {
                const formData = new FormData();
                formData.append('image', file, file.name);
                let config = {
                    onUploadProgress: e => {
                        progress(true, e.loaded, e.total);
                    }
                }
                this.$http.post(this.saveURL, formData, config)
                    .then((result) => {
                        let fileName = result.data;
                        load(fileName);
                        this.$emit('image', fileName, 1);
                    })
                    .catch((e) => {
                        console.log(e);
                        abort();
                    });
            },
            revert(fileName, load, error) {
                this.$http.post(this.deleteURL, {fileName: fileName})
                    .then((result) => {
                        load();
                        this.$emit('image', fileName, 0);
                    })
                    .catch((e) => {
                        console.log(e);
                        error();
                    });
            },
            load(source, load, error, progress, abort, headers) {
                console.log(this.image);
                fetch(source).then(res => {
                    console.log("zzz", res)
                    res.blob()
                }).then(load)
            },
        },
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
