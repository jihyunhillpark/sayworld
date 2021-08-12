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
  const url = `/userCategory/${state.email}`
  let body = payload
  return $axios.post(url, body)
}
