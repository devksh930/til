<template>
    <td @click="onClickTd">{{cellData}}</td>
</template>

<script>
    import {CLICK_CELL, SET_WINNER, RESET_GAME, CHANGE_TURN, NO_WINNER} from "./store";

    export default {
        props:{
            rowIndex: Number,
            cellIndex: Number,
        },
        computed:{
            cellData(){
                return this.$store.state.tableData[this.rowIndex][this.cellIndex];
            },
          tableData(){
            return this.$store.state.tableData;
          },
          turn(){
              return this.$store.state.turn;
          },
        },
        methods:{
            onClickTd(){
                if(this.cellData) return;

                this.$store.commit(CLICK_CELL,{row: this.rowIndex, cell:this.cellIndex});

                let win = false;
                //대각선 3목 됫는지 체크
                if (this.tableData[this.rowIndex][0] === this.turn && this.tableData[this.rowIndex][1] === this.turn && this.tableData[this.rowIndex][2] === this.turn) {
                    win = true;
                }
                if (this.tableData[0][this.cellIndex] === this.turn && this.tableData[1][this.cellIndex] === this.turn && this.tableData[2][this.cellIndex] === this.turn) {
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
                    }
                        this.$store.commit(CHANGE_TURN);
                }
            }
        }
    };
</script>

<style scoped>

</style>
