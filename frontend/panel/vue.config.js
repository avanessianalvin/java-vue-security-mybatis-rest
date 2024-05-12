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
        },
        // ResizeObserver loop completed with undelivered notifications. vue ESC issue
        client: {
            overlay: {
                runtimeErrors: (error) => {
                    const ignoreErrors = [
                        "ResizeObserver loop limit exceeded",
                        "ResizeObserver loop completed with undelivered notifications.",
                    ];
                    if (ignoreErrors.includes(error.message)) {
                        return false;
                    }
                    return true;
                },
            },
        },
    },

    pluginOptions: {
        vuetify: {
            // https://github.com/vuetifyjs/vuetify-loader/tree/next/packages/vuetify-loader
        }
    },




})
