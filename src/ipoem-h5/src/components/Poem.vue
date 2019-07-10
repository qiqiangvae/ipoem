<template>
  <div>
    <mt-cell class="title">
      <span>{{poem.title}}</span>
    </mt-cell>
    <mt-cell class="authorInfo">
      <span>{{authorInfo}}</span>
    </mt-cell>
    <div class="content">
      <mt-cell
        v-for="(line,index) in poem.content"
        :key="index"
      >
        <span>{{line}}</span>
      </mt-cell>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PoemDetails',
  props: {
    poem: Object
  },
  computed: {
    authorInfo: function () {
      return this.poem.dynasty + '·' + this.poem.author
    }
  }
}
</script>

<style scoped>
.title {
  font-family: 'KaiTi';
  font-size: 25px;
  font-weight: bold;
}
.authorInfo {
  font-family: 'KaiTi';
  font-size: 15px;
  padding: 1px;
}
.content {
  font-family: 'KaiTi';
  font-size: 16px;
  padding-top: 10px;
  padding-bottom: 10px;
}
</style>
