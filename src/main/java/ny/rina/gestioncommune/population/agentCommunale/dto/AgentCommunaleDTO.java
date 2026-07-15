package ny.rina.gestioncommune.population.agentCommunale.dto;

import java.time.LocalDate;

import lombok.*;
import ny.rina.gestioncommune.population.personne.dto.PersonneDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentCommunaleDTO extends PersonneDTO {
    private String matricule;

    private LocalDate dateEmbauche;
}
