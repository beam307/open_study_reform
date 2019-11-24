import Vue from 'vue'
import moment from "moment"

Vue.filter("date", (value, format) => {
  if(!value) return '';
  if(!format) return value;
  return moment(value).format(format);
});
