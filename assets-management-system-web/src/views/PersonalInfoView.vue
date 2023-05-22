<template>
<!-- 个人信息 -->
<div class="personal-info">
    <div class="card">
        <el-form :label-width="labelWidth">
            <el-form-item label="账号">
                <span class="form-line">{{ form.username }}</span>
            </el-form-item>
        </el-form>
        <el-form :label-width="labelWidth" :inline="true" 
            v-if="form.tel===undefined || form.tel===null || form.tel===''">
            <el-form-item label="手机号">
                <el-input v-model="form.tel"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="saveTel()">保存</el-button>
            </el-form-item>
            
        </el-form>
        <el-form :label-width="labelWidth" :inline="true" 
            v-else>
            <el-form-item label="手机号">
                <span class="form-line">{{ form.tel }}</span>
            </el-form-item>
        </el-form>
        <el-form :label-width="labelWidth">
            <el-form-item label="职位">
                <span class="form-line">{{ position(form.identity) }}</span>
            </el-form-item>
        </el-form>
        <el-form :label-width="labelWidth">
            <el-form-item label="最后登录时间">
                <span class="form-line">{{ formatTime(form.loginLastTime) }}</span>
            </el-form-item>
        </el-form>
        <el-form :label-width="labelWidth">
            <el-form-item label="登录次数">
                <span class="form-line">{{ form.loginTimes }}</span>
            </el-form-item>
        </el-form>

        <div class="user-icon">
            <font-awesome-icon :icon="['far', 'user']" />
        </div>
    </div>
</div>
</template>

    
<script lang="js">
import Service from '../Service'
import util from '../util'

export default {
    data() {
        return {
            labelWidth: '120px',
            form: {
                username: '---',
                tel: '',
                identity: '---',
                loginLastTime: null,
                loginTimes: '---',
            }
        }
    },
    methods: {
        position(identity) {
            switch (identity) {
                case 'system-administrator':
                    return '系统管理员'
                case 'assets-manager':
                    return '资产管理员'
                case 'assets-leader':
                    return '资产领导'
                default:
                    return identity
            }
        },
        formatTime(time) {
            return util.DateTimeFormat(time)
        },
        saveTel() {

        },
        getData() {
            Service.getPersonalInfo(data => {
                this.form.username = data.account
                this.form.tel = data.tel
                this.form.loginLastTime = data.loginLastTime
                this.form.loginTimes = data.loginTimes
            })
            this.form.identity = this.$route.params.identity
        }
    },
    mounted() {
        this.getData()
    },
    computed: {},
    props: [],
    components: {},
}
</script>

    
<style lang="less" scope>
.personal-info {
    >.card {
        margin: 30px;
        float: left;
        border: 2px dotted rgb(226, 226, 226);
        position: relative;
        min-width: 700px;
        >.el-form {
            margin-left: 130px;
            padding-right: 100px;
            border-bottom: 2px dotted rgb(226, 226, 226);
            margin-top: 10px;
            .form-line, .el-input__inner {
                display: block;
                // overflow: hidden;
                height: 40px;
                margin-left: 30px;
            }
            .el-button {
                margin-left: 20px;
            }
        }
        >.user-icon {
            color: rgb(235, 235, 235);
            position: absolute;
            bottom: 0;
            >svg {
                color: rgb(235, 235, 235);
                font-size: 160px;
            }
        }
    }
}
</style>
