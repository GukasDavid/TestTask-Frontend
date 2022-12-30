<template>
  <div class="container">
    <header class="jumbotron">
      <div >
      <table height="100px" width="100%" class="alTabl">
         <tr>
          <th >Позиция</th> 
          <th>Имя пользователья</th>
          <th>Среднее число попыток до угадания</th>
        </tr>
        <tr class="el" v-for="(item, index) in sortedList" :key="index">
            
             <td>{{index+1}}</td> <td>{{item.username}}</td> <td>{{item.statistic}}</td>
            
        </tr>
        
      </table>
    </div>
    </header>
    <div class="nav-item">
      
    </div>
  </div>
</template>

<script>
import UserService from '../services/user.service';

export default {
  name: 'Home',
  data() {
    return {
      content: []
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
  methods:{
  },
  computed:{
    sortedList()  {
      return this.content.sort(function (a, b) {
        if (a.statistic==0)
          return 1;
          if (b.statistic==0)
          return -1;
        if (a.statistic>b.statistic)
          return 1;
        else 
          return -1;
      });
    },
    
  }
};

</script>
<style>
.alTabl{
  text-align: center;
}
.inline-container {
    text-align: center; /* выравнивание по центру */
    margin-bottom: 20px; /* отступ снизу */
    width: 100%;
}

.inline-item {
    display: inline-block; /* отобразить элементы в строку */
    margin-bottom: 20px;
    margin-left: 20px; /* промежутки между блоками */
    
}
.el{
    text-align: justify;
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
