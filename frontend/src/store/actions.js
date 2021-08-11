// API
import $axios from 'axios'

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

export function searchRoom ({ state }, payload) {
  const url = `rooms/?search_type=${payload.searchType}&input=${payload.searchValue}`
  console.log(url)
  let headers = {
    Authorization: "Bearer " + state.token
  }
  return $axios.get(url, {headers: headers})
}

export function requestRoomInfo ({ state }, payload) {
  const url = "/rooms"
  console.log("ddddd");
  let body = payload
  return $axios.post(url,body)
}
