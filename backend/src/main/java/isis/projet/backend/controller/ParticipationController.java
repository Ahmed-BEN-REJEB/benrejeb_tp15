package isis.projet.backend.controller;

import isis.projet.backend.service.ParticipationProjet;
import isis.projet.backend.entity.Participation;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/participations")
@CrossOrigin("*") // Autorise les requêtes depuis le frontend
public class ParticipationController {

    private final ParticipationProjet participationService;
    private final ModelMapper mapper;

    public ParticipationController(ParticipationProjet participationService, ModelMapper mapper) {
        this.participationService = participationService;
        this.mapper = mapper;
    }

    /**
     * Enregistre une participation en récupérant les identifiants de la personne et du projet
     *
     * @param participationDTO DTO contenant les informations nécessaires
     * @return Réponse HTTP contenant soit la participation enregistrée, soit un message d'erreur
     */
    @PostMapping
    public ResponseEntity<?> enregistrerParticipation(@RequestBody ParticipationDTO participationDTO) {
        try {
            var participation = participationService.enregistrerParticipation(
                    participationDTO.getIdPersonne(), // Utilisation de l'ID de la personne
                    participationDTO.getIdProjet(), // Utilisation de l'ID du projet
                    participationDTO.getRole(),
                    participationDTO.getPourcentage()
            );
            var body = mapper.map(participation, ParticipationDTO.class);
            return ResponseEntity.ok(body);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO("La personne ou le projet n'existe pas."));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO("Cette personne participe déjà à ce projet."));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("Une erreur est survenue : " + e.getMessage()));
        }
    }

}
