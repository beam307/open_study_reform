<template>
  <v-container fluid>
    <v-card>
      <v-card-title class="title pl-4">
        검색기능
      </v-card-title>
      <v-card-text class="py-0">
        <v-layout wrap>
          <v-flex xs6 px-2 py-1>
            <v-select
              :items="regions"
              v-model="region"
              item-text="name"
              item-value="id"
              label="지역 분류"
              solo
              @change="list"
            ></v-select>
          </v-flex>
          <v-flex xs6 px-2 py-1>
            <v-select
              :items="categories"
              v-model="category"
              item-text="name"
              item-value="id"
              label="카테고리 분류"
              solo
              @change="list"
            ></v-select>
          </v-flex>
        </v-layout>
      </v-card-text>
    </v-card>
    <v-card class="mt-3">
      <v-card-text>
        <v-layout wrap justify-space-between>
          <v-flex sm3 xs6>
            <v-select
              :items="filters"
              item-text="text"
              item-value="value"
              label="정렬"
              v-model="filter"
              solo
              @change="list"
            ></v-select>
          </v-flex>
          <v-flex sm3 xs6>
            <v-select
              :items="counts"
              item-text="text"
              item-value="value"
              label="스터디 수"
              v-model="count"
              @change="list"
              solo
            ></v-select>
          </v-flex>
        </v-layout>
        <v-layout wrap>
          <v-flex xs12 sm4 pa-2 mb-2 v-for="study in studies" :key="study.id">
            <Thumbnail :study="study"></Thumbnail>
          </v-flex>
        </v-layout>
        <v-layout justify-center>
          <v-pagination
            v-model="page.value"
            :length="page.length"
            :value="page.value"
            @input="list"
          ></v-pagination>
        </v-layout>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
    import Thumbnail from '../../components/common/Thumbnail'

    export default {
        data() {
            return {
                studies: [],
                regions: [{id: null, name: '전체'}],
                categories: [{id: null, name: '전체', title: '전체'}],
                filters: [
                    {text: '최신순', value: 'l'},
                    {text: '인기순', value: 'p'},
                    {text: '조회수', value: 'r'}],
                counts: [
                    {text: '9개씩', value: 9},
                    {text: '18개씩', value: 18},
                    {text: '27개씩', value: 27},
                    {text: '36개씩', value: 36}],
                page: {
                    length: 1,
                    value: 1,
                },
                filter: 'l',
                count: 9,
                region: null,
                category: null,
            }
        },
        components: {
            Thumbnail
        },
        created() {
            this.$http.get('/api/study/category/all')
                .then((result) => {
                    this.categories.push(...result.data);
                })
                .catch((e) => {
                    console.log(e);
                });

            this.$http.get('/api/study/majorRegion')
                .then((result) => {
                    this.regions.push(...result.data);
                })
                .catch((e) => {
                    console.log(e);
                });

            this.list(null);
        },

        methods: {
            list() {
                this.$http.get('/api/study/list', {
                    params: {
                        page: this.page.value,
                        filter: this.filter,
                        count: this.count,
                        region: this.region,
                        category: this.category,
                    }
                })
                    .then((result) => {
                        this.studies = result.data.studies;
                        this.page.length = result.data.total;
                        this.studies.map(s => s.meta = JSON.parse(s.meta));
                    })
                    .catch((e) => {
                        console.log(e);
                    })
            }
        }

    }
</script>

<style scoped>

</style>
