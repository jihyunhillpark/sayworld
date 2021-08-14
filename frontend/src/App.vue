<template>
  <Main v-if="token"/>
  <Start v-else/>
</template>

<script>
import Main from '@/views/main/Main'
import Start from '@/views/Start'

export default {
  name: 'App',

  components: {
    Main,
    Start,
  },

  data() {
    return {
      token: localStorage.getItem('token')
    }
  },
}
</script>
