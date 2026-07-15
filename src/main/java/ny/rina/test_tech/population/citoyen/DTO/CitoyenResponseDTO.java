package ny.rina.test_tech.population.citoyen.DTO;
import lombok.*;
import ny.rina.test_tech.geo.fokontany.dto.FokontanyDTO;
import ny.rina.test_tech.population.citoyen.type.SituationFamiliale;
import ny.rina.test_tech.population.personne.Sexe;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
public class CitoyenResponseDTO {
    private Long id;


    // Personne

    private String nom;

    private String prenom;

    private LocalDate dateNaissance;

    private Sexe sexe;

    private String lieuNaissance;

    private String numeroCIN;

    private String adresse;


    // Citoyen

    private String profession;

    private SituationFamiliale situationFamiliale;


    // Fokontany

    private FokontanyDTO fokontany;
}
