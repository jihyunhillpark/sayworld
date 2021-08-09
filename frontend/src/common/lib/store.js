import { createStore } from "vuex"
import root from "@/store"
import createPersistedState from "vuex-persistedstate"

export default createStore({
  plugins: [createPersistedState()],
  modules: { root }
})
