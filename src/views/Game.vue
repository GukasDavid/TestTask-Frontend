<template>
  <div  :key="componentKey">
    <div style="border:2px solid #555; border-radius:5px; width: 100%; text-align:justify;  margin:20px; " > Интсрукция:
Угодайте 4-х значное число. Цифры загаданного числа не
повторяются. Укажите свой вариант числа и компьютер сообщит
сколько цифр точно угадано (бык) и сколько цифр угадано без учета
позиции (корова).</div>
    
      <h3 class="inline-container">{{content}}</h3>
      <h3 class="inline-container">
        <my-listBox class="inline-item" v-on:change_item="changeItem($event, 1)"></my-listBox>
        <my-listBox class="inline-item" v-on:change_item="changeItem($event, 2)"></my-listBox>
        <my-listBox class="inline-item" v-on:change_item="changeItem($event, 3)"></my-listBox>
        <my-listBox class="inline-item" v-on:change_item="changeItem($event, 4)"></my-listBox>
      </h3>
      <div class="inline-container">
          <button v-if="content=='Вы угадали число!'" class="button28" @click="updateDigit">
            загадать другое число
          </button>
          <button v-else class="button28" @click="equalsResult">
            мне повезёт
          </button>
          <br><br>
          <br>
          История попыток ({{ countAttemp }})
          <div class="el" v-for="item in ListAttemp">
          {{item}}
        </div>
      </div>
    
  </div>
</template>

<script>
import UserService from '../services/user.service';
export default {
  name: 'User',
  data() {
    return {
      componentKey:0,
      countAttemp:0,
      content: '',
      d1:0,
      d2:0,
      d3:0,
      d4:0,
      ListAttemp:[]
    };
  },
  mounted() {
    UserService.getNewDigit().then(
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
        changeItem(digit, blok) {
          switch(blok) {
            case 1:
              this.d1=digit;
              break
            case 2:
              this.d2=digit;
              break
            case 3:
              this.d3=digit;
              break
             case 4:
              this.d4=digit;
          }
        },
        
        equalsResult()
        {
          UserService.equalsResult(this.d1+''+this.d2+''+this.d3+''+this.d4).then(
          response => {
            this.content = response.data;
            this.ListAttemp.unshift(this.d1+''+this.d2+''+this.d3+''+this.d4+"  " + response.data)
          },
          error => {
            this.content =
              (error.response && error.response.data && error.response.data.message) ||
              error.message ||
              error.toString();
          }
          );
          this.countAttemp++;
        },

        updateDigit()
        {
          UserService.getNewDigit().then(
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
        this.ListAttemp=[];
        this.countAttemp=0;
      this.d1=0;
      this.d2=0;
      this.d3=0;
      this.d4=0;
      this.componentKey+=1;

        }
    },
    computed:{

    }
};
</script>
<style>
.inline-container {
    text-align: center; /* выравнивание по центру */
    /*margin-bottom: 20px; /* отступ снизу */
}

.inline-item {
    display: inline-block; /* отобразить элементы в строку */
    margin-bottom: 20px;
    margin-left: 20px; /* промежутки между блоками */
}

.button28 {
  text-align: center; /* выравнивание по центру */
  position: relative;
  display: inline-block;
  font-size: 90%;
  font-weight: 700;
  color: rgb(209,209,217);
  text-decoration: none;
  text-shadow: 0 -1px 2px rgba(0,0,0,.2);
  padding: .5em 1em;
  outline: none;
  border-radius: 3px;
  background: linear-gradient(rgb(110,112,120), rgb(81,81,86)) rgb(110,112,120);
  box-shadow:
   0 1px rgba(255,255,255,.2) inset,
   0 3px 5px rgba(0,1,6,.5),
   0 0 1px 1px rgba(0,1,6,.2);
  transition: .2s ease-in-out;
  
}
.button28:hover:not(:active) {
  background: linear-gradient(rgb(126,126,134), rgb(70,71,76)) rgb(126,126,134);
}
.button28:active {
  top: 1px;
  background: linear-gradient(rgb(76,77,82), rgb(56,57,62)) rgb(76,77,82);
  box-shadow:
   0 0 1px rgba(0,0,0,.5) inset,
   0 2px 3px rgba(0,0,0,.5) inset,
   0 1px 1px rgba(255,255,255,.1);
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
