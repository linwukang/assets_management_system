<template>
<div class="side-menu">
    <el-menu 
        class="el-menu-vertical-demo" 
        background-color="#ffffff" 
        text-color="#666666" 
        active-text-color="#ffd04b">
        <el-menu-item 
            v-for="item in menuList" 
            @click="to(item)"
            :style="{
                'background-color': '#ffffff',
                'color': '#666666'
                }"
            >
            <font-awesome-icon :icon="item.icon" class="icon" />
            <span slot="title">{{ item.name }}</span>
        </el-menu-item>
        <router-link to=""></router-link>

    </el-menu>
</div>
</template>

<script>
import { EventBus } from '../main';

export default {
    data() {
        return {};
    },
    methods: {
        to(item) {
            this.$router.push({path: item.to, params: {name: item.name}})
        }
    },
    mounted() {
    },
    computed: {},
    props: ["menuList"],
    components: {},
};
</script>

<style lang="less" scope>
.side-menu {
    .el-menu-item.is-active {
        background-color: #ffffff;
        color: #666666;
    }
    .icon {
        margin-right: 20px;
    }
}
</style>
