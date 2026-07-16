package ny.rina.gestioncommune.etat_civile;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;
import ny.rina.gestioncommune.geo.commune.Commune;
import ny.rina.gestioncommune.population.officierEtat.OfficierEtat;

@Entity
@Table(name = "acts")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Act {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String nomero;

    private LocalDate dateEtablissement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "officier_id")
    private OfficierEtat officierEtat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commune_id")
    private Commune commune;
}
