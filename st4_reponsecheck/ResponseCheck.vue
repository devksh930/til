<template>
    <div>
    <div id="screen" :class="state" @click="onClickScreen">{{message}}</div> <!-- v bind:class alias :class -->
    <div>
        <div>평균 시간 : {{result.reduce((a, c)=>a+c,0)/result.length || 0}}ms</div>
        <button @click="onReset">리셋 버튼</button>
    </div>
    </div>
</template>

<script>
    let startTime = 0;
    let endTime = 0;
    let timeOut = null;
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
                this.result = [];
            },
            onClickScreen(){
            if(this.state === 'waiting') {
                this.state = 'ready';
                this.message = '초록색이 되면 클릭하세요.';
                timeOut = setTimeout(() => {
                    this.state = 'now';
                    this.message = '지금 클릭!';
                    startTime = new Date(); //data에 안넣는 이유는 화면과 관련이 없다.
                }, Math.floor(Math.random()*1000)+2000); //2-3초
            }else if(this.state === 'ready'){
                clearTimeout(timeOut);
                this.state = 'waiting';
                this.message = '너무 성급하시군요! 초록색이 된후 클릭하세요';
            }else if(this.state === 'now'){
                endTime = new Date();
                this.state = 'waiting';
                this.message = '클릭해서 시작하세요. ';
                this.result.push(endTime - startTime);
            }
            }
        },
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