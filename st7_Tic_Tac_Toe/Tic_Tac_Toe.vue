<template>
<table-component :table-data="tableData"></table-component>
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
            ],
            };

        },
        computed: {

        },
        methods: {

        },
    };
</script>

<style scoped>

</style>