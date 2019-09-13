<template>
  <v-timeline align-top dense>
    <v-timeline-item color="black" small v-for="reply in replies" :key="reply.id">
      <v-layout pt-3>
        <v-flex xs6 sm3>
          <v-icon size="20" v-if="reply.secret">lock</v-icon>
          <template v-if="secret(reply.userId, reply.secret)">
            <strong>작성자</strong>
            <span>{{reply.nickname}}</span>
          </template>
        </v-flex>
        <v-spacer class="hidden-xs-only"></v-spacer>
        <v-flex xs6 sm3 class="text-sm-right text-xs-right">
          <strong>{{reply.createdAt | date('YYYY-MM-DD')}}</strong>
        </v-flex>
      </v-layout>
      <v-layout>
        <v-flex>
          <v-textarea v-if="secret(reply.userId, reply.secret)" readonly no-resize
                      solo flat background-color="transparent" rows="3" :value="reply.text"></v-textarea>
          <p v-else>비밀글 입니다.</p>
        </v-flex>
      </v-layout>
    </v-timeline-item>
  </v-timeline>
</template>

<script>
    import {mapGetters} from 'vuex'

    export default {
        props: ['studyWriterId'],
        data() {
            return {
                replies: []
            }
        },
        computed: {
            ...mapGetters({
                authenticated: 'user/authenticated',
                isProfile: 'user/isProfile',
                userId: 'user/id',
                name: 'user/name',
            }),
        },
        created() {
            this.$http.get(`/api/reply/${this.$route.params.id}`)
                .then(r => {
                    this.replies = r.data;
                }).catch(e => {
                console.log(e);
            })
        },
        methods: {
            secret(userId, secret) {
                return !(secret && (this.userId != userId || this.userId != this.studyWriterId));
            }
        }
    }
</script>

<style scoped>

</style>
