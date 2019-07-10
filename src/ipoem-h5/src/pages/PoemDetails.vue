<template>
    <div class="page" v-finger:swipe="swipeHandler">
        <Poem :poem="poem"></Poem>
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
      this.axios.get('/poemDetails?random=true')
        .then(res => {
          if (res.status === 200) {
            that.poem = res.data
          }
        })
    }
  },
  created() {
    this.getPoem()
  }
}
</script>
