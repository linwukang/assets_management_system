import util from "../util"

let Details = {
    'assets-maintain':[
        {
            title: '资产信息',
            icon: ['fas', 'book'],
            form: {
                left: [
                    {
                        type: 'text',
                        label: '资产名称',
                        fieldName: 'AssetsStorage(assetId).name',
                    },
                    {
                        type: 'text',
                        label: '资产类别',
                        fieldName: 'AssetsClass(AssetsStorage(assetId).assetClassId).name',
                    },
                    {
                        type: 'text',
                        label: '品牌',
                        fieldName: 'Brand(AssetsStorage(assetId).brandId).name',
                    },
                    {
                        type: 'text',
                        label: '入库时间',
                        fieldName: 'AssetsStorage(assetId).storageDate',
                        value(dataContent) {
                            return util.DateFormat(dataContent['AssetsStorage(assetId).storageDate'])
                        }
                    },
                ],
                right: [
                    {
                        type: 'text',
                        label: '资产编码',
                        fieldName: 'AssetsStorage(assetId).code',
                    },
                    {
                        type: 'text',
                        label: '供应商',
                        fieldName: 'Supplier(AssetsStorage(assetId).supplierId).name',
                    },
                    {
                        type: 'text',
                        label: '取得方式',
                        fieldName: 'GainingMethod(AssetsStorage(assetId).gainingMethodId).name',
                    },
                ],
            }
        },
        {
            title: '维修信息',
            icon: ['fas', 'screwdriver-wrench'],
            form: {
                left: [
                    {
                        type: 'text',
                        label: '维修单号',
                        fieldName: 'code',
                    },
                    {
                        type: 'text',
                        label: '报修日期',
                        fieldName: 'reportMaintainDate',
                        value(dataContent) {
                            return util.DateFormat(dataContent['reportMaintainDate'])
                        }
                    },
                    {
                        type: 'text',
                        label: '报修原因',
                        fieldName: 'reportMaintainReason',
                    },
                    {
                        type: 'text',
                        label: '维修单位',
                        fieldName: 'maintainUnit',
                    },
                    {
                        type: 'text',
                        label: '修复日期',
                        fieldName: 'repairDate',
                        value(dataContent) {
                            return util.DateFormat(dataContent['repairDate'])
                        }
                    },
                    {
                        type: 'text',
                        label: '故障说明',
                        fieldName: 'faultDescription',
                    },
                ],
                right: [
                    {
                        type: 'pass',
                        label: '',
                        fieldName: 'Personnel(reportMaintainPersonnelId).code',
                    },
                    {
                        type: 'text',
                        label: '报修人',
                        fieldName: 'Personnel(reportMaintainPersonnelId).name',
                        value(dataContent) {
                            return `${dataContent['Personnel(reportMaintainPersonnelId).name']}(${dataContent['Personnel(reportMaintainPersonnelId).code']})`
                        }
                    },
                    {
                        type: 'pass',
                        label: '',
                        fieldName: '',
                    },
                    {
                        type: 'pass',
                        label: '',
                        fieldName: '',
                    },
                    {
                        type: 'text',
                        label: '维修费用（元）',
                        fieldName: 'maintainCharge',
                    },
                ],
            }
        },
    ],
    'assets-purchase-requisition': [
        {
            title: '资产信息',
            icon: ['fas', 'book'],
            form: {
                left: [
                    {
                        type: 'text',
                        label: '申购资产',
                        fieldName: 'assetName',
                    }
                ],
                right: [
                    {
                        type: 'text',
                        label: '资产类别',
                        fieldName: 'AssetsClass(assetClassId).name',
                    }
                ],
            }
        },
        {
            title: '申购信息',
            icon: ['fas', 'list-check'],
            form: {
                left: [
                    {
                        type: 'text',
                        label: '申购单号',
                        fieldName: 'code',
                    },
                    {
                        fieldName: 'Personnel(proposerId).code',
                    },
                    {
                        type: 'text',
                        label: '申请人',
                        fieldName: 'Personnel(proposerId).name',
                        value(dataContent) {
                            return dataContent['Personnel(proposerId).name'] +
                                '(' + dataContent['Personnel(proposerId).code'] + ')'
                        }
                    },
                    {
                        type: 'text',
                        label: '申购数量',
                        fieldName: 'number',
                    },
                    {
                        type: 'text',
                        label: '设备用途',
                        fieldName: 'EquipmentUse(equipmentUseId).name',
                    },
                    {
                        type: 'image',
                        label: '资产照片',
                        fieldName: 'assetPhotograph',
                    },
                    {
                        type: 'text',
                        label: '申购建议',
                        fieldName: 'suggest',
                    },
                    {
                        type: 'text',
                        label: '申请日期',
                        fieldName: 'purchaseRequisitionDate',
                        value(dataContent) {
                            return util.DateFormat(dataContent.purchaseRequisitionDate)
                        }
                    },
                    {
                        type: 'text',
                        label: '申请状态',
                        fieldName: 'applicationStatus',
                        value(dataContent) {
                            if (dataContent.applicationStatus === 'unread')
                                return '未批阅'
                            if (dataContent.applicationStatus === 'not_approved')
                                return '审批不通过'
                            if (dataContent.applicationStatus === 'approved')
                                return '审批通过'
                            else
                                return dataContent.applicationStatus
                        }
                    },
                    {
                        type: 'text',
                        label: '审核不通过原因',
                        fieldName: 'notApprovingReasons'
                    },
                ],
                right: [
                    {
                        type: 'text',
                        label: '所属部门',
                        fieldName: 'Department(Personnel(proposerId).departmentId).name',
                    },
                    {
                        type: 'text',
                        label: '预计价格',
                        fieldName: 'anticipatedPrice',
                    }
                ],
            }

        }
    ],
    'assets-transfer': [
        {
            title: '资产信息',
            icon: ['fas', 'book'],
            form: {
                left: [
                    {
                        type: 'text',
                        label: '资产名称',
                        fieldName: 'AssetsStorage(AssetsBorrow(assetsBorrowId).assetId).name',
                    },
                    {
                        type: 'text',
                        label: '资产类别',
                        fieldName: 'AssetsClass(AssetsStorage(AssetsBorrow(assetsBorrowId).assetId).assetClassId).name',
                    },
                    {
                        type: 'text',
                        label: '品牌',
                        fieldName: 'Brand(AssetsStorage(AssetsBorrow(assetsBorrowId).assetId).brandId).name',
                    },
                    {
                        type: 'text',
                        label: '存放地点',
                        fieldName: 'StoragePlace(AssetsStorage(AssetsBorrow(assetsBorrowId).assetId).storagePlaceId).name',
                    },
                ],
                right: [
                    {
                        type: 'text',
                        label: '资产编码',
                        fieldName: 'AssetsStorage(AssetsBorrow(assetsBorrowId).assetId).code',
                    },
                    {
                        type: 'text',
                        label: '供应商',
                        fieldName: 'Supplier(AssetsStorage(AssetsBorrow(assetsBorrowId).assetId).supplierId).name',
                    },
                    {
                        type: 'text',
                        label: '取得方式',
                        fieldName: 'GainingMethod(AssetsStorage(AssetsBorrow(assetsBorrowId).assetId).gainingMethodId).name',
                    },
                    {
                        type: 'text',
                        label: '入库时间',
                        fieldName: 'AssetsStorage(AssetsBorrow(assetsBorrowId).assetId).storageDate',
                        value(dataContent) {
                            return util.DateFormat(dataContent['AssetsStorage(AssetsBorrow(assetsBorrowId).assetId).storageDate'])
                        }
                    },
                ],
            }
        },
        {
            title: '转移信息',
            icon: ['fas', 'arrow-down-up-across-line'],
            form: {
                left: [
                    {
                        type: 'text',
                        label: '转移单号',
                        fieldName: 'code',
                        bold: true
                    },
                    {
                        type: '',
                        label: '',
                        fieldName: 'Personnel(AssetsBorrow(assetsBorrowId).borrowerId).code',
                    },
                    {
                        type: 'text',
                        label: '原借用人',
                        fieldName: 'Personnel(AssetsBorrow(assetsBorrowId).borrowerId).name',
                        value(dataContent) {
                            return dataContent['Personnel(AssetsBorrow(assetsBorrowId).borrowerId).name'] +
                                '(' + dataContent['Personnel(AssetsBorrow(assetsBorrowId).borrowerId).code'] + ')'
                        }
                    },
                    {
                        type: '',
                        label: '',
                        fieldName: 'Personnel(newBorrowerId).code',
                    },
                    {
                        type: 'text',
                        label: '新借用人',
                        fieldName: 'Personnel(newBorrowerId).name',
                        value(dataContent) {
                            return dataContent['Personnel(newBorrowerId).name'] +
                                '(' + dataContent['Personnel(newBorrowerId).code'] + ')'
                        }
                    },
                    {
                        type: 'text',
                        label: '转移日期',
                        fieldName: 'transferDate',
                        value(dataContent) {
                            return util.DateFormat(dataContent.transferDate)
                        }
                    },
                    {
                        type: 'text',
                        label: '转移原因',
                        fieldName: 'transferReason',
                    },
                ],
                right: [
                    {
                        type: 'pass'
                    },
                    {
                        type: 'text',
                        label: '所属部门',
                        fieldName: 'Department(Personnel(AssetsBorrow(assetsBorrowId).borrowerId).departmentId).name',
                    },
                    {
                        type: 'text',
                        label: '所属部门',
                        fieldName: 'Department(Personnel(newBorrowerId).departmentId).name',
                    },
                ],
            }
        },
    ],
    'assets-scrap': [
        {
            title: '资产信息',
            icon: ['fas', 'book'],
            form: {
                left: [
                    {
                        type: 'text',
                        label: '资产名称',
                        fieldName: 'AssetsStorage(assetId).name',
                    },
                    {
                        type: 'text',
                        label: '资产类别',
                        fieldName: 'AssetsClass(AssetsStorage(assetId).assetClassId).name',
                    },
                    {
                        type: 'text',
                        label: '品牌',
                        fieldName: 'Brand(AssetsStorage(assetId).brandId).name',
                    },
                    {
                        type: 'text',
                        label: '入库时间',
                        fieldName: 'AssetsStorage(assetId).storageDate',
                        value(dataContent) {
                            return util.DateFormat(dataContent['AssetsStorage(assetId).storageDate'])
                        }
                    },
                ],
                right: [
                    {
                        type: 'text',
                        label: '资产编码',
                        fieldName: 'AssetsStorage(assetId).code',
                    },
                    {
                        type: 'text',
                        label: '供应商',
                        fieldName: 'Supplier(AssetsStorage(assetId).supplierId).name',
                    },
                    {
                        type: 'text',
                        label: '取得方式',
                        fieldName: 'GainingMethod(AssetsStorage(assetId).gainingMethodId).name',
                    },
                ],
            }
        },
        {
            title: '报废信息',
            icon: ['fas', 'ban'],
            form: {
                left: [
                    {
                        type: '',
                        label: '',
                        fieldName: 'Personnel(proposerId).code',
                    },
                    {
                        type: 'text',
                        label: '申请人',
                        fieldName: 'Personnel(proposerId).name',
                        value(dataContent) {
                            return `${dataContent['Personnel(proposerId).name']}(${dataContent['Personnel(proposerId).code']})`
                        }
                    },
                    {
                        type: 'text',
                        label: '报修日期',
                        fieldName: 'scrapDate',
                        value(dataContent) {
                            return util.DateFormat(dataContent['scrapDate'])
                        }
                    },
                    {
                        type: 'text',
                        label: '报废原因',
                        fieldName: 'scrapReason',
                    },
                    {
                        type: 'text',
                        label: '申请状态',
                        fieldName: 'applicationStatus',
                        value(dataContent) {
                            if (dataContent.applicationStatus === 'unread')
                                return '未批阅'
                            if (dataContent.applicationStatus === 'not_approved')
                                return '审批不通过'
                            if (dataContent.applicationStatus === 'approved')
                                return '审批通过'
                            else
                                return dataContent.applicationStatus
                        }
                    },
                ],
                right: [
                    {
                        type: 'text',
                        label: '报废方式',
                        fieldName: 'ScrapMode(scrapModeId).name',
                    },
                ],
            }
        },
    ],
    'assets-borrow': [
        {
            title: '资产信息',
            icon:  ['fas', 'book'],
            form: {
                left: [
                    {
                        type: 'text',
                        label: '资产名称',
                        fieldName: 'AssetsStorage(assetId).name',
                    },
                    {
                        type: 'text',
                        label: '资产类别',
                        fieldName: 'AssetsClass(AssetsStorage(assetId).assetClassId).name',
                    },
                    {
                        type: 'text',
                        label: '品牌',
                        fieldName: 'Brand(AssetsStorage(assetId).brandId).name',
                    },
                    {
                        type: 'text',
                        label: '存放地点',
                        fieldName: 'StoragePlace(AssetsStorage(assetId).storagePlaceId).name',
                    },
                ],
                right: [
                    {
                        type: 'text',
                        label: '资产编码',
                        fieldName: 'AssetsStorage(assetId).code',
                    },
                    {
                        type: 'text',
                        label: '供应商',
                        fieldName: 'Supplier(AssetsStorage(assetId).supplierId).name',
                    },
                    {
                        type: 'text',
                        label: '取得方式',
                        fieldName: 'GainingMethod(AssetsStorage(assetId).gainingMethodId).name',
                    },
                    {
                        type: 'text',
                        label: '入库时间',
                        fieldName: 'AssetsStorage(assetId).storageDate',
                        value(dataContent) {
                            return util.DateFormat(dataContent['AssetsStorage(assetId).storageDate'])
                        }
                    },
                ],
            }
        },
        {
            title: '借用信息',
            icon: ['fas', 'right-left'],
            form: {
                left: [
                    {
                        type: 'text',
                        label: '借用单号',
                        fieldName: 'code',
                        bold: true
                    },
                    {
                        type: '',
                        label: '',
                        fieldName: 'Personnel(borrowerId).code',
                    },
                    {
                        type: 'text',
                        label: '借用人',
                        fieldName: 'Personnel(borrowerId).name',
                        value(dataContent) {
                            return dataContent['Personnel(borrowerId).name'] +
                                '(' + dataContent['Personnel(borrowerId).code'] + ')'
                        }
                    },
                    {
                        type: 'text',
                        label: '借用日期',
                        fieldName: 'borrowDate',
                        value(dataContent) {
                            return util.DateFormat(dataContent.borrowDate)
                        }
                    },
                    {
                        type: 'text',
                        label: '借用原因',
                        fieldName: 'borrowReason',
                    },
                    {
                        type: 'date',
                        required: true,
                        label: '归还日期',
                        fieldName: 'returnDate',
                        value(dataContent) {
                            return util.DateFormat(dataContent.returnDate)
                        },
                        textualization(dataContent) {
                            return dataContent.returnState === 'returned'
                        }
                    },
                    {
                        type: 'input',
                        label: '备注',
                        rows: 6,
                        fieldName: 'remark',
                        textualization(dataContent) {
                            return dataContent.returnState === 'returned'
                        }
                    },
                ],
                right: [
                    {
                        type: 'text',
                        label: '借用部门',
                        fieldName: 'Department(Personnel(borrowerId).departmentId).name',
                    },
                ],
            }
        },
    ],
    'supplier': [
        {
            title: '查看供应商详情',
            icon: ['far', 'building'],
            form: {
                left: [
                    {
                        type: 'text',
                        label: '供应商名称',
                        fieldName: 'name',
                    },
                    {
                        type: 'text',
                        label: '供应商类型',
                        fieldName: 'type',
                    },
                    {
                        type: 'text',
                        label: '状态',
                        fieldName: 'enabled',
                        value(dataContent) {
                            return dataContent.enabled
                                ? "已启用"
                                : "已禁用"
                        }
                    },
                    {
                        type: 'text',
                        label: '联系人',
                        fieldName: 'contactPerson',
                    },
                    {
                        type: 'text',
                        label: '移动电话',
                        fieldName: 'contactTel',
                    },
                    {
                        type: 'text',
                        label: '地址',
                        fieldName: 'address',
                    },
                    {
                        type: 'text',
                        label: '备注',
                        fieldName: 'remark',
                    },
                ],
                right: [],
            }
        },
    ],
}

export default Details