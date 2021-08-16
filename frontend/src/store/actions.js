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

// openvidu
// export function joinSession({ state, dispatch, commit }, mySessionId) {
//   console.log("vuex joinsession", mySessionId)
//   // --- Get an OpenVidu object ---
//   const OV = new OpenVidu()

//   // --- Init a session ---
//   const session = OV.initSession()
//   // --- Specify the actions when events take place in the session ---

//   // On every new Stream received...
//   const subscribers = []
//   session.on('streamCreated', ({ stream }) => {
//     const subscriber = session.subscribe(stream)
//     subscribers.push(subscriber)
//   })

//   // On every Stream destroyed...
//   session.on('streamDestroyed', ({ stream }) => {
//     const index = subscribers.indexOf(stream.streamManager, 0)
//     if (index >= 0) {
//       subscribers.splice(index, 1)
//     }
//   })

//   // On every asynchronous exception...
//   session.on('exception', ({ exception }) => {
//       console.warn(exception)
//   })

//   // --- Connect to the session with a valid user token ---

//   // 'getToken' method is simulating what your server-side should do.
//   // 'token' parameter should be retrieved and returned by your own backend
//   dispatch('getToken', mySessionId)
//   .then(token => {
//     console.log(token)
//     session.connect(token, { clientData: state.userInfo.nickname })
//     // session.connect(token)
//     .then(() => {
//       // --- Get your own camera stream with the desired properties ---
//       let publisher = OV.initPublisher(undefined, {
//         audioSource: undefined, // The source of audio. If undefined default microphone
//         videoSource: undefined, // The source of video. If undefined default webcam
//         publishAudio: true,     // Whether you want to start publishing with your audio unmuted or not
//         publishVideo: true,     // Whether you want to start publishing with your video enabled or not
//         resolution: '640x480',  // The resolution of your video
//         frameRate: 30,         // The frame rate of your video
//         insertMode: 'APPEND',   // How the video is inserted in the target element 'video-container'
//         mirror: false          // Whether to mirror your local video or not
//       })

//       // localStorage.setItem('OV', JSON.stringify(OV))
//       // localStorage.setItem('mainStreamManager', JSON.stringify(publisher))
//       // localStorage.setItem('publisher', JSON.stringify(publisher))
//       // localStorage.setItem('session', JSON.stringify(session))
//       // localStorage.setItem('subscribers', subscribers)
//       // localStorage.setItem('ovToken', token)

//       // console.log(publisher)
//       // commit('SET_OV', OV);
//       // commit('SET_MAINSTREAMMANAGER', publisher);
//       // commit('SET_PUBLISHER', publisher);
//       // commit('SET_SESSION', session);
//       // commit('SET_SUBSCRIBERS', subscribers);
//       // commit('SET_OVTOKEN', token);

//       // --- Publish your stream ---
//       session.publish(publisher)

//     })
//     .catch(error => {
//       console.log('There was an error connecting to the session:', error.code, error.message);
//     })
//   })

//   window.addEventListener('beforeunload', this.leaveSession)
// }

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

export function requestBook ({ state }, payload) {
  const url = '/users/books'
  let body = payload
  console.log(payload)
  return $axios.post(url, body)
}
