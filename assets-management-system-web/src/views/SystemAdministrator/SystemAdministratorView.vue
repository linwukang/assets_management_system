<template>
    <div class="system-administrator-view">
        <FunctionalView :menu-list="menu_list"></FunctionalView>
    </div>
    </template>
    
        
    <script>
    import FunctionalView from '../FunctionalView.vue';
    export default {
        data() {
            return {
                menu_list: [{
                        name: "部门管理",
                        icon: "fa-solid fa-user-secret",
                        to: "/home/department",
                    },
                    {
                        name: "人员管理",
                        icon: "fa-solid fa-user-secret",
                        to: "/home/personnel",
                    },
                    {
                        name: "资产类别",
                        icon: "fa-solid fa-user-secret",
                        to: "/home/assets-class",
                    },
                    {
                        name: "品牌",
                        icon: "fa-solid fa-user-secret",
                        to: "/home/brand",
                    },
                    {
                        name: "取得方式",
                        icon: "fa-solid fa-user-secret",
                        to: "/home/gaining-method",
                    },
                    {
                        name: "供应商",
                        icon: "fa-solid fa-user-secret",
                        to: "/home/supplier",
                    },
                    {
                        name: "存放地点",
                        icon: "fa-solid fa-user-secret",
                        to: "/home/storage-place",
                    },
                    {
                        name: "设备用途",
                        icon: "fa-solid fa-user-secret",
                        to: "/home/equipment-use",
                    },
                    {
                        name: "报废方式",
                        icon: "fa-solid fa-user-secret",
                        to: "/home/scrap-mode",
                    },
                    {
                        name: "个人信息",
                        icon: "fa-solid fa-user-secret",
                        to: "/home/personal-info",
                    },
                ],
            }
        },
        computed: {},
        props: [],
        components: {
            FunctionalView
        },
    };
    </script>
    
    <style lang="less">
    </style>
    