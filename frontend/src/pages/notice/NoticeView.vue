<template>
  <v-container class="notice-list" d-flex justify-end>
    <v-list class="list">
      <template v-for="notice in notices">
        <v-list-tile :key="notice.id" avatar @click="goView(notice.id)" :class="active(notice.id)">
          <v-list-tile-content>
            <v-list-tile-title v-html="notice.title"></v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </template>
    </v-list>
    <v-card v-if="notice" flat color="#fafafa" class="container-wrapper pa-3">
      <v-toolbar class="pt-2" flat color="#fafafa">
        <v-toolbar-title><h4>{{notice.title}}</h4></v-toolbar-title>
      </v-toolbar>
      <v-card-text class="pa-4">
        <vue-markdown>{{notice.content}}</vue-markdown>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn dark @click="goList">목록</v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
    import VueMarkdown from 'vue-markdown'

    export default {
        data() {
            return {
                notice: null,
                notices: []
            }
        },
        created() {
            this.$http.get(`/api/notice/${this.$route.params.id}`)
                .then(result => this.notice = result.data)
                .catch((e) => {
                    console.log(e);
                });
            this.$http.get('/api/notice/list')
                .then(result => this.notices = result.data)
                .catch((e) => {
                    console.log(e);
                });
        },
        methods: {
            goList() {
                this.$router.push({name: 'noticeList'})
            },
            goView(id) {
                this.$router.push({name: 'noticeView', params: {id: id}})
            },
            active(id) {
                return {active: this.$route.params.id == id};
            }
        },
        components: {
            VueMarkdown
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
      flex: 0 0 20% !important;
    }

    .active {
      background: rgba(0, 0, 0, 0.04);
    }
  }
</style>
