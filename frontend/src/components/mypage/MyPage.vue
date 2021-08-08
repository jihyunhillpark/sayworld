<template>
  <el-card :body-style="{ padding: '0px' }">
    <div class="image-wrapper">
      <el-skeleton style="width: 100%">
        <template #template>
          <el-skeleton-item variant="image" style="width: 100%; height: 190px" />
        </template>
      </el-skeleton>
    </div>
    <section>
    <ul>
        <li>이메일</li>
        <li v-for="e_mail in info">{{ e_mail }}</li>
      <li>닉네임</li>
      <li v-for="nickname in info">{{ nickname }}</li>
    </ul>
</section>
<row align-h="center">
    <button size="md" variant="danger" type="submit"  v-on:click="getout">회원탈퇴</button>
</row>


    <div style="text-align: left; padding: 14px;">
      <span class="title">{{ title }}</span>
      <div class="bottom">
        <span>{{ desc }}</span>
      </div>
    </div>
  </el-card>
</template>
<script>

export default {
  name: "MyPage",
  data(){
    return{
      info: []
    }
  },

  created() {

    this.info.push(localStorage.getItem('email'));
    this.info.push(localStorage.getItem('nickname'));


  },

  methods: {
    getout() {
      this.localStorage.removeItem('email'); //로컬 스토리지 제거
      //localStorage.clear();
      alert('회원탈퇴가 완료되었습니다.');
      this.$router.push({ name: 'Start' });
      this.$router.go(); //새로고침 역할
    },




  },
};

</script>

<style scoped>

</style>
