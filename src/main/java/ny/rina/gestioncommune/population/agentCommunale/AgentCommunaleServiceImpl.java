package ny.rina.gestioncommune.population.agentCommunale;
import ny.rina.gestioncommune.population.personne.service.PersonneServiceImpl;

@SuppressWarnings("rawtypes")
public abstract class AgentCommunaleServiceImpl<A, R, Q, T extends AgentCommunaleRepository> extends PersonneServiceImpl<A,R,Q> {

    @SuppressWarnings("unchecked")
    protected AgentCommunaleServiceImpl(T repository){
        super(repository);
    }
}
