<template>
    <right-navigator :drawer="leftMenuState"/>
    <top-menu/>

    <v-container >
        <router-view></router-view>
    </v-container>
</template>

<script>
    import RightNavigator from "@/components/navigator/LeftNavigator";
    import TopMenu from "@/components/navigator/TopMenu";
    import {mapGetters} from "vuex";
    export default {
        name: "DashboardContainer",
        components: {TopMenu, RightNavigator},
        computed:{
            ...mapGetters(['leftMenuState'])
        },
    }
</script>

<style scoped>

</style>