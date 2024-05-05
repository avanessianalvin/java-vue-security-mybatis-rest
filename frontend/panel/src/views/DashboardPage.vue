<template>
    <div class="dashboard">
        <h1>Dashboard</h1>
    </div>
</template>

<script>
    import {mapGetters} from 'vuex'
    export default {
        name: "DashboardPage",
        computed:{
            ...mapGetters(['appName'])
        },
        methods:{
            getAppName(){
                this.$store.dispatch('getAppName')
            },

        }
    }
</script>

<style scoped>

</style>