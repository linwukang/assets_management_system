<template>
<!-- 数据操作 -->
<div class="data-operating" :style="{
      'flex-direction': vertical ? 'column' : 'row',
    }" v-if="true">

    <div v-for="btn, btn_type in bottons">
        <el-popconfirm 
            v-if="btn.popconfirm" 
            :confirm-button-text="btn.popconfirm.confirm" 
            :cancel-button-text="btn.popconfirm.cancel" 
            :title="btn.popconfirm.title" 
            icon="el-icon-info" 
            icon-color="orange" 
            @confirm="btn.click(ok)">
            <el-button 
                slot="reference" 
                :style="{
                    'opacity': 0.9, 
                    'margin-left': 0, 
                    'margin-right': '10px',
                    'width': elButton(btn_type).width + 'px'
                    }"
                :type="elButton(btn_type).type">
                <i :class="elButton(btn_type).icon"></i>
                {{ elButton(btn_type).text }}
            </el-button>
        </el-popconfirm>
        <el-button 
            v-else 
            slot="reference" 
            :style="{
                'opacity': 0.9, 
                'margin-left': 0, 
                'margin-right': '10px',
                'width': elButton(btn_type).width + 'px'
            }" 
            :type="elButton(btn_type).type" @click="btn.click(ok)">
            <i :class="elButton(btn_type).icon"></i>
            {{ elButton(btn_type).text }}
        </el-button>
    </div>

</div>
</template>

