<template>
<el-dialog 
    class="add-assets-to-inventory-dialog" 
    :visible.sync="show" 
    :close-on-click-modal="false" 
    :before-close="close">
    <div slot="title" class="title">新增资产到盘点单</div>

    <!-- 数据列表筛选操作 -->
    <div class="data-list-operating">
        <!-- 查询功能 -->
        <el-form :inline="true" :class="'left'">
            <span v-for="left, index in filterContent.left" :key="index">
                <span v-for="item, index in left" :key="index">
                <!-- 文本查询 -->
                    <el-form-item :inline="true" v-if="item.type === 'input'">
                        <el-input 
                            v-model="dataContent[item.fieldName]"
                            :placeholder="item.label"
                            :style="{'width': item.width + 'px'}">
                        </el-input>
                    </el-form-item>
                    <!-- 下拉列表 -->
                    <el-form-item v-else-if="item.type === 'select'">
                        <el-select 
                            v-model="dataContent[item.fieldName]"
                            :placeholder="item.label"
                            :style="{'width': item.width + 'px'}"
                            clearable
                            filterable>
                            <el-option
                                v-for="option in formStruct[item.fieldName].options"
                                :key="option.value"
                                :label="option.label"
                                :value="option.value"
                                :placeholder="option.label">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </span>
                <br v-if="index != filterContent.left.length - 1" />
            </span>
            <el-button 
                v-if="filterContent.left.length != 0 && filterContent.left[0].length != 0"
                @click="query"
                type="primary">
                查询
            </el-button>
            <el-button 
                v-if="filterContent.left.length != 0 && filterContent.left[0].length != 0"
                @click="addToInventory()"
                type="primary">
                批量加入盘点单
            </el-button>
        </el-form>
    </div>

    <!-- 数据列表 -->
    <div class="data-list">
        <table width="100%" cellspacing="0" class=".table">
            <tr>
                <td :style="{ width: display.orderNumber.width / widthSum * 100 + '%' }">
                    <input type="checkbox" v-model="isSelectAll" />
                    全选
                </td>
                <td 
                    v-for="(col, field_name) of display" 
                    :style="{ width: col.width / widthSum * 100 + '%' }" 
                    :key="field_name"
                    v-if="col.name && field_name !== 'orderNumber'">
                    {{ col.name }}
                </td>
            </tr>
            <tr v-for="line, i in dataList" >
                <td>
                    <input type="checkbox" v-model="select[i]" />
                    {{ i + 1 }}
                </td>
                <td 
                    v-for="(col, field_name) of display" 
                    :key="field_name"
                    v-if="col.name && field_name !== 'orderNumber'">
                    <span v-if="line[field_name]">
                        <router-link :to="col.to(dataList, i)" v-if="col.to" class="router-link">
                            {{ col.value ? col.value(dataList, i) : line[field_name] }}
                        </router-link>
                        <span v-else>
                            {{ col.value ? col.value(dataList, i) : line[field_name] }}
                        </span>
                    </span>
                    <span class="null" v-else>---</span>
                </td>
            </tr>
            <div v-if="dataList.length == 0">没有数据</div>
        </table>
    </div>
    <div slot="footer" class="footer">
        <div class="buttons">
            <el-button class="close" @click="close()">关闭</el-button>
        </div>
    </div>

</el-dialog>
</template>

<script>
import {
    EventBus
} from '../../main'
import DataList from '../DataList.vue'
import Data from '../../data/Data'
import Service from '../../Service'
import util from '../../util'
import { 
    assetsClassSelect,
    storagePlaceSelect 
} from '../../data/DataListOperating'

