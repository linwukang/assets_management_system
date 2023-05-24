<template>
<!-- 详情视图 -->
<div class="details-view">
    <div v-for="block in form" class="form-block">
        <h2 class="title">
            <span class="title-text">
                <font-awesome-icon :icon="block.icon" />
                {{ block.title }}
            </span>
        </h2>
        <div class="form-block-content" v-for="unit, key in block.form" :class="key">
            <el-form v-for="item in unit" class="form-line">
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
                <div v-else-if="item.type === 'pass'">
                    <div class="pass"> </div>
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
            // for (var fieldName in this.submitContent) {
            //     Service.updateById(
            //         this.$route.params.view,
            //         this.$route.params.id,
            //         fieldName,
            //         this.submitContent[fieldName],
            //         (okay, message) => {
            //             if (okay) {
            //                 this.tip('保存成功', 'success')
            //             } else {
            //                 this.tip(message, 'error')
            //             }
            //         }
            //     )
            // }
            Service.save(
                Data[this.$route.params.view].url,
                this.submitContent,
                (okay, message) => {
                    if (okay) {
                        this.tip("保存成功", 'success')
                    }
                    else {
                        this.tip(message, 'error')
                    }
                }
            )
        },
        cancel() {
            this.$router.go(-1)
        },
        tip(message, type) {
            this.$message({
                    title: '',
                    message: message,
                    type: type,
                    duration: 2000
                })
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
                    if (item.fieldName !== undefined
                        && item.fieldName !== null
                        && item.fieldName !== '') {
                        fieldNames.push(item.fieldName)
                    }
                    // switch (item.type) {
                    //     case "input":
                    //     case "date":
                    //     case "textarea":
                    //         this.$set(
                    //             this.submitContent, 
                    //             item.fieldName, 
                    //             null)
                    // }
                }
            }
        }

        // for (let fieldName in Data[this.$route.params.view].struct) {
        //     this.$set(
        //         this.submitContent, 
        //         fieldName, 
        //         null)
        // }
        Service.getById(
            Data[this.$route.params.view].url, 
            this.$route.params.id,
            (data) => {
                this.submitContent = data
            })

        console.log(fieldNames)
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

    
<style lang="less" scope>
.details-view {
    @line-height: 30px;
    line-height: @line-height;
    .form-block {
        .title {
            display: block;
            clear: both;
            margin: 10px 10px;
            padding: 0 0 10px 0;
            border-bottom: 2px solid #dcdcdc;
            .title-text{
                padding: 0 0 9px 0;
                border-bottom:3px solid #d0b58f ;
            }
        }
        .left, .right {
            float: left;
            width: 50%;
        }
        .form-line {
            margin-top: 7px;
            >div {
                display: flex;
                >.bold {
                    font-weight: bold;
                }
                >.label {
                    display: block;
                    float: left;
                    min-width: 100px;
                    text-align: right;
                    margin-right: 20px;
                    color: #a3a0a0;
                }
                >.required::before {
                    display: inline-block;
                    content: "*";
                    line-height: @line-height;
                    height: @line-height;
                    color: red;
                }
                >.pass {
                    display: block;
                    content: ' ';
                    height: @line-height;
                }
            }
        }
    }
    >.bottom {
        clear: both;
    }
}
</style>
