<template>
<el-dialog
    :visible.sync="show"
    :close-on-click-modal="false"
    :before-close="cancel"
    >
    <div slot="title" class="title">{{ title }}</div>
    <el-form ref="form" label-width="100px">
        <div v-for="item in content" :class="item.required ? 'required' : ''">
                <el-form-item :label="item.label">
                    <el-select v-model="data_line[item.fieldName]" v-if="item.options">
                        <el-option 
                            :label="option.label" 
                            :value="option.value" 
                            v-for="option in item.options">
                        </el-option>
                    </el-select>
                    <el-input v-model="data_line[item.fieldName]" v-else-if="item.rows == 1"></el-input>
                    <el-input type="textarea" v-model="data_line[item.fieldName]" :rows="item.rows" v-else></el-input>
                </el-form-item>
        </div>
    </el-form>
    <div slot="footer" class="footer">
        <div class="buttons">
            <el-button class="save" @click="save()">保存</el-button>
            <el-button class="cancel" @click="cancel()">取消</el-button>
        </div>
    </div>
</el-dialog>

</template>

<script>
import { EventBus } from '../main'
export default {
    data() {
        return {
            dataSource: {},
            data_line: {},
            show: true,
        };
    },
    methods: {
        save() {
            if (this.check()) {
                this.onSave(this.data_line, (okay, message) => {
                    if (okay) {
                        this.tip("保存成功", "success")
                        EventBus.$emit('updateDataList')
                        this.cancel()
                    }
                    else {
                        this.tip(message, "error")
                    }
                })
            }
            else {
            }
        },
        cancel() {
            this.onCancel()
            this.show = false
            this.$destroy(true);
            this.$el.parentNode.removeChild(this.$el);
        },
        check() {
            for (var item of this.content) {
                if (item.required && (!this.data_line[item.fieldName])) {
                    this.tip(item.label + "必填，请重新输入。", 'error')
                    return false
                }
                let rule = item.rule
                if (rule && this.data_line[item.fieldName] && !this.data_line[item.fieldName].match(rule)) {
                    this.tip(item.label + "输入有误，请重新输入。", "error")
                    return false
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
        }
    },
    computed: {},
    mounted() {
        this.data_line = {...this.dataLine}

        for (let fieldName in this.dataLine) {
            this.$set(this.data_line, fieldName, this.dataLine[fieldName])
        }

        console.log(this.data_line);
    },
    props: [
        "title", 
        "content", 
        "onSave", 
        "onCancel", 
        "checkRule",
        "dataStruct",
        "dataLine"
    ],
};
</script>

<style lang="less">
.form-dialog {
    border: 1px solid #bbe0ff;
    color: rgb(95, 95, 95);
    background-color: white;
    width: 800px;

    .title {
        background-color: #ecf5ff;
        padding: 15px 20px;
        border-bottom: 1px solid #bbe0ff;
    }

    

    @line-height: 30px;

    form {
        border-bottom: 1px solid #bbe0ff;
        padding: 10px 100px;

        .required .el-form-item__label::before {
            display: inline-block;
            content: "*";
            line-height: @line-height;
            height: @line-height;
            color: red;
        }

        .text {
            vertical-align: top;
            text-align: right;
            line-height: @line-height;
        }

        .input {
            vertical-align: top;
            text-align: left;

            select,
            input,
            textarea {
                width: 100%;
                border: 1px solid #bbe0ff;
            }

            select:focus,
            input:focus,
            textarea:focus {
                outline: 1px solid #bbe0ff;
            }

            select {
                height: @line-height;
            }

            input {
                line-height: @line-height;
                height: @line-height;
            }

            textarea {
                resize: none;
            }
        }
    }

    .footer {
        overflow: hidden;
        background-color: #faf9fc;
        padding: 10px 20px;

        .tip {
            float: left;
        }

        .buttons {
            float: right;

            .save,
            .cancel {
                width: 50px;
                height: 32px;
                border: none;
            }

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
}
</style>
