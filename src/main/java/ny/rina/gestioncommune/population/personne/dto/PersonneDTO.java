package ny.rina.gestioncommune.population.personne.dto;
import lombok.*;
import ny.rina.gestioncommune.population.personne.Sexe;

import java.time.LocalDate;


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
