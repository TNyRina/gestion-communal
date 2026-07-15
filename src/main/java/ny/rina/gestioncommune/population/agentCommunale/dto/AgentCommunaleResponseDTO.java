package ny.rina.gestioncommune.population.agentCommunale.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentCommunaleResponseDTO extends AgentCommunaleDTO{
    
    private Long id;

    private String commune;
}
