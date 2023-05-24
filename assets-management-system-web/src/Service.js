import Vue from "vue"
import Code from "./data/Code"
import Data from "./data/Data"
import store from "./store/store"

// 获取所有数据
let list = function (tableName, get, ok) {
    if (!ok) { ok = () => { } }

    let url = tableName
    Vue.prototype.$http
        .get(url)
        .then((result) => {
            if (result.data.code === Code.QUERY_ALL_OK) {
                // 查询成功
                get(result.data.data)
                ok(true)
            }
            else {
                ok(false, result.data.message)
            }
        }).catch((error) => {
            ok(false, error)
        })
}

let listByIdList = function (tableName, idList, get, ok) {
    if (!ok) { ok = () => { } }

    let url = tableName + '/id-list'
    Vue.prototype.$http
        .post(url, { idList: idList })
        .then((result) => {
            if (result.data.code === Code.QUERY_OK) {
                // 查询成功
                get(result.data.data)
                ok(true)
            }
            else {
                ok(false, result.data.message)
            }
        }).catch((error) => {
            ok(false, error)
        })
}

// 获取所有符合筛选条件的数据
let conditionList = function (tableName, conditions, get, ok) {
    let fieldNames = Object.keys(Data[tableName])
    console.log("conditions", conditions);
    joinList(
        tableName, 
        fieldNames,
        conditions, 
        get, 
        ok)
}

// 获取表中数据的行数
let count = function (tableName, get, ok) {
    if (!ok) { ok = () => { } }

    let url = `${tableName}/count`
    Vue.prototype.$http
        .post(url)
        .then((result) => {
            if (result.data.code === Code.QUERY_OK) {
                // 查询成功
                get(result.data.data)
                ok(true)
            }
            else {
                ok(false, result.data.message)
            }
        }).catch((error) => {
            ok(false, error)
        })
}

// 获取表中经过筛选的数据的行数
let conditionCount = function (tableName, conditions, get, ok) {
    if (!ok) { ok = () => { } }

    let url = `${tableName}/count`
    Vue.prototype.$http
        .post(url, { conditions })
        .then((result) => {
            if (result.data.code === Code.QUERY_OK) {
                // 查询成功
                get(result.data.data)
                ok(true)
            }
            else {
                ok(false, result.data.message)
            }
        }).catch((error) => {
            ok(false, error)
        })
}

let joinById = function (tableName, id, dataStruct, conditions, get, ok) {
    if (!ok) { ok = () => { } }

    let url = `${tableName}/join/${id}`
    Vue.prototype.$http
        .post(url, { dataStruct, conditions })
        .then((result) => {
            if (result.data.code === Code.QUERY_OK) {
                // 查询成功
                get(result.data.data)
                ok(true)
            }
            else {
                ok(false, result.data.message)
            }
        }).catch((error) => {
            ok(false, error)
        })
}

let joinList = function (tableName, dataStruct, conditions, get, ok) {
    if (!ok) { ok = () => { } }

    let url = `${tableName}/join`
    Vue.prototype.$http
        .post(url, { dataStruct, conditions })
        .then((result) => {
            if (result.data.code === Code.QUERY_ALL_OK) {
                // 查询成功
                get(result.data.data)
                ok(true)
            }
            else {
                ok(false, result.data.message)
            }
        }).catch((error) => {
            ok(false, error)
        })
}

let joinListByIdList = function (tableName, dataStruct, idList, get, ok) {
    if (!ok) { ok = () => { } }

    let url = tableName + '/join/id-list'
    Vue.prototype.$http
        .post(url, { idList: idList, dataStruct: dataStruct })
        .then((result) => {
            if (result.data.code === Code.QUERY_OK) {
                // 查询成功
                get(result.data.data)
                ok(true)
            }
            else {
                ok(false, result.data.message)
            }
        }).catch((error) => {
            ok(false, error)
        })
}

let joinPage = function (tableName, dataStruct, conditions, page, size, get, ok) {
    if (!ok) { ok = () => { } }

    let url = `${tableName}/join/page/${page}/${size}`
    Vue.prototype.$http
        .post(url, { dataStruct, conditions })
        .then((result) => {
            if (result.data.code === Code.QUERY_PAGE_OK) {
                // 查询成功
                get(result.data.data)
                ok(true)
            }
            else {
                ok(false, result.data.message)
            }
        }).catch((error) => {
            ok(false, error)
        })
}

// 获取一页数据
let page = function (tableName, page, size, get, ok) {
    if (!ok) { ok = () => { } }

    let url = `${tableName}/page/${page}/${size}`
    Vue.prototype.$http
        .get(url)
        .then((result) => {
            if (result.data.code === Code.QUERY_PAGE_OK) {
                // 查询成功
                get(result.data.data)
                ok(true)
            }
            else {
                ok(false, result.data.message)
            }
        }).catch((error) => {
            ok(false, error.message)
        })
}

// 通过id获取数据
let getById = function (tableName, id, get, ok) {
    if (!ok) { ok = () => { } }

    let url = `${tableName}/${id}`

    Vue.prototype.$http
        .get(url)
        .then((result) => {
            if (result.data.code === Code.QUERY_OK) {
                // 查询成功
                get(result.data.data)
                ok(true)
            }
            else {
                ok(false, result.data.message)
            }
        }).catch((error) => {
            ok(false, error.message)
        })
}

// 通过id获取字段值
let getFieldById = function (tableName, id, fieldName, get, ok) {
    if (!ok) { ok = () => { } }

    let url = `${tableName}/${id}/field/${fieldName}`


    Vue.prototype.$http
        .get(url)
        .then((result) => {
            if (result.data.code === Code.QUERY_OK) {
                get(result.data.data)
                ok(true)
            }
            else {
                ok(false, result.data.message)
            }
        }).catch((error) => {
            ok(false, error.message)
        })
}

