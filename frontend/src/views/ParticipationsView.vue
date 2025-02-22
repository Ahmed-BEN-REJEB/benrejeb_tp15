<template>
  <div class="participations">
    <h1>Gestion des Participations</h1>
    <RegisterParticipation />
  </div>
</template>

<script setup>
import RegisterParticipation from "@/components/RegisterParticipation.vue";
</script>

<style scoped>
.participations {
  padding: 20px;
}

@media (min-width: 1024px) {
  .participation {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
}
</style>
