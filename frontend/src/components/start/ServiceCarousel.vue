<template>
  <el-carousel :interval="3000" arrow="never" height="400px">
    <el-carousel-item v-for="item in 4" :key="item">
      <img class="item__image" :src="pic[item-1]" alt="" />
    </el-carousel-item>
  </el-carousel>
</template>

<script>
import pic1 from '@/assets/images/service1.png'
import pic2 from '@/assets/images/service2.png'
import pic3 from '@/assets/images/service3.png'
import pic4 from '@/assets/images/service4.png'

export default {
  name: "ServiceCarousel",
  data() {
    return {
      pic : [pic1, pic2, pic3, pic4],
    }
  }
}
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 400px;
  margin: 0;
}

.el-carousel__item {
  background-color: rgba( 255, 255, 255, 0.5 );
}

img {
  max-width: 99%;
  height: 98%;
  margin: 1% 0;
}

.el-carousel__button {
  background-color: #5780c1;
}
</style>
