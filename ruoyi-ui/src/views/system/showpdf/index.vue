<!--<template>-->
<!--    <div class="app-container">-->
<!--      <iframe :src="src" width="100%" height="100%"></iframe>-->
<!--    </div>-->
<!--</template>-->

<!--<script>-->
<!--import { getPdf } from "@/api/system/getPdf";-->

<!--export default {-->
<!--  name: "pdfUrl",-->
<!--  data() {-->
<!--    return {-->
<!--      src: '/static/web/viewer.html',-->
<!--    }-->
<!--  },-->
<!--  created() {-->
<!--    this.getPdf();-->
<!--  },-->
<!--  methods: {-->
<!--    getPdf() {-->
<!--      getPdf({-->
<!--        pdfUrl: '123.pdf'-->
<!--      }).then(res => {-->
<!--        if(res) {-->
<!--          let blob = new Blob([res],-->
<!--            {-->
<!--              type: 'application/pdf;charset=utf-8'-->
<!--            });-->
<!--          let url = URL.createObjectURL(blob);-->
<!--          this.src = url;-->

<!--        }-->
<!--      })-->
<!--    }-->
<!--  }-->
<!--}-->

<!--</script>-->

<!--<style scoped>-->
<!--.wrapper {-->
<!--  width: 100px;-->
<!--  height: 100px;-->
<!--  background: red;-->
<!--}-->
<!--</style>-->
