<template>
<!-- 查看盘点结果 -->
<div class="assets-inventory-result-view">
    <div class="top">
        <div class="left">
            <div>
                <span class="label">盘点单号</span>
                <span class="text"><b>{{ assetsInventory.code }}</b></span>
            </div>
            <div>
                <span class="label">盘点单名称</span>
                <span class="text">{{ assetsInventory.name }}</span>
            </div>
            <div>
                <span class="label">盘点开始日期</span>
                <span class="text">{{ assetsInventory.startDate }}</span>
            </div>
            <div>
                <span class="label">盘点说明</span>
                <span class="text">{{ assetsInventory.description }}</span>
            </div>
        </div>
        
        <div class="right">
            <div>
                <span class="label">盘点单状态</span>
                <span class="text">{{ state(assetsInventory.state) }}</span>
            </div>
            <div>
                <span class="label">盘点结束日期</span>
                <span class="text">{{ assetsInventory.endDate }}</span>
            </div>
        </div>
    </div>
    <div>
        <DataListOperating :content="filterContent">
        </DataListOperating>
        <!-- <div class="enter-batch">
            <el-button @click="enterBatch()">批量录入结果</el-button>
        </div> -->
    </div>

    <!-- 数据列表 -->
    <div class="data-list">
        <table width="100%" cellspacing="0" class=".table">
            <tr>
                <td :style="{ width: display.orderNumber.width / widthSum * 100 + '%' }">
                    <!-- <input type="checkbox" v-model="isSelectAll" /> -->
                    序号
                </td>
                <td v-for="(col, field_name) of display" :style="{ width: col.width / widthSum * 100 + '%' }" :key="field_name" v-if="col.name && field_name !== 'orderNumber'">
                    {{ col.name }}
                </td>
                <!-- <td>
                    操作
                </td> -->
            </tr>
            <tr v-for="line, i in dataList">
                <td>
                    <!-- <input type="checkbox" v-model="select[i]" :disabled="line.result === '正常' || line.result === '异常'" /> -->
                    {{ i + 1 }}
                </td>
                <td v-for="(col, field_name) of display" :key="field_name" v-if="col.name && field_name !== 'orderNumber'">
                    <span v-if="line[field_name] !== undefined">
                        <router-link :to="col.to(dataList, i)" v-if="col.to" class="router-link">
                            {{ col.value ? col.value(dataList, i) : line[field_name] }}
                        </router-link>
                        <span v-else>
                            {{ col.value ? col.value(dataList, i) : line[field_name] }}
                        </span>
                    </span>
                    <span class="null" v-else>---</span>
                </td>
                <!-- <td>
                    <DataOperating :bottons="operator(line)">
                    </DataOperating>
                </td> -->
            </tr>
            <div v-if="dataList.length == 0">没有数据</div>
        </table>
    </div>

    <div class=".bottom">
        <el-button @click="cancel()">返回</el-button>
    </div>
</div>
</template>

    
<script lang="js">
import DataList from '../../components/DataList.vue'
import FormDialog from '../../components/FormDialog.vue'
import Pagination from '../../components/Pagination.vue'
import DataListOperating from '../../components/DataListOperating.vue'
import DataOperating from '../../components/DataOperating.vue'

import dataOperating from '../../data/DataOperating'
import ListStruct from "../../data/ListStruct"
import {
    EventBus
} from '../../main';
import Service from '../../Service';
import Data from "../../data/Data"
import DataListOperatingContent from '../../data/DataListOperating'
import DetailsView from '../DetailsView.vue'
import util from '../../util'
import AddAssetsToInventoryDialog from '../../components/AssetsInventory/AddAssetsToInventoryDialog.vue'
import EnterResultDialog from '../../components/AssetsInventory/EnterResultDialog.vue'
import {
    assetsClassSelect
} from '../../data/DataListOperating'

