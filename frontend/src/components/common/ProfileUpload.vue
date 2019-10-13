<template>
  <div class="profile-upload">
    <input class="input-file center" type="file" name="image" accept="image/*"
           ref="fileInput"
           @change="upload($event.target.name, $event.target.files)"
           @drop="upload($event.target.name, $event.target.files)"/>
    <h3>click or drag&drop </h3>
    <div class="preview">
      <img v-if="imageUrl" :src="require(`@/assets/${imageUrl}`)" @click="trigger"/>
    </div>
  </div>
</template>

<script>

    export default {
        props: ['image'],
        data: function () {
            return {
                saveURL: '/api/upload/image',
                deleteURL: '/api/upload/delete',
            }
        },
        computed: {
            imageUrl() {
                return this.image;
            }
        },
        methods: {
            trigger() {
                this.$refs.fileInput.click();
            },
            upload(name, files) {
                const formData = new FormData();
                const maxSize = 7 * 1024 * 1024;
                if(files[0] && !files[0].type.includes('image')) {
                    alert("이미지만 첨부 가능합니다.");
                    return false;
                }
                if(files[0] && files[0].size > maxSize) {
                    alert("첨부파일 사이즈는 7MB 이내로 등록 가능합니다.");
                    return false;
                }
                formData.append(name, files[0], files[0].name);
                this.$http.post(this.saveURL, formData)
                    .then((result) => {
                        let fileName = result.data;
                        this.$emit('image', `temp/${fileName}`);
                    })
                    .catch((e) => {
                        console.log(e);
                        alert("이미지 업로드에 실패하였습니다.");
                    });
                //
            }
        },

        components: {}
    }
</script>

<style>
  .profile-upload {
    width: 170px;
    height: 170px;
    border-radius: 50%;
    border: 2px dashed #8c8c8c;
    background: #d9d9d9;
    position: relative;
    margin: 0 auto;
  }

  .profile-upload > h3 {
    position: absolute;
    top: 50%;
    left: 50%;
    width: 100%;
    text-align: center;
    transform: translate(-50%, -50%);
    z-index: 2;
  }

  .profile-upload .input-file {
    position: absolute;
    opacity: 0;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    z-index: 3;
  }

  .profile-upload .preview {
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .profile-upload .preview img {
    border-radius: 50%;
    max-width: 100%;
    z-index: 5;
    width: 170px;
    height: 170px;
    background-color: white;
  }

  .profile-upload .center {
    display: block;
    margin-left: auto;
    margin-right: auto;
    width: 50%;
  }
</style>
