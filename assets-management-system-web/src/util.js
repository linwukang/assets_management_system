import Vue from "vue"

function hashStringSync(str) {
    const encoder = new TextEncoder();
    const data = encoder.encode(str);
    const buffer = crypto.subtle.digestSync('SHA-256', data);
    const hashArray = Array.from(new Uint8Array(buffer));
    const hashHex = hashArray.map(b => b.toString(16).padStart(2, '0')).join('');
    return hashHex;
}

class Cache {
    constructor(defaultValidTime) {
        this.defaultValidTime = defaultValidTime
        // this.data = {}
        Vue.set(this, 'data', {})
    }
    set(key, value, validTime) {
        validTime = validTime || this.defaultValidTime
        // this.data[key] = {
        //     data: value,
        //     validTime: new Date().getTime() + validTime * 1000
        // }
        if (!this.has(key)) {
            Vue.set(this.data, key, {
                data: value,
                validTime: new Date().getTime() + validTime * 1000
            })
        }
        else {
            this.data[key] = {
                data: value,
                validTime: new Date().getTime() + validTime * 1000
            }
        }
    }
    get(key) {
        if (this.has(key) && this.isValid(key)) {
            return this.data[key].data
        }
        return undefined
    }
    has(key) {
        return key in this.data && this.isValid(key)
    }
    isValid(key) {
        var time = new Date().getTime()
        return time < this.data[key].validTime // 缓存有效期
    }
    clear() {
        this.data = {}
    }
    remove(key) {
        delete this.data[key]
    }
}
export {
    Cache
}


export default {
    http: {
        get(getData, url, then_after) {
            console.log(url)
            Vue.prototype.$http
                .get(url)
                .then((result) => {
                    console.log(result)
                    getData(result.data.data)
                    if (then_after) {
                        then_after()
                    }
                }).catch((err) => {
                    console.log(err)
                })
        },
        put(data, url, ok, then_after) {
            console.log(url)
            Vue.prototype.$http
                .put(url, data)
                .then((result) => {
                    console.log(result)
                    // vc.dataSource = result.data.data
                    if (result.data.code === 1100400) {
                        ok(true)
                    }
                    else {
                        ok(false, result.data.message)
                    }

                    if (then_after) {
                        then_after()
                    }
                }).catch((err) => {
                    console.log(err)
                })
        },
    },
    // 格式化日期 yyyy-MM-dd
    DateFormat(date) {
        if (date === null || date === undefined || date === "") {
            return ""
        }
        let b = new Date(date)
        return b.getFullYear() + "-" +
            (b.getMonth() + 1) + "-" +
            b.getDate()
    },
    // 格式化日期 yyyy-MM-dd hh:mm:ss
    DateTimeFormat(date) {
        if (date === undefined || date === null || date === '') return ''
        let b = new Date(date)
        return b.getFullYear() + "-" +
            (b.getMonth() + 1) + "-" +
            b.getDate() + ' ' +
            b.getHours() + ':' +
            b.getMinutes() + ':' +
            b.getSeconds()
    },
    // 动态创建 vue 组件实例
    createComponent(Component, props) {
        // new Vue({render() {}}),在render中把Component作为根组件
        const vm = new Vue({
            // h是createElement函数，它可以返回虚拟dom
            render(h) {
                // console.log(h(Component,{ props }))

                // 将Component作为根组件渲染出来
                // h(标签名称或组件配置对象，传递属性、事件等，孩子元素)
                return h(Component, { props })
            }
        }).$mount() // 挂载是为了把虚拟dom变成真实dom
        // 手动追加至body
        // 挂载之后$el可以访问到真实dom
        document.body.appendChild(vm.$el)

        // console.log(vm.$children)

        // 实例
        const comp = vm.$children[0]

        // 淘汰机制
        comp.remove = () => {
            // 删除dom
            document.body.removeChild(vm.$el)

            // 销毁组件
            vm.$destroy()
        }

        // 返回Component组件实例
        return comp
    },
    // Cache() {
    //     let cache_stroe = {}
    //     return {
    //         get(key) {
    //             let value = cache_stroe[key]
    //             console.log(key)
    //             console.log(cache_stroe)
    //             console.log(value)
    //             if (!value) {
    //                 return undefined
    //             }
    //             else if (validTime > Date.now()) {
    //                 return value.value
    //             }
    //             else {
    //                 return undefined
    //             }
    //         },
    //         delete(key) {
    //             cache_stroe[key] = undefined
    //         },
    //         clear() {
    //             cache_stroe = {}
    //         },
    //         set(key, value, maxAge) {
    //             maxAge = maxAge ? maxAge : 3
    //             // console.log("key", key)
    //             // console.log("value", value)
    //             // console.log("maxAge", maxAge)
    //             cache_stroe[key] = {
    //                 value: value,
    //                 validTime: Date.now() + maxAge * 1000,
    //             }
    //         }
    //     }
    // }
}