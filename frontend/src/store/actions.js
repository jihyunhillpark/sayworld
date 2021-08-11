// API
import $axios from 'axios'
import router from '@/common/lib/vue-router.js'

export function requestLogin ({ state }, payload) {
  const url = '/auth/login'
  let body = payload
  return $axios.post(url, body)
}

export function requestSignup ({ state }, payload) {
  const url = '/users/signup'
  let body = payload
  console.log(payload)
  return $axios.post(url, body)
}

export function requestUserInfo ({ state }) {
  const url = `/users/userInfo/${state.email}`
  let headers = {
    Authorization: "Bearer " + state.token
  }
  return $axios.get(url, {headers: headers})
}

export function sendCategory ({ state }, payload) {
  const url = `users/userCategory/${state.email}`
  let body = payload
  return $axios.post(url, body)
}

export function searchRoom ({ state, commit }, payload) {
  const url = `rooms/search?input=${payload.searchValue}&search_type=${payload.searchType}`
  let headers = {
    Authorization: "Bearer " + state.token
  }
  $axios({
    url: url,
    method: 'get',
    headers: headers,
  })
  .then((res) => {
    if (payload.searchType === 'title') {
      commit('SET_SEARCH_TITLE', res.data)
      router.push({name: "SearchResult", params: { searchValue: state.searchValue }})
    } else if (payload.searchType === 'keyword') {
      commit('SET_SEARCH_KEYWORD', res.data)
    }
  })
  .catch((err) => {
    console.log(err)
  })
}
