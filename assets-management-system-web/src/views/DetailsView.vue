<template>
<!-- 详情视图 -->
<div class="details-view">
    <div v-for="block in form">
        <h2 class="title"><i>{{ block.icon }}</i>{{ block.title }}</h2>
        <div class="form" v-for="unit, key in block.form" :class="key">
            <el-form  v-for="item in unit">
                <div v-if="item.type === 'text'">
                    <span>{{ item.label }}</span>&nbsp;&nbsp;&nbsp;
                    <span>{{ 
                        item.value 
                            ? item.value(dataContent, item.fieldName) 
                            : dataContent[item.fieldName]
                        }}
                    </span>
                </div>
            </el-form>
        </div>
    </div>
</div>
</template>

    
<script>
import Service from '../Service'
import Details from '../data/Details'

export default {
    data() {
        return {
            dataContent: {},
            form: [],
            id: undefined,
            tableName: undefined,
        }
    },
    methods: {
    },
    computed: {

    },
    beforeMount() {
        this.id = this.$route.params.id
        this.tableName = this.$route.params.view
        this.form = Details[this.tableName]

        let fieldNames = []
        for (let i in this.form) {
            for (let j in this.form[i].form) {
                for (let k in this.form[i].form[j]) {
                    fieldNames.push(this.form[i].form[j][k].fieldName)
                }
            }
        }
        console.log(fieldNames)
        console.log(this.tableName)
        console.log(this.id)

        Service.joinById(
            this.tableName, 
            this.id, 
            fieldNames, 
            [], 
            (data) => {
                this.dataContent = data
            }, 
            (ok, message) => {
                if (ok) {
                    console.log("ok")
                }
                else {
                    console.log(message)
                }
            })
    },
    mounted() {},
    computed: {},
    props: [],
}
</script>

    
<style lang="less">
.details-view {
    .left, .right {
        float: left;
        width: 50%;
    }
}
</style>
