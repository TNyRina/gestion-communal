package ny.rina.gestioncommune.population.personne;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonneRepository 
        extends JpaRepository<Personne, Long> {
}