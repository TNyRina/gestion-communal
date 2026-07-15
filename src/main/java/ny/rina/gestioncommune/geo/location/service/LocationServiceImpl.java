package ny.rina.gestioncommune.geo.location.service;

import java.util.List;

import ny.rina.gestioncommune.geo.location.Location;
import ny.rina.gestioncommune.geo.location.LocationRepository;
import ny.rina.gestioncommune.geo.location.dto.LocationDTO;


/**
 * Implémentation générique des opérations CRUD pour les entités de localisation.
 *
 * Elle utilise les types génériques suivants :
 * - {@code E} représente le type de l'entité qui hérite de {@link Location}
 * - {@code D} représente le type du DTO qui hérite de {@link LocationDTO}
 *
 * Les opérations CRUD principales sont déjà implémentées :
 * - création d'une localisation
 * - récupération de toutes les localisations
 * - recherche par identifiant
 * - mise à jour d'une localisation
 * - suppression d'une localisation
 *
 * Les classes filles doivent uniquement fournir la logique de conversion :
 * - {@code toDTO()} : conversion d'une entité vers un DTO
 * - {@code toEntity()} : conversion d'un DTO vers une entité
 *
 * Cette classe est abstraite car chaque type de localisation possède son propre
 * DTO et son propre mécanisme de conversion.
 *
 * Exemple d'utilisation :
 *
 * <pre>
 * public class CommuneService
 *        extends LocationServiceImpl&lt;Commune, CommuneDTO&gt; {
 *
 *     public CommuneService(CommuneRepository repository) {
 *         super(repository);
 *     }
 * }
 * </pre>
 *
 * @param <E> type de l'entité de localisation
 * @param <D> type du DTO associé
 */

public abstract class LocationServiceImpl<
        E extends Location,
        D extends LocationDTO>
        implements LocationService<E, D> {


    protected final LocationRepository<E> repository;


    protected LocationServiceImpl(
            LocationRepository<E> repository) {
        this.repository = repository;
    }


    @Override
    public D create(D dto) {

        E entity = toEntity(dto);

        E saved = repository.save(entity);

        return toDTO(saved);
    }

    @Override
    public D update(Long id, D dto) {

        E entity = repository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Localisation non trouvée"
                    ));


        entity.setNom(dto.getNom());
        entity.setCode(dto.getCode());


        E updated = repository.save(entity);

        return toDTO(updated);
    }



    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


    @Override
    public List<D> findAll() {

        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }


    @Override
    public D findById(Long id) {

        E entity = repository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Introuvable"));

        return toDTO(entity);
    }

    /**
     * Convertit une entité en objet DTO.
     *
     * Cette méthode doit être implémentée par chaque service concret
     * car chaque entité possède son propre DTO.
     */
    protected abstract D toDTO(E entity);


    /**
     * Convertit un DTO en entité.
     *
     * Cette méthode doit être implémentée par chaque service concret
     * afin de créer l'objet persistant correspondant.
     */
    abstract protected E toEntity(D dto) ;
}