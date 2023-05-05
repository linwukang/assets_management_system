<template>
<!-- 部门管理 -->
<div class="department-management-view">
    <el-button >新增</el-button>
    <DataList class="data-list" 
        :dataStruct="dataStruct" 
        :dataSource="dataSource" 
        :operating="operating" 
        :vertical="vertical" 
        :width="width"></DataList>
</div>
</template>

<script>
import DataList from '../../components/DataList.vue'
export default {
    data() {
        return {
            dataStruct: {
                "orderNumber": {
                    name: "序号",
                    width: '130px'
                },
                "id": {
                    name: "类别编码",
                    width: '300px'
                },
                "name": {
                    name: "类别名称",
                    width: '200px'
                },
                "enabled": {
                    name: "状态",
                    width: '400px'
                },
                "createTime": {
                    name: "创建日期",
                    width: '300px'
                },
            },
            dataSource: [],
            operating: (line) => {
                return [{
                    click: function () {},
                    type: "edit"
                },{
                    click: function () {},
                    type: "disable"
                }]
            },
            vertical: {},
            width: "1200px"
        }
    },
    mounted() {
        this.$http
            .get('/department/list')
            .then((result) => {
                console.log(result);
                this.dataSource = result.data
            }).catch((err) => {
                console.log(err);
            });

    },
    computed: {},
    props: [],
    components: {
        DataList
    },
};
</script>

<style lang="less">
</style>
