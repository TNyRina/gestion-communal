package ny.rina.gestioncommune.etat_civile.acte;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;
import ny.rina.gestioncommune.geo.commune.Commune;
import ny.rina.gestioncommune.population.officierEtat.OfficierEtat;

@Entity
@Table(name = "actes")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Acte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String numero;

    private LocalDate dateEtablissement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "officier_id")
    private OfficierEtat officierEtat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commune_id")
    private Commune commune;
}
