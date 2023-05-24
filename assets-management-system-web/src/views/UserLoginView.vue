<template>
<div class="user-login-view">
    <Login></Login>
</div>
</template>

<script>
import Login from '../components/Login.vue'
export default {
    data() {
        return {}
    },
    computed: {},
    props: [],
    components: {
        Login
    },
};
</script>

<style lang="less" scope>
.user-login-view {
    display: block;
    .login {
        float: right;
        margin: 100px 180px;
    }
}
</style>
