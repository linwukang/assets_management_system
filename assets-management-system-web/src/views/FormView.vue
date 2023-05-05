<template>
<div class="form-view">
    <div slot="title" class="title">{{ title }}</div>
    <el-form ref="form" label-width="100px">
        <div :class="index" v-for="(part, index) in formContent" :key="index">
            <div v-for="item in part" :class="item.required ? 'required' : ''">
                <el-form-item :label="item.label">
                    <!-- {{ item.fieldName }} -->
                    <!-- 下拉选择框 -->
                    <el-select 
                        v-model="dataContent[item.fieldName]" 
                        style="width: 100%;"
                        v-if="item.options">
                        <el-option 
                            :label="option.label" 
                            :value="option.value" 
                            style="width: 100%"
                            v-for="option in item.options">
                        </el-option>
                    </el-select>
                    <!-- 日期选择 -->
                    <el-date-picker 
                        type="date" 
                        placeholder="选择日期" 
                        v-model="dataContent[item.fieldName]" 
                        style="width: 100%;"
                        v-else-if="item.date">
                    </el-date-picker>
                    <!-- 文件上传 -->
                    <el-upload
                        drag
                        action="item.upload.action"
                        multiple
                        style="width: 100%;"
                        v-else-if="item.upload">
                        <i class="el-icon-upload"></i>
                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                    </el-upload>
                    <!-- 普通文本 -->
                    <el-input
                        :readonly="true"
                        :class="'text'"
                        v-model="dataContent[item.fieldName]"
                        v-else-if="item.text">

                    </el-input>
                    <!-- 单行输入框 -->
                    <el-input 
                        v-model="dataContent[item.fieldName]" 
                        v-else-if="item.rows == 1">
                    </el-input>
                    <!-- 多行输入框 -->
                    <el-input type="textarea" 
                        v-model="dataContent[item.fieldName]" 
                        :rows="item.rows" v-else>
                    </el-input>
                </el-form-item>
            </div>
        </div>
    </el-form>
    <div slot="footer" class="footer">
        <!-- <div class="tip" :style="{ color: tip.color }">{{ tip.message }}</div> -->
        <div class="buttons">
            <el-button class="save" @click="save()">保存</el-button>
            <el-button class="cancel" @click="cancel()">取消</el-button>
        </div>
        <!-- {{ dataContent }} -->
    </div>
</div>
</template>

<script>
import Data from '../data/Data'
import FormContent from '../data/FormContent'
import { EventBus } from '../main'
import Service from '../Service'
export default {
    data() {
        return {
            formContent: {},
            dataContent: {},
            fieldNames: [],
            show: true,
            title: ''
        }
    },
    methods: {
        save() {
            if (this.check()) {
                this.onSave(this.dataContent, (ok, message) => {
                    if (ok) {
                        this.tip("保存成功", 'success')
                        EventBus.$emit('updateDataList')
                        this.cancel()
                    }
                    else {
                        this.tip(message, 'error')
                    }
                })
            } else {}
        },
        cancel() {
            this.onCancel()
        },
        check() {
            for (var i in this.content) {
                for (var item of this.content[i]) {
                // this.tip.message = item;
                if (item.required && (!this.dataContent[item.fieldName])) {
                    this.tip(item.label + "必填，请重新输入。", 'error')
                    return false
                }
                let rule = item.rule;
                if (rule && this.dataContent[item.fieldName] && !this.dataContent[item.fieldName].match(rule)) {
                    this.tip(item.label + "输入有误，请重新输入。", "error")
                    return false
                }
            }
            }
            
            return true
        },
        tip(message, type) {
            this.$message({
                title: '',
                message: message,
                type: type,
                duration: 2000
            })
        },
        getData() {
            for (let i in this.formContent) {
                for (let j in this.formContent[i]) {
                    this.formContent[i][j].fieldName
                    if (this.formContent[i][j].from && this.formContent[i][j].options.length == 0) {
                        let from = this.formContent[i][j].from
                        if (from.conditions) {
                            Service.conditionList(
                                from.table, 
                                from.conditions,
                                (data) => {
                                    for (let k in data) {
                                        this.formContent[i][j].options.push({
                                            label: data[k][from.label],
                                            value: data[k][from.value],
                                        })
                                    }
                                },
                                () => {})
                        }
                        else {
                            Service.list(
                                from.table, 
                                (data) => {
                                    for (let k in data) {
                                        this.formContent[i][j].options.push({
                                            label: data[k][from.label],
                                            value: data[k][from.value],
                                        })
                                    }
                                },
                                () => {})
                        }
                    }
                }
            }
        }
    },
    computed: {},
    mounted() {
        this.formContent = FormContent[this.$route.params.view]
        this.fieldNames = []
        for (let i in this.formContent) {
            for (let j in this.formContent[i]) {
                if (this.formContent[i][j].fieldName != null 
                    && this.formContent[i][j].fieldName != undefined
                    && this.formContent[i][j].fieldName != '') {
                    this.fieldNames.push(this.formContent[i][j].fieldName)
                }
            }
        }
        console.log("fieldNames", this.fieldNames);
        if (this.$route.params.id) {
            Service.joinById(
                Data[this.$route.params.view].url, 
                this.$route.params.id, 
                this.fieldNames, 
                [], 
                (data) => { this.dataContent = data }, 
                (ok, message) => {})
            console.log(this.dataContent)
        }
        
        this.getData()
    },
    props: [
        // "title",
        // "content",
        "onSave",
        "onCancel",
        // "checkRule",
        // "dataLine",
    ],
}
</script>

<style lang="less">
.form-view {
    background-color: white;
    
    @line-height: 30px;

    .required .el-form-item__label::before {
        display: inline-block;
        content: "*";
        line-height: @line-height;
        height: @line-height;
        color: red;
    }

    .left, .right {
        float: left;
        // overflow: hidden;
        padding: 30px 50px;
        position: relative;
        width: 30%;
    }
    .left::after, .right::after {
        content: "";
        display: table;
        height: 0;
        clear:both;
        visibility: hidden;
    }

    .bottom::before {
        content: "";
        display: table;
        height: 0;
        clear:both;
        visibility: hidden;
    }

    .footer {
        overflow: hidden;
        padding: 10px 20px;
        .save {
            background-color: #326ab0;
            color: white;
            margin-right: 15px;
        }
        .cancel {
            background-color: #dbdbdb;
        }
    }
}
</style>
