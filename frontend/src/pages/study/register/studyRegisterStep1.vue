<template>
  <v-card class="mb-5" color="grey lighten-4">
    <Category v-model="checked"></Category>
    <v-layout wrap>
      <v-flex xs4 sm2 px-2 py-1>
        <v-subheader style="align-items:flex-end">스터디명</v-subheader>
      </v-flex>
      <v-flex xs8 sm10 px-2 py-1>
        <v-text-field label="스터디명" v-model="name"></v-text-field>
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
          @change="minorRegionChoice(majorRegion)"
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
                minorRegionAll: [],
            }
        },
        created() {
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
        },
        methods: {
            minorRegionChoice(code) {
                if (code == 40) {
                    this.minorRegions = [{text: '전국', value: 4000}];
                    return;
                }
                let minor = this.minorRegionAll[code];
                this.minorRegions = minor.map(r => {
                    return {
                        text: r.name,
                        value: r.id
                    }
                });
            },
        },
        computed: {
            name: {
                get() {
                    return this.$store.state.study.name
                },
                set(value) {
                    this.$store.commit('study/setName', value)
                }
            },
            majorRegion: {
                get() {
                    return this.$store.state.study.majorRegionId
                },
                set(value) {
                    this.$store.commit('study/setMajorRegion', value)
                }
            },
            minorRegion: {
                get() {
                    return this.$store.state.study.minorRegionId
                },
                set(value) {
                    this.$store.commit('study/setMinorRegion', value)
                }
            },
            checked: {
                get() {
                    return this.$store.state.study.categoryIds
                },
                set(value) {
                    this.$store.commit('study/setCategories', value)
                }
            }
        },
        components: {
            Category
        }
    }
</script>

<style scoped>

</style>
