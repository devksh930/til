<template>
    <div>
    <table-component :table-data="tableData" />
        <div>{{turn}}님의 턴 입니다.</div>
    </div>
</template>


<script>
    import TableComponent from "./TableComponent";
    export default {
        components:{
            TableComponent,

        },
        data() {
            return{
            tableData:[
                ['','',''],
                ['','',''],
                ['','',''],
            ], turn: 'O',
            };
        },
        methods: {
            onChangeDate(){
                //this.tableData[1][0]= 'X'; 작동하지 않
               // Vue.set(this.tableDat음a[1], 0, 'X');     import Vue from 'vue';임포트 해서 사용하거
                this.$set(this.tableData[1],0,'X'); //vue set과 동일
            }
        },
    };
</script>

<style>
table{
    border-collapse: collapse;
}
    td{
        border: 1px solid black;
        width: 40px;
        height: 40px;
        text-align: center;
    }
</style>