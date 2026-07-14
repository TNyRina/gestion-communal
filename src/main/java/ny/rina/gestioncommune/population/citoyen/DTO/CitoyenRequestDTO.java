package ny.rina.gestioncommune.population.citoyen.DTO;

import lombok.*;
import ny.rina.gestioncommune.population.citoyen.type.SituationFamiliale;
import ny.rina.gestioncommune.population.personne.Sexe;

import java.time.LocalDate;


@Getter
@Setter
public class CitoyenRequestDTO {


    // Personne

    private String nom;

    private String prenom;

    private LocalDate dateNaissance;

    private Sexe sexe;

    private String lieuNaissance;

    private String numeroCIN;

    private String adresse;


    // Citoyen

    private Long fokontanyId;

    private String profession;

    private SituationFamiliale situationFamiliale;

}