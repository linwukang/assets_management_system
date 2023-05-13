<template>
<el-dialog :visible.sync="show" :close-on-click-modal="false" :before-close="cancel" :class="'close-inventory-dialog'">
    <div slot="title" class="title">结束盘点</div>
    <el-form ref="form" label-width="120px">
        <div :class="'required'">
            <el-form-item label="盘点结束日期">
                <el-date-picker
                    v-model="endDate"
                    type="date" 
                    placeholder="选择日期">
                </el-date-picker>
                <p :style="{color: 'red'}">结束盘点之后将不能再修改盘点结果，请慎重操作。</p>
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
import Service from '../../Service';
import {
    EventBus
} from '../../main'
export default {
    data() {
        return {
            show: true,
            endDate: new Date(),
        };
    },
    methods: {
        save() {
            if (this.check()) {
                let obj = {
                    id: this.id,
                    state: 'finished',
                    endDate: this.endDate
                }
                Service.save(
                    'assets-inventory', 
                    obj,
                    (okay, message) => {
                        if (okay) {
                            this.cancel()
                            EventBus.$emit('updateDataList', [])
                        } else {
                            this.tip(message, 'error')
                        }
                    }
                )
            } else {}
        },
        cancel() {
            this.show = false
            this.$destroy(true);
            this.$el.parentNode.removeChild(this.$el);
        },
        check() {
            if (this.endDate === undefined ||
                this.endDate === null ||
                this.endDate === '') {
                this.tip("盘点结束日期必填，请重新输入。", 'error')
                return false
            }
            else if (this.endDate.getTime() > new Date().getTime()) {
                this.tip("盘点日期输入有误，请重新输入。", 'error')
                return false
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
    },
    props: [
        "id"
    ],
};
</script>

    
<style lang="less">
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