export default {
    data() {
        return {
            show: true,
            widthSum: 0,
            display: {
                'orderNumber': {
                    name: '全选',
                    width: 150
                }, 
                'code': {
                    name: '资产编码',
                    width: 150
                }, 
                'name': {
                    name: '资产名称',
                    width: 250
                }, 
                'AssetsClass(assetClassId).name': {
                    name: '资产类别',
                    width: 200
                },
                'StoragePlace(storagePlaceId).name': {
                    name: '存储位置',
                    width: 170
                }, 
                'storageDate': {
                    name: '入库时间',
                    width: 170,
                    value(dataSource, index) {
                        return util.DateFormat(dataSource[index].storageDate)
                    }
                }
            },
            dataList: [],
            dataContent: [],
            formStruct: {},
            width: 800,
            select: [],
            isSelectAll: false,
            filterContent: {
                left: [[
                    { 
                        type: 'input', 
                        label: '资产名称/编码',
                        fieldName: 'name, code'
                    },
                    {
                        ...assetsClassSelect,
                        fieldName: 'assetClassId',
                        label: '资产类别',
                        width: 150
                    },
                    {
                        ...storagePlaceSelect,
                        fieldName: 'storagePlaceId',
                        label: '存放地点',
                        width: 150
                    },
                ]],
                right: []
            }
        };
    },
    methods: {
        close() {
            this.show = false
            this.$destroy(true);
            this.$el.parentNode.removeChild(this.$el);
        },
        tip(message, type) {
            this.$message({
                title: '',
                message: message,
                type: type,
                duration: 2000
            })
        },
        updateDataList(conditions) {
            // EventBus.$emit("loading", true)
            conditions = conditions ? conditions : []
            if (this.excludeAssetsId !== undefined) {
                this.excludeAssetsId.forEach(id => {
                    conditions.push({
                        operator: 'ne',
                        values: [
                            'id', 
                            id
                        ]
                    })
                })
            }

            Service.joinList(
                Data['assets-storage'].url,
                Object.keys(this.display),
                conditions,
                (data) => {
                    this.dataList = data
                    this.select = new Array(data.length)
                    for (let index = 0; index < this.select.length; index++) {
                        this.select[index] = false
                        
                    }
                },
                (okay, message) => {
                    // EventBus.$emit("loading", false)
                    okay ? null : this.tip(message, "error")
                }
            )
        },
        query() {
            console.log(this.getConditions());
            this.updateDataList(this.getConditions())
        },
        addToInventory() {
            // let assetList = []
            // for (let index = 0; index < this.select.length; index++) {
            //     if (this.select[index]) {
            //         assetList.push({
            //             assetsInventoryId: this.assetsToinventoryId,
            //             assetId: this.dataList[index].id
            //         })
            //     }
            // }
            // // console.log(assetIdList)
            // Service.createMultiple(
            //     'assets-inventory-sheet',
            //     assetList,
            //     (okay, message) => {
            //         if (okay) {
            //             this.tip('添加成功', 'success')
            //             this.close()
            //         }
            //         else {
            //             this.tip(message, 'error')
            //         }
            //     }
            // )
            let assetIdList = []
            for (let index = 0; index < this.select.length; index++) {
                if (this.select[index]) {
                    assetIdList.push(this.dataList[index].id)
                }
            }
            this.getAssetsId(assetIdList)
            this.close()
        },
        selectAll() {
            if (this.select.every(s => s)) {    // 如果已全选
                console.log("=======");
                this.select.forEach((s, i) => {
                    this.select[i] = false
                })
            }
            else {
                console.log("++++++");
                this.select.forEach((s, i) => {
                    this.select[i] = true
                })
            }
        },
        getConditions() {
            let conditions = []
            for (var i in this.filterContent.left) {
                for (var j in this.filterContent.left[i]) {
                    let item = this.filterContent.left[i][j]
                    if (this.dataContent[item.fieldName] !== null 
                        && this.dataContent[item.fieldName] !== undefined) {
                        switch (item.type) {
                            case "input":
                                this.addCondition(
                                    conditions, 
                                    "like", 
                                    item.fieldName, 
                                    this.dataContent[item.fieldName]
                                )

                                break
                            case "select":
                                this.addCondition(
                                    conditions, 
                                    "eq", 
                                    item.fieldName, 
                                    this.dataContent[item.fieldName]
                                )
                                break
                            case "date":
                                if (this.dataContent[item.fieldName].start && this.dataContent[item.fieldName].end) {
                                    this.addCondition(
                                        conditions, 
                                        "between", 
                                        item.fieldName, 
                                        this.dataContent[item.fieldName].start,
                                        this.dataContent[item.fieldName].end
                                    )
                                }
                                else if (!this.dataContent[item.fieldName].start && this.dataContent[item.fieldName].end) {
                                    this.addCondition(
                                        conditions, 
                                        "le", 
                                        item.fieldName, 
                                        this.dataContent[item.fieldName].end
                                    )
                                }
                                else if (this.dataContent[item.fieldName].start && !this.dataContent[item.fieldName].end) {
                                    this.addCondition(
                                        conditions, 
                                        "ge", 
                                        item.fieldName, 
                                        this.dataContent[item.fieldName].start
                                    )
                                }
                                break
                            default:
                                break
                        }
                    }
                }
            }
            return conditions
        },
        getColumnNames(fieldNames) {
            if (!fieldNames) return []
            else return fieldNames.split(",").map(col => col.trim())
        },
        getCondition(operator, fieldName, value1, value2, value3) {
            let fieldNames = this.getColumnNames(fieldName)
            let condition = {}
            switch (operator) {
                case "like":
                    condition = {
                        operator,
                        values: [
                            fieldName, 
                            "%" + value1 + "%"
                        ]
                    }
                    break
                case "eq":
                case "ne":
                case "lt":
                case "le":
                case "gt":
                case "ge":
                    condition = {
                        operator,
                        values: [
                            fieldName, 
                            value1
                        ]
                    }
                    break
                case "between":
                    condition = {
                        operator,
                        values: [
                            fieldName, 
                            value1,
                            value2
                        ]
                    }
                    break
            }
            
            return condition
        },
        getConditionOr(operator, fieldNames, value1, value2, value3) {
            if (fieldNames.length == 1) {
                return this.getCondition(operator, fieldNames[0], value1, value2, value3)
            }
            else {
                return {
                    left: this.getCondition(operator, fieldNames[0], value1, value2, value3),
                    right: this.getConditionOr(operator, fieldNames.slice(1), value1, value2, value3)
                }
            }
        },
        addCondition(conditions, operator, fieldName, value1, value2, value3) {
            let fieldNames = this.getColumnNames(fieldName)
            if (fieldNames.length == 0) {
                return
            }
            else if (fieldNames.length == 1) {
                conditions.push(this.getCondition(operator, fieldName, value1, value2, value3))
            }
            else if (fieldNames.length > 1) {
                conditions.push(this.getConditionOr(operator, fieldNames, value1, value2, value3))
            }
        }
    },
    watch: {
        select: {
            deep: true,
            immediate: true,
            handler: function (val, oldVal) {
                if (this.select.every(s => s)) {    // 如果已全选
                    this.isSelectAll = true
                }
                else {
                    this.isSelectAll = false
                }
            }
        },
        isSelectAll(val, oldVal) {
            if (val) {
                this.select.forEach((s, i) => {
                    this.select[i] = true
                })
            }
            else if (!val && oldVal && this.select.every(s => s)) {
                this.select.forEach((s, i) => {
                    this.select[i] = false
                })
            }
        }
    },
    beforeMount() {
        for (var i in this.filterContent.left) {
            for (var j in this.filterContent.left[i]) {
                let item = this.filterContent.left[i][j]
                switch (item.type) {
                    case "input":
                        this.$set(this.filterContent, item.fieldName, null)
                        break
                    case "select":
                        this.$set(this.filterContent, item.fieldName, null)
                        break
                    case "date":
                        this.$set(this.filterContent, item.fieldName, {})
                        this.$set(this.filterContent[item.fieldName], "start", null)
                        this.$set(this.filterContent[item.fieldName], "end", null)
                        break
                    default:
                        break
                }

                this.$set(this.formStruct, item.fieldName, {})
                for (var key in item) {
                    this.$set(this.formStruct[item.fieldName], key, item[key])
                }

                if (this.filterContent.left[i][j].from) {
                    let from = this.filterContent.left[i][j].from
                    Service.list(
                        from.table, 
                        (data) => {
                            if (this.formStruct[item.fieldName].options.length == 0) {
                                for (let k in data) {
                                    this.formStruct[item.fieldName].options.push({
                                        label: data[k][from.label],
                                        value: data[k][from.value],
                                    })
                                }
                            }
                        },
                        () => {})
                }
            }
        }
    },
    mounted() {
        for (let field_name in this.display) {
            this.widthSum += this.display[field_name].width
        }
        this.updateDataList([])
    },
    props: [
        'excludeAssetsId', 
        // 'assetsToinventoryId',
        'getAssetsId',
    ],
    component: [DataList]
}
</script>

<style lang="less">
.add-assets-to-inventory-dialog {
    border: 1px solid #bbe0ff;
    color: rgb(95, 95, 95);
    // background-color: white;
    // width: 800px;
    .el-dialog {
        width: 1000px;
        padding: 0;

        .title {
            background-color: #ecf5ff;
            padding: 15px 20px;
            border-bottom: 1px solid #bbe0ff;
        }
        .el-dialog__header, .el-dialog__body, .el-dialog__footer {
            padding: 0;
        }

        .el-dialog__body {
            padding-top: 0px;
            padding: 0 10px;
            .data-list-operating {
                clear: both;
            }
            .data-list {
                clear: both;
                display: block;
                height: 400px;
                overflow-y: scroll;
            }
        }
        @line-height: 30px;

        .footer {
            overflow: hidden;
            background-color: #faf9fc;
            padding: 10px 20px;

            .tip {
                float: left;
            }

            .buttons {
                float: right;

                .save,
                .cancel {
                    width: 50px;
                    height: 32px;
                    border: none;
                }

                .save {
                    background-color: #326ab0;
                    color: white;
                    margin-right: 15px;
                }

                .cancel {
                    background-color: #dbdbdb;
                }
            }
        }
    }
    
}
</style>
