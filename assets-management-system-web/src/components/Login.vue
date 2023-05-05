<template>
<div class="login">
    <div class="title">
        <i slot="prefix" class="el-input__icon el-icon-user"></i>
        用户登录
    </div>
    <el-form class="login-form">
        <el-input v-model="username" placeholder="用户名">
            <i slot="prefix" class="el-input__icon el-icon-user"></i>
        </el-input>
        <el-input v-model="password" placeholder="密码" show-password>
            <i slot="prefix" class="el-input__icon el-icon-unlock"></i>
        </el-input>
        <el-button type="primary" class="login-button" @click="login">登录</el-button>
    </el-form>
</div>
</template>

<script>
import Service from "../Service";
import Code from "../data/Code"
export default {
    data() {
        return {
            username: "",
            password: "",
        };
    },
    methods: {
        login() {
            console.log("login");
            Service.login(this.username, this.password, this.tip)
        },
        tip(message, type) {
            this.$message({
                    title: '',
                    message: message,
                    type: type,
                    duration: 2000
                })
        },
    },
}
</script>

<style lang="less">
.login {
    width: 500px;
    background-color: white;
    border: 3px solid #cce9ff;

    .title {
        color: #326ab0;
        font-weight: bolder;
        padding: 10px 40px;
        border-bottom: 2px solid #dfdfdf;
    }

    .login-form {
        padding: 60px 50px;
        display: flex;
        flex-direction: column;

        >* {
            margin-bottom: 30px;
        }

        .login-button {
            background-color: #326ab0;
        }

        .tip {
            text-align: center;
            color: red;
        }
    }
}
</style>
