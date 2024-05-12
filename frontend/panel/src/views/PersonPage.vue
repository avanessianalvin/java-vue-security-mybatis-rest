<template>
    <div class="about">
        <div class="d-flex">
            <h1>Person</h1>
            <div class="ml-2">
                <person-info-dialog />
                <v-btn color="success" @click="getPersonList" variant="tonal">
                    <v-icon class="mdi mdi-refresh"/>
                    <v-tooltip activator="parent" location="bottom">Refresh</v-tooltip>
                </v-btn>
            </div>
        </div>

        <v-data-table :headers="headers" :items="personList" hover items-per-page="-1">
            <!-- eslint-disable-next-line -->
            <template v-slot:item.actions="{ item }" >
                <person-info-dialog :person="item" is-update="true"/>
                <v-btn color="error" variant="outlined" small @click="removeItem(item)">
                    <v-icon class="mdi mdi-trash-can"/>
                </v-btn>
            </template>
            <template #bottom></template>
        </v-data-table>

    </div>
</template>

<script>
    import {mapGetters} from 'vuex'
    import PersonInfoDialog from "@/components/person/PersonInfoDialog";

    export default {
        components: {PersonInfoDialog},
        computed: {
            ...mapGetters(['personList'])
        },
        data() {
            return {
                headers: [
                    {key: 'name', title: 'First Name'},
                    {key: 'family', title: 'Last Name'},
                    {key: 'username', title: 'username'},
                    {key: 'actions', title: 'Actions'}
                ],
            }
        },
        methods: {
            editItem() {
                this.$store.dispatch('showPersonInfoDialog',true)
            },
            removeItem(item) {
                //this.$awn.success('Your custom message')

                this.$confirm( {
                    title : "Are you sure?",
                    text : "Are you sure you want to remove this item?",
                    acceptText : "Yes remove it",
                    cancelText : "No"
                } ).then( () => this.$store.dispatch('removePerson',item.id) ).catch( () =>{});
            },
            getPersonList() {
                this.$store.dispatch('getPersonList')
            }
        },

        beforeMount() {
            this.getPersonList()
        }
    }

</script>

<style scoped>

</style>