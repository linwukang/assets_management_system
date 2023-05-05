<template>
<!-- 人员管理 -->
<div class="personnel-view">
    <el-button >添加人员</el-button>
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
import { ref } from '@vue/reactivity';
import Vue from 'vue';
export default {
    data() {
        return {
            dataStruct: {
                key: "id",
                "orderNumber": {
                    name: "序号",
                    width: '130px',
                    value(line) {
                        line.orderNumber = 0
                    }
                },
                "name": {
                    name: "姓名",
                    width: '300px'
                },
                "id": {
                    name: "工号",
                    width: '200px'
                },
                "gender": {
                    name: "性别",
                    width: '200px'
                },
                "birthDate": {
                    name: "出生日期",
                    width: '300px',
                    value(line) {
                        let bd = new Date(line.birthDate)
                        line.birthDate = 
                            bd.getFullYear() + "-" +
                            bd.getMonth() + "-" +
                            bd.getDay()
                    }
                },
                "departmentId": {
                    name: "所属部门",
                    width: '300px',
                    value(line) {
                        Vue.prototype.$http
                            .get(`/department/id/${line.departmentId}`)
                            .then((result) => {
                                line.departmentId = result.data.name
                            }).catch((err) => {
                                console.log(err)
                            })
                    }
                },
                "category": {
                    name: "人员类别",
                    width: '400px',
                    value(line) {
                        
                    }
                },
            },
            dataSource: [],
            operating: (line) => {
                return [{
                    click: function () {},
                    type: "edit"
                },{
                    click: function () {},
                    type: "delete"
                }]
            },
            vertical: false,
            width: "1200px"
        }
    },
    mounted() {
        this.$http
            .get('/personnel/list')
            .then((result) => {
                console.log(result)
                this.dataSource = result.data

                for (var line in this.dataSource)
                {
                    for(var i in this.dataStruct) {
                        if (this.dataStruct[i].value) {
                            this.dataStruct[i].value(this.dataSource[line])
                        }
                    }
                }
    
            }).catch((err) => {
                console.log(err)
            })
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
