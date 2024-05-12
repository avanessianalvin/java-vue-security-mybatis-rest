// Styles
import '@mdi/font/css/materialdesignicons.css'
import 'vuetify/styles'

// Vuetify
import { createVuetify } from 'vuetify'
import { VSnackbarQueue } from 'vuetify/labs/VSnackbarQueue'

export default createVuetify({
    // https://vuetifyjs.com/en/introduction/why-vuetify/#feature-guides
    components: {
        VSnackbarQueue,
    },
})


