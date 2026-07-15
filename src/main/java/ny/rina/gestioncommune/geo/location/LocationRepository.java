package ny.rina.gestioncommune.geo.location;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository<E extends Location> extends JpaRepository<E, Long> {
    
}
