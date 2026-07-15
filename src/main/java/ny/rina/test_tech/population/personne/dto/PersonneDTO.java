
import lombok.*;
package ny.rina.test_tech.population.personne.dto;

import java.time.LocalDate;

import lombok.*;
import ny.rina.test_tech.population.personne.Sexe;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonneDTO {
    
    private String nom;

    private String prenom;

    private LocalDate dateNaissance;

    private Sexe sexe;

    private String lieuNaissance;

    private String numeroCIN;

    private String adresse;
}