export default {
    data() {
        return {
            widthSum: 0,
            display: {
                "orderNumber": {
                    name: "序号",
                    width: 100,
                    value(dataSource, index) {
                        return Number.parseInt(index) + 1
                    }
                },
                "AssetsStorage(assetId).code": {
                    name: "资产编码",
                    width: 200
                },
                "AssetsStorage(assetId).name": {
                    name: "资产名称",
                    width: 200
                },
                "AssetsClass(AssetsStorage(assetId).assetClassId).name": {
                    name: "资产类别",
                    width: 300,
                },
                "Brand(AssetsStorage(assetId).brandId).name": {
                    name: "品牌",
                    width: 200,
                },
                "StoragePlace(AssetsStorage(assetId).storagePlaceId).name": {
                    name: "存放地点",
                    width: 200,
                },
                "AssetsStorage(assetId).storageDate": {
                    name: "入库日期",
                    width: 200,
                    value(dataSource, index) {
                        return util.DateFormat(dataSource[index]["AssetsStorage(assetId).storageDate"])
                    }
                },
                "AssetsStorage(assetId).state": {
                    name: "资产状态",
                    width: 200
                },
                "result": {
                    name: "盘点结果",
                    width: 200
                },
                "remark": {
                    name: "盘点备注",
                    width: 200
                },
                "isInventoried": {
                    name: "是否已盘点",
                    width: 200,
                    value(dataSource, index) {
                        if (dataSource[index].isInventoried) {
                            return '已盘点'
                        } else {
                            return '未盘点'
                        }
                    }
                },
            },
            dataList: [],
            dataContent: [],
            formStruct: {},
            assetsInventory: {},
            width: 800,
            select: [],
            isSelectAll: false,
            name: null,
            description: null,
            filterContent: {
                left: [
                    [{
                            type: 'select',
                            options: [{
                                    label: '已盘点',
                                    value: true
                                },
                                {
                                    label: '未盘点',
                                    value: false
                                }
                            ],
                            fieldName: 'isInventoried',
                            label: '是否已盘点',
                            width: 150
                        },
                        {
                            type: 'select',
                            options: [{
                                    label: '正常',
                                    value: '正常'
                                },
                                {
                                    label: '异常',
                                    value: '异常'
                                }
                            ],
                            fieldName: 'result',
                            label: '盘点结果',
                            width: 150
                        },
                        {
                            ...assetsClassSelect,
                            fieldName: 'AssetsStorage(assetId).assetClassId',
                            label: '资产类别',
                            width: 150
                        },
                        {
                            type: 'input',
                            fieldName: 'AssetsStorage(assetId).code,AssetsStorage(assetId).name',
                            label: '资产编码/名称',
                            width: 180
                        }
                    ]
                ],
                right: []
            },
        }
    },
    methods: {
        tip(message, type) {
            this.$message({
                message: message,
                type: type,
                duration: 2000
            })
        },
        cancel() {
            this.$router.push('/asset-administrator/home/assets-inventory')
        },
        state(state) {
            switch (state) {
                case "not_started":
                    return "未开始"
                case "in_progress":
                    return "进行中"
                case "finished":
                    return "已结束"
            }
        },
        operator(line) {
            // 录入结果
            let inputResult = {
                click: () => {
                    util.createComponent(EnterResultDialog, {
                        title: '录入盘点结果',
                        assetName: line["AssetsStorage(assetId).name"],
                        assetCode: line["AssetsStorage(assetId).code"],
                        onSave: (data, ok) => {
                            data.id = line.id
                            Service.save(
                                'assets-inventory-sheet',
                                data,
                                ok
                            )
                        },
                        onCancel: () => {},
                    })
                }
            }
            // 修改结果   
            let editResult = {
                click: () => {
                    util.createComponent(EnterResultDialog, {
                        title: '录入盘点结果',
                        assetName: line["AssetsStorage(assetId).name"],
                        assetCode: line["AssetsStorage(assetId).code"],
                        result: line.result,
                        remark: line.remark,
                        onSave: (data, ok) => {
                            data.id = line.id
                            Service.save(
                                'assets-inventory-sheet',
                                data,
                                ok
                            )
                        },
                        onCancel: () => {},
                    })
                }
            }
            if (line.result === '正常' || line.result === '异常') {
                return {
                    'edit-result': editResult
                }
            } else {
                return {
                    'input-result': inputResult
                }
            }
        },
        enterBatch() {
            let idList = []
            this.select.forEach((s, i) => {
                if (s === true) {
                    idList.push(this.dataList[i].id)
                }
            })

            if (idList.length === 0) {
                this.tip("请先选择要录入的资产，再进行操作！", "error")
                return
            }

            util.createComponent(EnterResultDialog, {
                title: '批量录入盘点结果',
                count: idList.length,
                onSave: (data, ok) => {
                    let list = []
                    idList.forEach((id, i) => {
                        list.push({
                            id: id,
                            assetsInventoryId: null,
                            assetId: null,
                            result: null,
                            isInventoried: null,
                            remark: null,
                            ...data
                        })
                    })
                    console.log(list);
                    Service.saveBatch(
                        'assets-inventory-sheet',
                        list,
                        ok
                    )
                    // idList.forEach((id, i) => {
                    //     let updateData = {
                    //         id: id,
                    //         ...data
                    //     }
                    //     Service.save(
                    //         'assets-inventory-sheet', 
                    //         updateData, 
                    //         ok
                    //     )
                    // })
                },
                onCancel: () => {},
            })

        },
        updateDataList(conditions) {
            conditions = conditions ? conditions : []
            Service.getById(
                'assets-inventory',
                this.$route.params.id,
                (data) => {
                    this.assetsInventory = data

                    Service.joinList(
                        'assets-inventory-sheet',
                        Object.keys(this.display),
                        [{
                                operator: 'eq',
                                values: ['assetsInventoryId', this.assetsInventory.id]
                            },
                            ...conditions
                        ],
                        (data) => {
                            this.dataList = data

                            this.select = new Array(this.dataList.length)
                            for (let index = 0; index < this.select.length; index++) {
                                if (!(this.dataList[index].result === '正常' || this.dataList[index].result === '异常'))
                                    this.select[index] = false
                                else
                                    this.select[index] = null
                            }
                        },
                        (okay, message) => {

                        }
                    )
                },
                (okay, message) => {

                }
            )
        },
    },
    watch: {
        select: {
            deep: true,
            immediate: true,
            handler: function (val, oldVal) {
                if (this.select.every(s => s === true || s === null)) { // 如果已全选
                    this.isSelectAll = true
                } else {
                    this.isSelectAll = false
                }
            }
        },
        isSelectAll(val, oldVal) {
            if (val) {
                this.select.forEach((s, i) => {
                    if (!(this.dataList[i].result === '正常' || this.dataList[i].result === '异常'))
                        this.select[i] = true
                    else
                        this.select[i] = null
                })
            } else if (!val && oldVal && this.select.every(s => s === true || s === null)) {
                this.select.forEach((s, i) => {
                    if (!(this.dataList[i].result === '正常' || this.dataList[i].result === '异常'))
                        this.select[i] = false
                    else
                        this.select[i] = null
                })
            }
        }
    },
    mounted() {
        this.updateDataList([])
        EventBus.$on("updateDataList", this.updateDataList)
    },
    computed: {},
    props: [],
    components: {
        DataList,
        FormDialog,
        Pagination,
        DataListOperating,
        DetailsView,
        DataOperating
    },
};
</script>

    
<style lang="less" scope>
.assets-inventory-result-view {
    >div>.data-list-operating {
        float: left;
        overflow: hidden;
    }

    >div>.enter-batch {
        float: left;
        overflow: hidden;
        padding: 20px;
    }

    >.top {
        background-color: #fff1e3;
        padding: 10px 50px;
        >.left>div>.label, >.right>div>.label {
            color: rgb(95, 95, 95);
        }
    }
}
</style>
