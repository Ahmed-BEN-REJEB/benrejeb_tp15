<template>
  <div class="card">
    <h2>Enregistrer une Participation</h2>

    <div class="form-group">
      <label>👤 Personne</label>
      <select v-model="currentPerson">
        <option v-for="person in persons" :key="person.matricule" :value="person.matricule">
          {{ person.nom }}
        </option>
      </select>
    </div>

    <div class="form-group">
      <label>📁 Projet</label>
      <select v-model="currentProject">
        <option v-for="project in projects" :key="project.codeProjet" :value="project.codeProjet">
          {{ project.nom }}
        </option>
      </select>
    </div>

    <div class="form-group">
      <label>🎭 Rôle</label>
      <input type="text" v-model="role" placeholder="Ex: Développeur" />
    </div>

    <div class="form-group">
      <label>📊 Pourcentage</label>
      <input type="range" v-model="pourcentage" min="0" max="100" />
      <span>{{ pourcentage }}%</span>
    </div>

    <button @click="registerParticipation">
      ✅ Enregistrer
    </button>

    <div v-if="errorMessage" class="error">
      ❌ {{ errorMessage }}
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";

const persons = ref([]);
const projects = ref([]);
const currentPerson = ref(null);
const currentProject = ref(null);
const role = ref("");
const pourcentage = ref(10);
const errorMessage = ref("");

async function chargementDonnees() {
  try {
    // Récupération des personnes
    const responsePersonnes = await fetch("/api/personnes");
    if (!responsePersonnes.ok) throw new Error("Erreur de chargement des personnes");
    const dataPersonnes = await responsePersonnes.json();
    console.log("Données personnes:", dataPersonnes);
    persons.value = dataPersonnes._embedded?.personnes || [];

    // Récupération des projets
    const responseProjets = await fetch("/api/projets");
    if (!responseProjets.ok) throw new Error("Erreur de chargement des projets");
    const dataProjets = await responseProjets.json();
    console.log("Données projets:", dataProjets);
    projects.value = dataProjets._embedded?.projets || [];
  } catch (error) {
    console.error("Erreur de récupération des données :", error);
    errorMessage.value = error.message;
  }
}

const registerParticipation = async () => {
  try {
    console.log(currentPerson.value, currentProject.value, role.value);

    const response = await fetch("/api/participation", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        matricule: currentPerson.value,
        codeProjet: currentProject.value,
        role: role.value,
        pourcentage: pourcentage.value / 100,
      }),
    });

    if (!response.ok) {
      const errorData = await response.json();
      throw new Error(errorData.message || "Erreur lors de l’enregistrement.");
    }

    alert("Participation enregistrée avec succès !");
  } catch (error) {
    errorMessage.value = error.message;
  }
};

onMounted(chargementDonnees);
</script>

<style scoped>
.card {
  background: linear-gradient(to right, #f8f9fa, #e3e6eb);
  padding: 25px;
  border-radius: 15px;
  box-shadow: 0px 6px 12px rgba(0, 0, 0, 0.15);
  max-width: 450px;
  margin: auto;
  font-family: "Poppins", sans-serif;
  text-align: center;
}

h2 {
  font-size: 1.7rem;
  color: #333;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 20px;
  text-align: left;
}

label {
  font-size: 1rem;
  font-weight: bold;
  color: #444;
  display: block;
  margin-bottom: 8px;
}

select,
input {
  width: 100%;
  padding: 12px;
  font-size: 1rem;
  border: 1px solid #ccc;
  border-radius: 8px;
  transition: border 0.3s ease-in-out;
}

select:hover,
input:hover {
  border: 1px solid #007bff;
}

input[type="range"] {
  width: 100%;
  margin-top: 5px;
}

span {
  display: block;
  font-size: 1rem;
  margin-top: 5px;
  color: #007bff;
  font-weight: bold;
}

button {
  width: 100%;
  padding: 12px;
  font-size: 1rem;
  font-weight: bold;
  background: #007bff;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background 0.3s ease-in-out, transform 0.2s;
}

button:hover {
  background: #0056b3;
  transform: scale(1.05);
}

.error {
  color: red;
  font-size: 0.95rem;
  margin-top: 15px;
  background: rgba(255, 0, 0, 0.1);
  padding: 10px;
  border-radius: 8px;
}
</style>
