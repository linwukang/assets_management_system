<template>
<!-- 编辑视图 -->
<div class="new-view">
    <FormView
        :onSave="onSave"
        :onCancel="onCancel"
        >
    </FormView>
</div>
</template>

    
<script lang="js">
import DataList from '../components/DataList.vue'
import FormDialog from '../components/FormDialog.vue'
import util from '../util';
import DataStruct from "../data/DataStruct"
import DataOperating from '../data/DataOperating'
import FormView from "./FormView.vue"
import FormContent from '../data/FormContent'
import Service from '../Service';
import Data from '../data/Data';
export default {
    data() {
        return {
            title: this.$route.query.title,
            content: FormContent[this.$route.params.view],
            onSave: (dataLine, ok) => {
                Service.create(Data[this.$route.params.view].url, dataLine, ok)
            },
            onCancel: () => {
                this.$router.go(-1)
            },
        }
    },
    mounted() {
    },
    computed: {},
    props: [],
    components: {
        FormView,
    },
}
</script>

    
<style lang="less">
</style>
