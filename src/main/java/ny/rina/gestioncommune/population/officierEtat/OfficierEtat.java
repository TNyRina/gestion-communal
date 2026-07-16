package ny.rina.gestioncommune.population.officierEtat;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import ny.rina.gestioncommune.etat_civile.Act;
import ny.rina.gestioncommune.population.agentCommunale.AgentCommunale;

@Entity
@DiscriminatorValue("OFFICIER_ETAT")
@Getter
@Setter
public class OfficierEtat extends AgentCommunale{
    /*
     * Une commune enregistre plusieurs acts
     */
    @OneToMany(
        mappedBy = "officierEtat",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Act> acts= new ArrayList<>();
}
