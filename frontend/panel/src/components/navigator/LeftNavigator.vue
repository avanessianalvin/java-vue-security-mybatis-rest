<template>
    <v-navigation-drawer v-model="n_drawer" app>
        <v-list dense>
            <v-list-item :to="{name:'dashboard'}" >
                <v-icon>mdi-view-dashboard</v-icon>
                <v-list-item-title>Dashboard</v-list-item-title>
            </v-list-item>
            <v-list-item :to="{name:'person'}">
                <v-icon class="mdi mdi-account-group"></v-icon>
                <v-list-item-title>Person</v-list-item-title>
            </v-list-item>
            <v-list-item :to="{name:'about'}">
                <v-icon>mdi-folder-multiple</v-icon>
                <v-list-item-title>About</v-list-item-title>
            </v-list-item>
        </v-list>
    </v-navigation-drawer>
</template>

<script>
    export default {
        name: "LeftNavigator",
        props:["drawer"],
        watch:{
          drawer(newValue){
              this.n_drawer = newValue
          }
        },
        data(){
            return{
                n_drawer : this.drawer
            }
        }
    }
</script>

<style scoped>

</style>