package ny.rina.gestioncommune.etat_civile.acte;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActeRepositoty<E extends Acte> extends JpaRepository<E, Long> {}
