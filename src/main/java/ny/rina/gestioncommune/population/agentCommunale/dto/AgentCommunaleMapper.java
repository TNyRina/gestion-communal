package ny.rina.gestioncommune.population.agentCommunale.dto;

import ny.rina.gestioncommune.population.agentCommunale.AgentCommunale;
import ny.rina.gestioncommune.population.personne.dto.PersonneMapperDTO;

public class AgentCommunaleMapper {
    public static AgentCommunaleRequestDTO toRequestDTO(AgentCommunale agent){
        AgentCommunaleRequestDTO dto = PersonneMapperDTO.toDTO(agent, AgentCommunaleRequestDTO::new);

        dto.setMatricule(agent.getMatricule());
        dto.setDateEmbauche(agent.getDateEmbauche());
        dto.setCommuneId(agent.getCommune().getId());

        return dto;
    }

    public static AgentCommunaleResponseDTO toResponseDTO(AgentCommunale agent){
        AgentCommunaleResponseDTO dto = PersonneMapperDTO.toDTO(agent, AgentCommunaleResponseDTO::new);

        dto.setId(agent.getId());
        dto.setMatricule(agent.getMatricule());
        dto.setDateEmbauche(agent.getDateEmbauche());
        dto.setCommune(agent.getCommune().getNom());

        return dto;
    }
}
