<!-- SCRIPT -->
<script setup>
import { useRoute } from 'vue-router'
import InputText from '@/shared/components/InputText.vue'
import imgEnfermeira from '../../assets/enfermeira_256x256_pc.png'
import imgPaciente from '../../assets/paciente_256x256_pc.png'

const route = useRoute()

// route.params.tipo
const userType = route.params.tipo

if (userType !== 'paciente' && userType !== 'profissional') {
  // Redirecionar para a página inicial ou exibir uma mensagem de erro
  console.error('Tipo de usuário inválido:', userType)
  // Aqui você pode usar o router para redirecionar, se necessário
}

const userImg = userType === 'paciente' ? imgPaciente : imgEnfermeira
const userLabel = userType === 'paciente' ? 'Paciente' : 'Profissional'
const userButtonLabel = userType === 'paciente' ? 'Cartão de Vacina' : 'WorkFlow'
</script>
<!-- HTML -->
<template>
  <span class="card">
    <img class="img" draggable="false" :src="userImg" />

    <span class="label-dinamica">
      Informe o CPF do
      <p>
        {{ userLabel }}
      </p></span
    >

    <InputText label="CPF"></InputText>

    <button class="btn-acess">Acessar {{ userButtonLabel }}<!--Ou WorkFlow--></button>
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
