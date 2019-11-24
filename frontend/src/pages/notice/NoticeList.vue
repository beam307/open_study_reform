<template>
  <v-container class="notice-list" d-flex justify-end>
    <v-card flat color="#fafafa" class="container-wrapper pa-3">
      <v-subheader class="title font-weight-bold">공지사항</v-subheader>
      <v-list class="list">
        <template v-for="notice in notices">
          <v-list-tile :key="notice.id" avatar @click="goView(notice.id)">
            <v-list-tile-content>
              <v-list-tile-title v-html="notice.title"></v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
        </template>
      </v-list>
    </v-card>
  </v-container>
</template>

<script>
    export default {
        data() {
            return {
                notices: []
            }
        },
        created() {
            this.$http.get('/api/notice/list')
                .then(result => this.notices = result.data)
                .catch((e) => {
                    console.log(e);
                });
        },
        methods: {
            goView(id) {
                this.$router.push({name: 'noticeView', params: {id: id}})
            }
        }
    }
</script>

<style lang="scss" scoped>
  .notice-list {
    max-width: 1160px;

    .container-wrapper {
      flex: 0 0 80% !important;
    }

    .list {
      background: #fafafa;
    }
  }
</style>
