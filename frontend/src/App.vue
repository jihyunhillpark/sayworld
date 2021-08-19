<template>
  <MeetingRoom v-if="token && $route.name === 'MeetingRoom'" />
  <Main v-else-if="token" />
  <Start v-else />
</template>

<script>
import Main from '@/views/main/Main'
import Start from '@/views/Start'
import MeetingRoom from '@/views/MeetingRoom'

export default {
  name: 'App',

  components: {
    Main,
    Start,
    MeetingRoom,
  },

  data() {
    return {
      token: localStorage.getItem('token')
    }
  },
}
</script>
