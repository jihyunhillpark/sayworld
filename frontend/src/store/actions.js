// API
import $axios from 'axios'
import router from '@/common/lib/vue-router.js'
import { OpenVidu } from 'openvidu-browser'

const OPENVIDU_SERVER_URL = "https://" + "i5a407.p.ssafy.io"
const OPENVIDU_SERVER_SECRET = "MY_SECRET"

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
  const url = `users/userCategory/${payload.email}`
  let body = payload.category
  return $axios.post(url, body)
}

export function requestCategory ({ state }, payload) {
  const url = `category/${payload.category}`
  let headers = {
    Authorization: "Bearer " + state.token
  }
  return $axios.get(url, {headers: headers})
}

export function searchRoom ({ state, commit }, payload) {
  let headers = {
    Authorization: "Bearer " + state.token
  }
  $axios({
    url: `rooms/page/${state.curPage}/search?search_type=${payload.searchType}&input=${payload.searchValue}`,
    method: 'get',
    headers: headers,
  })
  .then((res) => {
    if (payload.searchType === 'title') {
      console.log(res.data)
      commit('SET_SEARCH_TITLE', res.data)
      router.push({name: "SearchResult", params: { searchValue: payload.searchValue }})
    } else if (payload.searchType === 'keyword') {
      console.log(res.data)
      commit('SET_SEARCH_KEYWORD', res.data)
    } else {
      commit('SET_SEARCH_CATEGORY', res.data)
    }
  })
  .catch((err) => {
    console.log(err)
  })
}

// 블로그 글 조회
export function blogList({state}) {
  const url = `/blogs/${state.email}`
  return $axios.get(url)
}

// 블로그 글 등록
export function postBlog({state}, payload) {
  const url = `/blogs`
  let body = {}
  body.userEmail = state.email
  body.blogCreatedAt = Date.now()
  body.blogCategory = payload.blogCategory
  body.blogId = payload.blogId
  body.blogLock = payload.blogLock
  body.blogTitle = payload.blogTitle
  body.blogContent = payload.blogContent
  return $axios.post(url, body)
}

// 블로그 글 수정
export function putBlog({state}, payload) {
  const url = `/blogs`
  let body = {}
  body.userEmail = state.email
  body.blogCreatedAt = Date.now()
  body.blogCategory = payload.blogCategory
  body.blogId = payload.blogId
  body.blogLock = payload.blogLock
  body.blogTitle = payload.blogTitle
  body.blogContent = payload.blogContent
  console.log("blog Id     "+ body.blogId)
  return $axios.put(url, body)
}

// 블로그 글 삭제
export function deleteBlog({state}, payload) {
  const url = `blogs`
  let body = {}
  console.log("in actions")
  body.data = payload
  body.data.email = state.email
  console.log(body.data)
  return $axios.delete(url, body)
}

export function getToken ({ dispatch }, mySessionId) {
  console.log('getTOken: ', mySessionId)
  return dispatch('createSession', mySessionId).then(sessionId => dispatch('createToken', sessionId));
}

export function createSession ({state}, sessionId) {
  console.log('createsession:', sessionId)
  return new Promise((resolve, reject) => {
    $axios
    .post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
      customSessionId: sessionId,
    }), {
      auth: {
        username: 'OPENVIDUAPP',
        password: OPENVIDU_SERVER_SECRET,
      },
    })
    .then(response => response.data)
    .then(data => resolve(data.id))
    .catch(error => {
      if (error.response.status === 409) {
        resolve(sessionId)
      } else {
        console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
        if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
          location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`)
        }
        reject(error.response)
      }
    })
  })
}

export function createToken ({ dispatch }, sessionId) {
  return new Promise((resolve, reject) => {
    $axios
    .post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
      auth: {
        username: 'OPENVIDUAPP',
        password: OPENVIDU_SERVER_SECRET,
      },
    })
    .then(response => response.data)
    .then(data => resolve(data.token))
    .catch(error => reject(error.response))
  })
}
export function createRoom({state}, payload) {
  const url = `/rooms`
  return $axios.post(url,payload)
}
export function requestHistory ({ state }) {
  const url = `/users/history/${state.email}`
  let headers = {
    Authorization: "Bearer " + state.token
  }
  return $axios.get(url, {headers: headers})
}

export function deleteRoom ({state}, payload) {
  const url =`/rooms/${payload}`
  return $axios.delete(url);
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
