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
   #app{
        height: 968px;
        background-image: url("../assets/图层.jpg");
        background-repeat: no-repeat;
        // background-size:1100px 100% ;
        background-size: 100%;
        background-color: #417bba;
    }
</style>
