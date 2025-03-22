package isis.projet.backend.service;

import isis.projet.backend.entity.Projet;
import isis.projet.backend.dao.ProjetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetService {
    private final ProjetRepository projetRepository;

    public ProjetService(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    public List<Projet> getAllProjects() {
        try {
            List<Projet> projets = projetRepository.findAll();
            System.out.println("Nombre de projets trouvés: " + projets.size()); // Debug log
            return projets;
        } catch (Exception e) {
            System.err.println("Erreur dans le service: " + e.getMessage());
            throw new RuntimeException("Erreur lors de la récupération des projets", e);
        }
    }
}
