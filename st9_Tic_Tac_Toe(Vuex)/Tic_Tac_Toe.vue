<template>
    <div>
        <div>{{turn}}님의 턴입니다.</div>
    <table-componentc />
        <div v-if="winner">{{winner}}님의 승리</div>
    </div>
</template>


<script>
    import store from "./store";
    import TableComponent from "./TableComponent";

    export default {
        store,
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
                winner : '',
            };
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