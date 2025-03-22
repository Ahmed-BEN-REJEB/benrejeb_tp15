package isis.projet.backend.controller;

import isis.projet.backend.entity.Projet;
import isis.projet.backend.service.ProjetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projets")
@CrossOrigin(origins = "http://localhost:5173")
public class ProjetController {

    private final ProjetService projetService;

    public ProjetController(ProjetService projetService) {
        this.projetService = projetService;
    }

    @GetMapping
    public ResponseEntity<List<Projet>> getAllProjects() {
        try {
            List<Projet> projets = projetService.getAllProjects();
            System.out.println("Projets récupérés: " + projets.size()); // Debug log
            return ResponseEntity.ok(projets);
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des projets: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
