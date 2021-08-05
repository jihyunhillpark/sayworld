<template>
  <Main />
  <!-- <Main v-if="token"/> -->
  <!-- <Start v-else/> -->
</template>

<script>
import Main from '@/views/main/Main'
import Start from '@/views/start/Start'

export default {
  name: 'App',

  components: {
    Main,
    Start,
  },

  data: () => ({
    token: localStorage.getItem('token')
  }),
}
</script>
