package ny.rina.gestioncommune.population.agentCommunale;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentCommunaleRepository<A extends AgentCommunale> extends JpaRepository<A, Long> {
}
