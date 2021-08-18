<template>
  <el-row
    class="main-footer"
    :gutter="10">
    <div class="contents">
      Copyright © SAMSUNG All Rights Reserved.
    </div>
  </el-row>
</template>
<style>
.main-footer {
  height: 80px;
  position: absolute;
}
.main-footer .contents {
  line-height: 80px;
  text-align: center;
  margin: 0 auto;
}
</style>
<script>
import { reactive } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'MainFooter',

  props: {
    height: {
      type: String,
      default: '80px'
    }
  },

  setup() {
    const state = reactive({})

    return { state }
  }
}
</script>
<style>
</style>
