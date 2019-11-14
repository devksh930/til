<template>
    <td @click="onClickTd">{{cellData}}</td>
</template>

<script>
    export default {
        props:{
            cellData: String,
            rowIndex: Number,
            cellIndex: Number,
        },
        methods:{
            onClickTd(){
                const rootData = this.$root.$data;
                console.log(rootData);
                this.$set(rootData.tableData[this.rowIndex], this.cellIndex, rootData.turn);
                this.$root.$data.turn = this.$root.$data.turn === 'O' ? 'X' : 'O' ;
            }
        }
    };
</script>

<style scoped>

</style>
