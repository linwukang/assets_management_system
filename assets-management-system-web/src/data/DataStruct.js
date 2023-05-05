import Vue from "vue"
import util from "../util"
import Service from "../Service"

export default {
	// 资产借还
	'assets-borrow': {
		"orderNumber": {
			name: "序号",
			width: '130px',
			value(dataSource, index) {
				dataSource[index].orderNumber = Number.parseInt(index) + 1
			}
		},
		"code": {
			name: "借用单号",
			width: '300px',
		},
		"assetId": {
			name: "资产名称",
			width: '300px',
			value(dataSource, index) {
				Service.getById(
					"assets-storage", 
					dataSource[index].assetId, 
					(data) => { dataSource[index].assetId = data.name },
					() => {})
			}

		},
		"borrowDate": {
			name: "借用时间",
			width: '300px',
			value(dataSource, index) {
				dataSource[index].borrowDate = util.DateFormat(dataSource[index].borrowDate)
			}
		},
		"borrowerId": {
			name: "借用人",
			width: '200px',
			value(dataSource, index) {
				Service.getById(
					"personnel", 
					dataSource[index].borrowerId, 
					(data) => { dataSource[index].borrowerId = data.name },
					() => {})
			}
		},
		"borrowReason": {
			name: "借用原因",
			width: '200px'
		},
		"returnState": {
			name: "归还状态",
			width: '200px',
			value(dataSource, index) {
				if (dataSource[index].returnState === "returned") {
					dataSource[index].returnState = "已归还"
				}
				else {
					dataSource[index].returnState = "未归还"
				}
			}
		},
		"returnDate": {
			name: "归还日期",
			width: '200px',
			value(dataSource, index) {
				dataSource[index].returnDate = util.DateFormat(dataSource[index].returnDate)
			}
		},
	},
	// 资产类别
	'assets-class': {
		"orderNumber": {
			name: "序号",
			width: '130px',
			value(dataSource, index) {
				dataSource[index].orderNumber = Number.parseInt(index) + 1
			}
		},
		"code": {
			name: "类别编码",
			width: '300px'
		},
		"name": {
			name: "设备类型名称",
			width: '200px'
		},
		"enabled": {
			name: "状态",
			width: '300px',
			value(dataSource, index) {
				if (dataSource[index].enabled) dataSource[index].enabled = '已启用'
				else dataSource[index].enabled = '已禁用'
			}
		},
		"createTime": {
			name: "创建日期",
			width: '300px',
			value(dataSource, index) {
				dataSource[index].createTime = util.DateTimeFormat(dataSource[index].createTime)
			}
		},
	},
	// 资产盘点
	'assets-inventory': {
		"orderNumber": {
			name: "序号",
			width: '130px',
			value(dataSource, index) {
				dataSource[index].orderNumber = Number.parseInt(index) + 1
			}
		},
			
		"code": {
			name: "盘点单编号",
			width: '300px'
		},
		"name": {
			name: "盘点名称",
			width: '200px'
		},
		"state": {
			name: "盘点单状态",
			width: '200px'
		},
		"endDate": {
			name: "盘点结束日期",
			width: '200px',
			value(dataSource, index) {
				dataSource[index].createTime = util.DateFormat(dataSource[index].createTime)
			}
		},
		"startDate": {
			name: "开始日期",
			width: '200px',
			value(dataSource, index) {
				dataSource[index].createTime = util.DateFormat(dataSource[index].createTime)
			}
		},
		"createTime": {
			name: "创建时间",
			width: '200px',
			value(dataSource, index) {
				dataSource[index].createTime = util.DateFormat(dataSource[index].createTime)
			}
		},
	},
	// 资产盘点表
	'assets-inventory-sheet': {
		"orderNumber": {
			name: "序号",
			width: '130px',
			value(dataSource, index) {
				dataSource[index].orderNumber = Number.parseInt(index) + 1
			}
		},
		"id": {
			name: "id",
			width: '300px'
		},
		"assetsInventoryId": {
			name: "盘点单编号",
			width: '200px'
		},
		"assetsId": {
			name: "资产编号",
			width: '300px',
		},
	},
	// 资产维修
	'assets-maintain': {
		"orderNumber": {
			name: "序号",
			width: '130px',
			value(dataSource, index) {
				dataSource[index].orderNumber = Number.parseInt(index) + 1
			}
		},
		"code": {
			name: "维修单号",
			width: '300px'
		},
		"assetId": {/* 资产 id */},
		"assetCode": {
			name: "资产编码",
			width: '300px',
			value(dataSource, index) {
				Service.getById(
					"assets-storage", 
					dataSource[index].assetId, 
					(asset) => { 
						dataSource[index].assetCode = asset.code 
						dataSource[index].name = asset.name
					},
					() => {})
			}
		},
		"name": {
			name: "资产名称",
			width: '300px',
			// value(dataSource, index) {
			// 	Service.getById(
			// 		"assets-storage", 
			// 		dataSource[index].assetId, 
			// 		(data) => { dataSource[index].name = data.name },
			// 		() => {})
			// }
		},
		"reportMaintainDate": {
			name: "报修日期",
			width: '200px',
			value(dataSource, index) {
				dataSource[index].reportMaintainDate = util.DateFormat(dataSource[index].reportMaintainDate)
			}
		},
		"reportMaintainPersonnelId": {
			name: "报修人",
			width: '300px',
			value(dataSource, index) {
				Service.getById(
					"personnel", 
					dataSource[index].reportMaintainPersonnelId, 
					(data) => { dataSource[index].reportMaintainPersonnelId = data.name + `(${data.code})` },
					() => {})
			}
		},
		"reportMaintainReason": {
			name: "报修原因",
			width: '300px',
		},
		"maintainUnit": {
			name: "维修单位",
			width: '300px',
		},
		"repairDate": {
			name: "修复日期",
			width: '300px',
			value(dataSource, index) {
				dataSource[index].repairDate = util.DateFormat(dataSource[index].repairDate)
			}
		},
		"maintainCharge": {
			name: "维修费用",
			width: '300px',
		},
		// "faultDescription": {
		// 	name: "故障说明",
		// 	width: '300px',
		// },
	},
	// 资产申购
	'assets-purchase-requisition': {
		"orderNumber": {
			name: "序号",
			width: '130px',
			value(dataSource, index) {
				dataSource[index].orderNumber = Number.parseInt(index) + 1
			}
		},
		"code": {
			name: "申购单号",
			width: '300px'
		},
		"name": {
			name: "申购资产名",
			width: '200px',
		},
		"assetClassId": {
			name: "资产类别",
			width: '300px',
			value(dataSource, index) {
				Service.getById(
					"assets-class", 
					dataSource[index].assetClassId, 
					(data) => { dataSource[index].assetClassId = data.name },
					() => {})
			}
		},
		"proposerId": {
			name: "申请人",
			width: '300px',
			value(dataSource, index) {
				Service.getById(
					"personnel", 
					dataSource[index].proposerId, 
					(data) => { dataSource[index].proposerId = data.name },
					() => {})
			}
		},
		"number": {
			name: "申购数量",
			width: '300px',
		},
		"anticipatedPrice": {
			name: "预计价格",
			width: '300px',
		},
		"proposerState": {
			name: "申请状态",
			width: '300px',
			value(dataSource, index) {
			}
		}
	},
	"equipment-use": {
		"equipmentUseId": {
			name: "设备用途编号",
			width: '300px',
		},
		"assetPhotograph": {
			name: "资产照片",
			width: '300px',
		},
		"suggest": {
			name: "申购建议",
			width: '300px',
		},
		"purchaseDate": {
			name: "申购日期",
			width: '300px',
			value(dataSource, index) {
				dataSource[index].orderNumber = Number.parseInt(index) + 1
			}
		},
		"reason": {
			name: "申购理由",
			width: '300px',
		},
	},
	// 资产报废
	'assets-scrap': {
		"orderNumber": {
			name: "序号",
			width: '130px',
			value(dataSource, index) {
				dataSource[index].orderNumber = Number.parseInt(index) + 1
			}
		},
		"assetId": {
			name: "资产编号",
			width: '300px'
		},
		"proposerId": {
			name: "申请人编号",
			width: '200px'
		},
		"scrapModeId": {
			name: "报废方式编号",
			width: '200px'
		},
		"scrapDate": {
			name: "报废日期",
			width: '200px',
			value(dataSource, index) {
				dataSource[index].orderNumber = Number.parseInt(index) + 1
			}
		},
		"scrapReason": {
			name: "报废原因",
			width: '200px'
		},
	},
	// 资产入库
	'assets-storage': {
		"orderNumber": {
			name: "序号",
			width: '130px',
			value(dataSource, index) {
				dataSource[index].orderNumber = Number.parseInt(index) + 1
			}
		},
		"code": {
			name: "资产编码",
			width: '300px'
		},
		"name": {
			name: "资产名称",
			width: '200px'
		},
		"assetClassId": {
			name: "资产类别",
			width: '200px',
			value(dataSource, index) {
				Service.getFieldById(
					"assets-class", 
					dataSource[index].assetClassId, 
					"name",
					(assets_class_name) => { dataSource[index].assetClassId = assets_class_name },
					)
			}
		},
		"supplierId": {
			name: "供应商",
			width: '200px',
			value(dataSource, index) {
				Service.getFieldById(
					"supplier", 
					dataSource[index].supplierId, 
					"name",
					(supplier_name) => { dataSource[index].supplierId = supplier_name }
				)
			}
		},
		"brandId": {
			name: "品牌",
			width: '200px',
			value(dataSource, index) {
				Service.getFieldById(
					"brand", 
					dataSource[index].brandId, 
					"name",
					(brand_name) => { dataSource[index].brandId = brand_name }
				)
			}
		},
		"gainingMethodId": {
			name: "取得方式",
			width: '200px',
			value(dataSource, index) {
				Service.getFieldById(
					"gaining-method", 
					dataSource[index].gainingMethodId, 
					"name",
					(gaining_method_name) => { dataSource[index].gainingMethodId = gaining_method_name },
				)
			}
		},
		"storageTime": {
			name: "入库日期",
			width: '300px',
			value(dataSource, index) {
				dataSource[index].storageTime = util.DateFormat(dataSource[index].storageTime)
			}
		},
		"storagePlaceId": {
			name: "存放地点",
			width: '200px',
			value(dataSource, index) {
				Service.getFieldById(
					"storage-place", 
					dataSource[index].storagePlaceId, 
					"name",
					(storage_place_name) => { dataSource[index].storagePlaceId = storage_place_name },
				)
			}
		},
		"assetState": {
			name: "资产状态",
			width: '200px',
			value(dataSource, index) {
				
			}
		},
	},
	// 资产转移
	'assets-transfer': {
		"orderNumber": {
			name: "序号",
			width: '130px',
			value(dataSource, index) {
				dataSource[index].orderNumber = Number.parseInt(index) + 1
			}
		},
		"code": {
			name: "转移单号",
			width: '300px'
		},
		"assetsBorrowId": {/*资产借还 id*/},
		"assetCode": {
			name: "资产编码",
			width: '300px',
			value(dataSource, index) {
				// 获取借用资产的 Id
				Service.getFieldById(
					"assets-borrow",
					dataSource[index].assetsBorrowId,
					"assetId",
					(asset_id) => {
						// 获取借用资产的编码
						Service.getFieldById(
							"assets-storage", 
							asset_id, 
							"code",
							code => Vue.set(dataSource[index], "assetCode", code),
						)
					}
				)

				
			}
		},
		"name": {
			name: "资产名称",
			width: '300px',
			value(dataSource, index) {
				// 获取借用资产的 Id
				Service.getFieldById(
					"assets-borrow",
					dataSource[index].assetsBorrowId,
					"assetId",
					(asset_id) => {
						// 获取借用资产的名称
						Service.getFieldById(
							"assets-storage", 
							asset_id, 
							"name",
							asset_name => Vue.set(dataSource[index], "name", asset_name)
						)
					}
				)
			}
		},
		"assetsClassName": {
			name: "资产类别",
			width: '200px',
			value(dataSource, index) {
				// 获取借用资产的 Id
				Service.getFieldById(
					"assets-borrow",
					dataSource[index].assetsBorrowId,
					"assetId",
					(asset_id) => {
						// 获取借用资产的类别 Id
						Service.getFieldById(
							"assets-class", 
							asset_id, 
							"id",
							(asset_id) => {
								// 获取借用资产的类别名称
								Service.getFieldById(
									"assets-class",
									asset_id,
									"name",
									asset_name => Vue.set(dataSource[index], "assetsClassName", asset_name)
								)
							})
					}
				)
			}
		},
		"borrowerName": {
			name: "原借用人",
			width: '200px', 
			value(dataSource, index) {
				Service.getFieldById(
					"assets-borrow",
					dataSource[index].assetsBorrowId,
					"borrowerId",
					(borrower_id) => {
						Service.getFieldById(
							"personnel", 
							borrower_id, 
							"name",
							(borrower_name) => { 
								Vue.set(dataSource[index], "borrowerName", borrower_name)
							})
					}
				)
			}
		},
		"newBorrowerId": {
			name: "新借用人",
			width: '200px',
			value(dataSource, index) {
				Service.getFieldById(
					"personnel", 
					dataSource[index].newBorrowerId, 
					"name",
					(new_borrower_name) => { 
						dataSource[index].newBorrowerId = new_borrower_name
					})
			}
		},
		"transferDate": {
			name: "转移日期",
			width: '300px',
			value(dataSource, index) {
				dataSource[index].transferDate = util.DateFormat(dataSource[index].transferDate)
			}
		},
		"transferReason": {
			name: "转移原因",
			width: '300px',
		}
	},
	// 品牌
	'brand': {
		"orderNumber": {
			name: "序号",
			width: '130px',
			value(dataSource, index) {
				dataSource[index].orderNumber = Number.parseInt(index) + 1
			}
		},
		"name": {
			name: "品牌名称",
			width: '150px'
		},
		"description": {
			name: "品牌说明",
			width: '150px'
		},
		"enabled": {
			name: "状态",
			width: '130px',
			value(dataSource, index) {
				if (dataSource[index].enabled) dataSource[index].enabled = '已启用'
				else dataSource[index].enabled = '已禁用'
			}
		},
		"createTime": {
			name: "创建日期",
			width: '300px',
			value(dataSource, index) {
				dataSource[index].createTime = util.DateTimeFormat(dataSource[index].createTime)
			}
		},
		"profile": {
			name: "部门简介",
			width: '300px'
		},
	},
	// 部门管理
	'department': {
		"orderNumber": {
			name: "序号",
			width: '130px',
			value(dataSource, index) {
				dataSource[index].orderNumber = Number.parseInt(index) + 1
			}
		},
		"code": {
			name: "部门编号",
			width: '300px'
		},
		"name": {
			name: "部门名称",
			width: '200px'
		},
		"createTime": {
			name: "创建日期",
			width: '300px',
			value(dataSource, index) {
				dataSource[index].createTime = util.DateFormat(dataSource[index].createTime)
			}
		},
		"profile": {
			name: "部门简介",
			width: '400px'
		},
	},
	// 设备用途
	'equipment-use': {
		"orderNumber": {
			name: "序号",
			width: '130px',
			value(dataSource, index) {
				dataSource[index].orderNumber = Number.parseInt(index) + 1
			}
		},
		"code": {
			name: "设备用途编号",
			width: '300px'
		},
		"name": {
			name: "设备用途名称",
			width: '200px'
		},
		"enabled": {
			name: "状态",
			width: '300px',
			value(dataSource, index) {
				if (dataSource[index].enabled) dataSource[index].enabled = '已启用'
				else dataSource[index].enabled = '已禁用'
			}
		},
		"remark": {
			name: "备注",
			width: '400px'
		},
		"createTime": {
			name: "创建日期",
			width: '300px',
			value(dataSource, index) {
				dataSource[index].createTime = util.DateTimeFormat(dataSource[index].createTime)
			}
		},
	},
	// 取得方式
	'gaining-method': {
		"orderNumber": {
			name: "序号",
			width: '130px',
			value(dataSource, index) {
				dataSource[index].orderNumber = Number.parseInt(index) + 1
			}
		},
		"code": {
			name: "取得方式编码",
			width: '300px'
		},
		"name": {
			name: "取得方式名称",
			width: '200px'
		},
		"enabled": {
			name: "状态",
			width: '300px',
			value(dataSource, index) {
				if (dataSource[index].enabled) dataSource[index].enabled = '已启用'
				else dataSource[index].enabled = '已禁用'
			}
		},
		"createTime": {
			name: "创建日期",
			width: '300px',
			value(dataSource, index) {
				dataSource[index].createTime = util.DateTimeFormat(dataSource[index].createTime)
			}
		},
	},
	// 个人信息
	'personal-info': {

		"account": {
			name: "账号",
			width: '300px'
		},
		"tel": {
			name: "手机号",
			width: '200px'
		},
		"loginLastTime": {
			name: "最后登录时间",
			width: '300px',
			value(dataSource, index) {
				dataSource[index].createTime = util.DateFormat(dataSource[index].createTime)
			}
		},
		"loginTime": {
			name: "登录次数",
			width: '300px',
		},
	},
	// 人员管理
	'personnel': {
		"orderNumber": {
			name: "序号",
			width: '130px',
			value(dataSource, index) {
				dataSource[index].orderNumber = Number.parseInt(index) + 1
			}
		},
		"name": {
			name: "姓名",
			width: '300px'
		},
		"code": {
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
			value(dataSource, index) {
				dataSource[index].birthDate = util.DateFormat(dataSource[index].birthDate)
			}
		},
		"departmentId": {
			name: "所属部门",
			width: '300px',
			value(dataSource, index) {
				// Vue.prototype.$http
				// 	.get(`/department/${dataSource[index].departmentId}`)
				// 	.then((result) => {
				// 		dataSource[index].departmentId = result.data.data.name
				// 	}).catch((err) => {
				// 		console.log(err)
				// 	})
				Service.getById(
					"department", 
					dataSource[index].departmentId, 
					(data) => { dataSource[index].departmentId = data.name },
					() => {})
			}
		},
		"category": {
			name: "人员类别",
			width: '400px',
			value(dataSource, index) {

			}
		},
	},
	// 报废方式
	'scrap-mode': {
		"orderNumber": {
			name: "序号",
			width: '130px',
			value(dataSource, index) {
				dataSource[index].orderNumber = Number.parseInt(index) + 1
			}
		},
		"code": {
			name: "报废方式编号",
			width: '300px'
		},
		"name": {
			name: "报废方式名称",
			width: '200px'
		},
		"enabled": {
			name: "状态",
			width: '200px',
			value(dataSource, index) {
				if (dataSource[index].enabled) dataSource[index].enabled = '已启用'
				else dataSource[index].enabled = '已禁用'
			}
		},
		"remark": {
			name: "备注",
			width: '200px'
		}
	},
	// 存放地点
	'storage-place': {
		"orderNumber": {
			name: "序号",
			width: '130px',
			value(dataSource, index) {
				dataSource[index].orderNumber = Number.parseInt(index) + 1
			}
		},
		"name": {
			name: "存放地点名称",
			width: '200px'
		},
		"enabled": {
			name: "状态",
			width: '300px',
			value(dataSource, index) {
				if (dataSource[index].enabled) dataSource[index].enabled = '已启用'
				else dataSource[index].enabled = '已禁用'
			}
		},
		"remark": {
			name: "备注",
			width: '300px'
		},
		"createTime": {
			name: "创建时间",
			width: '300px',
			value(dataSource, index) {
				dataSource[index].createTime = util.DateTimeFormat(dataSource[index].createTime)
			}
		},
	},
	// 供应商
	'supplier': {
		"orderNumber": {
			name: "序号",
			width: '130px',
			value(dataSource, index) {
				dataSource[index].orderNumber = Number.parseInt(index) + 1
			}
		},
		"name": {
			name: "供应商名称",
			width: '200px'
		},
		"type": {
			name: "类型",
			width: '300px',
		},
		"enabled": {
			name: "状态",
			width: '300px',
			value(dataSource, index) {
				if (dataSource[index].enabled) dataSource[index].enabled = '已启用'
				else dataSource[index].enabled = '已禁用'
			}
		},
		"contactPerson": {
			name: "联系人",
			width: '300px',
		},
		"contactTel": {
			name: "移动电话",
			width: '300px',
		},
		"createTime": {
			name: "创建时间",
			width: '300px',
			value(dataSource, index) {
				dataSource[index].createTime = util.DateTimeFormat(dataSource[index].createTime)
			}
		},
	},
	// 用户登录信息
	'user-info': {
		"username": {
			name: "用户名",
			width: '300px'
		},
		"password": {
			name: "密码",
			width: '200px'
		},
		"identity": {
			name: "身份",
			width: '300px',
		},
	},
}
