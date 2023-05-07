import FormDialog from "../components/FormDialog.vue"
import util from "../util"
import FormStruct from "./FormStruct"
import router from "../router"
import Service from "../Service"

export default {
    // 资产借还
    'assets-borrow': null, 
    // 资产类别
    'assets-class': (line) => {
        let edit = {
            click: function () {
                util.createComponent(FormDialog, {
                    visible: true,
                    title: "修改资产类别",
                    content: FormStruct["assets-class"],
                    dataLine: line,
                    onSave: (data, ok) => { 
                        util.http.put(
                            data,
                            'assets-class',
                            ok
                        )
                    },
                    onCancel: () => { },
                })
            },
        }
        let enableOrDisable = {
            popconfirm: {
                confirm: (line.enabled) ? "禁用" : "启用",
                cancel: "取消",
                title: "修改",
            },
            click(ok) {
                Service.updateById(
                    "assets-class", 
                    line.id, 
                    "enabled", 
                    (line.enabled) ? 0 : 1, 
                    ok)
            }
        }
        if (line.enabled) {
            return {
                edit,
                disable: enableOrDisable
            }
        }
        else {
            return {
                edit,
                enable: enableOrDisable
            }
        }
    }, 
    // 资产信息维护
	'assets-information-maintenance': (line) => {
        return {
            edit: {
                click: () => {
                    router.push({
                        path: `assets-information-maintenance/edit/${line.id}`
                    })
                 },
            }
        }
    },
    // 资产盘点
    'assets-inventory': (line) => {
        let edit = {
            click: () => {
                router.push({
                    path: `assets-inventory/edit/${line.id}`
                })
            }
        }

        let deleteLine = {
            click: () => {
                Service.deleteById("assets-inventory", line.id, () => {})
            }
        }

        let startInventory = {}
        let continueInventory = {}
        let closeInventory = {}

        switch (line.state) {
            case "not_started":     // 未开始
                return {
                    "edit": edit,
                    "delete": deleteLine,
                    "start-inventory": startInventory,
                }
            case "in_progress":     // 进行中
                return {
                    "continue-inventory": continueInventory,
                    "close-inventory": closeInventory
                }
            case "finished":        // 已结束
                return {}
            default:
                return
        }
    }, 
    'assets-inventory-sheet': null, 
    // 资产维修
    'assets-maintain': null, 
    // 资产申购
    'assets-purchase-requisition': (line) => {
        let edit = {
            click: () => {
                router.push({
                    path: `assets-purchase-requisition/edit/${line.id}`,
                })
            },
        }
        return {
            edit,
            submit: {
                click: () => {}
            },
            delete: {
                click: () => {}
            }
        }
    }, 
    // 资产报废
    'assets-scrap': null, 
    // 资产入库
    'assets-storage': null, 
    // 资产转移
    'assets-transfer': null, 
    // 品牌
    'brand': (line) => {
        let edit = {
            click() {
                util.createComponent(FormDialog, {
                    visible: true,
                    title: "修改报废方式",
                    content: FormStruct["brand"],
                    dataLine: line,
                    onSave: (data, ok) => { 
                        util.http.put(
                            data,
                            'brand',
                            ok
                        )
                    },
                    onCancel: () => { },
                })
            },
        }
        let enableOrDisable = {
            popconfirm: {
                confirm: (line.enabled) ? "禁用" : "启用",
                cancel: "取消",
                title: "修改",
            },
            click(ok) {
                Service.updateById(
                    "brand", 
                    line.id, 
                    "enabled", 
                    (line.enabled) ? 0 : 1, 
                    ok)
            }
        }
        if (line.enabled) {
            return {
                edit,
                disable: enableOrDisable
            }
        }
        else {
            return {
                edit,
                enable: enableOrDisable
            }
        }
    }, 
    // 部门管理
    'department': (line) => {
        return {
            edit: {
                click: function () {
                    util.createComponent(FormDialog, {
                        title: "修改部门",
                        content: FormStruct['department'],
                        dataLine: line,
                        onSave: (data, ok) => { 
                            util.http.put(
                                data,
                                'department',
                                ok
                            )
                        },
                        onCancel: () => { },
                    })
                },
            }
        }
    },
    // 设备用途
    'equipment-use': (line) => {
        let edit = {
            click: function () {
                util.createComponent(FormDialog, {
                    visible: true,
                    title: "修改设备用途",
                    content: FormStruct["equipment-use"],
                    dataLine: line,
                    onSave: (data, ok) => { 
                        // util.http.put(
                        //     data,
                        //     'equipment-use',
                        //     ok
                        // )
                        Service.save('equipment-use', data, ok)
                    },
                    onCancel: () => { },
                })
            },
        }
        let enableOrDisable = {
            popconfirm: {
                confirm: (line.enabled) ? "禁用" : "启用",
                cancel: "取消",
                title: "修改",
            },
            click(ok) {
                // util.http.put(
                //     {},
                //     `equipment-use/${line.id}/enabled/${(line.enabled) ? 0 : 1}`,
                //     ok
                // )
                Service.updateById(
                    "equipment-use", 
                    line.id, 
                    "enabled", 
                    (line.enabled) ? 0 : 1,
                    ok)
            }
        }
        if (line.enabled) {
            return {
                edit,
                disable: enableOrDisable
            }
        }
        else {
            return {
                edit,
                enable: enableOrDisable
            }
        }
    }, 
    // 取得方式
    'gaining-method': (line) => {
        let edit = {
            click() {
                util.createComponent(FormDialog, {
                    visible: true,
                    title: "修改报废方式",
                    content: FormStruct["gaining-method"],
                    dataLine: line,
                    onSave: (data, ok) => { 
                        util.http.put(
                            data,
                            'gaining-method',
                            ok
                        )
                    },
                    onCancel: () => { },
                })
            },
        }
        let enableOrDisable = {
            popconfirm: {
                confirm: (line.enabled) ? "禁用" : "启用",
                cancel: "取消",
                title: "修改",
            },
            click(ok) {
                Service.updateById(
                    "gaining-method", 
                    line.id, 
                    "enabled", 
                    (line.enabled) ? 0 : 1, 
                    ok)
            }
        }
        if (line.enabled) {
            return {
                edit,
                disable: enableOrDisable
            }
        }
        else {
            return {
                edit,
                enable: enableOrDisable
            }
        }
    }, 
    // 
    'personal-info': null, 
    // 人员管理
    'personnel': (line) => {
        return {
            edit: {
                click: () => {
                    router.push({
                        path: `personnel/edit/${line.id}`
                    })
                 },
            }, 
            delete: {
                click: function () { },
            }
        }
    },
    // 报废方式
    'scrap-mode': (line) => {
        let edit = {
            click() {
                util.createComponent(FormDialog, {
                    visible: true,
                    title: "修改报废方式",
                    content: FormStruct["scrap-mode"],
                    dataLine: line,
                    onSave: (data, ok) => { 
                        util.http.put(
                            data,
                            'scrap-mode',
                            ok
                        )
                    },
                    onCancel: () => { },
                })
            },
        }
        let enableOrDisable = {
            popconfirm: {
                confirm: (line.enabled) ? "禁用" : "启用",
                cancel: "取消",
                title: "修改",
            },
            click(ok) {
                Service.updateById(
                    "scrap-mode", 
                    line.id, 
                    "enabled", 
                    (line.enabled) ? 0 : 1, 
                    ok)
            }
        }
        if (line.enabled) {
            return {
                edit,
                disable: enableOrDisable
            }
        }
        else {
            return {
                edit,
                enable: enableOrDisable
            }
        }
    }, 
    // 存放地点
    'storage-place': (line) => {
        let edit = {
            click() {
                util.createComponent(FormDialog, {
                    visible: true,
                    title: "修改存放地点",
                    content: FormStruct["storage-place"],
                    dataLine: line,
                    onSave: (data, ok) => { 
                        util.http.put(
                            data,
                            'storage-place',
                            ok
                        )
                    },
                    onCancel: () => { },
                })
            },
        }
        let enableOrDisable = {
            popconfirm: {
                confirm: (line.enabled) ? "禁用" : "启用",
                cancel: "取消",
                title: "修改",
            },
            click(ok) {
                Service.updateById(
                    "storage-place", 
                    line.id, 
                    "enabled", 
                    (line.enabled) ? 0 : 1, 
                    ok)
            }
        }
        if (line.enabled) {
            return {
                edit,
                disable: enableOrDisable
            }
        }
        else {
            return {
                edit,
                enable: enableOrDisable
            }
        }
    }, 
    // 供应商
    'supplier': (line) => {
        let edit = {
            click: function () {
                util.createComponent(FormDialog, {
                    visible: true,
                    title: "修改供应商",
                    content: FormStruct["supplier"],
                    dataLine: line,
                    onSave: (data, ok) => { 
                        util.http.put(
                            data,
                            'supplier',
                            ok
                        )
                    },
                    onCancel: () => { },
                    checkRule: {
                        // '部门名称': /^[0-9a-zA-Z一-龥]{3,20}$/,
                        // '部门编码': /^[1-9a-zA-Z][0-9a-zA-Z]{9}$/,
                    }
                })
            },
        }
        let enableOrDisable = {
            popconfirm: {
                confirm: (line.enabled) ? "禁用" : "启用",
                cancel: "取消",
                title: "修改",
            },
            click(ok) {
                Service.updateById(
                    "supplier", 
                    line.id, 
                    "enabled", 
                    (line.enabled) ? 0 : 1, 
                    ok)
            }
        }
        if (line.enabled) {
            return {
                edit,
                disable: enableOrDisable
            }
        }
        else {
            return {
                edit,
                enable: enableOrDisable
            }
        }
    }, 
    'user-info': null
}