<template>
    <v-dialog max-width="600" v-model="dialog">

        <template v-slot:activator="{ props: activatorProps }">
            <v-btn @click="resetDialog" color="primary" small v-bind="activatorProps" v-if="isUpdate" variant="outlined">
                <v-icon class="mdi mdi-pencil"/>
                <v-tooltip activator="parent" location="bottom">Edit</v-tooltip>
            </v-btn>
            <v-btn @click="resetDialog" color="primary" small v-bind="activatorProps" v-else variant="tonal">
                <v-icon class="mdi mdi-plus"/>
                <v-tooltip activator="parent" location="bottom">Add</v-tooltip>
            </v-btn>
        </template>

        <v-card :title="title()" prepend-icon="mdi-account">
            <v-card-text>
                <v-row dense>
                    <v-col cols="12" md="4" sm="6">
                        <v-text-field :counter="0" :error-messages="v$.localPerson.name.$errors.map(e => e.$message)" @blur="v$.localPerson.name.$touch" @input="v$.localPerson.name.$touch"
                                      label="First name*"
                                      maxlength="50"
                                      required
                                      v-model="localPerson.name"
                        ></v-text-field>
                    </v-col>

                    <v-col cols="12" md="4" sm="6">
                        <v-text-field :counter="0" :error-messages="v$.localPerson.family.$errors.map(e => e.$message)" @blur="v$.localPerson.family.$touch" @input="v$.localPerson.family.$touch"
                                      label="Last name*"
                                      maxlength="50"
                                      required
                                      v-model="localPerson.family"
                        ></v-text-field>
                    </v-col>
                </v-row>

                <small class="text-caption text-medium-emphasis">*indicates required field</small>
            </v-card-text>

            <v-divider></v-divider>

            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn @click="closeDialog" text="Close" variant="plain"></v-btn>
                <v-btn @click="updatePerson" color="primary" text="Update" v-if="isUpdate" variant="tonal"></v-btn>
                <v-btn @click="addPerson" color="primary" text="Add" v-else variant="tonal"></v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>
<script>
    import {areObjectsEqual} from '@/util/objectUtils'
    import {useVuelidate} from '@vuelidate/core'
    import {required} from '@vuelidate/validators'


    export default {
        name: "PersonInfoDialog",
        props: ['person', 'isUpdate'],
        setup() {
            return {
                v$: useVuelidate()
            }
        },
        data() {
            return {
                dialog: false,
                localPerson: {},

            }
        },
        validations() {
            return {
                localPerson: {
                    name: {required},
                    family: {required}
                }
            }
        },
        methods: {
            async updatePerson() {
                const result = await this.v$.$validate()
                if (!result) {
                    return
                }
                if (!areObjectsEqual(this.person, this.localPerson)) {
                    this.$store.dispatch('updatePerson', this.localPerson)
                        .then(() => this.closeDialog())
                        .catch(() => {
                        })

                } else {
                    this.$alert({
                        title: "No changes",
                        text: "No changes for update",
                        acceptText: "Ok"
                    })
                }
            },
            async addPerson() {
                const result = await this.v$.$validate()
                if (!result) {
                    return
                }
                if (!areObjectsEqual(this.person, this.localPerson)) {
                    this.$store.dispatch('addPerson', this.localPerson)
                        .then(() => this.closeDialog())
                        .catch(() => {
                        })

                }
            },
            closeDialog() {
                this.dialog = false
            },
            resetDialog() {
                this.localPerson = {...this.person}
            },
            title() {
                return this.isUpdate ? "Edit" : "Add"
            }
        },

        beforeMount() {
            this.resetDialog()
        }
    }
</script>

<style scoped>

</style>