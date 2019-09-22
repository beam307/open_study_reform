<template>
  <v-container fluid>
    <v-card>
      <v-card-text>
        <v-layout wrap>
          <v-flex xs6 px-2 py-1>
            <v-select
              :items="majorRegions"
              v-model="majorRegion"
              item-text="text"
              item-value="value"
              label="지역 분류"
              solo
              @change="list"
            ></v-select>
          </v-flex>
          <v-flex xs6 px-2 py-1>
            <v-select
              :items="majorCategories"
              v-model="majorCategory"
              item-text="text"
              item-value="value"
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
                majorRegions: [],
                minorRegions: [],
                minorRegionAll: [],
                majorCategories: [],
                minorCategories: [],
                filters: [
                    {text: '최신순', value: 'l'},
                    {text: '인기순', value: 'p'},
                    {text: '조회수', value: 'r'}],
                counts: [
                    {text: '9개씩', value: 1},
                    {text: '18개씩', value: 2},
                    {text: '27개씩', value: 3},
                    {text: '36개씩', value: 4}],
                page: {
                    length: 1,
                    value: parseInt(this.$route.query.page) || 1,
                },
                filter: this.$route.query.filter || 'l',
                count: parseInt(this.$route.query.count) || 1,
                majorRegion: parseInt(this.$route.query.majorRegion) || null,
                majorCategory: parseInt(this.$route.query.majorCategory) || null,
            }
        },
        components: {
            Thumbnail
        },
        created() {
            this.$http.get('/api/study/category')
                .then((result) => {
                    this.majorCategories = _.values(_.mapValues(result.data, (i) => {
                        return {
                            text: i[0].title,
                            items: i
                        }
                    }));
                })
                .catch((e) => {
                    console.log(e);
                });

            this.$http.get('/api/study/majorRegion')
                .then((result) => {
                    this.majorRegions = result.data.map(r => {
                        return {
                            text: r.name,
                            value: r.id
                        }
                    });
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
                        majorRegion: this.majorRegion,
                        majorCategory: this.majorCategory,
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
