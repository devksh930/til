<template>
    <div>
    <div id="screen" :class="state" @click="onClickScreen">{{message}}</div> <!-- v bind:class alias :class -->
    <div>
        <div>평균 시간 : {{}}</div>
        <button @click="onReset">리셋 버튼</button>
    </div>
    </div>
</template>

<script>
    export default {
        data() {
        return{
            result: [],
            state: 'waiting',
            message: '클릭해서 시작하세요',
         }
        },
        methods: {
            onReset(){

            },
            onClickScreen(){
            if(this.state === 'waiting') {
                this.state = 'ready';
            }else if(this.state === 'ready'){
                this.state = 'now';
            }else if(this.state === 'now'){
                this.state = 'waiting';
            }
            },
        }
    };
</script>

<style scoped>
    /*<!--이파일에서만 로컬로 css쓰고싶다 scoped 추가 -->*/
 #screen{
     width: 300px;
     height: 200px;
     text-align: center;
     user-select: none;
 }
    #screen.waiting{
        background-color: aqua;
    }
    #screen.ready{
        background-color: red;
        color: white;
    }
    #screen.now{
        background-color: greenyellow;
    }
</style>