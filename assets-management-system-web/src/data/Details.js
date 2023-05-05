import util from "../util"

let Details = {
    'assets-maintain': [],
    'assets-purchase-requisition': [
        {
            title: '资产信息',
            icon: 'icon',
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
            icon: 'icon',
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
    'assets-transfer': [],
    'assets-scrap': [],
    'assets-borrow': [],
    'supplier': [],
}

export default Details