// 新建数据
let create = function (tableName, data, ok) {
    if (!ok) { ok = () => { } }
    let url = tableName
    console.log("create", data);
    Vue.prototype.$http
        .post(url, data)
        .then((result) => {
            console.log(result)
            if (result.data.code === Code.CREATE_OK) {
                ok(true, result.data.data)
            }
            else {
                ok(false, result.data.message)
            }
        }).catch((error) => {
            ok(false, error.message)
        })
}

// 新建多个数据
let createMultiple = function (tableName, datalist, ok) {
    if (!ok) { ok = () => { } }
    let url = tableName + "/list"
    console.log("create multiple", datalist);
    Vue.prototype.$http
        .post(url, datalist)
        .then((result) => {
            console.log(result)
            if (result.data.code === Code.CREATE_OK) {
                ok(true, result.data.data)
            }
            else {
                ok(false, result.data.message)
            }
        }).catch((error) => {
            ok(false, error.message)
        })
}

// 保存数据
let save = function (tableName, data, ok) {
    if (!ok) { ok = () => { } }
    let url = tableName
    Vue.prototype.$http
        .put(url, data)
        .then((result) => {
            if (result.data.code === Code.UPDATE_OK) {
                ok(true, result.data.data)
            }
            else {
                ok(false, result.data.message)
            }
        }).catch((error) => {
            ok(false, error.message)
        })
}

// 保存数据
let saveBatch = function (tableName, data, ok) {
    if (!ok) { ok = () => { } }
    let url = tableName + '/list'
    Vue.prototype.$http
        .put(url, data)
        .then((result) => {
            if (result.data.code === Code.UPDATE_OK) {
                ok(true)
            }
            else {
                ok(false, result.data.message)
            }
        }).catch((error) => {
            ok(false, error.message)
        })
}

// 通过id更新某个字段的值
let updateById = function (tableName, id, fieldName, value, ok) {
    if (!ok) { ok = () => { } }
    let url = `${tableName}/${id}/${fieldName}/${value}`
    Vue.prototype.$http
        .put(url)
        .then((result) => {
            console.log(result)
            if (result.data.code === Code.UPDATE_OK) {
                ok(true)
            }
            else {
                ok(false, result.data.message)
            }
        }).catch((error) => {
            ok(false, error.message)
        })
}

// 通过id删除数据
let deleteById = function (tableName, id, ok) {
    if (!ok) { ok = () => { } }

    let url = `${tableName}/${id}`
    Vue.prototype.$http
        .delete(url)
        .then((result) => {
            console.log(result)
            if (result.data.code === Code.DELETE_OK) {
                ok(true)
            }
            else {
                ok(false, result.data.message)
            }
        }).catch((error) => {
            ok(false, error.message)
        })
}

let login = function (username, password, ok) {
    ok = ok ? ok : () => {}

    Vue.prototype.$http
        .get("/login", {
            params: {
                username,
                password
            }
        })
        .then((response) => {
            if (response.data.code === Code.LOGIN_SUCCEED) {
                // 将 token 保存到本地
                localStorage.setItem('token', response.data.data)
                // store.state.token = response.data.data
                ok(true)
                
            }
            else {
                ok(false, response.data.message)
            }
        })
        .catch((error) => {
            ok(false, error.message)
        })
}

let isLoggedIn = function (username, password, ok) {
    ok = ok ? ok : () => {}
}

// 结束盘点
let canCloseInventory = function (id, ok) {
    let url = '/assets-inventory/can-close/' + id
    Vue.prototype.$http
        .get(url)
        .then((result) => {
            console.log(result)
            if (result.data.code === Code.QUERY_OK) {
                ok(true)
            }
            else {
                ok(false, result.data.message)
            }
        }).catch((error) => {
            ok(false, error.message)
        })
}

// 获取个人信息
let getPersonalInfo = function (get, ok) {
    ok = ok ? ok : () => {}
    let url = '/user-info/personal-info'
    Vue.prototype.$http
        .get(url)
        .then((result) => {
            console.log(result)
            if (result.data.code === Code.QUERY_OK) {
                get(result.data.data)
                ok(true)
            }
            else {
                ok(false, result.data.message)
            }
        }).catch((error) => {
            ok(false, error.message)
        })
}

let getIdentity = function (get, ok) {
    ok = ok ? ok : () => {}
    let url = '/user-info/identity'
    Vue.prototype.$http
        .get(url)
        .then((result) => {
            console.log(result)
            if (result.data.code === Code.QUERY_OK) {
                get(result.data.data)
                ok(true)
            }
            else {
                ok(false, result.data.message)
            }
        }).catch((error) => {
            ok(false, error.message)
        })
}

// 上传文件
let uploadFile = function (file, action, get, ok) {
    ok = ok ? ok : () => {}
    let url = action

    const formData = new FormData()
    formData.append('file', file)
    
    // 设置请求头的 enctype 属性为 multipart/form-data
    const config = {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    }

    Vue.prototype.$http
        .post(url, formData, config)
        .then((result) => {
            if (result.data.code === Code.UPLOAD_OK) {
                get(result.data.data)
                ok(true)
            }
            else {
                ok(false, result.data.message)
            }
        }).catch((error) => {
            ok(false, error.message)
        })
}

const Service = {
    list,
    listByIdList,
    conditionList,
    count,
    conditionCount,
    joinById,
    joinList,
    joinListByIdList,
    joinPage,
    page,
    getById,
    getFieldById,
    create,
    createMultiple,
    save,
    saveBatch,
    updateById,
    deleteById,
    login,

    canCloseInventory,
    getPersonalInfo,
    getIdentity,
    uploadFile,
}

export { 
    Service
}

export default Service