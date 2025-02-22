package isis.projet.backend.controller;

import isis.projet.backend.entity.Projet;
import isis.projet.backend.service.ProjetService;
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
    public List<Projet> getAllProjects() {
        return projetService.getAllProjects();
    }
}
