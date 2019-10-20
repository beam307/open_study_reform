<template>
  <div>
    <v-tabs dark grow v-model="active">
      <v-tab v-for="category in categories" :key="category.id" ripple>{{category.title}}</v-tab>
      <v-tab-item v-for="category in categories" :key="category.id">
        <v-card flat>
          <v-layout wrap>
            <v-flex xs12 sm4 v-for="item in category.items" :key="item.id">
              <v-checkbox :label="item.name" :disabled="disabled(item.id)" :value="item.id"
                          v-model="internalValue"></v-checkbox>
            </v-flex>
          </v-layout>
        </v-card>
      </v-tab-item>
    </v-tabs>
  </div>
</template>

<script>
    import _ from 'lodash'

    export default {
        props: ['value'],
        data() {
            return {
                categories: [],
            }
        },
        created() {
            this.$http.get('/api/study/category')
                .then((result) => {
                    this.categories = _.chain(result.data).values().mapValues(i => {
                        return {
                            title: i[0].title,
                            items: i,
                            id: parseInt(i[0].id / 100)
                        }
                    }).sortBy('id').value();
                })
                .catch((e) => {
                    console.log(e);
                })
        },
        methods: {
            disabled(id) {
                return this.internalValue.length >= 5 && !this.internalValue.includes(id)
            }
        },
        computed: {
            internalValue: {
                get() {
                    return this.value;
                },
                set(value) {
                    this.$emit('input', value);
                }
            },
            active: {
                get() {
                    if (this.value && this.value[0]) {
                        return parseInt(this.value[0] / 100);
                    } else {
                        return 0;
                    }
                },
                set(value) {
                }
            }
        }
    }
</script>

<style scoped>

</style>
