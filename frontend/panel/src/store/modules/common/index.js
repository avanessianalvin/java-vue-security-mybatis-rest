import axios from "axios";

const state = {
    appName:'',
    loading:false,
    isLoggedIn:window.localStorage.getItem("VUE_PROJECT_IS_LOGGED_IN")==='true',
    leftMenuState:false
}

const mutations = {
    SET_APP_NAME(state, payload) {
        state.appName = payload
    },
    SET_LOADING(state, value) {
        state.loading = value;
    },
    SET_LOGGED_IN(state, payload){
        state.isLoggedIn = payload
        window.localStorage.setItem("VUE_PROJECT_IS_LOGGED_IN",payload)
    },
    TOGGLE_LEFT_MENU_STATE(state){
        state.leftMenuState = !state.leftMenuState
    }
}

const actions = {

    getAppName({commit}) {
        commit('SET_LOADING', true)
        return axios.get('/rest/common/app-name').then((response) => {
            commit('SET_APP_NAME',response.data)
        }).catch(() => {
        }).finally(() => {
            commit('SET_LOADING', false)
        })
    },

    loading({commit},state){
        commit('SET_LOADING',state);
    },

    async loginToDashboard({commit},payload){
        commit('SET_LOADING', true)
        return await axios.post('/rest/common/login.do',payload).then(() => {
            commit('SET_LOGGED_IN',true)
            return true
        }).catch(() => {
            return false
        }).finally(() => {
            commit('SET_LOADING', false)
        })
    },

    isLoggedInDashboard({commit}) {
        commit('SET_LOGGED_IN',window.localStorage.getItem("VUE_PROJECT_IS_LOGGED_IN"))
        commit('SET_LOADING', true)
        return axios.get('/rest/common/is-logged').then(() => {
                commit('SET_LOGGED_IN',true)
            }
        ).catch(() => {
                commit('SET_LOGGED_IN',false)
            }
        ).finally(()=>{
            commit('SET_LOADING', false)
        })
    },



    logoutFromDashboard({commit},payload){
        return axios.get('/rest/common/logout.do',payload).then(() => {
            commit('SET_LOGGED_IN',false)
        }).catch(() => {
        }).finally(() => {
            commit('SET_LOADING', false)

        })
    },

    toggleLeftMenuState({commit}){
        commit('TOGGLE_LEFT_MENU_STATE')
    }
}

const getters = {
    appName: state => state.appName,
    loading : state=> state.loading,
    isLoggedIn: state=> state.isLoggedIn,
    leftMenuState: state => state.leftMenuState
}

const common = {
    state,
    mutations,
    actions,
    getters
}

export default common;