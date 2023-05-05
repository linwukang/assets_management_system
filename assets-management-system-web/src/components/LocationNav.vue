<template>
<div class="location-nav">
    <router-link v-for="item in path" :to="item.to" :key="item.to">
        {{ item.name }}
    </router-link>
</div>
</template>

<script>
export default {
    data() {
        return {};
    },
    computed: {},
    props: ["path"],
};
</script>

<style lang="less">
.location-nav {
    background: linear-gradient(#ffffff, #f0f0f0);
    border-bottom: 1px solid #dfdfdf;
    padding: 10px 20px;

    a {
        text-decoration: none;
        color: #3d7cca;
    }

    a::after {
        display: inline-block;
        content: ">";
        color: rgb(71, 71, 71);
        font-size: large;
    }

    a:last-child {
        color: #515050;
    }

    a:last-child::after {
        display: none;
    }
}
</style>
