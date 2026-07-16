package ny.rina.gestioncommune.population.officierEtat;

import jakarta.persistence.*;
import lombok.*;
import ny.rina.gestioncommune.population.agentCommunale.AgentCommunale;

@Entity
@DiscriminatorValue("OFFICIER_ETAT")
@Getter
@Setter
public class OfficierEtat extends AgentCommunale{}
