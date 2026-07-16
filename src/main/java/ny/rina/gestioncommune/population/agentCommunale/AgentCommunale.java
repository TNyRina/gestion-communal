package ny.rina.gestioncommune.population.agentCommunale;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;
import ny.rina.gestioncommune.geo.commune.Commune;
import ny.rina.gestioncommune.population.personne.Personne;

@Entity
@Table(name = "agents")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "fonction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AgentCommunale extends Personne {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commune_id")
    private Commune commune;
    
    @Column(unique = true)
    private String matricule;

    @Column(nullable = false)
    private LocalDate dateEmbauche;
}
