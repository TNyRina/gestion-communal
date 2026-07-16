package ny.rina.gestioncommune.population.agentCommunale;
import ny.rina.gestioncommune.population.agentCommunale.dto.AgentCommunaleRequestDTO;
import ny.rina.gestioncommune.population.agentCommunale.dto.AgentCommunaleResponseDTO;
import ny.rina.gestioncommune.population.personne.service.PersonneServiceImpl;

public abstract class AgentCommunaleServiceImpl<
A extends AgentCommunale, 
R extends AgentCommunaleResponseDTO, 
Q extends AgentCommunaleRequestDTO, 
T extends AgentCommunaleRepository<A>
> extends PersonneServiceImpl<A,R,Q> {

    protected AgentCommunaleServiceImpl(T repository){
        super(repository);
    }
}
