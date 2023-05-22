<template>
<el-dialog :visible.sync="show" :close-on-click-modal="false" :before-close="cancel" :class="'enter-result-dialog'">
    <div slot="title" class="title">{{ title }}</div>
    <el-form ref="form" label-width="100px">
        <div v-if="!count">
            <el-form-item label="资产名称">
                {{ assetName }}
            </el-form-item>
            <el-form-item label="资产编码">
                {{ assetCode }}
            </el-form-item>
        </div>
        <div v-else>
            <el-form-item label="资产数量">
                {{ count }}
            </el-form-item>
        </div>
        <div :class="'required'">
            <el-form-item label="盘点结果">
                <el-select v-model="data_line.result">
                    <el-option :label="'正常'" :value="'正常'"></el-option>
                    <el-option :label="'异常'" :value="'异常'"></el-option>
                </el-select>
            </el-form-item>
        </div>
        <div>
            <el-form-item label="盘点备注">
                <el-input v-model="data_line.remark"></el-input>
                <p :style="{color: 'red'}">盘点结果异常时，盘点备注必填。</p>
            </el-form-item>
        </div>
    </el-form>
    <div slot="footer" class="footer">
        <div class="buttons">
            <el-button type="primary" class="save" @click="save()">保存</el-button>
            <el-button class="cancel" @click="cancel()">取消</el-button>
        </div>
    </div>
</el-dialog>
</template>

    
<script>
import {
    EventBus
} from '../../main'
export default {
    data() {
        return {
            dataSource: {},
            data_line: {
                result: this.result,
                remark: this.remark,
            },
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
                    } else {
                        this.tip(message, "error")
                    }
                })
            } else {}
        },
        cancel() {
            this.onCancel()
            this.show = false
            this.$destroy(true);
            this.$el.parentNode.removeChild(this.$el);
        },
        check() {
            if (this.data_line.result === undefined 
                || this.data_line.result === null
                || this.data_line.result === '') {
                this.tip("录入结果必填，请重新输入。", 'error')
                return false
            }
            if (this.data_line.result === '异常') {
                if (this.data_line.remark === undefined 
                    || this.data_line.remark === null
                    || this.data_line.remark === '') {
                    this.tip("盘点结果异常时，盘点备注必填。", 'error')
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
        this.data_line = {
            ...this.dataLine
        }

        for (let fieldName in this.dataLine) {
            this.$set(this.data_line, fieldName, this.dataLine[fieldName])
        }

        console.log(this.data_line);
    },
    props: [
        "title",
        "assetName",
        "assetCode",
        "result",
        "remark",
        "onSave",
        "onCancel",
        "count",
    ],
};
</script>

    
<style lang="less" scope>
.el-dialog {
    border: 1px solid #bbe0ff;
    color: rgb(95, 95, 95);
    background-color: white;
    width: 800px;
    .el-dialog__header, 
    .el-dialog__body, 
    .el-dialog__footer {
        padding: 0;
    }

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

        // .buttons {
        //     float: right;

        //     .save,
        //     .cancel {
        //         width: 50px;
        //         height: 32px;
        //         border: none;
        //     }

        //     .save {
        //         background-color: #326ab0;
        //         color: white;
        //         margin-right: 15px;
        //     }

        //     .cancel {
        //         background-color: #dbdbdb;
        //     }
        // }
    }
}
</style>
