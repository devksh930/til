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
                if(this.cellData) return;
                const rootData = this.$root.$data;
                console.log(rootData);
                this.$set(rootData.tableData[this.rowIndex], this.cellIndex, rootData.turn);

                let win = false;
                //대각선 3목 됫는지 체크
                if (rootData.tableData[this.rowIndex][0] === rootData.turn && rootData.tableData[this.rowIndex][1] === rootData.turn && rootData.tableData[this.rowIndex][2] === rootData.turn) {
                    win = true;
                }
                if (rootData.tableData[0][this.cellIndex] === rootData.turn && rootData.tableData[1][this.cellIndex] === rootData.turn && rootData.tableData[2][this.cellIndex] === rootData.turn) {
                    win = true;
                }
                if (rootData.tableData[0][0] === rootData.turn && rootData.tableData[1][1] === rootData.turn && rootData.tableData[2][2] === rootData.turn) {
                    win = true;
                }
                if (rootData.tableData[0][2] === rootData.turn && rootData.tableData[1][1] === rootData.turn && rootData.tableData[2][0] === rootData.turn) {
                    win = true;
                }
                if (win) { //이긴경우 : 3목 완성
                    rootData.winner = rootData.turn;
                    rootData.turn = 'O';
                    rootData.tableData = [['', '', ''], ['', '', ''], ['', '', '']];
                }
                else { // 무승부
                    let all = true; //all이 true면 draw
                    rootData.tableData.forEach((row)=>{ // draw check
                        row.forEach((cell)=>{
                            if(!cell){
                                all = false;
                            }
                        });
                    });
                    if(all){ //무승부
                        rootData.winner = '';
                        rootData.turn = 'O';
                        rootData.tableData = [['', '', ''], ['', '', ''], ['', '', '']];
                    }
                    rootData.turn = rootData.turn === 'O' ? 'X' : 'O';

                }
            }


        }
    };
</script>

<style scoped>

</style>
