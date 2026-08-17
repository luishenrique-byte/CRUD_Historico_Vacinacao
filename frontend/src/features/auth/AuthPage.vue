<!-- SCRIPT -->
<script setup>
import InputText from '@/shared/components/InputText.vue'

//é necessário receber do login se é paciente ou profissional
//para os atributos
//src:"" das img
//interpolação do label
//interpolação do button
</script>
<!-- HTML -->
<template>
  <span class="card">
    <img class="img" draggable="false" src="../../assets/paciente_256x256_pc.png" />

    <span class="label-dinamica"
      >Informe o CPF do
      <p>
        Paciente
        <!--é necessário fazer e interpolação aqui-->
      </p></span
    >

    <InputText label="CPF"></InputText>

    <button class="btn-acess">Acessar Cartão de Vacina<!--Ou WorkFlow--></button>
  </span>
</template>
<!-- Informe o  CPF do PacienteOu profissional -->

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
