import Vue from "vue"
import Code from "./data/Code"

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

// 获取所有符合筛选条件的数据数据
let conditionList = function (tableName, conditions, get, ok) {
    if (!ok) { ok = () => { } }

    let url = tableName
    Vue.prototype.$http
        .get(url, { params: { conditions: conditions } })
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
                ok(true)
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
    if (!ok) {
        ok = () => { }
    }
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
                ok("登陆成功", 'success')
                // 将 token 保存到本地
                localStorage.setItem('token', response.data.data)
            }
            else {
                ok(response.data.message, 'error')
            }
        })
        .catch((error) => {
            ok(false, error.message)
        })
}

let isLoggedIn = function (username, password, ok) {
    ok = ok ? ok : () => {}
}

const Service = {
    list,
    conditionList,
    count,
    conditionCount,
    joinById,
    joinList,
    joinPage,
    page,
    getById,
    getFieldById,
    create,
    save,
    updateById,
    deleteById,
    login
}

export { Service }
export default Service