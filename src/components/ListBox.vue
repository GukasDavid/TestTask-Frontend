<template>
    <div >
        <div id="app">
    <select v-model="digit" @change="updateValueChange()" >
        <option>0</option>
        <option>1</option>
        <option>2</option>
        <option>3</option>
        <option>4</option>
        <option>5</option>
        <option>6</option>
        <option>7</option>
        <option>8</option>
        <option>9</option>
    </select>
</div>
    </div>
  </template>
  <script>
  import UserService from '../services/user.service';
  
  export default {
    name: 'ListBox',
    props:['number'],
    data() {
      return {
        digit:0,
        content: ''
      };
    },
    mounted() {
      UserService.getPublicContent().then(
        response => {
          this.content = response.data;
        },
        error => {
          this.content =
            (error.response && error.response.data && error.response.data.message) ||
            error.message ||
            error.toString();
        }
      );
    },
    methods: {
        updateValueChange() {
            // генерируем событие 'remove' и передаём id элемента
            this.$emit('change_item', this.digit);
        },
    }
  };
  </script>