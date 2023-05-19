<template>
<!-- 编辑资产盘点 -->
<div class="edit-assets-inventory-view">
    <div class="top">
        <el-form :inline="true" ref="form" label-width="100px">
            <el-form-item label="盘点单名称">
                <el-input v-model="name"></el-input>
            </el-form-item>
            <el-form-item label="盘点单说明">
                <el-input v-model="description"></el-input>
            </el-form-item>
        </el-form>
    </div>
    <div class="right">
        <el-button @click="addAssets()">添加</el-button>
        <el-button @click="deleteBatch()">批量删除</el-button>
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

    <div class=".bottom">
        <el-button @click="save()">保存</el-button>
        <el-button @click="cancel()">取消</el-button>
    </div>
</div>
</template>

    
<script lang="js">
import DataList from '../../components/DataList.vue'
import FormDialog from '../../components/FormDialog.vue'
import Pagination from '../../components/Pagination.vue'
import DataListOperating from '../../components/DataListOperating.vue'

import DataOperating from '../../data/DataOperating'
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
                    name: "资产编码",
                    width: 200
                },
                "name": {
                    name: "资产名称",
                    width: 200
                },
                "AssetsClass(assetClassId).name": {
                    name: "资产类别",
                    width: 300,
                },
                "Brand(brandId).name": {
                    name: "品牌",
                    width: 200,
                },
                "StoragePlace(storagePlaceId).name": {
                    name: "存放地点",
                    width: 200,
                },
                "storageDate": {
                    name: "入库日期",
                    width: 200,
                    value(dataSource, index) {
                        return util.DateFormat(dataSource[index].storageDate)
                    }
                },
                "state": {
                    name: "资产状态",
                    width: 200
                },
            },
            dataList: [],
            dataContent: [],
            formStruct: {},
            width: 800,
            select: [],
            isSelectAll: false,
            name: null,
            description: null,
            code: null,
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
        addAssets() {
            util.createComponent(
                AddAssetsToInventoryDialog, 
                {
                    excludeAssetsId: this.dataList.map(asset => asset.id),
                    // assetsToinventoryId: ,
                    getAssetsId: (assetsId) => {
                        Service.joinListByIdList(
                            'assets-storage', 
                            Object.keys(this.display),
                            assetsId,
                            (data) => {
                                this.dataList = data
                                this.select = new Array(data.length)
                                for (let index = 0; index < this.select.length; index++) {
                                    this.select[index] = false
                                }
                            },
                            (okay, message) => {
                                if (okay) {

                                }
                                else {
                                    this.tip(message, 'error')
                                }
                            })
                    },
                })
        },
        deleteBatch() {
            if (this.select.filter(x => x).length === 0) {
                this.$confirm('请先选择要删除的资产，再批量删除！', '', {
                    confirmButtonText: '确定',
                    type: 'warning',
                    showClose: false,
                    center: true
                }).then(() => {
                }).catch(() => {
                });
            }
            else {
                this.dataList = this.dataList.filter(
                    (d, index) => {
                        return !this.select[index]
                    }
                )
                this.select = new Array(this.dataList.length)
                for (let index = 0; index < this.select.length; index++) {
                    this.select[index] = false
                }
            }
        },
        save() {
            Service.save(
                'assets-inventory',
                {
                    id: this.$route.params.id,
                    name: this.name,
                    description: this.description
                },
                (okay, data) => {
                    if (okay) {
                        let assetList = []
                        for (let index = 0; index < this.dataList.length; index++) {
                            assetList.push({
                                assetsInventoryId: this.$route.params.id,
                                assetId: this.dataList[index].id
                            })
                        }
                        console.log(assetList)
                        Service.createMultiple(
                            'assets-inventory-sheet',
                            assetList,
                            (okay, data) => {
                                if (okay) {
                                    this.tip('添加成功', 'success')
                                    this.cancel()
                                }
                                else {
                                    this.tip(data, 'error')
                                }
                            }
                        )
                    } else {
                        this.tip(data, 'error')
                    }
                }
            )
        },
        cancel() {
            this.$router.push('/asset-administrator/home/assets-inventory')
        },
        getDataList() {
            Service.getById(
                'assets-inventory',
                this.$route.params.id,
                (data) => {
                    console.log(data);
                    this.name = data.name
                    this.description = data.description
                    this.code = data.code
                },
                (okay, message) => {
                    if (okay) {
                        console.log(okay);
                    } else {
                        console.log(okay, message);
                    }
                })

            Service.joinList(
                'assets-inventory-sheet',
                ['assetId'],
                [{
                    operator: 'eq', 
                    values: ['assetsInventoryId', Number.parseInt(this.$route.params.id)]
                }],
                (assetsList) => {
                    let idList = assetsList.map(a => a.assetId)
                    console.log(idList)

                    Service.joinListByIdList(
                    'assets-storage', 
                    Object.keys(this.display),
                    idList,
                    (data) => {
                        this.dataList = data
                        this.select = new Array(data.length)
                        for (let index = 0; index < this.select.length; index++) {
                            this.select[index] = false
                        }
                    },
                    (okay, message) => {
                        if (okay) {

                        }
                        else {
                            this.tip(message, 'error')
                        }
                    })
                }
            )
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
    mounted() {
        this.getDataList()
    },
    computed: {},
    props: [],
    components: {
        DataList,
        FormDialog,
        Pagination,
        DataListOperating,
        DetailsView
    },
};
</script>

    
<style lang="less">
</style>
