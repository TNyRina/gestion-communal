package ny.rina.gestioncommune.population.agentCommunale;
import java.util.function.Supplier;

import jakarta.persistence.EntityNotFoundException;
import ny.rina.gestioncommune.geo.commune.Commune;
import ny.rina.gestioncommune.geo.commune.CommuneRepository;
import ny.rina.gestioncommune.population.agentCommunale.dto.AgentCommunaleRequestDTO;
import ny.rina.gestioncommune.population.agentCommunale.dto.AgentCommunaleResponseDTO;
import ny.rina.gestioncommune.population.personne.PersonneServiceImpl;

public abstract class AgentCommunaleServiceImpl<
A extends AgentCommunale, 
R extends AgentCommunaleResponseDTO, 
Q extends AgentCommunaleRequestDTO, 
T extends AgentCommunaleRepository<A>
> extends PersonneServiceImpl<A,R,Q> {

    final private CommuneRepository communeRepository;

    protected AgentCommunaleServiceImpl(T repository, CommuneRepository communeRepository, Supplier<A> supplier){
        super(repository, supplier);

        this.communeRepository = communeRepository;
    }

    protected void toAgentCommunaleEntity(A agent, Q dto){
        toPersonneEntity(agent, dto);
        agent.setMatricule(dto.getMatricule());
        agent.setDateEmbauche(dto.getDateEmbauche());

        Commune commune = communeRepository.findById(dto.getCommuneId()).orElseThrow( () -> new EntityNotFoundException(
                    "Commune introuvable"
                ));
        agent.setCommune(commune);
    }
}
