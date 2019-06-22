<template>
  <v-tabs dark grow>
    <v-tab v-for="category in categories" :key="category.title" ripple>{{ category.title }}</v-tab>
    <v-tab-item v-for="category in categories" :key="category.title">
      <v-card flat>
        <v-layout wrap>
          <v-flex xs12 sm4 v-for="item in category.items" :key="item.id">
            <v-checkbox :label="item.name" :disabled="disabled(item.id)" :value="item.id"
                        v-model="checked"></v-checkbox>
          </v-flex>
        </v-layout>
      </v-card>
    </v-tab-item>
  </v-tabs>
</template>

<script>
  import _ from 'lodash'

  export default {
    data() {
      return {
        categories: [],
        checked: [],
      }
    },
    created() {
      this.$http.get(`${process.env.JAVA_API_URL}/api/study/category`)
        .then((result) => {
          this.categories = _.values(_.mapValues(result.data, (i) => {
            return {
              title: i[0].title,
              items: i
            }
          }));
        })
        .catch((e) => {
          console.log(e);
        })
    },
    methods: {
      disabled(id) {
        return this.checked.length>=5 && !this.checked.includes(id)
      }
    }
  }
</script>

<style scoped>

</style>
