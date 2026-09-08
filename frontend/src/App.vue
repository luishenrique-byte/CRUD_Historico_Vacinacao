<script setup>
import { useError } from './shared/composables/useError.js'
import ErrorMenssage from './shared/components/ErrorMenssage.vue'

const error = useError()

const dropError = () => {
  error.error.value = null
}
</script>

<template>
  <main>
    <ErrorMenssage :error="error.error.value" @finish-timeout="dropError"></ErrorMenssage>
    <RouterView></RouterView>
  </main>
</template>

<style scoped>
main {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100vw;
  background: linear-gradient(122deg, #fff 0%, #5d8dd4 64.95%);
}
</style>
