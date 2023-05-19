import Vue from "vue"
import util from "../util"

let Data = {
    // 资产借还
	'assets-borrow': {
		idFieldName: "id",
		entityClassName: "AssetsBorrow",
		url: 'assets-borrow',
		name: '资产借还',
        struct: {
			"id": {},
            "code": {},
            "assetId": {},
            "borrowDate": {},
            "borrowerId": {},
            "borrowReason": {},
            "returnState": {},
            "returnDate": {},
        },
        display: {
            "orderNumber": {
                name: "序号",
                width: 100,
                value(_dataSource, index) {
                    return Number.parseInt(index) + 1
                }
            },
            "code": {
				to(dataSource, index) {
					return `assets-borrow/details/${dataSource[index].id}`
				},
                name: "借用单号",
                width: 400,
            },
            "AssetsStorage(assetId).code": {
                name: "资产编码",
                width: 200,
            },
			"AssetsStorage(assetId).name": {
                name: "资产名称",
                width: 200,
            },
            "borrowDate": {
                name: "借用日期",
                width: 200,
                value(dataSource, index) {
                    return util.DateFormat(dataSource[index].borrowDate)
                }
            },
			"Personnel(borrowerId).code": {},
            "Personnel(borrowerId).name": {
                name: "借用人",
                width: 200,
				value(dataSource, index) {
					let name = dataSource[index]["Personnel(borrowerId).name"]
					let code = dataSource[index]["Personnel(borrowerId).code"]
					return `${name}(${code})`
				}
            },
            "borrowReason": {
                name: "借用原因",
                width: 300
            },
            "returnDate": {
				name: "归还日期",
                width: 200,
                value(dataSource, index) {
					return util.DateFormat(dataSource[index].returnDate)
                }
            },
			"returnState": {
				name: "归还状态",
				width: 150,
				value(dataSource, index) {
					return dataSource[index].returnState === "returned"
						? "已归还"
						: "未归还"
				}
			},
        },
    },
	// 资产类别
	'assets-class': {
		idFieldName: "id",
		entityClassName: "AssetsClass",
		url: 'assets-class',
		name: '资产类别',
		struct: {
			"id": {},
			"code": {},
			"name": {},
			"enabled": {},
			"createTime": {},
		},
		display: {
			"orderNumber": {
				name: "序号",
				width: 150,
				value(_dataSource, index) {
					return Number.parseInt(index) + 1
				}
			},
			"code": {
				name: "类别编码",
				width: 200
			},
			"name": {
				name: "设备类型名称",
				width: 250
			},
			"enabled": {
				name: "状态",
				width: 150,
				value(dataSource, index) {
					return dataSource[index].enabled
						? "已启用"
						: "已禁用"
				}
			},
			"createTime": {
				name: "创建日期",
				width: 400,
				value(dataSource, index) {
					return util.DateTimeFormat(dataSource[index].createTime)
				}
			},
		}
		
	},
	// 资产信息维护
	'assets-information-maintenance': {
		idFieldName: "id",
		entityClassName: "AssetsStorage",
		url: 'assets-storage',
		name: '资产信息维护',
		struct: {
			"id": {},
			"code": {},
			"name": {},
			"assetClassId": {},
			"supplierId": {},
			"brandId": {},
			"gainingMethodId": {},
			"storageDate": {},
			"storagePlaceId": {},
			"assetPhotograph": {},
			"state": {}
		},
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
			"Supplier(supplierId).name": {
				name: "供应商",
				width: 300,
			},
			"Brand(brandId).name": {
				name: "品牌",
				width: 200,
			},
			"GainingMethod(gainingMethodId).name": {
				name: "取得方式",
				width: 200,
			},
			"storageDate": {
				name: "入库日期",
				width: 200,
				value(dataSource, index) {
					return util.DateFormat(dataSource[index].storageDate)
				}
			},
			"StoragePlace(storagePlaceId).name": {
				name: "存放地点",
				width: 200,
			},
			"state": {
				name: "资产状态",
				width: 200,
			},
		}
	},
	// 资产盘点
	'assets-inventory': {
		idFieldName: "id",
		entityClassName: "AssetsInventory",
		url: 'assets-inventory',
		name: '资产盘点',
		struct: {
			"id": {},
			"code": {},
			"name": {},
			"description": {},
			"state": {},
			"endDate": {},
			"startDate": {},
			"createTime": {},
		},
		display: {
			"orderNumber": {
				name: "序号",
				width: 100,
				value(_dataSource, index) {
					return Number.parseInt(index) + 1
				}
			},
			"code": {
				to(dataSource, index) {
					return `assets-inventory/result/${dataSource[index].id}`
				},
				name: "盘点单编号",
				width: 300
			},
			"name": {
				name: "盘点名称",
				width: 300
			},
			"state": {
				name: "盘点单状态",
				width: 200,
				value(dataSource, index) {
					if (dataSource[index].state === "not_started") {
						return "未开始"
					}
					switch (dataSource[index].state) {
						case "not_started":
							return "未开始"
						case "in_progress":
							return "进行中"
						case "finished":
							return "已结束"
						default:
							return dataSource[index].state
					}
				}
			},
			"startDate": {
				name: "盘点开始日期",
				width: 250,
				value(dataSource, index) {
					return util.DateFormat(dataSource[index].startDate)
				}
			},
			"endDate": {
				name: "盘点结束日期",
				width: 250,
				value(dataSource, index) {
					return util.DateFormat(dataSource[index].endDate)
				}
			},
			"createTime": {
				name: "创建时间",
				width: 250,
				value(dataSource, index) {
					return util.DateFormat(dataSource[index].createTime)
				}
			},
		}
		
	},
	// 资产盘点表
	'assets-inventory-sheet': {
		idFieldName: "id",
		entityClassName: "AssetsInventorySheet",
		url: 'assets-inventory-sheet',
		struct: {
			"id": {},
			"assetsInventoryId": {},
			"assetId": {},
			"result": {},
			"remark": {},
			"isInventoried": {},
		},
		display: {
		}
	},
	// 资产维修
	'assets-maintain': {
		idFieldName: "id",
		entityClassName: "AssetsMaintain",
		url: 'assets-maintain',
		name: '资产维修',
		struct: {
			"id": {},
			"code": {},
			"assetId": {},
			"assetCode": {},
			"name": {},
			"reportMaintainDate": {},
			"reportMaintainPersonnelId": {},
			"reportMaintainReason": {},
			"maintainUnit": {},
			"repairDate": {},
			"maintainCharge": {},
			"faultDescription": {},
		},
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
					return `assets-maintain/details/${dataSource[index].id}`
				},
				name: "维修单号",
				width: 350
			},
			"AssetsStorage(assetId).code": {
				name: "资产编码",
				width: 150,
			},
			"AssetsStorage(assetId).name": {
				name: "资产名称",
				width: 150,
			},
			"AssetsClass(AssetsStorage(assetId).assetClassId).name": {
				name: "资产类别",
				width: 150,
			},
			"reportMaintainDate": {
				name: "报修日期",
				width: 200,
				value(dataSource, index) {
					return util.DateFormat(dataSource[index].reportMaintainDate)
				}
			},
			"Personnel(reportMaintainPersonnelId).name": {
				name: "报修人",
				width: 200,
			},
			"reportMaintainReason": {
				name: "报修原因",
				width: 200,
			},
			"maintainUnit": {
				name: "维修单位",
				width: 150,
			},
			"repairDate": {
				name: "修复日期",
				width: 200,
				value(dataSource, index) {
					return util.DateFormat(dataSource[index].repairDate)
				}
			},
			"maintainCharge": {
				name: "维修费用",
				width: 200,
			},
		}
	},
	// 资产申购
	'assets-purchase-requisition': {
		idFieldName: "id",
		entityClassName: "AssetsPurchaseRequisition",
		url: 'assets-purchase-requisition',
		name: '资产申购',
		struct: {
			"id": {},
			"code": {},
			"assetName": {},
			"assetClassId": {},
			"proposerId": {},
			"number": {},
			"anticipatedPrice": {},
			"equipmentUseId": {},
			"assetPhotograph": {},
			"suggest": {},
			"purchaseRequisitionDate": {},
			"reason": {},
			"applicationStatus": {},
			"notApprovingReasons": {},
		},
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
					return `assets-purchase-requisition/details/${dataSource[index].id}`
				},
				name: "申购单号",
				width: 400
			},
			"assetName": {
				name: "申购资产名",
				width: 300,
			},
			"AssetsClass(assetClassId).name": {
				name: "资产类别",
				width: 300,
			},
			"number": {
				name: "申购数量",
				width: 100,
			},
			"anticipatedPrice": {
				name: "预计价格",
				width: 200,
			},
			"Personnel(proposerId).name": {
				name: "申请人",
				width: 150,
			},
			"purchaseRequisitionDate": {
				name: "申购日期",
				width: 150,
				value(dataSource, index) {
					return util.DateFormat(dataSource[index].purchaseRequisitionDate)
				}
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
			}
		}
	},
	// 资产报废
	'assets-scrap': {
		idFieldName: "id",
		entityClassName: "AssetsScrap",
		url: 'assets-scrap',
		name: '资产报废',
		struct: {
			"id": {},
			"assetId": {},
			"proposerId": {},
			"scrapModeId": {},
			"scrapDate": {},
			"scrapReason": {},
		},
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
		}
	},
	// 资产入库
	'assets-storage': {
		idFieldName: "id",
		entityClassName: "AssetsStorage",
		url: 'assets-storage',
		name: '资产入库',
		struct: {
			"id": {},
			"code": {},
			"name": {},
			"assetClassId": {},
			"supplierId": {},
			"brandId": {},
			"gainingMethodId": {},
			"storageDate": {},
			"storagePlaceId": {},
			"assetPhotograph": {},
			"state": {}
		},
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
			"Supplier(supplierId).name": {
				name: "供应商",
				width: 300,
			},
			"Brand(brandId).name": {
				name: "品牌",
				width: 200,
			},
			"GainingMethod(gainingMethodId).name": {
				name: "取得方式",
				width: 200,
			},
			"storageDate": {
				name: "入库日期",
				width: 200,
				value(dataSource, index) {
					return util.DateFormat(dataSource[index].storageDate)
				}
			},
			"StoragePlace(storagePlaceId).name": {
				name: "存放地点",
				width: 200,
			},
			"state": {
				name: "资产状态",
				width: 200
			},
		}
	},
	// 资产转移
	'assets-transfer': {
		idFieldName: "id",
		entityClassName: "AssetsTransfer",
		url: 'assets-transfer',
		name: '资产转移',
		struct: {
			"id": {},
			"code": {},
			"assetsBorrowId": {},
			"newBorrowerId": {},
			"transferDate": {},
			"transferReason": {},
		},
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
					return `assets-transfer/details/${dataSource[index].id}`
				},
				name: "转移单号",
				width: 300
			},
			"AssetsStorage(AssetsBorrow(assetsBorrowId).assetId).code": {
				name: "资产编码",
				width: 200,
			},
			"AssetsStorage(AssetsBorrow(assetsBorrowId).assetId).name": {
				name: "资产名称",
				width: 200,
			},
			"AssetsClass(AssetsStorage(AssetsBorrow(assetsBorrowId).assetId).assetClassId).name": {
				name: "资产类别",
				width: 200,
			},
			"Personnel(AssetsBorrow(assetsBorrowId).borrowerId).code": {},
			"Personnel(AssetsBorrow(assetsBorrowId).borrowerId).name": {
				name: "原借用人",
				width: 300, 
				value(dataSource, index) {
					let name = dataSource[index]["Personnel(AssetsBorrow(assetsBorrowId).borrowerId).name"]
					let code = dataSource[index]["Personnel(AssetsBorrow(assetsBorrowId).borrowerId).code"]
					return `${name}(${code})`
				}
			},
			"Personnel(newBorrowerId).code": {},
			"Personnel(newBorrowerId).name": {
				name: "新借用人",
				width: 300,
				value(dataSource, index) {
					let name = dataSource[index]["Personnel(newBorrowerId).name"]
					let code = dataSource[index]["Personnel(newBorrowerId).code"]
					return `${name}(${code})`
				}
			},
			"transferDate": {
				name: "转移日期",
				width: 300,
				value(dataSource, index) {
					return util.DateFormat(dataSource[index].transferDate)
				}
			},
			"transferReason": {
				name: "转移原因",
				width: 400,
			}
		}
	},
	// 品牌
	'brand': {
		idFieldName: "id",
		entityClassName: "Brand",
		url: 'brand',
		name: '品牌',
		struct: {
			"id": {},
			"name": {},
			"description": {},
			"enabled": {},
			"createTime": {},
		},
		display: {
			"orderNumber": {
				name: "序号",
				width: 150,
				value(dataSource, index) {
					return Number.parseInt(index) + 1
				}
			},
			"name": {
				name: "品牌名称",
				width: 300
			},
			"description": {
				name: "品牌说明",
				width: 500
			},
			"enabled": {
				name: "状态",
				width: 150,
				value(dataSource, index) {
					return dataSource[index].enabled 
						? "已启用"
						: "已禁用"
				}
			},
			"createTime": {
				name: "创建日期",
				width: 350,
				value(dataSource, index) {
					return util.DateTimeFormat(dataSource[index].createTime)
				}
			},
		}
	},
	// 部门管理
	'department': {
		idFieldName: "id",
		entityClassName: "Department",
		url: 'department',
		name: '部门管理',
		struct: {
			"id": {},
			"code": {},
			"name": {},
			"profile": {},
			"createTime": {},
		},
		display: {
			"orderNumber": {
				name: "序号",
				width: 150,
				value(dataSource, index) {
					return Number.parseInt(index) + 1
				}
			},
			"code": {
				name: "部门编码",
				width: 350
			},
			"name": {
				name: "部门名称",
				width: 300
			},
			"createTime": {
				name: "创建日期",
				width: 300,
				value(dataSource, index) {
					return util.DateFormat(dataSource[index].createTime)
				}
			},
			"profile": {
				name: "部门简介",
				width: 500
			},
		}
	},
	// 设备用途
	'equipment-use': {
		idFieldName: "id",
		entityClassName: "EquipmentUse",
		url: 'equipment-use',
		name: '设备用途',
		struct: {
			"id": {},
			"code": {},
			"name": {},
			"enabled": {},
			"remark": {},
			"createTime": {},
		},
		display: {
			"orderNumber": {
				name: "序号",
				width: 150,
				value(dataSource, index) {
					return Number.parseInt(index) + 1
				}
			},
			"code": {
				name: "设备用途编号",
				width: 350
			},
			"name": {
				name: "设备用途名称",
				width: 350
			},
			"enabled": {
				name: "状态",
				width: 200,
				value(dataSource, index) {
					return dataSource[index].enabled
						? "已启用"
						: "已禁用"
				}
			},
			"remark": {
				name: "备注",
				width: 500
			},
			"createTime": {
				name: "创建日期",
				width: 350,
				value(dataSource, index) {
					return util.DateTimeFormat(dataSource[index].createTime)
				}
			},
		}
	},
	// 取得方式
	'gaining-method': {
		idFieldName: "id",
		entityClassName: "GainingMethod",
		url: 'gaining-method',
		name: '取得方式',
		struct: {
			"id": {},
			"code": {},
			"name": {},
			"enabled": {},
			"createTime": {},
		},
		display: {
			"orderNumber": {
				name: "序号",
				width: 150,
				value(dataSource, index) {
					return Number.parseInt(index) + 1
				}
			},
			"code": {
				name: "取得方式编码",
				width: 350
			},
			"name": {
				name: "取得方式名称",
				width: 350
			},
			"enabled": {
				name: "状态",
				width: 200,
				value(dataSource, index) {
					return dataSource[index].enabled
						? "已启用"
						: "已禁用"
				}
			},
			"createTime": {
				name: "创建日期",
				width: 500,
				value(dataSource, index) {
					return util.DateTimeFormat(dataSource[index].createTime)
				}
			},
		}
	},
	// 个人信息
	'personal-info': {
		idFieldName: "id",
		entityClassName: "PersonalInfo",
		url: 'personal-info',
		name: '个人信息',
		struct: {
			"id": {},
			"account": {},
			"tel": {},
			"loginLastTime": {},
			"loginTime": {},
		},
		display: {
			"account": {
				name: "账号",
				width: 100
			},
			"tel": {
				name: "手机号",
				width: 100
			},
			"loginLastTime": {
				name: "最后登录时间",
				width: 100,
				value(dataSource, index) {
					return util.DateFormat(dataSource[index].createTime)
				}
			},
			"loginTime": {
				name: "登录次数",
				width: 100,
			},
		}
	},
	// 人员管理
	'personnel': {
		idFieldName: "id",
		entityClassName: "Personnel",
		url: 'personnel',
		name: '人员管理',
		struct: {
			"id": {},
			"orderNumber": {},
			"name": {},
			"code": {},
			"gender": {},
			"birthDate": {},
			"departmentId": {},
			"category": {},
		},
		display: {
			"orderNumber": {
				name: "序号",
				width: 150,
				value(dataSource, index) {
					return Number.parseInt(index) + 1
				}
			},
			"name": {
				name: "姓名",
				width: 250
			},
			"code": {
				name: "工号",
				width: 200
			},
			"gender": {
				name: "性别",
				width: 200
			},
			"birthDate": {
				name: "出生日期",
				width: 300,
				value(dataSource, index) {
					return util.DateFormat(dataSource[index].birthDate)
				}
			},
			"Department(departmentId).name": {
				name: "所属部门",
				width: 250,
			},
			"category": {
				name: "人员类别",
				width: 250,
			},
		}
	},
	// 报废方式
	'scrap-mode': {
		idFieldName: "id",
		entityClassName: "ScrapMode",
		url: 'scrap-mode',
		name: '报废方式',
		struct: {
			"id": {},
			"code": {},
			"name": {},
			"enabled": {},
			"remark": {}
		},
		display: {
			"orderNumber": {
				name: "序号",
				width: 150,
				value(dataSource, index) {
					return Number.parseInt(index) + 1
				}
			},
			"code": {
				name: "报废方式编号",
				width: 300
			},
			"name": {
				name: "报废方式名称",
				width: 300
			},
			"enabled": {
				name: "状态",
				width: 150,
				value(dataSource, index) {
					return dataSource[index].enabled
						? "已启用"
						: "已禁用"
				}
			},
			"remark": {
				name: "备注",
				width: 500
			}
		}
	},
	// 存放地点
	'storage-place': {
		idFieldName: "id",
		entityClassName: "StoragePlace",
		url: 'storage-place',
		name: '存放地点',
		struct: {
			"id": {},
			"name": {},
			"enabled": {},
			"remark": {},
			"createTime": {},
		},
		display: {
			"orderNumber": {
				name: "序号",
				width: 150,
				value(dataSource, index) {
					return Number.parseInt(index) + 1
				}
			},
			"name": {
				name: "存放地点名称",
				width: 300
			},
			"enabled": {
				name: "状态",
				width: 150,
				value(dataSource, index) {
					return dataSource[index].enabled
						? "已启用"
						: "已禁用"
				}
			},
			"remark": {
				name: "备注",
				width: 500
			},
			"createTime": {
				name: "创建时间",
				width: 400,
				value(dataSource, index) {
					return util.DateTimeFormat(dataSource[index].createTime)
				}
			},
		}
	},
	// 供应商
	'supplier': {
		idFieldName: "id",
		entityClassName: "Supplier",
		url: 'supplier',
		name: '供应商',
		struct: {
			"id": {},
			"name": {},
			"type": {},
			"enabled": {},
			"contactPerson": {},
			"contactTel": {},
			"createTime": {},
		},
		display: {
			"orderNumber": {
				name: "序号",
				width: 150,
				value(dataSource, index) {
					return Number.parseInt(index) + 1
				}
			},
			"name": {
				to(dataSource, index) {
					return `supplier/details/${dataSource[index].id}`
				},
				name: "供应商名称",
				width: 350
			},
			"type": {
				name: "类型",
				width: 200,
			},
			"enabled": {
				name: "状态",
				width: 200,
				value(dataSource, index) {
					return dataSource[index].enabled
						? "已启用"
						: "已禁用"
				}
			},
			"contactPerson": {
				name: "联系人",
				width: 200,
			},
			"contactTel": {
				name: "移动电话",
				width: 250,
			},
			"createTime": {
				name: "创建时间",
				width: 300,
				value(dataSource, index) {
					return util.DateTimeFormat(dataSource[index].createTime)
				}
			},
		}
	},
	// 用户登录信息
	'user-info': {
		idFieldName: "username",
		entityClassName: "UserInfo",
		url: 'user-info',
		name: '用户登录信息',
		struct: {
			"username": {},
			"password": {},
			"identity": {},
		},
		display: {
			"username": {
				name: "用户名",
				width: 100
			},
			"password": {
				name: "密码",
				width: 100
			},
			"identity": {
				name: "身份",
				width: 100,
			},
		}
	},
}


export default Data