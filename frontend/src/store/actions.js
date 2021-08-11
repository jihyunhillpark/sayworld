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

export function requestCategory ({ state }, payload) {
  let headers = {
    Authorization: "Bearer " + state.token
  }
  $axios({
    url: `category/${payload.category}`,
    method: 'get',
    headers: headers,
  })
  .then((res) => {
    console.log(res.data)
    return res.data
  })
  .catch((err) => {
    console.log(err)
  })
}

// 카테고리 검색 추가해야함
export function searchRoom ({ state, commit }, payload) {
  let headers = {
    Authorization: "Bearer " + state.token
  }
  $axios({
    url: `rooms/search?input=${payload.searchValue}&search_type=${payload.searchType}`,
    method: 'get',
    headers: headers,
  })
  .then((res) => {
    if (payload.searchType === 'title') {
      commit('SET_SEARCH_TITLE', res.data)
      router.push({name: "SearchResult", params: { searchValue: payload.searchValue }})
    } else if (payload.searchType === 'keyword') {
      commit('SET_SEARCH_KEYWORD', res.data)
    }
  })
  .catch((err) => {
    console.log(err)
  })
}
