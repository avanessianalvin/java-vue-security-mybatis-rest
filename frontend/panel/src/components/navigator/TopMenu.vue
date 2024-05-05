<template>
    <v-app-bar app>
        <v-app-bar-nav-icon @click.stop="toggleLeftMenuState"></v-app-bar-nav-icon>
        <v-toolbar-title>Admin Dashboard</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-menu offset-y>
            <template v-slot:activator="{ on }">
                <v-btn icon v-on="on">
                    <v-icon>mdi-bell</v-icon>
                </v-btn>
            </template>
            <v-list>
                <v-list-item v-for="(notification, index) in notifications" :key="index">
                    <v-list-item-title>{{ notification }}</v-list-item-title>
                </v-list-item>
            </v-list>
        </v-menu>
        <v-menu>
            <template v-slot:activator="{ props }">
                <v-btn icon v-bind="props">
                    <v-icon class="mdi mdi-account"/>
                </v-btn>
            </template>
            <v-list>
                <v-list-item @click="handleItemClick('account')">
                    <v-list-item-title>Account</v-list-item-title>
                </v-list-item>
                <v-list-item @click="handleItemClick('settings')">
                    <v-list-item-title>Settings</v-list-item-title>
                </v-list-item>
                <v-divider></v-divider>
                <v-list-item @click="logoutFromDashboard">
                    <v-list-item-title>Logout</v-list-item-title>
                </v-list-item>
            </v-list>
        </v-menu>
    </v-app-bar>
</template>

<script>

    export default {
        name: "TopMenu",

        methods:{
            logoutFromDashboard(){
                this.$store.dispatch('logoutFromDashboard').then(()=>{
                    this.$router.push({name:'home'})
                })
            },
            toggleLeftMenuState(){
                this.$store.dispatch('toggleLeftMenuState')
            }
        }
    }
</script>

<style scoped>

</style>