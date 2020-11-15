<template>
    <div>
        <div>{{turn}}님의 턴입니다.</div>
        <table>
            <tr v-for="(rowData,rowIndex) in tableData">
                <td @click="onClickTd(rowIndex,cellIndex)" v-for="(cellData,cellIndex) in rowIndex">{{cellData}}}</td>
            </tr>
        </table>
        <div v-if="winner">{{winner}}님의 승리</div>
    </div>
</template>


<script>
    import {mapState} from 'vuex';
    import store, {CHANGE_TURN, CLICK_CELL, NO_WINNER, RESET_GAME, SET_WINNER} from "./store";
    import TableComponent from "./TableComponent";

    export default {
        store,
        components:{
            TableComponent,
        },
       computed:{
            ...mapState(["winner","turn","tableData"]),

            // winner(){
            //     return this.$store.state.winner;
            // },
            // turn(){
            //     return this.$store.state.turn;
            },
        methods:{

            onClickTd(rowIndex,cellIndex){
                if(this.cellData) return;

                this.$store.commit(CLICK_CELL,{row: rowIndex, cell: cellIndex});

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
                    this.$store.commit(SET_WINNER, this.turn);
                    this.$store.commit(RESET_GAME);
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
                        this.$store.commit(NO_WINNER);
                        this.$store.commit(RESET_GAME);
                    } else {
                        this.$store.commit(CHANGE_TURN);
                    }
                }
            }
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