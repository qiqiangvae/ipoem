<template>
  <div>
    <div>
      <mt-search class="search-input" v-model="searchValue" placeholder="输入搜索内容"> </mt-search>
    </div>
    <div
      class="content"
      v-finger:swipe="swipeHandler"
      @keyup.right="swipeHandler"
      @keyup.left="swipeHandler"
    >
      <Poem :poem="poem" class="poem-content"></Poem>
    </div>
  </div>
</template>

<script>
import Poem from '@/components/Poem'

export default {
  components: {
    Poem
  },
  data() {
    return {
      searchValue: '',
      poem: {
        title: '',
        dynasty: '',
        author: '',
        content: []
      }
    }
  },
  methods: {
    swipeHandler(e) {
      this.getPoem()
    },
    getPoem(that) {
      that = this
      this.axios.get('/random/shi?simple=true').then(res => {
        if (res.status === 200) {
          that.poem = res.data
        }
      })
    },
    search(that) {
      that = this
      if (this.searchValue) {
        this.axios.get('/search/shi?simple=true&search=' + this.searchValue).then(res => {
          if (res.status === 200) {
            console.log(res.data)
            if (res.data && res.data.length > 0) {
              that.poem = res.data[0]
            }
          }
        })
      }
    }
  },
  watch: {
    searchValue() {
      this.search()
    }
  },
  created() {
    this.getPoem()
  }
}
</script>

<style scoped>
.poem-content {
  margin-top: 50px;
  text-align: center;
}
.search-input{
  width: 100px;
}
</style>
