<template>
  <v-card class="mb-5" color="grey lighten-4">
    <Category></Category>
    <v-layout wrap>
      <v-flex xs4 sm2 px-2 py-1>
        <v-subheader style="align-items:flex-end">스터디명</v-subheader>
      </v-flex>
      <v-flex xs8 sm10 px-2 py-1>
        <v-text-field label="스터디명" v-on:click="test"></v-text-field>
      </v-flex>
    </v-layout>
    <v-layout wrap>
      <v-flex xs2 px-2 py-1>
        <v-subheader>지역</v-subheader>
      </v-flex>
      <v-flex xs5 px-2 py-1>
        <v-select
          :items="majorRegions"
          label="지역 대분류"
          solo
          v-model="majorRegion"
          v-on:change="minorRegionChoice(majorRegion)"
        ></v-select>
      </v-flex>
      <v-flex xs5 px-2 py-1>
        <v-select
          :items="minorRegions"
          label="지역 소분류"
          solo
          v-model="minorRegion"
        ></v-select>
      </v-flex>
    </v-layout>
  </v-card>
</template>

<script>
  import Category from '../../../components/common/Category'

  export default {
    data() {
      return {
        majorRegions: [],
        minorRegions: [],
        MinorRegionAll: [],
        majorRegion: 0,
        minorRegion: 0,
      }
    },
    created() {
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
        })
      this.$http.get(`${process.env.JAVA_API_URL}/api/study/minorRegion`)
        .then((result) => {
          this.MinorRegionAll = result.data;
        })
        .catch((e) => {
          console.log(e);
        })
    },
    methods: {
      test() {
        console.log(this.majorRegion, this.minorRegion);
      },
      minorRegionChoice(code) {
        if (code == 40) {
          this.minorRegions = [{text: '전국', value: 4000}];
          return;
        }
        let minor = this.MinorRegionAll[code];
        this.minorRegions = minor.map(r => {
          return {
            text: r.name,
            value: r.id
          }
        });
      }
    },
    components: {
      Category
    }
  }
</script>

<style scoped>

</style>
