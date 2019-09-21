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
              label="지역 대분류"
              solo
              @change="minorRegionChoice($event)"
            ></v-select>
          </v-flex>
          <v-flex xs6 px-2 py-1>
            <v-select
              :items="minorRegions"
              v-model="minorRegion"
              item-text="text"
              item-value="value"
              label="지역 소분류"
              solo
            ></v-select>
          </v-flex>
        </v-layout>
        <v-layout wrap>
          <v-flex xs6 px-2 py-1>
            <v-select
              :items="majorCategories"
              v-model="majorCategory"
              item-text="text"
              item-value="value"
              label="카테고리 대분류"
              solo
              @change="minorCategoryChoice($event)"
            ></v-select>
          </v-flex>
          <v-flex xs6 px-2 py-1>
            <v-select
              :items="minorCategories"
              v-model="minorCategory"
              item-text="text"
              item-value="value"
              label="카테고리 소분류"
              solo
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
            ></v-select>
          </v-flex>
          <v-flex sm3 xs6>
            <v-select
              :items="counts"
              item-text="text"
              item-value="value"
              label="스터디 수"
              v-model="count"
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
            v-model="page"
            :length="5"
            :value="page"
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
        page: parseInt(this.$route.query.page) || 1,
        filter: this.$route.query.filter || 'l',
        count: parseInt(this.$route.query.count) || 1,
        majorRegion: parseInt(this.$route.query.majorRegion) || null,
        minorRegion: parseInt(this.$route.query.minorRegion) || null,
        majorCategory: parseInt(this.$route.query.majorCategory) || null,
        minorCategory: parseInt(this.$route.query.minorCategory) || null
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

      this.$http.get('/api/study/minorRegion')
        .then((result) => {
          this.minorRegionAll = result.data;
        })
        .catch((e) => {
          console.log(e);
        });

      this.$http.get('/api/study/list', {
          params: {
              page: this.page,
              filter: this.filter,
              count: this.count,
              majorRegion: this.majorRegion,
              minorRegion: this.minorRegion,
              majorCategory: this.majorCategory,
              minorCategory: this.minorCategory
          }
      })
        .then((result) => {
          this.studies = result.data;
          this.studies.map(s => s.meta = JSON.parse(s.meta));
        })
        .catch((e) => {
          console.log(e);
        });
    },
    methods: {
      minorCategoryChoice(major) {
        let majorCategory = _.find(this.majorCategories, m => m.text == major);
        this.minorCategories = majorCategory.items.map(c => {
          return {
            text: c.name,
            value: c.id
          }
        });
      },
      minorRegionChoice(code) {
        if (code == 40) {
          this.minorRegions = [{text: '전국', value: 4000}];
          return;
        }
        this.minorRegions = this.minorRegionAll[code].map(r => {
          return {
            text: r.name,
            value: r.id
          }
        });
      },
    }

  }
</script>

<style scoped>

</style>
