<template>
    <div>
        <div>{{turn}}님의 턴입니다.</div>
    <table-component :table-data="tableData" />
        <div v-if="winner">{{winner}}님의 승리</div>
    </div>
</template>


<script>
    import TableComponent from "./TableComponent";
    import EventBus from "./EventBus";
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
                winner : '',
            };
        },
        methods: {
            onChangeDate(){
                //this.tableData[1][0]= 'X'; 작동하지 않
               // Vue.set(this.tableDat음a[1], 0, 'X');     import Vue from 'vue';임포트 해서 사용하거
               this.$set(this.tableData[1],0,'X'); //vue set과 동일
            },
            onClickTd(rowIndex,cellIndex){
                console.log(rowIndex,cellIndex);
                this.$set(this.tableData[rowIndex], cellIndex, this.turn);

                let win = false;
                //대각선 3목 됫는지 체크
                if (this.tableData[rowIndex][0] === this.turn && this.tableData[rowIndex][1] === this.turn && this.tableData[rowIndex][2] === this.turn) {
                    win = true;
                }
                if (this.tableData[0][cellIndex] === this.turn && this.tableData[1][cellIndex] === this.turn && this.tableData[2][cellIndex] === this.turn) {
                    win = true;
                }
                if (this.tableData[0][0] === this.turn && this.tableData[1][1] === this.turn && this.tableData[2][2] === this.turn) {
                    win = true;
                }
                if (this.tableData[0][2] === this.turn && this.tableData[1][1] === this.turn && this.tableData[2][0] === this.turn) {
                    win = true;
                }
                if (win) { //이긴경우 : 3목 완성
                    this.winner = this.turn;
                    this.turn = 'O';
                    this.tableData = [['', '', ''], ['', '', ''], ['', '', '']];
                }
                else { // 무승부
                    let all = true; //all이 true면 draw
                    this.tableData.forEach((row)=>{ // draw check
                        row.forEach((cell)=>{
                            if(!cell){
                                all = false;
                            }
                        });
                    });
                    if(all){ //무승부
                        this.winner = '';
                        this.turn = 'O';
                        this.tableData = [['', '', ''], ['', '', ''], ['', '', '']];
                    }
                    this.turn = this.turn === 'O' ? 'X' : 'O';

                }
            }
        },
        created() {
            EventBus.$on('clickTd',this.onClickTd);
        }
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