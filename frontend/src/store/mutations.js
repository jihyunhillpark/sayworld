export function setPlatform (state, isDesktop) {
  state.isDesktopPlatform = isDesktop
}

export function setMenuActive (state, index) {
	console.log('setMenuActive', state,index)
	const keys = Object.keys(state.menus)
	state.activeMenu = keys[index]
}

export function setMenuActiveMenuName (state, menuName) {
	state.activeMenu = menuName
}

export function SET_TOKEN (state, token) {
  state.token = token
}

export function SET_EMAIL(state, email) {
  state.email = email
}

export function SET_USERINFO (state, userInfo) {
  state.userInfo = userInfo
}

export function SET_SEARCH_KEYWORD (state, searchKeyword) {
  state.searchKeyword = searchKeyword
}

export function SET_SEARCH_TITLE (state, searchTitle) {
  state.searchTitle = searchTitle
}

export function SET_SEARCH_CATEGORY (state, searchCategory) {
  state.searchCategory = searchCategory
}

export function SET_CURRENT_PAGE (state, page) {
  console.log('state', page)
  state.curPage = page
  console.log(state.curPage)
}

// Openvidu
export function SET_MYSESSIONID(state, mySessionId) {
  state.mySessionId = mySessionId
}

export function SET_OV(state, OV) {
  state.OV = OV
  console.log('mutation', state.OV)
}

export function SET_OVTOKEN(state, ovToken) {
  console.log("checkovToken")
  state.ovToken = ovToken
}

export function SET_SESSION(state, session) {
  console.log('checksession')
  state.session = session
}

export function SET_MAINSTREAMMANAGER(state, mainStreamManager) {
  console.log('qe')
  state.mainStreamManager = mainStreamManager
}

export function SET_PUBLISHER(state, publisher) {
  console.log('23')
  state.publisher = publisher
}

export function SET_SUBSCRIBERS(state, subscribers) {
  state.subscribers = subscribers
}
