package ny.rina.test_tech.population.personne;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonneRepository 
        extends JpaRepository<Personne, Long> {
}