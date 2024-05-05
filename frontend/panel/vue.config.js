const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true,

    devServer: {
        //alvin:: while setting proxy check cors issue also and set filter!
        proxy: {
            '/rest': {
                target: 'http://localhost:8080', // The target port you want to redirect to
                changeOrigin: true
            }
        }
    },

    pluginOptions: {
        vuetify: {
            // https://github.com/vuetifyjs/vuetify-loader/tree/next/packages/vuetify-loader
        }
    },




})
