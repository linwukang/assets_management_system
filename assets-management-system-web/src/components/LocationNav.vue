<template>
<div class="location-nav">
    <router-link v-for="item in paths" :to="item.to" :key="item.to">
        {{ item.name }}
    </router-link>
    <!-- <hr>
    {{ paths }} -->
</div>
</template>

<script>
import { EventBus } from '../main'
import Vue from 'vue';
import Data from '../data/Data';

export default {
    data() {
        return {
            paths: []
        };
    },
    methods: {
        update() {
            if (this.$route.fullPath.indexOf("/details/") !== -1) {
                this.paths = [
                    { name: '首页', to: `/${this.$route.params.identity}/home` },
                    { name: Data[this.$route.params.view].name, to: `/${this.$route.params.identity}/home/${this.$route.params.view}` },
                    { name: Data[this.$route.params.view].name + '详情', to: `/${this.$route.params.identity}/home/${this.$route.params.view}/details/${this.$route.params.id}` }
                ]
            }
            else if (this.$route.params.view != undefined) {
                this.paths = [
                    { name: '首页', to: `/${this.$route.params.identity}/home` },
                    { name: Data[this.$route.params.view].name, to: `/${this.$route.params.identity}/home/${this.$route.params.view}` }
                ]
            }
            else if (this.$route.params.identity != undefined) {
                this.paths = [
                    { name: '首页', to: `/${this.$route.params.identity}/home` }
                ]
            }
        }
    },
    computed: {},
    mounted() {
        this.$watch("$route", {
            immediate: true,
            handler: (to, from) => this.update()
        })
        // EventBus.$on("location-nav-to", (paths) => {
        //     console.log("location-nav-to")
        //     this.paths = paths
        // })
        // EventBus.$on("location-nav-push", (path, level) => {
        //     // console.log(path, level)
        //     // console.log(this.paths, this.paths.length)
        //     if (level === undefined) {
        //         this.paths.push(path)
        //     }
        //     else if (this.paths.length == level) {
        //         console.log(1);
        //         Vue.set(this.paths, level - 1, path)
        //     }
        //     else if (this.paths.length == level - 1) {
        //         console.log(2);
        //         this.paths.push(path)
        //     }
        //     else if (this.paths.length > level) {
        //         console.log(3);
        //         this.paths = this.paths.slice(0, level)
        //     }
        // })
    },
    props: [],
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
