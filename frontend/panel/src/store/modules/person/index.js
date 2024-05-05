import axios from "axios";

const state = {
 personList:[]
}

const mutations = {
    SET_PERSON_LIST(state, payload) {
        state.personList = payload
    },
}

const actions = {

    getPersonList({commit}) {
        commit('SET_LOADING', true)
        return axios.get('/rest/person/findAll').then((response) => {
            commit('SET_PERSON_LIST',response.data)
        }).catch(() => {
        }).finally(() => {
            commit('SET_LOADING', false)
        })
    },
    removePerson({commit,dispatch},id){
        commit('SET_LOADING', true)
        return axios.get('/rest/person/remove?id='+id).then(() => {
            dispatch('getPersonList')
        }).catch(() => {
        }).finally(() => {
            commit('SET_LOADING', false)
        })
    }
}

const getters = {
    personList: state => state.personList
}

const person = {
    state,
    mutations,
    actions,
    getters
}

export default person;