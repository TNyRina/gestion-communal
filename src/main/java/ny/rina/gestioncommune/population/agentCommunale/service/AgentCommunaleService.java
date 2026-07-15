package ny.rina.gestioncommune.population.agentCommunale.service;


import ny.rina.gestioncommune.population.agentCommunale.dto.AgentCommunaleRequestDTO;
import ny.rina.gestioncommune.population.agentCommunale.dto.AgentCommunaleResponseDTO;
import ny.rina.gestioncommune.population.personne.service.PersonneService;

    public interface AgentCommunaleService extends PersonneService<
        AgentCommunaleResponseDTO,
        AgentCommunaleRequestDTO
    >{}