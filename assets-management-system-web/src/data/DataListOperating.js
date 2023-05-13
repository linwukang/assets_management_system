import router from '../router/index'
import util from '../util'
import FormDialog from '../components/FormDialog.vue'
import FormStruct from './FormStruct'
import Service from '../Service'

let assetsClassSelect = {
    type: 'select',
    options: [],
    from: {
        table: "assets-class",
        label: "name",
        value: "id",
    }
}

let storagePlaceSelect = {
    type: 'select',
    options: [],
    from: {
        table: "storage-place",
        label: "name",
        value: "id",
    }
}

export {
    assetsClassSelect,
    storagePlaceSelect
}

export default {
    'assets-borrow': {
        left: [[
            {
                type: 'select',
                options: [
                    { label: '已归还', value: 'returned' },
                    { label: '未归还', value: 'not_returned' }
                ],
                fieldName: 'returnState',
                label: '归还状态',
                width: 150
            },
            { type: 'input', fieldName: 'code', label: '借用单号', width: 150 },
            { type: 'input', fieldName: 'Personnel(borrowerId).name,Personnel(borrowerId).code', label: '借用人姓名/工号', width: 150 },
            {
                ...assetsClassSelect,
                fieldName: 'AssetsStorage(assetId).assetClassId',
                label: '资产类别',
                width: 150
            },
            { type: 'input', fieldName: 'AssetsStorage(assetId).name,AssetsStorage(assetId).code', label: '资产编码/名称', width: 150 },
        ]],
        right: [
            {
                text: '借用登记',
                click() {
                    router.push('assets-borrow/new')
                },
            }
        ]
    },
    'assets-class': {
        left: [],
        right: [{
            text: '新增',
            click() {
                util.createComponent(FormDialog, {
                    title: "新增资产类别",
                    content: FormStruct['assets-class'],
                    dataLine: {},
                    onSave: (data, ok) => { 
                        Service.create(
                            'assets-class', 
                            data, 
                            ok
                        )
                    },
                    onCancel: () => { },
                })
            },
        }]
    },
    'assets-information-maintenance': {
        left: [[
            {
                type: 'select',
                options: [
                    { label: '正常', value: '正常' },
                    { label: '报废', value: '报废' }
                ],
                fieldName: 'state',
                label: '资产状态',
                width: 150
            },
            {
                ...assetsClassSelect,
                fieldName: 'assetClassId',
                label: '资产类别',
                width: 150
            },
            {
                type: 'select',
                options: [],
                from: {
                    table: "gaining-method",
                    label: "name",
                    value: "id",
                },
                fieldName: 'gainingMethodId',
                label: '取得方式',
                width: 150
            },
            {
                type: 'input',
                fieldName: 'code,name',
                label: '资产编码/名称',
                width: 180
            }
        ]],
        right: []
    },
    'assets-inventory': {
        left: [[
            {
                type: 'select',
                options: [
                    { label: '未开始', value: 'not_started' },
                    { label: '进行中', value: 'in_progress' },
                    { label: '已结束', value: 'finished' },
                ],
                fieldName: 'state',
                label: '盘点单状态',
                width: 150
            },
            { type: 'input', fieldName: 'name', label: '盘点单号', width: 150 },
        ]],
        right: [{
            text: '新增盘点单',
            click() {
                router.push('assets-inventory/new-inventory')
            },
        }]
    },
    'assets-inventory-sheet': {
        left: [],
        right: []
    },
    'assets-maintain': {
        left: [[
            { type: 'input', fieldName: 'code', label: '维修单号', width: 150 },
            {
                ...assetsClassSelect,
                fieldName: 'AssetsStorage(assetId).assetClassId',
                label: '资产类别',
                width: 150
            },
            { type: 'input', fieldName: 'AssetsStorage(assetId).name,AssetsStorage(assetId).code', label: '资产编码/名称', width: 150 },
            {
                type: 'date',
                fieldName: 'reportMaintainDate',
                label: '报修日期',
                startLabel: '开始日期',
                endLabel: '结束日期',
                joinText: '到',
                width: 150
            }
        ]],
        right: [{
            text: '维修登记',
            click() {
                router.push('assets-maintain/new')
            },
        }]
    },
    'assets-purchase-requisition': {
        left: [[
            { type: 'input', fieldName: 'code', label: '申购单号', width: 150 },
            { type: 'input', fieldName: 'assetName', label: '申购资产', width: 150 },
            { type: 'input', fieldName: 'Personnel(proposerId).name,Personnel(proposerId).code', label: '借用人姓名/工号', width: 150 },
            {
                type: 'date',
                fieldName: 'purchaseRequisitionDate',
                label: '申请日期',
                startLabel: '开始日期',
                endLabel: '结束日期',
                joinText: '到',
                width: 150
            }
        ]],
        right: [{
            text: '申购登记',
            click() {
                router.push('assets-purchase-requisition/new')
            },
        }]
    },
    'assets-scrap': {
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
                joinText: '到',
                width: 150
            }
        ]],
        right: [{
            text: '报废登记',
            click() {
                router.push('assets-scrap/new')
            },
        }]
    },
    'assets-storage': {
        left: [[
            {
                type: 'select',
                options: [
                    { label: '正常', value: '正常' },
                    { label: '报废', value: '报废' }
                ],
                fieldName: 'state',
                label: '资产状态',
                width: 150
            },
            {
                ...assetsClassSelect,
                fieldName: 'assetClassId',
                label: '资产类别',
                width: 150
            },
            {
                type: 'select',
                options: [],
                from: {
                    table: "gaining-method",
                    label: "name",
                    value: "id",
                },
                fieldName: 'gainingMethodId',
                label: '取得方式',
                width: 150
            },
            {
                type: 'input',
                fieldName: 'code,name',
                label: '资产编码/名称',
                width: 180
            }
        ]],
        right: [{
            text: '入库登记',
            click() {
                router.push('assets-storage/new')
            },
        }]
    },
    'assets-transfer': {
        left: [[
            { type: 'input', fieldName: 'code', label: '转移单号', width: 150 },
            {
                ...assetsClassSelect,
                fieldName: 'AssetsStorage(AssetsBorrow(assetsBorrowId).assetId).assetClassId',
                label: '资产类别',
                width: 150
            },
            { type: 'input', fieldName: 'AssetsStorage(AssetsBorrow(assetsBorrowId).assetId).name,AssetsStorage(AssetsBorrow(assetsBorrowId).assetId).code', label: '资产编码/名称', width: 150 },
            {
                type: 'date',
                fieldName: 'transferDate',
                label: '转移日期',
                startLabel: '开始日期',
                endLabel: '结束日期',
                joinText: '到',
                width: 150
            }
        ]],
        right: [{
            text: '转移登记',
            click() {
                router.push('assets-transfer/new')
            },
        }]
    },
    'brand': {
        left: [],
        right: [{
            text: '新增',
            click() {
                util.createComponent(FormDialog, {
                    title: "新增品牌",
                    content: FormStruct['brand'],
                    dataLine: {},
                    onSave: (data, ok) => { 
                        Service.create(
                            'brand', 
                            data, 
                            ok
                        )
                    },
                    onCancel: () => { },
                })
            },
        }]
    },
    'department': {
        left: [],
        right: [{
            text: '新增',
            click() {
                util.createComponent(FormDialog, {
                    title: "新增部门",
                    content: FormStruct['department'],
                    dataLine: {},
                    onSave: (data, ok) => { 
                        Service.create(
                            'department', 
                            data, 
                            ok
                        )
                    },
                    onCancel: () => { },
                })
            },
        }]
    },
    'equipment-use': {
        left: [[
            { 
                type: 'input', 
                fieldName: 'name,code', 
                label: '设备用途名称或编号', 
                width: 200 
            },
        ]],
        right: [{
            text: '新增',
            click() {
                util.createComponent(FormDialog, {
                    title: "新增设备用途",
                    content: FormStruct['equipment-use'],
                    dataLine: {},
                    onSave: (data, ok) => { 
                        Service.create(
                            'equipment-use', 
                            data, 
                            ok
                        )
                    },
                    onCancel: () => { },
                })
            },
        }]
    },
    'gaining-method': {
        left: [],
        right: [{
            text: '新增',
            click() {
                util.createComponent(FormDialog, {
                    title: "新增取得方式",
                    content: FormStruct['gaining-method'],
                    dataLine: {},
                    onSave: (data, ok) => { 
                        Service.create(
                            'gaining-method', 
                            data, 
                            ok
                        )
                    },
                    onCancel: () => { },
                })
            },
        }]
    },
    'personal-info': {
        left: [],
        right: []
    },
    'personnel': {
        left: [[
            {
                type: 'select',
                options: [],
                from: {
                    table: "department",
                    label: "name",
                    value: "id",
                },
                fieldName: 'departmentId',
                label: '所属部门',
                width: 150
            },
            {
                type: 'select',
                options: [
                    { label: '在编', value: '在编' },
                    { label: '合同', value: '合同' },
                    { label: '临时', value: '临时' },
                ],
                fieldName: 'category',
                label: '人员类别',
                width: 150
            },
            {
                type: 'input',
                fieldName: 'name,code',
                label: '姓名或工号',
                width: 150
            }
        ]],
        right: [{
            text: '添加人员',
            click() {
                router.push('personnel/new')
            },
        }]
    },
    'scrap-mode': {
        left: [[
            { 
                type: 'input', 
                fieldName: 'name,code', 
                label: '报废方式名称或编号', 
                width: 200 
            },
        ]],
        right: [{
            text: '新增',
            click() {
                util.createComponent(FormDialog, {
                    title: "新增报废方式",
                    content: FormStruct['scrap-mode'],
                    dataLine: {},
                    onSave: (data, ok) => { 
                        Service.create(
                            'scrap-mode', 
                            data, 
                            ok
                        )
                    },
                    onCancel: () => { },
                })
            },
        }]
    },
    'storage-place': {
        left: [[
            {
                type: 'select',
                options: [
                    { label: '启用', value: 1 },
                    { label: '禁用', value: 0 },
                ],
                fieldName: 'enabled',
                label: '全部状态',
                width: 150
            },
            {
                type: 'input',
                fieldName: 'name',
                label: '存放地点名称',
                width: 150
            }
        ]],
        right: [{
            text: '新增',
            click() {
                util.createComponent(FormDialog, {
                    title: "新增存放地点",
                    content: FormStruct['storage-place'],
                    dataLine: {},
                    onSave: (data, ok) => { 
                        Service.create(
                            'storage-place', 
                            data, 
                            ok
                        )
                    },
                    onCancel: () => { },
                })
            },
        }]
    },
    'supplier': {
        left: [[
            {
                type: 'select',
                options: [
                    { label: '启用', value: 1 },
                    { label: '禁用', value: 0 },
                ],
                fieldName: 'enabled',
                label: '全部状态',
                width: 150
            },
            {
                type: 'select',
                options: [
                    { label: '生产商', value: '生产商' },
                    { label: '零售商', value: '零售商' },
                    { label: '代理商', value: '代理商' },
                    { label: '其他', value: '其他' },
                ],
                fieldName: 'type',
                label: '全部类型',
                width: 150
            },
            {
                type: 'input',
                fieldName: 'name',
                label: '供应商名称',
                width: 150
            }
        ]],
        right: [{
            text: '新增',
            click() {
                util.createComponent(FormDialog, {
                    title: "新增供应商",
                    content: FormStruct['supplier'],
                    dataLine: {},
                    onSave: (data, ok) => { 
                        Service.create(
                            'supplier', 
                            data, 
                            ok
                        )
                    },
                    onCancel: () => { },
                })
            },
        }]
    },
    'user-info': {
        left: [],
        right: []
    },
}