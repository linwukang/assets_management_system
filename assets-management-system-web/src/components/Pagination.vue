<template>
<!-- 分页操作 -->
<div class="pagination">
    <el-button 
        :disabled="currentPage == 1" 
        @click="firstPage">首页</el-button>
    <el-button 
        :disabled="currentPage == 1" 
        @click="prevPage">上一页</el-button>
    <el-button 
        :disabled="currentPage == pages" 
        @click="nextPage">下一页</el-button>
    <el-button 
        :disabled="currentPage == pages" 
        @click="lastPage">尾页</el-button>
</div>
</template>

    
<script lang="js">

export default {
    data() {
        return {
        }
    },
    methods: {
        firstPage() {
            this.$router.push({
                path: "",
                query: {
                    page: 1
                }
            })
        },
        prevPage() {
            this.$router.push({
                path: "",
                query: {
                    page: this.currentPage - 1
                }
            })
        },
        nextPage() {
            this.$router.push({
                path: "",
                query: {
                    page: this.currentPage + 1
                }
            })
        },
        lastPage() {
            this.$router.push({
                path: "",
                query: {
                    page: this.pages
                }
            })
        }
    },
    mounted() {
        this.$on("firstPage", this.nextPage)
        this.$on("prevPage", this.nextPage)
        this.$on("nextPage", this.nextPage)
        this.$on("lastPage", this.nextPage)
    },
    computed: {},
    props: [
        "currentPage",
        "pages"
    ],
    components: {
    },
}
</script>

    
<style lang="less" scope>
</style>
