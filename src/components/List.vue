<template>
    <div id ="parent">
    <template >
      <ul>
        
        <div class="el" v-for="item in arrElem">
          {{item}}
        </div>
      </ul>
    </template>
    </div>
</template>
<script>
export default {
  props:['arrElem'],
  data(){
    return{
    };
    
  },
  mounted() {
  },
  computed: {

    
  },
  methods: {
}
}
</script>
<style>
      .listSoong {
      cursor: default;
      }

  .el{
    text-align: center;
    cursor: default;
    color: #5a5a5a; /* Цвет текста */
    background: #ffc; /* Цвет фона */
    padding: 10px; /* Поля вокруг текста */
    transition: 100ms linear; /* Время изменения */
  }
    .el:hover{
      /*color: #fff; /* Цвет текста */
    background: #383534; /* Цвет фона */
    }

</style>