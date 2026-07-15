package ny.rina.test_tech.population.agentCommunale;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;
import ny.rina.test_tech.geo.commune.Commune;
import ny.rina.test_tech.population.personne.Personne;

@Entity
@Table(name = "agents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentCommunale extends Personne {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commune_id")
    private Commune commune;
    
    @Column(unique = true)
    private String matricule;

    @Column(nullable = false)
    private LocalDate dateEmbauche;
}
