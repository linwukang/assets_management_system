<template>
<div class="data-list">
    <table width="100%" cellspacing="0" class=".table">
        <tr>
            <td 
                v-for="(col, field_name) of display" 
                :style="{ width: col.width / widthSum * 100 + '%' }" 
                :key="field_name"
                v-if="col.name">
                {{ col.name }}
            </td>
            <td class="operating" v-if="operating">操作</td>
        </tr>
        <tr v-for="line, i in dataContent" >
            <td 
                v-for="(col, field_name) of display" 
                :key="field_name"
                v-if="col.name">
                <span v-if="line[field_name]">
                    <router-link :to="col.to(dataContent, i)" v-if="col.to" class="router-link">
                        {{ line[field_name] }}
                    </router-link>
                    <span v-else>
                        {{ line[field_name] }}
                    </span>
                </span>
                <span class="null" v-else>---</span>
            </td>
            <td class="operating" v-if="operating">
                <DataOperating 
                    :bottons="operating(dataList[i])" 
                    :vertical="vertical" />
            </td>
        </tr>
        <div v-if="dataContent.length == 0 && !loading">没有数据</div>
    </table>
</div>
</template>

<script>
import util from "../util"
import { EventBus } from "../main";
import DataOperating from "./DataOperating.vue"
import { Loading } from 'element-ui'

export default {
    data() {
        return {
            edit_data: this.onEdit ? this.onEdit : function () {},
            delete_data: this.onDelete ? this.onDelete : function () {},
            dataContent: [],
            widthSum: 0,
            loading: false,
            loadingInstance: null
        }
    },
    methods: {
        updateDataSource() {
            this.dataContent = []
            this.dataList.forEach((line, index) => {
                let cloneLine = {...line}

                this.dataContent.push(cloneLine)

                for (let field_name in this.display) {
                    if (this.display[field_name] && this.display[field_name].value) {
                        this.dataContent[index][field_name] = this.display[field_name].value(this.dataContent, index)
                    }
                }
                
            })
            console.log(this.dataContent);
        }
    },
    mounted() {
        // if (this.operating === undefined) {
        //     this.operating = []
        // }
        // else if (this.vertical === undefined) {
        //     this.vertical = false
        // }
        this.updateDataSource()
        for (let field_name in this.display) {
            this.widthSum += this.display[field_name].width
        }
        EventBus.$on("loading", (loading) => {
            // console.log("loading event", loading)
            this.loading = loading
        })
    },
    beforeUpdate() {
        EventBus.$emit("loading", false)
    },
    computed: {},
    watch: {
        dataList: {
            deep: true,
            immediate: true,
            handler(oldValue, newValue) {
                this.updateDataSource()
            }
        },
        loading: {
            deep: true,
            immediate: true,
            handler(oldValue, newValue) {
                // console.log("watch loading", this.loading)
                if (this.loading) {
                    this.loadingInstance = Loading.service({
                        target: ".home>.main>.right",
                        fullscreen: true,
                    })
                }
                else {
                    if (this.loadingInstance !== null)
                        this.loadingInstance.close()
                }
        }
        }
    },
    props: [
        "display",
        "dataList",
        "operating",
        "vertical",
    ],
    components: {
        DataOperating
    },
};
</script>

<style lang="less" scope>
.data-list {
    >table {
        .router-link {
            text-decoration: none;
            color: #5382bd;
        }

        .router-link::after {
            display: inline-block;
            content: ">";
            color: rgb(161, 161, 161);
            font-size: large;
        }
        
        >tr {
            background-color: #f5f8fa;

            //min-height: 50px;
            >td {
                height: 35px;
                padding: 5px 20px;
                border-bottom: 1px solid #dfdfdf;

                .null {
                    color: #afafaf;
                }
            }

            >.operating {
                width: 150px;
            }
        }

        >tr:first-child {
            color: #515050;
            font-weight: bold;
            background-color: #e8f2fe;

            >td {
                border-top: 1px solid #bbe0ff;
                border-bottom: $border-top;
            }
        }

        >tr:nth-child(2n) {
            background-color: white;
        }
    }
}
</style>
