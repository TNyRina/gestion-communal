package ny.rina.gestioncommune.etat_civile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActRepositoty<E extends Act> extends JpaRepository<E, Long> {}