<script>
import {
    EventBus
} from '../main';
export default {
    data() {
        return {};
    },
    methods: {
        // elButtonType(type) {
        //     if (type === 'edit') { // 修改
        //         return 'primary'
        //     } else if (type === 'disable') { // 禁用
        //         return 'danger'
        //     } else if (type === 'enable') { // 启用
        //         return 'success'
        //     } else if (type === 'submit') { // 提交
        //         return 'success'
        //     } else if (type === 'delete') { // 删除
        //         return 'info'
        //     } else if (type === 'return') { // 归还
        //         return 'primary'
        //     } else if (type === 'start-inventory') { // 开始盘点
        //         return 'success'
        //     } else if (type === 'close-inventory') { // 结束盘点
        //         return 'info'
        //     } else if (type === 'continue-inventory') { // 继续盘点
        //         return 'warning'
        //     } else if (type === 'edit-result') { // 修改结果
        //         return 'primary'
        //     } else if (type === 'input-result') { // 录入结果
        //         return 'success'
        //     } else if (type === 'approve') { // 通过
        //         return 'success'
        //     } else if (type === 'fail-to-approve') { // 不通过
        //         return 'danger'
        //     } else { // 默认
        //         return 'default'
        //     }
        // },
        // elButtonText(type) {
        //     if (type === 'edit') { // 修改
        //         return '修改'
        //     } else if (type === 'disable') { // 禁用
        //         return '禁用'
        //     } else if (type === 'enable') { // 启用
        //         return '启用'
        //     } else if (type === 'submit') { // 提交
        //         return '提交'
        //     } else if (type === 'delete') { // 删除
        //         return '删除'
        //     } else if (type === 'return') { // 归还
        //         return '归还'
        //     } else if (type === 'start-inventory') { // 开始盘点
        //         return '开始盘点'
        //     } else if (type === 'close-inventory') { // 结束盘点
        //         return '结束盘点'
        //     } else if (type === 'continue-inventory') { // 继续盘点
        //         return '继续盘点'
        //     } else if (type === 'edit-result') { // 修改结果
        //         return '修改结果'
        //     } else if (type === 'input-result') { // 录入结果
        //         return '录入结果'
        //     } else if (type === 'approve') { // 通过
        //         return '通过'
        //     } else if (type === 'fail-to-approve') { // 不通过
        //         return '不通过'
        //     } else { // 默认
        //         return '?????'
        //     }
        // },
        // elButtonIcon(type) {
        //     if (type === 'edit') { // 修改
        //         return 'el-icon-edit'
        //     } else if (type === 'disable') { // 禁用
        //         return 'el-icon-remove-outline'
        //     } else if (type === 'enable') { // 启用
        //         return 'el-icon-circle-check'
        //     } else if (type === 'submit') { // 提交
        //         return 'el-icon-circle-check'
        //     } else if (type === 'delete') { // 删除
        //         return 'el-icon-delete'
        //     } else if (type === 'return') { // 归还
        //         return 'el-icon-sort'
        //     } else if (type === 'start-inventory') { // 开始盘点
        //         return 'el-icon-coin'
        //     } else if (type === 'close-inventory') { // 结束盘点
        //         return 'el-icon-minus'
        //     } else if (type === 'continue-inventory') { // 继续盘点
        //         return 'el-icon-caret-right'
        //     } else if (type === 'edit-result') { // 修改结果
        //         return 'el-icon-edit'
        //     } else if (type === 'input-result') { // 录入结果
        //         return 'el-icon-document-copy'
        //     } else if (type === 'approve') { // 通过
        //         return 'el-icon-folder-checked'
        //     } else if (type === 'fail-to-approve') { // 不通过
        //         return 'el-icon-folder-delete'
        //     } else { // 默认
        //         return '?????'
        //     }

        // },

        elButton(type) {
            let returnObject = {};
            switch (type) {
                case 'edit':
                    returnObject.type = 'primary'
                    returnObject.text = '修改'
                    returnObject.icon = 'el-icon-edit'
                    returnObject.width = 80
                    break;
                case 'disable':
                    returnObject.type = 'danger'
                    returnObject.text = '禁用'
                    returnObject.icon = 'el-icon-remove-outline'
                    returnObject.width = 80
                    break;
                case 'enable':
                    returnObject.type = 'success'
                    returnObject.text = '启用'
                    returnObject.icon = 'el-icon-circle-check'
                    returnObject.width = 80
                    break;
                case 'submit':
                    returnObject.type = 'success'
                    returnObject.text = '提交'
                    returnObject.icon = 'el-icon-circle-check'
                    returnObject.width = 80
                    break;
                case 'delete':
                    returnObject.type = 'info'
                    returnObject.text = '删除'
                    returnObject.icon = 'el-icon-delete'
                    returnObject.width = 80
                    break;
                case 'return':
                    returnObject.type = 'primary'
                    returnObject.text = '归还'
                    returnObject.icon = 'el-icon-sort'
                    returnObject.width = 80
                    break;
                case 'start-inventory':
                    returnObject.type = 'success'
                    returnObject.text = '开始盘点'
                    returnObject.icon = 'el-icon-coin'
                    returnObject.width = 100
                    break;
                case 'close-inventory':
                    returnObject.type = 'info'
                    returnObject.text = '结束盘点'
                    returnObject.icon = 'el-icon-minus'
                    returnObject.width = 100
                    break;
                case 'continue-inventory':
                    returnObject.type = 'warning'
                    returnObject.text = '继续盘点'
                    returnObject.icon = 'el-icon-caret-right'
                    returnObject.width = 100
                    break;
                case 'edit-result':
                    returnObject.type = 'primary'
                    returnObject.text = '修改结果'
                    returnObject.icon = 'el-icon-edit'
                    returnObject.width = 100
                    break;
                case 'input-result':
                    returnObject.type = 'success'
                    returnObject.text = '录入结果'
                    returnObject.icon = 'el-icon-document-copy'
                    returnObject.width = 100
                    break;
                case 'approve':
                    returnObject.type = 'success'
                    returnObject.text = '通过'
                    returnObject.icon = 'el-icon-folder-checked'
                    returnObject.width = 80
                    break;
                case 'fail-to-approve':
                    returnObject.type = 'danger'
                    returnObject.text = '不通过'
                    returnObject.icon = 'el-icon-folder-delete'
                    returnObject.width = 80
                    break;
                default:
                    returnObject.type = 'default'
                    returnObject.text = 'default'
                    returnObject.icon = 'default'
                    returnObject.width = 80
                    break;
            }
            return returnObject;
        },

        ok(ok, message) {
            if (ok) {
                EventBus.$emit("updateDataList")
            } else {
                this.$message({
                    message: message,
                    type: 'error',
                    duration: 2000
                })
            }
        }
    },
    computed: {},
    props: ["bottons", "vertical"],
};
</script>

<style lang="less">
.data-operating {
    display: flex;

    button {
        min-width: 75px;
        padding: 4px;
        @font-size: 20px;
        line-height: @font-size;

        i {
            display: block;
            height: @font-size;
            font-size: @font-size;
            float: left;
        }
    }
}
</style>
