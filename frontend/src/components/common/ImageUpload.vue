<template>
  <div>
    <file-pond
      label-idle="드래그 또는 클릭하세요.(최대 4개)"
      allow-multiple="true"
      maxFiles="4"
      :server="{process, revert}"
      accepted-file-types="image/*"/>
  </div>
</template>

<script>
    import vueFilePond from 'vue-filepond';
    import FilePondPluginFileValidateType from 'filepond-plugin-file-validate-type';
    import FilePondPluginImagePreview from 'filepond-plugin-image-preview';

    const FilePond = vueFilePond(FilePondPluginFileValidateType, FilePondPluginImagePreview);

    export default {
        props: ['images'],
        data: function () {
            return {
                saveURL: '/api/upload/image',
                deleteURL: '/api/upload/delete',
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
                        let file = result.data;
                        load(file.key);
                        this.images.push(file.url);
                    })
                    .catch((e) => {
                        console.log(e);
                        abort();
                    });
            },
            revert(uniqueFileId, load, error) {
                this.$http.post(this.deleteURL, {fileName: uniqueFileId})
                    .then(() => {
                        let index = this.images.indexOf(uniqueFileId);
                        this.images.splice(index, 1);
                        load();
                    })
                    .catch((e) => {
                        console.log(e);
                        error();
                    });
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
