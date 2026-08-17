<script setup></script>

<template>
  <!-- Necessário implementar as rotas-->
  <div class="conteiner">
    <span class="button-card">
      <img class="img" draggable="false" src="../../assets/enfermeira_256x256_pc.png" />

      <span class="user-type">Técnica de Enf./ Enfermeira</span>
    </span>

    <span class="button-card">
      <img class="img" draggable="false" src="../../assets/paciente_256x256_pc.png" />

      <span class="user-type">Paciente</span>
    </span>
  </div>
</template>

<style scoped>
.conteiner {
  display: flex;
  flex-direction: row;
  gap: 100px;
}

.button-card {
  display: flex;
  width: 300px;
  height: 300px;
  flex-direction: column;
  justify-content: flex-end;
  align-items: center;
  gap: 20px;
  border-radius: 15px;
  background: #fff;
  box-shadow: 6px 4px 9px 0 rgba(0, 0, 0, 0.25);

  transform: translateY(0);
  transition:
    transform 0.3s ease,
    background-color 0.3s ease;
}

.button-card:hover {
  cursor: pointer;
  transform: translateY(-4px);
}

.img {
  height: 50%;
  width: auto;
}

.user-type {
  display: flex;
  flex-direction: column;
  height: fit-content;
  width: fit-content;
  padding: 3px 5px;
  margin-bottom: 10px;
  justify-content: center;
  align-items: center;
  gap: 30px;
  border-radius: 11px;
  background: #3ac4f6;
  font-weight: 700;
}
</style>
