import { ref } from 'vue'

const error = ref(null)

function showError(mensagem) {
  error.value = mensagem
}

// Como o ErroMenssage deve poder receber error de qualquer página
// é mais viavél usar esse composable para refenciar propriedades
// ao invés de passar mais props
export function useError() {
  return {
    error,
    showError,
  }
}
