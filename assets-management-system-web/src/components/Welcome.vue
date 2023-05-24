<template>
<div class="welcome">
    <div class="content">
        <div class="head">

        </div>
        <div>
            <router-link :to="'/' + $route.params.identity + '/home/my'">
                {{ username }}({{ identity }})
            </router-link>
        </div>
    </div>
</div>
</template>

    
<script>
export default {
    data() {
        return {
        };
    },
    props: [
        'username',
        'identity'
    ]
}
</script>

    
<style lang="less" scoped>
.welcome {
    position: relative;
    .content {
        .head {
            width: 100%;
            height: 80%;
            background-color: rgba(0, 0, 0, 0.1);
        }

        width: 250px;
        height: 300px;
        background-color: white;
        border: 3px solid #a9cae8;
        position: relative;
        left: 0;
        right: 0;
        margin: 50px auto;

        >div>a {
            display: block;
            text-align: center;
            margin-top: 20px;
        }
    }
}
</style>
