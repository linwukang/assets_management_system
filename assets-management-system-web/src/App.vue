<template>
<div id="app">
    <!-- <div>
        <el-button @click="gotoHome('/system-administrator')">系统管理员</el-button><br>
        <el-button @click="gotoHome('/assets-manager')">资产管理员</el-button><br>
        <el-button @click="gotoHome('/assets-leader')">资产领导</el-button><br>
    </div> -->
    <!-- <div>
        <AddAssetsToInventoryDialog
            :excludeAssetsId="[1, 2, 4]"
            :assetsToinventoryId="1">
        </AddAssetsToInventoryDialog>
    </div> -->
    <router-view></router-view>
</div>
</template>

<script>
import DataList from "./components/DataList.vue"
import DataOperating from "./components/DataOperating.vue"
import LocationNav from "./components/LocationNav.vue"
import FormDialog from "./components/FormDialog.vue"
import Login from "./components/Login.vue"
import SideMenu from "./components/SideMenu.vue"
import Home from "./views/HomeView.vue"
import FormView from "./views/FormView.vue"
import AddAssetsToInventoryDialog from "./components/AssetsInventory/AddAssetsToInventoryDialog.vue"
export default {
    data() {
        return {
            systemAdministrator: [{
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
            assetAdministrator: [],
            assetLeader: [],
            currentMenuList: []
        };
    },
    methods: {
        gotoHome(path) {
            console.log("gotoHome");
            this.$router.push({path: path + "/home"})
        }
    },
    components: {
        DataList,
        DataOperating,
        LocationNav,
        FormDialog,
        Login,
        SideMenu,
        Home,
        FormView,
        AddAssetsToInventoryDialog,
    },
};
</script>

<style lang="less" scope>
* {
    margin: 0;
    padding: 0;
}
html {
    background-image: url("./assets/bg.jpg");
    background-repeat: no-repeat;
    background-size: 100%;
    #app {
        .home-view {
        }
    }
}

</style>
