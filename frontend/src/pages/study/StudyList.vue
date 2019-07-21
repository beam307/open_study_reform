<template>
  <v-container fluid>
    <v-card>
      <v-card-text>
        <v-layout wrap>
          <v-flex sm9 xs8 pa-2>
            <v-text-field height="36" autofocus label="검색어를 입력하세요." v-model="keyword"></v-text-field>
          </v-flex>
          <v-flex sm3 xs4 pa-2>
            <v-btn block large type="submit">검색</v-btn>
          </v-flex>
        </v-layout>
        <v-layout wrap>
          <v-flex xs6 px-2 py-1>
            <v-select
              :items="majorRegions"
              label="지역 대분류"
              solo
              @change="minorRegionChoice($event)"
            ></v-select>
          </v-flex>
          <v-flex xs6 px-2 py-1>
            <v-select
              :items="minorRegions"
              label="지역 소분류"
              solo
            ></v-select>
          </v-flex>
        </v-layout>
        <v-layout wrap>
          <v-flex xs6 px-2 py-1>
            <v-select
              :items="majorCategories"
              label="카테고리 대분류"
              solo
              @change="minorCategoryChoice($event)"
            ></v-select>
          </v-flex>
          <v-flex xs6 px-2 py-1>
            <v-select
              :items="minorCategories"
              label="카테고리 소분류"
              solo
            ></v-select>
          </v-flex>
        </v-layout>
      </v-card-text>
    </v-card>
    <v-card class="mt-3">
      <v-card-text>
        <v-layout wrap>
          <v-flex sm3 xs6>
            <v-select
              :items="filter"
              label="정렬"
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
        keyword: '',
        majorRegions: [],
        minorRegions: [],
        minorRegionAll: [],
        majorCategories: [],
        minorCategories: [],
        filter: ['최신순', '인기순', '조회수'],
        page: 1,
        studies: [],
      }
    },
    components: {
      Thumbnail
    },
    created() {
      this.$http.get(`${process.env.JAVA_API_URL}/api/study/category`)
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

      this.$http.get(`${process.env.JAVA_API_URL}/api/study/majorRegion`)
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

      this.$http.get(`${process.env.JAVA_API_URL}/api/study/minorRegion`)
        .then((result) => {
          this.minorRegionAll = result.data;
        })
        .catch((e) => {
          console.log(e);
        });

      this.$http.get(`${process.env.JAVA_API_URL}/api/study/list`)
        .then((result) => {
          this.studies = result.data;
          this.studies.map(s => s.meta = JSON.parse(s.meta));
        })
        .catch((e) => {
          console.log(e);
          this.$route.router.go('/');
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
