import api from '@/util/api'

const state = {
    personList:[],
}

const mutations = {
    SET_PERSON_LIST(state, payload) {
        state.personList = payload
    },
    SET_SHOW_PERSON_INFO_DIALOG(state,payload){
        state.showPersonInfoDialog = payload
    }
}

const actions = {

    getPersonList({commit}) {
        commit('SET_LOADING', true)
        return api.get('/rest/person/findAll').then((response) => {
            commit('SET_PERSON_LIST',response.data)
        }).catch(() => {
        }).finally(() => {
            commit('SET_LOADING', false)
        })
    },
    removePerson({commit,dispatch},id){
        commit('SET_LOADING', true)
        return api.get('/rest/person/remove?id='+id).then(() => {
            dispatch('getPersonList')
        }).catch(() => {
        }).finally(() => {
            commit('SET_LOADING', false)
        })
    },

    updatePerson({commit,dispatch},payload){
        commit('SET_LOADING', true)
        return api.post('/rest/person/update',payload).then(() => {
            dispatch('getPersonList')
        }).catch((e) => {
            throw e
        }).finally(() => {
            commit('SET_LOADING', false)
        })
    },

    addPerson({commit,dispatch},payload){
        commit('SET_LOADING', true)
        return api.post('/rest/person/add',payload).then(() => {
            dispatch('getPersonList')
        }).catch((e) => {
            throw e
        }).finally(() => {
            commit('SET_LOADING', false)
        })
    },

}

const getters = {
    personList: state => state.personList,
}

const person = {
    state,
    mutations,
    actions,
    getters
}

export default person;