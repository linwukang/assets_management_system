<template>
<!-- 详情视图 -->
<div class="details-view">
    <div v-for="block in form">
        <h2 class="title"><i>{{ block.icon }}</i>{{ block.title }}</h2>
        <div class="form" v-for="unit, key in block.form" :class="key">
            <el-form v-for="item in unit">
                <div v-if="item.type === 'text' 
                            || (item.textualization 
                                && item.textualization(dataContent, item.fieldName))">
                    <span class="label" :class="item.bold ? 'bold' : ''">{{ item.label }}</span>
                    <span :class="item.bold ? 'bold' : ''">
                        {{ 
                            item.value 
                                ? item.value(dataContent, item.fieldName) 
                                : dataContent[item.fieldName]
                        }}
                    </span>
                </div>
                <div v-else-if="item.type === 'date'">
                    <span 
                        class="label" 
                        :class="item.required ? 'required' : ''">
                        {{ item.label }}
                    </span>
                    <el-date-picker v-model="submitContent[item.fieldName]">

                    </el-date-picker>
                </div>
                <div v-else-if="item.type === 'input'">
                    <span 
                        class="label" 
                        :class="item.required ? 'required' : ''">
                        {{ item.label }}
                    </span>
                    <el-input
                        :type="item.rows && item.rows > 1 ? 'textarea' : ''"
                        v-model="submitContent[item.fieldName]"
                        :rows="item.rows">

                    </el-input>
                </div>
            </el-form>
        </div>
    </div>
    <!-- {{ submitContent }} -->
    <div class="bottom" v-if="showBottom()">
        <el-button @click="save" type="primary">保存</el-button>
        <el-button @click="cancel">取消</el-button>
    </div>
</div>
</template>

    
<script>
import Service from '../Service'
import Details from '../data/Details'
import Data from '../data/Data'
import { EventBus } from '../main'

export default {
    data() {
        return {
            dataContent: {},
            submitContent: {},
            form: [],
            id: undefined,
            tableName: undefined,
        };
    },
    methods: {
        save() {

        },
        cancel() {
            this.$router.go(-1)
        },
        showBottom() {
            if (Object.keys(this.submitContent).length == 0) return false;
            else {
                let flag = false
                for (let i in this.form) {
                    for (let j in this.form[i].form) {
                        for (let k in this.form[i].form[j]) {
                            let item = this.form[i].form[j][k]
                            if (item.textualization && !item.textualization(this.dataContent)) {
                                flag = true
                            }
                        }
                    }
                }
                return flag
            }
        }
    },
    computed: {
        
    },
    beforeMount() {
        this.id = this.$route.params.id;
        this.tableName = this.$route.params.view;
        this.form = Details[this.tableName];
        let fieldNames = [];
        for (let i in this.form) {
            for (let j in this.form[i].form) {
                for (let k in this.form[i].form[j]) {
                    let item = this.form[i].form[j][k]
                    fieldNames.push(item.fieldName)
                    switch (item.type) {
                        case "input":
                        case "date":
                        case "textarea":
                            this.$set(
                                this.submitContent, 
                                item.fieldName, 
                                null)
                    }
                }
            }
        }
        // console.log(fieldNames)
        // console.log(this.tableName)
        // console.log(this.id)
        Service.joinById(
            this.tableName, 
            this.id, 
            fieldNames, 
            [], 
        (data) => {
                this.dataContent = data;
                for (let k in this.submitContent) {
                    this.submitContent[k] = this.dataContent[k]
                }
            }, 
            (ok, message) => {
            if (ok) {
                console.log("ok");
            }
            else {
                console.log(message);
            }
        });
    },
    mounted() { },
    computed: {},
    props: [],
    components: {  }
}
</script>

    
<style lang="less">
.details-view {
    @line-height: 30px;
    line-height: @line-height;
    .title {
        display: block;
        clear: both;
    }
    .left, .right {
        float: left;
        width: 50%;
    }
    .form {
        .bold {
            font-weight: bold;
        }
        .label {
            display: inline-block;
            min-width: 100px;
            text-align: right;
            margin-right: 20px;
            color: #515050;
        }
        .required::before {
            display: inline-block;
            content: "*";
            line-height: @line-height;
            height: @line-height;
            color: red;
        }
    }
    >.bottom {
        clear: both;
    }
}
</style>
