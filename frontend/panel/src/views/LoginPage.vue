<template>
    <div class="login">
        <v-container>
            <v-card max-width="400" class="mx-auto">
                <v-card-title>Login</v-card-title>
                <v-card-text>
                    <v-text-field v-model="username" label="Username"></v-text-field>
                    <v-text-field v-model="password" label="Password" type="password"></v-text-field>
                </v-card-text>
                <v-card-actions>
                    <v-btn color="primary" @click="login">Login</v-btn>
                </v-card-actions>
            </v-card>
        </v-container>
    </div>
</template>

<script>
    export default {
        name: "LoginPage",
        data() {
            return {
                username: '',
                password: ''
            };
        },
        methods: {
            login() {
                const payload = {
                    username : this.username,
                    password: this.password
                }
                this.$store.dispatch('loginToDashboard',payload).then((res)=>{
                    if (res){
                        this.$router.push({name:'dashboard'})
                    }else {
                        alert("user/pass uas not correct!")
                    }
                })
            }
        }
    };
</script>

<style scoped>

</style>