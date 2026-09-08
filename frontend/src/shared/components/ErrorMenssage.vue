<script setup>
defineProps({
    error:{
        type: String,
        default: null
    }
})
</script>
<template>
    <div class="conteiner" v-if="error">

        <div class="init">
            <img src="../../assets/circulo_com x_cinza_64x64_pc_mb.png" alt="">
            Error
        </div>

        <div>
            {{ error }}
        </div>

    </div>
</template>
<style scoped>
.conteiner{
    position:absolute;
    top: 5%;
    right: 5%;
    display: flex;
    flex-direction: column;
    gap: 10px;
    padding: 15px;
    border-radius: 15px;
    background-color: rgba(165, 27, 20, 0.9);
    border: 3px solid rgba(241, 37, 19, 0.71);
    color: #fff;
    font-weight: bold;
}
.init {
    display: flex;
    flex-direction: row;
    gap:5px;
    color: #a3a3a3;
}

.init img {
    height: 20px;
    width: auto;
}
</style>