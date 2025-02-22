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
        return projetRepository.findAll();
    }
}
