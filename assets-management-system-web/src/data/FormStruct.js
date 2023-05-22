export default {
    // 资产借还
    'assets-borrow': {
        left: [
            {
                label: '资产名称',
                fieldName: 'assetId',
                required: true,
                options: [],
                from: {
                    table: 'assets-storage',
                    label: 'name',
                    value: 'id',
                    conditions: [
                        {
                            operator: 'eq',
                            values: ['state', '正常']
                        }
                    ]
                }
            },
            {
                label: '借用人',
                fieldName: 'borrowerId',
                required: true,
                options: [],
                from: {
                    table: 'personnel',
                    label: 'name',
                    value: 'id',
                }
            },
            {
                label: '借用日期',
                fieldName: 'borrowDate',
                required: true,
                date: true
            },
        ],
        right: [
            {
                label: '资产编码',
                text: '自动获取',
                fieldName: 'AssetsStorage(assetId).code'
            },
            {
                label: '借用部门',
                text: '自动获取'
            },
        ],
        bottom: [
            {
                label: '借用原因',
                fieldName: 'borrowReason',
                required: true,
                rule: /^.{0,200}$/,
                rows: 6,
            },
        ]
    }, 
    // 资产类别
    'assets-class': [
        {
            label: '类别名称',
            fieldName: 'name',
            required: true,
            rule: /^[一-龥]{3,20}$/,
            rows: 1,
        },
        {
            label: '类别编码',
            fieldName: 'code',
            required: true,
            rule: /^[1-9a-zA-Z][0-9a-zA-Z]{5,7}$/,
            rows: 1,
        },
    ], 
    // 资产信息维护
	'assets-information-maintenance':{
        left: [
            {
                label: '资产名称',
                fieldName: 'name',
                required: true,
                rows: 1
            },
            {
                label: '资产类别',
                fieldName: 'assetClassId',
                required: true,
                options: [],
                from: {
                    table: 'assets-class',
                    label: 'name',
                    value: 'id',
                    conditions: [
                        {
                            operator: 'eq',
                            values: ['enabled', true]
                        }
                    ]
                }
            },
            {
                label: '品牌',
                fieldName: 'brandId',
                required: true,
                options: [],
                from: {
                    table: 'brand',
                    label: 'name',
                    value: 'id',
                    conditions: [
                        {
                            operator: 'eq',
                            values: ['enabled', true]
                        }
                    ]
                }
            },
            {
                label: '入库时间',
                fieldName: 'storageDate',
                required: true,
                date: true
            },
            {
                label: '资产照片',
                fieldName: 'assetPhotograph',
                required: false,
                upload: {}
            },
        ],
        right: [
            {
                label: '资产编码',
                fieldName: 'code',
                required: true,
                rows: 1,
                tip: '注意：资产编码保存后不允许修改，请认真填写。'
            },
            {
                label: '供应商',
                fieldName: 'supplierId',
                required: true,
                options: [],
                from: {
                    table: 'supplier',
                    label: 'name',
                    value: 'id',
                    conditions: [
                        {
                            operator: 'eq',
                            values: ['enabled', true]
                        }
                    ]
                }
            },
            {
                label: '取得方式',
                fieldName: 'gainingMethodId',
                required: true,
                options: [],
                from: {
                    table: 'gaining-method',
                    label: 'name',
                    value: 'id',
                    conditions: [
                        {
                            operator: 'eq',
                            values: ['enabled', true]
                        }
                    ]
                }
            },
            {
                label: '存放地点',
                fieldName: 'storagePlaceId',
                required: true,
                options: [],
                from: {
                    table: 'storage-place',
                    label: 'name',
                    value: 'id',
                    conditions: [
                        {
                            operator: 'eq',
                            values: ['enabled', true]
                        }
                    ]
                }
            },
        ],
        bottom: [],
    },
    // 资产盘点
    'assets-inventory': {

    }, 
    'assets-inventory-sheet': [], 
    // 资产维修
    'assets-maintain': {
        left: [
            {
                label: '资产名称',
                fieldName: 'asset_id',
                required: true,
                options: [],
                from: {
                    table: 'assets-storage',
                    label: 'id',
                    value: 'name',
                }
            },
            {
                label: '报修日期',
                fieldName: 'reportMaintainDate',
                required: true,
                date: true
            },
            {
                label: '报修原因',
                fieldName: 'reportMaintainReason',
                required: true,
                rule: /^[0-9a-zA-Z一-龥]{0,20}$/,
                rows: 1
            },
            {
                label: '维修单位',
                fieldName: 'maintainUnit',
                required: true,
                rule: /^[0-9a-zA-Z一-龥]{0,20}$/,
                rows: 1
            },
            {
                label: '修复日期',
                fieldName: 'repairDate',
                required: true,
                date: true
            },
        ],
        right: [
            {
                label: '资产编码',
                // fieldName: 'assetId',
                text: '自动获取'
            },
            {
                label: '报修人',
                fieldName: 'reportMaintainPersonnelId',
                required: true,
                options: [],
                from: {
                    table: 'personnel',
                    label: 'id',
                    value: 'name',
                }
            },
            {
                label: '维修费用',
                fieldName: 'maintainCharge',
                required: true,
                rows: 1
            },
        ],
        bottom: [
            {
                label: '故障说明',
                fieldName: 'faultDescription',
                required: true,
                rule: /^.{0,200}$/,
                rows: 6
            },
        ]
    }, 
    // 资产申购
    'assets-purchase-requisition': {
        left: [
            {
                label: '申购资产',
                fieldName: 'assetName',
                required: true,
                rule: /^[0-9a-zA-Z一-龥]{2,30}$/,
                rows: 1
            },
            {
                label: '申请人',
                fieldName: 'proposerId',
                required: true,
                options: [],
                from: {
                    table: 'personnel',
                    label: 'name',
                    value: 'id',
                }
            },
            {
                label: '申购数量',
                fieldName: 'number',
                required: true,
                rows: 1
            },
            {
                label: '设备用途',
                fieldName: 'equipmentUseId',
                required: true,
                options: [],
                from: {
                    table: 'equipment-use',
                    label: 'name',
                    value: 'id',
                    conditions: [
                        {
                            operator: 'eq',
                            values: ['enabled', true]
                        }
                    ]
                }
            },
            {
                label: '资产照片',
                fieldName: 'assetPhotograph',
                required: false,
                upload: {
                    action: 'upload/images'
                }
            },
            {
                label: '申购建议',
                fieldName: 'suggest',
                required: false,
                rule: /^[0-9a-zA-Z一-龥]{2,20}$/,
                rows: 1
            },
            {
                label: '申请日期',
                fieldName: 'purchaseRequisitionDate',
                required: true,
                date: true
            },
        ],
        right: [
            {
                label: '资产类别',
                fieldName: 'assetClassId',
                required: true,
                options: [],
                from: {
                    table: 'assets-class',
                    label: 'name',
                    value: 'id',
                    conditions: [
                        {
                            operator: 'eq',
                            values: ['enabled', true]
                        }
                    ]
                }
            },
            {
                label: '所属部门',
                fieldName: 'Department(Personnel(proposerId).departmentId).name',
                text: '自动获取...',
                
            },
            {
                label: '预计价格',
                fieldName: 'anticipatedPrice',
                required: false,
                rows: 1
            },
        ],
        bottom: [
            {
                label: '申购理由',
                fieldName: 'reason',
                required: true,
                rule: /^.{0,200}$/,
                rows: 6
            },
        ]
    }, 
    // 资产报废
    'assets-scrap': {
        left: [
            {
                label: '资产名称',
                fieldName: 'assetId',
                required: true,
                options: [],
                from: {
                    table: 'assets-storage',
                    label: 'id',
                    value: 'name',
                }
            },
            {
                label: '申请人',
                fieldName: 'proposerId',
                required: true,
                options: [],
                from: {
                    table: 'personnel',
                    label: 'name',
                    value: 'id',
                }
            },
            {
                label: '报废方式',
                fieldName: 'scrapModeId',
                required: true,
                options: [],
                from: {
                    table: 'scrap-mode',
                    label: 'name',
                    value: 'id',
                    conditions: [
                        {
                            operator: 'eq',
                            values: ['enabled', true]
                        }
                    ]
                }
            },
            {
                label: '报废日期',
                fieldName: 'scrapDate',
                required: true,
                date: true
            },
        ],
        right: [
            {
                label: '资产编码',
                text: '自动获取'
            },
            {
                label: '所属部门',
                text: '自动获取'
            },
        ],
        bottom: [
            {
                label: '报废原因',
                fieldName: 'scrapReason',
                required: true,
                rows: 6
            }
        ]
    }, 
    // 资产入库
    'assets-storage': {
        left: [
            {
                label: '资产名称',
                fieldName: 'name',
                required: true,
                rows: 1
            },
            {
                label: '资产类别',
                fieldName: 'assetClassId',
                required: true,
                options: [],
                from: {
                    table: 'assets-class',
                    label: 'name',
                    value: 'id',
                    conditions: [
                        {
                            operator: 'eq',
                            values: ['enabled', true]
                        }
                    ]
                }
            },
            {
                label: '品牌',
                fieldName: 'brandId',
                required: true,
                options: [],
                from: {
                    table: 'brand',
                    label: 'name',
                    value: 'id',
                    conditions: [
                        {
                            operator: 'eq',
                            values: ['enabled', true]
                        }
                    ]
                }
            },
            {
                label: '入库时间',
                fieldName: 'storageDate',
                required: true,
                date: true
            },
            {
                label: '资产照片',
                fieldName: 'assetPhotograph',
                required: false,
                upload: {}
            },
        ],
        right: [
            {
                label: '资产编码',
                fieldName: 'code',
                required: true,
                rows: 1,
                tip: '注意：资产编码保存后不允许修改，请认真填写。'
            },
            {
                label: '供应商',
                fieldName: 'supplierId',
                required: true,
                options: [],
                from: {
                    table: 'supplier',
                    label: 'name',
                    value: 'id',
                    conditions: [
                        {
                            operator: 'eq',
                            values: ['enabled', true]
                        }
                    ]
                }
            },
            {
                label: '取得方式',
                fieldName: 'gainingMethodId',
                required: true,
                options: [],
                from: {
                    table: 'gaining-method',
                    label: 'name',
                    value: 'id',
                    conditions: [
                        {
                            operator: 'eq',
                            values: ['enabled', true]
                        }
                    ]
                }
            },
            {
                label: '存放地点',
                fieldName: 'storagePlaceId',
                required: true,
                options: [],
                from: {
                    table: 'storage-place',
                    label: 'name',
                    value: 'id',
                    conditions: [
                        {
                            operator: 'eq',
                            values: ['enabled', true]
                        }
                    ]
                }
            },
        ],
        bottom: [],
    }, 
    // 资产转移
    'assets-transfer': {
        left: [
            {
                label: '资产名称',
                fieldName: 'assetId',
                required: true,
                options: [],
                from: {
                    table: 'assets-storage',
                    label: 'id',
                    value: 'name',
                }
            },
            {
                label: '原借用人',
                text: '自动获取当前使用人'
            },
            {
                label: '新借用人',
                fieldName: '',
                required: true,
                options: [],
                from: {
                    table: 'personnel',
                    label: 'id',
                    value: 'name',
                }
            },
            {
                label: '转移日期',
                fieldName: '',
                required: true,
                date: true
            },
        ],
        right: [
            {
                label: '资产编码',
                text: '自动获取'
            },
            {
                label: '所属部门',
                text: '自动获取原使用人所属部门'
            },
            {
                label: '所属部门',
                text: '自动获取新使用人所属部门'
            },
        ],
        bottom: [
            {
                label: '转移原因',
                fieldName: '',
                required: true,
                rows: 6
            },
        ],
    }, 
    // 品牌
    'brand': [
        {
            label: '品牌名称',
            fieldName: 'name',
            required: true,
            rows: 1,
            rule: /^[一-龥]{3,20}$/
        },
        {
            label: '品牌说明',
            fieldName: 'description',
            required: true,
            rows: 1,
            rule:/^.{0,500}$/
        },
    ], 
    // 部门管理
    'department': [
        {
            label: '部门名称',
            fieldName: 'name',
            required: true,
            rows: 1,
            rule: /^[0-9a-zA-Z一-龥]{3,20}$/,
        },
        {
            label: '部门编码',
            fieldName: 'code',
            required: true,
            rows: 1,
            rule: /^[1-9a-zA-Z][0-9a-zA-Z]{9}$/,
        },
        {
            label: '部门简介',
            fieldName: 'profile',
            required: false,
            rows: 6,
        },
    ],
    // 设备用途
    'equipment-use': [
        {
            label: '设备用途编号',
            fieldName: 'code',
            required: true,
            rows: 1,
            rule: /^[1-9a-zA-Z][0-9a-zA-Z]{7}$/,
        },
        {
            label: '设备用途名称',
            fieldName: 'name',
            required: true,
            rows: 1,
            rule: /^[1-9a-zA-Z一-龥]{2,10}$/,
        },
        {
            label: '备注',
            fieldName: 'remark',
            required: false,
            rows: 6,
            rule:/^.{0,100}$/
        },
    ], 
    // 取得方式
    'gaining-method': [
        {
            label: '取得方式名称',
            fieldName: 'name',
            required: true,
            rows: 1,
            rule: /^[一-龥]{3,20}$/
        },
        {
            label: '取得方式编码',
            fieldName: 'code',
            required: true,
            rows: 1,
           rule: /^[1-9a-zA-Z][0-9a-zA-Z]{6,8}$/
        },
    ], 
    // 个人信息
    'personal-info': [], 
    // 人员管理
    'personnel': {
        left: [
            {
                label: '姓名',
                fieldName: 'name',
                required: true,
                rows: 1,
                rule: /^[1-9a-zA-Z一-龥]{2,10}$/,
            },
            {
                label: '工号',
                fieldName: 'code',
                required: true,
                rows: 1,
                rule: /^[1-9][0-9]{3}$/,
            },
            {
                label: '所属部门',
                fieldName: 'departmentId',
                required: true,
                options: [],
                from: {
                    table: 'department',
                    label: 'name',
                    value: 'id',
                }
            },
            {
                label: '人员类别',
                fieldName: 'category',
                required: true,
                options: [
                    { label: '在编', value: '在编'},
                    { label: '合同', value: '合同'},
                    { label: '临时', value: '临时'},
                ]
            },
            {
                label: '性别',
                fieldName: 'gender',
                required: true,
                options: [
                    { label: '男', value: '男'},
                    { label: '女', value: '女'},
                ]
            },
            {
                label: '出生日期',
                fieldName: 'birthDate',
                required: false,
                date: true
            },
            {
                label: '照片',
                fieldName: 'identificationPhoto',
                required: false,
                upload: {}
            },
            
        ],
        right: [],
        bottom: []
    },
    // 报废方式
    'scrap-mode': [
        {
            label:'报废方式编号',
            fieldName: 'code',
            required:true,
            rows:1,
            rule: /^[1-9a-zA-Z][0-9a-zA-Z]{7}$/,
        },
        {
            label:'报废方式名称',
            fieldName:'name',
            required:true,
            rows:1,
            rule: /^[1-9a-zA-Z一-龥]{2,10}$/,
        },
        {
            label:'备注',
            fieldName:'remark',
            required:false,
            rows:6,
            rule:/^.{0,100}$/
        },
    ], 
    // 存放地点
    'storage-place': [
        {
            label: '存放地点名称',
            fieldName: 'name',
            required: true,
            rows: 1,
            rule: /^[a-zA-Z一-龥]{2,20}$/
        },
        {
            label: '备注',
            fieldName: 'remark',
            required: false,
            rows: 6,
            rule:/^.{0,100}$/
        },
    ], 
    // 供应商
    'supplier': [
        {
            label: '供应商名称',
            fieldName: 'name',
            required: true,
            rows: 1,
            rule: /^[1-9a-zA-Z一-龥]{2,40}$/
        },
        {
            label: '供应商类型',
            fieldName: 'type',
            required: true,
            options: [
                { label: '生产商', value: '生产商' },
                { label: '代理商', value: '代理商' },
                { label: '零售', value: '零售' },
                { label: '其他', value: '其他' },
            ]
        },
        {
            label: '联系人',
            fieldName: 'contactPerson',
            required: true,
            rows: 1,
            rule: /^[a-zA-Z一-龥]{2,20}$/
        },
        {
            label: '移动电话',
            fieldName: 'contactTel',
            required: true,
            rows: 1,
            rule: /^[0-9]{11}$/
        },
        {
            label: '地址',
            fieldName: 'address',
            required: false,
            rows: 1,
            rule: /^[1-9a-zA-Z一-龥]{2,30}$/
        },
        {
            label: '备注',
            fieldName: 'remark',
            required: false,
            rows: 6,
            rule:/^.{0,500}$/
        },
    ], 
    // 用户信息
    'user-info': []
}