package isis.projet.backend.controller;

import lombok.Data;
import java.io.Serializable;

/**
 * DTO pour la gestion des participations
 */
@Data
public class ParticipationDTO implements Serializable {
    private Integer id; // ID de la participation
    private Integer idPersonne; // ID de la personne (matricule)
    private Integer idProjet; // ID du projet (codeProjet)
    private String role;
    private Float pourcentage;
}
