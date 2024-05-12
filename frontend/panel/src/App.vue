<template>
  <v-app>
    <v-main>
      <v-progress-linear color="cyan-darken-2" indeterminate v-if="loading"></v-progress-linear>
      <router-view></router-view>

    </v-main>
  </v-app>


</template>

<script>

import {mapGetters} from 'vuex'

export default {
  name: 'App',
  components: {

  },
  computed:{
    ...mapGetters(["isLoggedIn", "loading"]),
  },

}
</script>
<style>

</style>