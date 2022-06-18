<template>
    <div class="app-container">
      <iframe :src="src" width="100%" height="100%"></iframe>
      <div>pdf展示页面</div>
    </div>
</template>

<script>
export default {
  name: "index.vue",
  data() {
    return {
      src: '/static/web/viewer.html',
    }
  },
  methods: {

  }
}

</script>

<style scoped>

</style>
