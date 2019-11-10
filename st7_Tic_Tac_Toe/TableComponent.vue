<template>
    <table>
    <tr-component v-for="(rowData,index)in tableData" :row-data ="rowData" :row-index="index"></tr-component>
    </table>
</template>

<script>
    import TrComponent from './TrComponent'

    export default {
        props: {
            tableData: Array,
        },
        component: {
            TrComponent,
        }
    }
</script>

<style scoped>

</style>