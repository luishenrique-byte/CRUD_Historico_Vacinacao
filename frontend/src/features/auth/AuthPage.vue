<!-- SCRIPT -->
<script setup>
import { useError } from '../../shared/composables/useError'
import { useRoute, useRouter } from 'vue-router'
import { ref } from 'vue'
import InputText from '@/shared/components/InputText.vue'
import imgEnfermeira from '../../assets/enfermeira_256x256_pc.png'
import imgPaciente from '../../assets/paciente_256x256_pc.png'
import { findByCpf } from '../../shared/services/pacienteServices'

const route = useRoute()

const router = useRouter()

const { showError } = useError()

// route.params.tipo
const userType = route.params.tipo //Recebe o tipo atráves da rota

if (userType !== 'paciente' && userType !== 'profissional') {
  // Exibir uma mensagem de erro
  console.error('Tipo de usuário inválido:', userType)

  // Redirecionar para a página inicial
  router.push('/')
}

const userImg = userType === 'paciente' ? imgPaciente : imgEnfermeira
const userLabel = userType === 'paciente' ? 'Paciente' : 'Profissional'
const userButtonLabel = userType === 'paciente' ? 'Cartão de Vacina' : 'WorkFlow'

const cpf = ref('')

async function login() {
  if (userType === 'paciente') {
    try {
      ;(await findByCpf(cpf.value)).data
      localStorage.setItem('tipo', 'paciente')
      localStorage.setItem('paciente-CPF', cpf.value)
      router.push('/paciente/vaccine')
    } catch (e) {
      showError(e.response.data.mensagem)
    }
  } else if (userType === 'profissional') {
    // TODO: implementar login do profissional
  }
}
</script>
<!-- HTML -->
<template>
  <span class="card">
    <img class="img" draggable="false" :src="userImg" />

    <span class="label-dinamica">
      Informe o CPF do
      <p>
        {{ userLabel }}
      </p>
    </span>

    <InputText label="CPF" v-model="cpf"></InputText>

    <button class="btn-acess" @click="login">Acessar {{ userButtonLabel }}</button>
  </span>
</template>

<!-- STYLE -->
<style scoped>
.card {
  display: flex;
  flex-direction: column;
  gap: 6%;
  align-items: center;
  justify-content: center;
  width: 300px;
  height: 300px;
  padding: 20px;
  border-radius: 15px;
  background: #fff;
  box-shadow: 6px 4px 9px 0 rgba(0, 0, 0, 0.25);
}

.img {
  height: 30%;
  width: auto;
  margin-bottom: 20px;
}

.label-dinamica {
  display: flex;
  flex-direction: row;
  gap: 5px;
}

.btn-acess {
  padding: 10px 20px;
  background: #5d8dd4;
  color: white;
  border: none;
  border-radius: 5px;
  transition: 0.3s ease;
}
.btn-acess:hover {
  transform: translateY(-5px);
  cursor: pointer;
}
</style>
