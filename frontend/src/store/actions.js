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
  let token = localStorage.getItem("token")
  const url = "/users/userInfo"
  axios.defaults.headers.common["Authorization"] = `Bearer ${token}`
  return $axios.get(url)
}

export function requestBook ({ state }, payload) {
  const url = '/users/books'
  let body = payload
  console.log(payload)
  return $axios.post(url, body)
}

export function requestCulture ({ state }, payload) {
  const url = '/users/culture'
  let body = payload
  console.log(payload)
  return $axios.post(url, body)
}
