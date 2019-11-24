<template>
  <v-tabs centered dark grow>
    <v-tabs-slider color="yellow"></v-tabs-slider>
    <v-tab href="#user">
      <v-icon>person</v-icon>
    </v-tab>
    <v-tab href="#study">
      <v-icon>menu_book</v-icon>
    </v-tab>
    <v-tab-item value="user">
      <UserManage></UserManage>
    </v-tab-item>
    <v-tab-item value="study">
      <StudyManage></StudyManage>
    </v-tab-item>
  </v-tabs>
</template>

<script>
    import StudyManage from "./StudyManage";
    import UserManage from "./UserManage";

    export default {
        components: {
            UserManage,
            StudyManage,
        },
    }
</script>

<style scoped>

</style>
