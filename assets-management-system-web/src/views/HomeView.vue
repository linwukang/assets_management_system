<template>
<div class="home-view">
    <div class="top-nav">
        <h1>功能测试资产管理系统</h1>
        <div class="top-nav-right">
            <router-link :to="'/' + $route.params.identity + '/home/my'">
                {{ userName + '(' + identityName +  ')' }}
            </router-link>
            <router-link @click="logout" to="/login">退出</router-link>
        </div>
    </div>
    <div class="main">
        <div class="left">
            <SideMenu class="side-menu" :menu-list="menu_list" :style="{width: '200px'}"></SideMenu>
        </div>
        <div class="right">
            <LocationNav class="location-nav"></LocationNav>
            <div class="content">
                <router-view :key="$route.fullPath"></router-view>
                <div v-if="$route.fullPath.indexOf('/home/') == -1">
                    <Welcome 
                        :username="userName"
                        :identity="identityName"/>
                </div>
            </div>
        </div>
    </div>
</div>
</template>

<script>
import SideMenu from '../components/SideMenu.vue'
import LocationNav from '../components/LocationNav.vue'
import Welcome from '../components/Welcome.vue'
import { EventBus } from '../main';
import store from "../store/store"
import Service from '../Service';
export default {
    data() {
        let basePath = "/" + this.$route.params.identity + "/home"
        return {
            userName: '',
            identity: '',
            menu_list: [],
            menu: {
                'system-administrator': [
                    {
                        name: "部门管理",
                        icon: "fa-solid fa-user-secret",
                        to: basePath + "/department",
                    },
                    {
                        name: "人员管理",
                        icon: "fa-solid fa-user-secret",
                        to: basePath + "/personnel",
                    },
                    {
                        name: "资产类别",
                        icon: "fa-solid fa-user-secret",
                        to: basePath + "/assets-class",
                    },
                    {
                        name: "品牌",
                        icon: "fa-solid fa-user-secret",
                        to: basePath + "/brand",
                    },
                    {
                        name: "取得方式",
                        icon: "fa-solid fa-user-secret",
                        to: basePath + "/gaining-method",
                    },
                    {
                        name: "供应商",
                        icon: "fa-solid fa-user-secret",
                        to: basePath + "/supplier",
                    },
                    {
                        name: "存放地点",
                        icon: "fa-solid fa-user-secret",
                        to: basePath + "/storage-place",
                    },
                    {
                        name: "设备用途",
                        icon: "fa-solid fa-user-secret",
                        to: basePath + "/equipment-use",
                    },
                    {
                        name: "报废方式",
                        icon: "fa-solid fa-user-secret",
                        to: basePath + "/scrap-mode",
                    },
                    {
                        name: "个人信息",
                        icon: "fa-solid fa-user-secret",
                        to: basePath + "/my",
                    },
                ],
                'assets-manager': [
                    {
                        name: "资产申购",
                        icon: "fa-solid fa-user-secret",
                        to: basePath + "/assets-purchase-requisition",
                    },
                    {
                        name: "资产入库",
                        icon: "fa-solid fa-user-secret",
                        to: basePath + "/assets-storage",
                    },
                    {
                        name: "资产信息维护",
                        icon: "fa-solid fa-user-secret",
                        to: basePath + "/assets-information-maintenance",
                    },
                    {
                        name: "资产借还",
                        icon: "fa-solid fa-user-secret",
                        to: basePath + "/assets-borrow",
                    },
                    {
                        name: "资产转移",
                        icon: "fa-solid fa-user-secret",
                        to: basePath + "/assets-transfer",
                    },
                    {
                        name: "资产维修",
                        icon: "fa-solid fa-user-secret",
                        to: basePath + "/assets-maintain",
                    },
                    {
                        name: "资产报废",
                        icon: "fa-solid fa-user-secret",
                        to: basePath + "/assets-scrap",
                    },
                    {
                        name: "资产盘点",
                        icon: "fa-solid fa-user-secret",
                        to: basePath + "/assets-inventory",
                    },
                    {
                        name: "资产盘点统计",
                        icon: "fa-solid fa-user-secret",
                        to: basePath + "/????",
                    },
                    {
                        name: "个人信息",
                        icon: "fa-solid fa-user-secret",
                        to: basePath + "/my",
                    },
                ],
                'assets-leader': [
                    {
                        name: "资产申购审批",
                        icon: "fa-solid fa-user-secret",
                        to: basePath + "/assets-purchase-requisition-approval",
                    },
                    {
                        name: "资产报废审批",
                        icon: "fa-solid fa-user-secret",
                        to: basePath + "/assets-scrap-approval",
                    },
                ],
            },
            homePath: `/${this.$route.params.identity}/home`
        }
    },
    mounted() {
        console.log(this.$route.params.identity)
        this.menu_list = this.menu[this.$route.params.identity]
    },
    updated() {
        Service.getPersonalInfo(data => {
            this.userName = data.account
        })
        Service.getIdentity(
            (identity) => {
                console.log("identity", identity)
                switch (identity) {
                    case 'assets_leader':
                        this.identity = 'assets-leader'
                        break
                    case 'assets_manager':
                        this.identity = 'assets-manager'
                        break
                    case 'system_administrator':
                        this.identity = 'system-administrator'
                        break
                    default:
                        break
                }
            }
        )
    },
    methods: {
        logout() {
            localStorage.setItem('token', '')
        },
        
    },
    computed: {
        identityName() {
            switch (this.$route.params.identity) {
                case 'system-administrator': return '系统管理员'
                case 'assets-manager': return '资产管理员'
                case 'assets-leader': return '资产领导'
                default: return '未登录'
            }
        },
    },
    props: ["menuList"],
    components: {
        SideMenu,
        LocationNav,
        Welcome
    },
};
</script>

<style lang="less" scope>
.home-view {
    height: 100%;
    >.top-nav {
        @height: 100px;
        height: @height;

        h1 {
            margin: 0;
            padding: 0;
            padding-left: 250px;
            height: @height;
            line-height: @height;
            float: left;
        }

        >.top-nav-right {
            float: right;
            margin-top: 50px;
            a {
                display: block;
                float: left;
                margin-right: 20px;
                color: aliceblue;
                text-decoration: none;
                text-shadow: 1px 1px #bbb;
            }
        }
    }

    >.main {
        padding: 0 10px;
        min-height: 800px;
        overflow: scroll;
 
        display: flex;
        flex-direction: row;
        align-items: flex-start;
        justify-content:space-between;
        background-color: #fff;

        >.left {
            // flex: 1 0 auto;
            margin-right: 8px;
        }

        >.right {
            // flex: 8 0 auto;
            width: 100%;
            >.content {
                height: 600px;
            }
        }

        // .left,
        // .right {
        //     // box-shadow: 0px 0px 10px #5382bd;

        // }
    }
}
</style>
