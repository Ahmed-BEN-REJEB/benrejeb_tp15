<template>
  <div class="projects">
    <h1>Liste des Projets</h1>
    <div v-if="error" class="error">{{ error }}</div>
    <div v-else-if="loading">Chargement...</div>
    <div v-else class="projects-grid">
      <div v-for="project in projects" :key="project.code" class="project-card">
        <h3>{{ project.nom }}</h3>
        <p>Date début: {{ formatDate(project.debut) }}</p>
        <p>Date fin: {{ project.fin ? formatDate(project.fin) : 'En cours' }}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const projects = ref([])
const loading = ref(true)
const error = ref(null)

const formatDate = (dateString) => {
  return new Date(dateString).toLocaleDateString()
}

onMounted(async () => {
  try {
    const response = await fetch('http://localhost:8989/api/projets')
    if (!response.ok) throw new Error('Erreur lors du chargement des projets')
    projects.value = await response.json()
  } catch (e) {
    error.value = e.message
  } finally {
    loading.value = false
  }
})
</script>

<style scoped>
.projects {
  padding: 20px;
}

.projects-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 20px;
  margin-top: 20px;
}

.project-card {
  padding: 15px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background: white;
}

.error {
  color: red;
  padding: 10px;
  margin: 10px 0;
}
</style>
