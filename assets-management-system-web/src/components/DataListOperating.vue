<template>
<!-- 数据列表操作 -->
<div class="data-list-operating">
    <!-- 查询功能 -->
    <el-form :inline="true" :class="'left'">
        <span v-for="left, index in content.left" :key="index">
            <span v-for="item, index in left" :key="index">
            <!-- 文本查询 -->
                <el-form-item :inline="true" v-if="item.type === 'input'">
                    <el-input 
                        v-model="dataContent[item.fieldName]"
                        :placeholder="item.label"
                        :style="{'width': item.width + 'px'}">
                    </el-input>
                </el-form-item>
                <!-- 日期范围查询 -->
                <el-form-item :label="item.label" v-else-if="item.type === 'date'">
                    <el-date-picker 
                        v-model="dataContent[item.fieldName].start"
                        type="date" 
                        :placeholder="item.startLabel"
                        :style="{'width': item.width + 'px'}">
                    </el-date-picker>
                    <span>{{ item.joinText }}</span>
                    <el-date-picker 
                        v-model="dataContent[item.fieldName].end"
                        type="date" 
                        :placeholder="item.endLabel"
                        :style="{'width': item.width + 'px'}">
                    </el-date-picker>
                </el-form-item>
                <!-- 下拉列表 -->
                <el-form-item v-else-if="item.type === 'select'">
                    <el-select 
                        v-model="dataContent[item.fieldName]"
                        :placeholder="item.label"
                        :style="{'width': item.width + 'px'}"
                        clearable
                        filterable>
                        <el-option
                            v-for="option in formStruct[item.fieldName].options"
                            :key="option.value"
                            :label="option.label"
                            :value="option.value"
                            :placeholder="option.label">
                        </el-option>
                    </el-select>
                </el-form-item>
            </span>
            <br v-if="index != content.left.length - 1" />
        </span>
        <el-button 
            v-if="content.left.length != 0 && content.left[0].length != 0"
            @click="query"
            type="primary">
            查询
        </el-button>
    </el-form>
    <div class="right">
        <el-button 
            v-for="right, index in content.right" 
            @click="right.click()">
            {{ right.text }}
        </el-button>
    </div>
    <!-- {{ getConditions() }} -->
</div>
</template>

<script>
import { EventBus } from '../main'
import Service from '../Service'
export default {
    data() {
        return {
            dataContent: {/* fieldName: value */},
            formStruct: {/* fieldName: value */},
        }
    },
    methods: {
        query() {
            EventBus.$emit("updateDataList", this.getConditions())
        },
        getConditions() {
            let conditions = []
            for (var i in this.content.left) {
                for (var j in this.content.left[i]) {
                    let item = this.content.left[i][j]
                    if (this.dataContent[item.fieldName] !== null 
                        && this.dataContent[item.fieldName] !== undefined
                        && this.dataContent[item.fieldName] !== '') {
                        switch (item.type) {
                            case "input":
                                this.addCondition(
                                    conditions, 
                                    "like", 
                                    item.fieldName, 
                                    this.dataContent[item.fieldName]
                                )

                                break
                            case "select":
                                this.addCondition(
                                    conditions, 
                                    "eq", 
                                    item.fieldName, 
                                    this.dataContent[item.fieldName]
                                )
                                break
                            case "date":
                                if (this.dataContent[item.fieldName].start && this.dataContent[item.fieldName].end) {
                                    this.addCondition(
                                        conditions, 
                                        "between", 
                                        item.fieldName, 
                                        this.dataContent[item.fieldName].start,
                                        this.dataContent[item.fieldName].end
                                    )
                                }
                                else if (!this.dataContent[item.fieldName].start && this.dataContent[item.fieldName].end) {
                                    this.addCondition(
                                        conditions, 
                                        "le", 
                                        item.fieldName, 
                                        this.dataContent[item.fieldName].end
                                    )
                                }
                                else if (this.dataContent[item.fieldName].start && !this.dataContent[item.fieldName].end) {
                                    this.addCondition(
                                        conditions, 
                                        "ge", 
                                        item.fieldName, 
                                        this.dataContent[item.fieldName].start
                                    )
                                }
                                break
                            default:
                                break
                        }
                    }
                }
            }
            return conditions
        },
        getColumnNames(fieldNames) {
            if (!fieldNames) return []
            else return fieldNames.split(",").map(col => col.trim())
        },
        getCondition(operator, fieldName, value1, value2, value3) {
            let fieldNames = this.getColumnNames(fieldName)
            let condition = {}
            switch (operator) {
                case "like":
                    condition = {
                        operator,
                        values: [
                            fieldName, 
                            "%" + value1 + "%"
                        ]
                    }
                    break
                case "eq":
                case "ne":
                case "lt":
                case "le":
                case "gt":
                case "ge":
                    condition = {
                        operator,
                        values: [
                            fieldName, 
                            value1
                        ]
                    }
                    break
                case "between":
                    condition = {
                        operator,
                        values: [
                            fieldName, 
                            value1,
                            value2
                        ]
                    }
                    break
            }
            
            return condition
        },
        getConditionOr(operator, fieldNames, value1, value2, value3) {
            if (fieldNames.length == 1) {
                return this.getCondition(operator, fieldNames[0], value1, value2, value3)
            }
            else {
                return {
                    left: this.getCondition(operator, fieldNames[0], value1, value2, value3),
                    right: this.getConditionOr(operator, fieldNames.slice(1), value1, value2, value3)
                }
            }
        },
        addCondition(conditions, operator, fieldName, value1, value2, value3) {
            let fieldNames = this.getColumnNames(fieldName)
            if (fieldNames.length == 0) {
                return
            }
            else if (fieldNames.length == 1) {
                conditions.push(this.getCondition(operator, fieldName, value1, value2, value3))
            }
            else if (fieldNames.length > 1) {
                conditions.push(this.getConditionOr(operator, fieldNames, value1, value2, value3))
            }
            
        
        
        }
    },
    computed: {
        
    },
    beforeMount() {
// {operator: "like", values: ["code", "%72%"]},

        for (var i in this.content.left) {
            for (var j in this.content.left[i]) {
                let item = this.content.left[i][j]
                switch (item.type) {
                    case "input":
                        this.$set(this.dataContent, item.fieldName, null)
                        break
                    case "select":
                        this.$set(this.dataContent, item.fieldName, null)
                        break
                    case "date":
                        this.$set(this.dataContent, item.fieldName, {})
                        this.$set(this.dataContent[item.fieldName], "start", null)
                        this.$set(this.dataContent[item.fieldName], "end", null)
                        break
                    default:
                        break
                }

                this.$set(this.formStruct, item.fieldName, {})
                for (var key in item) {
                    this.$set(this.formStruct[item.fieldName], key, item[key])
                }



                if (this.content.left[i][j].from) {
                    let from = this.content.left[i][j].from
                    Service.list(
                        from.table, 
                        (data) => {
                            if (this.formStruct[item.fieldName].options.length == 0) {
                                for (let k in data) {
                                    this.formStruct[item.fieldName].options.push({
                                        label: data[k][from.label],
                                        value: data[k][from.value],
                                    })
                                }
                            }
                        },
                        () => {})
                }
            }
        }
        // console.log(this.formStruct);
    },
    mounted() {
    },
    computed: {},
    props: [
        "content"
    ],
}
</script>

<style lang="less" scope>
.data-list-operating {
    padding: 20px 0;
    .left {
        float: left;
    }

    .right {
        float: right;
    }
}
</style>
