<template>
    <div class="about">
        <h1>About</h1>

        <v-btn @click="click">Promise sample</v-btn>
    </div>
</template>

<script>
    export default {
        name: "AboutPage",
        methods: {
            click() {
                this.click2().then(n=>{
                    console.log("click1 Then -> " + n)
                }).catch(e=> {
                    console.log("click1 Catch -> " + e)
                }).finally(f=>{
                    console.log("click1 Finally " + f)
                })

            },
            click2() {
                const myPromise = new Promise((resolve, reject) => {
                    // Perform an asynchronous operation
                    // For example, simulate a delay using setTimeout
                    setTimeout(() => {
                        const randomNumber = Math.random();

                        // Resolve the promise if the random number is greater than 0.5
                        if (randomNumber > 0.5) {
                            resolve(randomNumber);
                        } else {
                            // Reject the promise if the random number is less than or equal to 0.5
                            reject(new Error('Random number is too low.'));
                        }
                    }, 500);
                });

                return myPromise.then(n => {
                    console.log("click2 Then -> " + n)
                    return n
                }).catch(s => {
                    console.log("click2 Catch-> " + s)
                    throw s
                }).finally(f => {
                    console.log("click2 Finally -> " + f)
                })
            }
        }
    }
</script>

<style scoped>

</style>