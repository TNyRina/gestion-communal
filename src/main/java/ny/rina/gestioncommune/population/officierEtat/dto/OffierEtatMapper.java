package ny.rina.gestioncommune.population.officierEtat.dto;

import ny.rina.gestioncommune.population.agentCommunale.dto.AgentCommunaleMapper;
import ny.rina.gestioncommune.population.officierEtat.OfficierEtat;

public class OffierEtatMapper {

    public static OfficierEtatRequestDTO toRequestDTO(OfficierEtat offier){

        return AgentCommunaleMapper.toRequestDTO(offier, OfficierEtatRequestDTO::new);
    }

    public static OfficierEtatResponseDTO toResponseDTO(OfficierEtat offier){

        return AgentCommunaleMapper.toResponseDTO(offier, OfficierEtatResponseDTO::new);
    }
}
