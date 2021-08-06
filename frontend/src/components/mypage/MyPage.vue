<template>
  <b-container class="bv-example-row" >
    <b-row align-h="center" class="mt-3 mb-3" cols="2">
      <h3>{{localStorage.getItem('email')}}님의 정보</h3>
    </b-row>
    <b-row align-h="center">
      <b-table-simple cols="2">
        <b-tbody>
          <b-tr>
            <b-td colspan="6">이메일</b-td>
            <b-td colspan="6">{{localStorage.getItem('email')}}</b-td>
          </b-tr>
        </b-tbody>
      </b-table-simple>
    </b-row>
    <b-row align-h="center">
      <b-button size="md" variant="danger" type="submit"  v-on:click="getout">회원탈퇴</b-button>
    </b-row>
  </b-container>
</template>

<script>

export default {
  name: "MyPage",
  data(){
    return{

    }
  },

  methods: {
    getout() {
      this.localStorage.removeItem('email'); //로컬 스토리지 제거
      //localStorage.clear();
      alert('회원탈퇴가 완료되었습니다.');
      this.$router.push({ name: 'Start' });
      this.$router.go(); //새로고침 역할
    }
  },
};

</script>

<style scoped>

</style>
