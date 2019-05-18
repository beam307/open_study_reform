<template>
  <v-card class="mb-5" color="grey lighten-4">
    <v-layout wrap>
      <v-flex xs4 sm2 px-2 py-1>
        <v-subheader style="align-items:flex-end">연령대</v-subheader>
      </v-flex>
      <v-flex xs8 sm10 px-2 py-1>
        <v-layout wrap>
          <v-flex xs12 sm2>
            <v-checkbox v-model="age"
                        value="10"
                        :label="'10대'"
            ></v-checkbox>
          </v-flex>
          <v-flex xs12 sm2>
            <v-checkbox v-model="age"
                        value="20"
                        :label="'20대'"
            ></v-checkbox>
          </v-flex>
          <v-flex xs12 sm2>
            <v-checkbox v-model="age"
                        value="30"
                        :label="'30대'"
            ></v-checkbox>
          </v-flex>
          <v-flex xs12 sm2>
            <v-checkbox v-model="age"
                        value="40"
                        :label="'40대'"
            ></v-checkbox>
          </v-flex>
          <v-flex xs12 sm2>
            <v-checkbox v-model="age"
                        value="50"
                        :label="'50대'"
            ></v-checkbox>
          </v-flex>
          <v-flex xs12 sm2>
            <v-checkbox v-model="age"
                        value="0"
                        :label="'무관'"
            ></v-checkbox>
          </v-flex>
        </v-layout>
      </v-flex>
    </v-layout>
    <v-layout wrap>
      <v-flex xs2 px-2 py-1>
        <v-subheader>시작날짜</v-subheader>
      </v-flex>
      <v-flex xs10 px-2 py-1>
        <v-menu
          v-model="startDatePicker"
          :close-on-content-click="false"
          :nudge-right="40"
          transition="scale-transition"
          offset-y
          full-width
          min-width="290px"
        >
          <template v-slot:activator="{ on }">
            <v-text-field
              v-model="startDate"
              readonly
              v-on="on"
              class="mt-1 pt-0"
            ></v-text-field>
          </template>
          <v-date-picker reactive no-title dark v-model="startDate" @input="startDatePicker = false"></v-date-picker>
        </v-menu>
      </v-flex>
    </v-layout>
    <v-layout wrap>
      <v-flex xs2 px-2 py-1>
        <v-subheader>요일</v-subheader>
      </v-flex>
      <v-flex xs10 px-2 py-1>
        <v-select
          :items="weekday"
          label="요일"
          solo
        ></v-select>
      </v-flex>
    </v-layout>
    <v-layout wrap>
      <v-flex xs2 px-2 py-1>
        <v-subheader>시간대</v-subheader>
      </v-flex>
      <v-flex xs5 px-2 py-1>
        <v-menu
          ref="startTimeMenu"
          v-model="startTimePicker"
          :close-on-content-click="false"
          :nudge-right="40"
          :return-value.sync="startTime"
          transition="scale-transition"
          offset-y
          full-width
          max-width="290px"
          min-width="290px"
        >
          <template v-slot:activator="{ on }">
            <v-text-field
              v-model="startTime"
              label="시작시간"
              prepend-icon="access_time"
              readonly
              v-on="on"
            ></v-text-field>
          </template>
          <v-time-picker
            v-if="startTimePicker"
            v-model="startTime"
            full-width
            @click:minute="$refs.startTimeMenu.save(startTime)"
          ></v-time-picker>
        </v-menu>
      </v-flex>
      <v-flex xs5 px-2 py-1>
        <v-menu
          ref="endTimeMenu"
          v-model="endTimePicker"
          :close-on-content-click="false"
          :nudge-right="40"
          :return-value.sync="endTime"
          transition="scale-transition"
          offset-y
          full-width
          max-width="290px"
          min-width="290px"
        >
          <template v-slot:activator="{ on }">
            <v-text-field
              v-model="endTime"
              label="종료시간"
              prepend-icon="access_time"
              readonly
              v-on="on"
            ></v-text-field>
          </template>
          <v-time-picker
            v-if="endTimePicker"
            v-model="endTime"
            full-width
            @click:minute="$refs.endTimeMenu.save(endTime)"
          ></v-time-picker>
        </v-menu>
      </v-flex>
    </v-layout>
    <v-layout wrap>
      <v-flex xs2 px-2 py-1>
        <v-subheader>최대인원</v-subheader>
      </v-flex>
      <v-flex xs10 px-2 py-1>
        <v-text-field
          v-model="count"
          class="mt-1 pt-0"
        ></v-text-field>
      </v-flex>
    </v-layout>
  </v-card>
</template>

<script>
  import Category from '../../../components/common/Category'

  export default {
    data() {
      return {
        age: [],
        count: 0,
        weekday: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
        startDate: new Date().toISOString().substr(0, 10),
        startDatePicker: false,
        startTime: null,
        startTimePicker: false,
        endTime: null,
        endTimePicker: false,
      }
    },
    components: {
      Category
    },
  }
</script>

<style scoped>

</style>
