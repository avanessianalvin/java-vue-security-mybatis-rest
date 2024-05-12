<template>
    <div class="login">
        <v-container>
            <v-card class="mx-auto" max-width="400">

                <v-card-title>Login</v-card-title>
                <v-form @submit.prevent="login" ref="form">
                    <v-card-text>
                        <v-text-field :rules="requiredRule" label="Username" v-model="payload.username"></v-text-field>
                        <v-text-field :rules="requiredRule" label="Password" type="password" v-model="payload.password"></v-text-field>
                    </v-card-text>
                    <v-card-actions>
                        <v-btn color="primary" type="submit">Login</v-btn>
                    </v-card-actions>
                </v-form>
                <v-alert v-if="errorMessage !== ''" type="error" variant="" :text="errorMessage"/>
            </v-card>
        </v-container>
    </div>
</template>

<script>
    export default {
        name: "LoginPage",
        data() {
            return {
                payload: {
                    username: '',
                    password: ''
                },
                requiredRule: [
                    v => !!v || 'required'
                ],
                errorMessage:''
            };
        },
        methods: {

            async validate() {
                const {valid} = await this.$refs.form.validate()
                return valid
            },

            login() {
                this.validate().then((valid) => {
                    if (valid) {
                        this.$store.dispatch('loginToDashboard', this.payload).then((res) => {
                            if (res) {
                                this.$router.push({name: 'dashboard'})
                            } else {
                                this.errorMessage = "user/pass is not correct!"
                            }
                        })
                    }
                })
            }
        }
    };
</script>

<style scoped>

</style>