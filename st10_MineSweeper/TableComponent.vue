<template>
    <table>
        <tr v-for="(rowData,rowIndex) in tableData" :key="rowIndex">
            <td v-for="(cellData,cellIndex) in rowData" :key="cellIndex">{{cellData}}</td>
        </tr>
    </table>
</template>

<script>
    import {mapState} from 'vuex';

    export default {
        computed: {
            ...mapState(['tableData']),
        }
    };
</script>