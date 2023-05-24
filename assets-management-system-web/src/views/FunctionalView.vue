<template>
<!-- 功能 -->
<div class="functional-view">
    <DataListOperating
        :content="dataListOperatingContent">
    </DataListOperating>
    <DataList class="data-list" 
        :display="dataStruct" 
        :data-list="dataSource" 
        :operating="operating" 
        :vertical="vertical"
        :style="{width: width}">
    </DataList>
    <Pagination 
        v-if="this.dataCount > 15"
        :current-page="currentPage" 
        :pages="Math.ceil(this.dataCount / 15)">
    </Pagination>
</div>
</template>

<script lang="js">
import DataList from '../components/DataList.vue'
import FormDialog from '../components/FormDialog.vue'
import Pagination from '../components/Pagination.vue'
import DataListOperating from '../components/DataListOperating.vue'

import DataOperating from '../data/DataOperating'
import ListStruct from "../data/ListStruct"
import { EventBus } from '../main';
import Service from '../Service';
import Data from "../data/Data"
import DataListOperatingContent from '../data/DataListOperating'
import DetailsView from './DetailsView.vue'
import util from '../util'

export default {
    data() {
        return {
            dataListOperatingContent: DataListOperatingContent[this.$route.params.view],
            dataStruct: Data[this.$route.params.view].display,
            dataSource: [],
            operating: DataOperating[this.$route.params.view],
            vertical: ListStruct[this.$route.params.view].vertical,
            width: ListStruct[this.$route.params.view].width,
            currentPage: this.$route.query.page 
                ? Number.parseInt(this.$route.query.page) 
                : 1,
            dataCount: 0,
        }
    },
    methods: {
        updateDataList(conditions) {
            EventBus.$emit("loading", true)
            conditions = conditions ? conditions : []
            Service.joinPage(
                Data[this.$route.params.view].url, 
                Object.keys(Data[this.$route.params.view].display),
                conditions,
                this.currentPage,
                15,
                (data) => { 
                    this.dataSource = data 
                }, 
                (okay, message) => { 
                    EventBus.$emit("loading", false)
                    okay ? null : this.tip(message, "error") 
                }
            )
            Service.conditionCount(
                Data[this.$route.params.view].url,
                conditions,
                (data) => { this.dataCount = data },
                // (okay, message) => { okay ? null : this.tip(message, "error") }
            )
        },
        tip(message, type) {
            this.$message({
                    message: message,
                    type: type,
                    duration: 2000
                })
        },

    },
    mounted() {
        this.updateDataList()
        EventBus.$on("updateDataList", this.updateDataList)
    },
    computed: {},
    props: [],
    components: {
        DataList,
        FormDialog,
        Pagination,
        DataListOperating,
        DetailsView
    },
};
</script>

<style lang="less" scope>
.functional-view {
    padding: 0 30px;
    .data-list-operating {
        overflow: hidden;
    }
    .data-list {
        height: 500px;
        overflow-y: scroll;
    }
}
</style>
