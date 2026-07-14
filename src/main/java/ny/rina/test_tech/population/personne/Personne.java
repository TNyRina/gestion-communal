package ny.rina.test_tech.population.personne;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "personnes")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String nom;


    @Column(nullable = false)
    private String prenom;


    private LocalDate dateNaissance;


    @Enumerated(EnumType.STRING)
    private Sexe sexe;


    private String lieuNaissance;


    @Column(unique = true)
    private String numeroCIN;


    private String adresse;
}