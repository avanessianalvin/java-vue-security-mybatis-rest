<template>
    <div class="about">
        <div class="d-flex">
            <h1>Person</h1>
            <div class="ml-2">
                <v-btn color="primary">
                    <v-icon class="mdi mdi-plus"/>
                    <v-tooltip activator="parent" location="bottom">Add New</v-tooltip>
                </v-btn>
                <v-btn color="success" @click="getPersonList">
                    <v-icon class="mdi mdi-refresh"/>
                    <v-tooltip activator="parent" location="bottom">Refresh</v-tooltip>
                </v-btn>
            </div>
        </div>

        <v-data-table :headers="headers" :items="personList" hover items-per-page="-1">
            <!-- eslint-disable-next-line -->
            <template v-slot:item.actions="{ item }">
                <v-btn color="primary" small @click="editItem(item)">
                    <v-icon class="mdi mdi-pencil"/>
                </v-btn>
                <v-btn color="error" small @click="removeItem(item)">
                    <v-icon class="mdi mdi-trash-can"/>
                </v-btn>
            </template>
            <template #bottom></template>
        </v-data-table>


    </div>
</template>

<script>
    import {mapGetters} from 'vuex'

    export default {
        computed: {
            ...mapGetters(['personList'])
        },
        data() {
            return {
                headers: [
                    {key: 'name', title: 'name'},
                    {key: 'family', title: 'ohoy'},
                    {key: 'username', title: 'fitttt'},
                    {key: 'actions', title: 'Actions'}
                ],
            }
        },
        methods: {
            editItem(item) {
                // Edit item logic
                console.log('Edit', item);
            },
            removeItem(item) {
                this.$store.dispatch('removePerson',item.id)
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