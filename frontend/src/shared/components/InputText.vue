<script setup>
defineProps({
  label: {
    type: String,
    required: true,
  },
})
</script>

<template>
  <div class="group-input">
    <input required id="input" type="text" />
    <label for="input">{{ label }}</label>
  </div>
</template>
<style scoped>
.group-input {
  position: relative;
}

label {
  /* margin-bottom: 10px; */
  color: black;
  padding: 5px;
  position: absolute;
  border-radius: 20px;
  transform: translateY(-50%);
  top: 50%;
  left: 1em;
  pointer-events: none;
  transition: 0.3s;
}

input {
  padding: 10px;
  border: 2px solid #5d8dd4;
  border-radius: 5px;
  outline: none;
}

input:focus ~ label,
input:valid ~ label {
  top: 0;
  background-color: #fff;
  color: #5d8dd4;
  font-size: 0.7rem;
}
</style>
