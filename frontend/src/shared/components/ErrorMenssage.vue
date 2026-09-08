<script setup>
import { watch } from 'vue'

const props = defineProps({
  error: {
    type: String,
    default: null,
  },
})

const emit = defineEmits(['finish-timeout'])

watch(
  () => props.error,
  (novoErro) => {
    if (novoErro) {
      setTimeout(() => {
        emit('finish-timeout')
      }, 3000)
    }
  },
)
</script>
<template>
  <Transition name="error">
    <div class="container" v-if="error">
      <div class="init">
        <img src="../../assets/circulo_com x_cinza_64x64_pc_mb.png" alt="" />
        Error
      </div>
      <div>
        {{ error }}
      </div>
    </div>
  </Transition>
</template>
<style scoped>
.container {
  position: absolute;
  top: 5%;
  right: 3%;
  display: flex;
  flex-direction: column;
  gap: 10px;
  padding: 15px;
  width: 15%;
  border-radius: 15px;
  background-color: rgba(165, 27, 20, 0.9);
  border: 3px solid rgba(241, 37, 19, 0.71);
  color: #fff;
  font-weight: bold;
}
.init {
  display: flex;
  flex-direction: row;
  gap: 5px;
  color: #a3a3a3;
}

.init img {
  height: 20px;
  width: auto;
}

/* classe do <Trasition> */
.error-enter-from {
  transform: translateY(-100%);
  opacity: 0;
}

.error-enter-to {
  transform: translateY(0);
  opacity: 1;
}
.error-enter-active,
.error-leave-active {
  transition: all 0.2s ease;
}

.error-leave-from {
  transform: translateX(0);
  opacity: 1;
}

.error-leave-to {
  transform: translateX(100%);
  opacity: 0;
}
</style>
