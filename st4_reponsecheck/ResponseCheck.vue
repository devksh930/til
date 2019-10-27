<template>
    <div>
    <div id="screen">{{message}}</div>
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
            state: 'wating',
            message: '클릭해서 시작하세요',
         }
        },
        methods: {
            onRest(){

            },

        }
    };
</script>

<style>

</style>