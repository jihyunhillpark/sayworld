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
  console.log(token)
  state.token = token
}

export function SET_EMAIL(state, email) {
  console.log(email)
  state.email = email
}

export function SET_USERINFO (state, userInfo) {
  console.log(userInfo)
  state.userInfo = userInfo
}
