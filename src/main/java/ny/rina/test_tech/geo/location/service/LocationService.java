package ny.rina.test_tech.geo.location.service;

import java.util.List;

import ny.rina.test_tech.geo.location.Location;
import ny.rina.test_tech.geo.location.dto.LocationDTO;

/**
 * Interface générique définissant les opérations CRUD de base
 * pour les entités de type {@link Location}.
 *
 * Cette interface fournit une structure commune pour gérer les différentes
 * ressources de localisation (par exemple : Commune, Fokontany, Région, etc.).
 *
 * Elle permet d'éviter la duplication du code métier en centralisant les
 * opérations standards :
 * - création d'une localisation
 * - récupération de la liste des localisations
 * - recherche d'une localisation par son identifiant
 * - mise à jour d'une localisation
 * - suppression d'une localisation
 *
 * Les classes qui implémentent cette interface doivent préciser :
 * - le type de l'entité {@code E} qui hérite de {@link Location}
 * - le type du DTO {@code D} qui hérite de {@link LocationDTO}
 *
 * Cette interface est volontairement limitée à un CRUD simple.
 * Les règles métier spécifiques à chaque type de localisation doivent être
 * ajoutées dans les services concrets.
 *
 * @param <E> type de l'entité de localisation
 * @param <D> type du DTO associé à l'entité
 */

public interface LocationService<
        E extends Location,
        D extends LocationDTO> {

    D create(D dto);

    List<D> findAll();

    D findById(Long id);

    D update(Long id, D dto);

    void delete(Long id);
}