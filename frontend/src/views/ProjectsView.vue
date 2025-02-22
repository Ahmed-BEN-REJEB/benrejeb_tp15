<template>
  <div class="projects">
    <h1>Liste des Projets</h1>
    <div v-if="loading">Chargement...</div>
    <div v-else-if="error" class="error">{{ error }}</div>
    <div v-else-if="projects.length === 0">Aucun projet trouvé</div>
    <div v-else class="projects-grid">
      <div v-for="project in projects" :key="project.code" class="project-card">
        <h3>{{ project.nom }}</h3>
        <p>Début: {{ formatDate(project.debut) }}</p>
        <p>Fin: {{ project.fin ? formatDate(project.fin) : 'En cours' }}</p>
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

const fetchProjects = async () => {
  try {
    console.log('Fetching projects...') // Debug log
    const response = await fetch('/api/projets')
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`)
    }
    const data = await response.json()
    console.log('Projects received:', data) // Debug log
    projects.value = data
  } catch (e) {
    console.error('Fetch error:', e) // Debug log
    error.value = `Erreur de chargement: ${e.message}`
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  fetchProjects()
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
