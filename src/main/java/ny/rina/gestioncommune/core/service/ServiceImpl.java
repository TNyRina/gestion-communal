package ny.rina.gestioncommune.core.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Implémentation générique des opérations CRUD communes pour les services
 * métier de l'application.
 *
 * Cette classe fournit une implémentation partielle de {@link Service} afin
 * d'éviter la duplication du code pour les différentes ressources métier.
 *
 * Elle prend en charge les opérations communes :
 * <ul>
 *     <li>Recherche d'une ressource par son identifiant</li>
 *     <li>Récupération de toutes les ressources</li>
 *     <li>Suppression d'une ressource</li>
 * </ul>
 *
 * Les opérations de création et de modification ne sont pas implémentées ici
 * car elles peuvent nécessiter des traitements spécifiques selon le domaine
 * métier. Elles doivent être définies dans les classes services concrètes.
 *
 * Cette classe utilise les types génériques suivants :
 * <ul>
 *     <li>
 *         {@code E} : type de l'entité JPA manipulée
 *     </li>
 *     <li>
 *         {@code R} : type du DTO utilisé pour les réponses
 *     </li>
 *     <li>
 *         {@code Q} : type du DTO utilisé pour les requêtes
 *     </li>
 * </ul>
 *
 * La conversion entre l'entité et le DTO de réponse est déléguée aux classes
 * filles via la méthode abstraite {@link #toResponseDTO(Object)}.
 *
 * Exemple d'utilisation :
 *
 * <pre>
 * {@code
 * public class CitoyenServiceImpl
 *        extends ServiceImpl<
 *              Citoyen,
 *              CitoyenResponseDTO,
 *              CitoyenRequestDTO> {
 *
 *     public CitoyenServiceImpl(
 *          CitoyenRepository repository) {
 *          super(repository);
 *     }
 * }
 * }
 * </pre>
 *
 * @param <E> type de l'entité JPA
 * @param <R> type du DTO de réponse
 * @param <Q> type du DTO de requête
 */
public abstract class ServiceImpl<
        E, // Enity
        R, // Response DTO
        Q // Request DTO
> implements Service<R, Q> {


    protected final JpaRepository<E, Long> repository;


    protected ServiceImpl(
            JpaRepository<E, Long> repository
    ){
        this.repository = repository;
    }


    



     @Override
    public R findById(Long id) {

        E entity = repository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Introuvable"));

        return toResponseDTO(entity);
    }



    
    @Override
    public List<R> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }





    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }




    /**
     * Convertit une entité en DTO de réponse.
     *
     * Cette méthode doit être implémentée par chaque service concret,
     * car chaque domaine possède sa propre structure de DTO.
     *
     * @param entity entité à convertir
     * @return DTO représentant l'entité
     */
    protected abstract R toResponseDTO(E entity);


    /**
     * Copie les données d'un DTO de requête dans une entité.
     *
     * Cette méthode est appelée lors des opérations de création ou de mise à jour
     * afin de transférer les informations du DTO vers l'entité métier.
     *
     * Les classes concrètes doivent implémenter cette méthode pour gérer
     * les attributs spécifiques de leur domaine, y compris la résolution
     * des relations avec d'autres entités si nécessaire.
     *
     * @param entity entité à initialiser ou à mettre à jour
     * @param dto DTO contenant les données à copier dans l'entité
     */
    protected abstract void toEntity(E enity, Q dto);

}