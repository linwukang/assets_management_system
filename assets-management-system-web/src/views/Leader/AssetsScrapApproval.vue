<template>
<!-- 资产申购审批 -->
<div class="assets-scrap-approval">
    <div>
        <DataListOperating :content="filterContent">
        </DataListOperating>
    </div>

    <!-- 数据列表 -->
    <div class="data-list">
        <table width="100%" cellspacing="0" class=".table">
            <tr>
                <td :style="{ width: display.orderNumber.width / widthSum * 100 + '%' }">
                    序号
                </td>
                <td v-for="(col, field_name) of display" :style="{ width: col.width / widthSum * 100 + '%' }" :key="field_name" v-if="col.name && field_name !== 'orderNumber'">
                    {{ col.name }}
                </td>
                <td>
                    操作
                </td>
            </tr>
            <tr v-for="line, i in dataList">
                <td>
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
                <td>
                    <DataOperating :bottons="operator(line)">
                    </DataOperating>
                </td>
            </tr>
            <div v-if="dataList.length == 0">没有数据</div>
        </table>
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
import NotApprovedReasonDialog from '../../components/Leader/NotApprovedReasonDialog.vue'
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
                "code": {
                    to(dataSource, index) {
                        return `assets-scrap/details/${dataSource[index].id}`
                    },
                    name: "报废单号",
                    width: 300
                },
                "AssetsStorage(assetId).code": {
                    name: "资产编码",
                    width: 200
                },
                "AssetsStorage(assetId).name": {
                    name: "资产名称",
                    width: 200
                },
                "scrapDate": {
                    name: "报废日期",
                    width: 250,
                    value(dataSource, index) {
                        return util.DateFormat(dataSource[index].scrapDate)
                    }
                },
                "ScrapMode(scrapModeId).name": {
                    name: "报废方式",
                    width: 200
                },
                "AssetsStorage(assetId).state": {
                    name: "资产状态",
                    width: 200
                },
                "applicationStatus": {
                    name: "申请状态",
                    width: 150,
                    value(dataSource, index) {
                        if (dataSource[index].applicationStatus === 'uncommitted')
                            return '未提交'
                        if (dataSource[index].applicationStatus === 'committed')
                            return '未批阅'
                        if (dataSource[index].applicationStatus === 'not_approved')
                            return '审批不通过'
                        if (dataSource[index].applicationStatus === 'approved')
                            return '审批通过'
                        else
                            return dataSource[index].applicationStatus

                    }
                },
            },
            dataList: [],
            dataContent: [],
            formStruct: {},
            width: 800,
            currentPage: 1,
            dataCount: 0,
            filterContent: {
                left: [[
                    { type: 'input', fieldName: 'AssetsStorage(assetId).name', label: '资产名称', width: 150 },
                    { type: 'input', fieldName: 'Personnel(proposerId).name,Personnel(proposerId).code', label: '申请人姓名/工号', width: 150 },
                    {
                        type: 'select',
                        options: [],
                        from: {
                            table: "scrap-mode",
                            label: "name",
                            value: "id",
                        },
                        fieldName: 'scrapModeId',
                        label: '报废方式',
                        width: 150
                    },
                    {
                        type: 'date',
                        fieldName: 'purchaseRequisitionDate',
                        label: '报废日期',
                        startLabel: '开始日期',
                        endLabel: '结束日期',
                        joinText: '至',
                        width: 150
                    }
                ]],
                right: []
            },
        }
    },
    methods: {
        tip(message, type, title) {
            this.$message({
                title,
                message: message,
                type: type,
                duration: 2000
            })
        },
        operator(line) {
            let approve = (approve) => {
                switch (line.applicationStatus) {
                    case 'not_approved':
                        this.tip('状态错误，无法操作。', 'error', '审批不通过')
                        break;
                    case 'uncommitted':
                        this.tip('状态错误，无法操作。', 'error', '未提交')
                        break;
                    case 'approved':
                        this.tip('状态错误，无法操作。', 'error', '审批通过')
                        break;
                    case 'committed':
                        if (approve) {
                            Service.updateById(
                                'assets-scrap',
                                line.id,
                                'applicationStatus',
                                'approved',
                                (okay, message) => {
                                    if (okay) {
                                        this.updateDataList([])
                                    } else {
                                        this.tip(message, 'error')
                                    }
                                }
                            )
                        }
                        else {
                            util.createComponent(NotApprovedReasonDialog, {
                                id: line.id,
                                tableName: 'assets-scrap'
                            })
                        }
                        break;
                    default:
                        break;
                }
            }


            return {
                'approve': {
                    popconfirm: {
                        confirm: "通过",
                        cancel: "取消",
                        title: "审批通过",
                    },
                    click: () => {
                        approve(true)
                    }
                },
                'fail-to-approve': {
                    click: () => {
                        approve(false)
                    }
                }
            }
        },
        updateDataList(conditions) {
            conditions = conditions ? conditions : []
            Service.joinPage(
                'assets-scrap', 
                Object.keys(this.display),
                conditions,
                this.currentPage,
                15,
                (data) => { 
                    this.dataList = data 
                }, 
                (okay, message) => { 
                    EventBus.$emit("loading", false)
                    okay ? null : this.tip(message, "error") 
                }
            )
            Service.conditionCount(
                'assets-scrap',
                conditions,
                (data) => { this.dataCount = data },
                (okay, message) => { okay ? null : this.tip(message, "error") }
            )
        },
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

<style lang="less">
.assets-scrap-approval {
    >div>.data-list-operating {
        float: left;
        overflow: hidden;
    }

    >div>.enter-batch {
        float: left;
        overflow: hidden;
        padding: 20px;
    }
}
</style>
