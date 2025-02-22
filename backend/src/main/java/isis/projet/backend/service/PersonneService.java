package isis.projet.backend.service;

import isis.projet.backend.entity.Personne;
import isis.projet.backend.dao.PersonneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonneService {
    private final PersonneRepository personneRepository;

    public PersonneService(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    public List<Personne> getAllPersonnes() {
        return personneRepository.findAll();
    }
}
