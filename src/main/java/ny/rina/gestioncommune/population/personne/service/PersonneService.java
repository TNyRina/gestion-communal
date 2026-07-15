package ny.rina.gestioncommune.population.personne.service;

import java.util.List;

public interface PersonneService<
        R, // Response DTO
        Q  // Request DTO
    > {

    List<R> findAll();

    R findById(Long id);

    R save(Q dto);

    R update(Long id, Q dto);

    void delete(Long id);
}
