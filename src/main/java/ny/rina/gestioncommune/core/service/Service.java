package ny.rina.gestioncommune.core.service;

import java.util.List;



/**
 * Interface générique définissant les opérations CRUD communes
 * pour les différentes couches de service de l'application.
 *
 * Cette interface fournit une abstraction standardisée pour manipuler
 * les ressources métier à travers des objets de transfert de données (DTO),
 * sans exposer directement les entités JPA.
 *
 * Les opérations disponibles sont :
 * <ul>
 *     <li>
 *         Récupération de toutes les ressources
 *     </li>
 *     <li>
 *         Recherche d'une ressource par son identifiant
 *     </li>
 *     <li>
 *         Création d'une nouvelle ressource à partir d'un DTO de requête
 *     </li>
 *     <li>
 *         Mise à jour d'une ressource existante
 *     </li>
 *     <li>
 *         Suppression d'une ressource
 *     </li>
 * </ul>
 *
 * Cette abstraction permet de réutiliser la même structure CRUD pour
 * plusieurs domaines métier (par exemple : Personne, Location, Acte, etc.).
 *
 * Les types génériques utilisés sont :
 * <ul>
 *     <li>
 *         {@code R} : DTO utilisé pour les réponses retournées par le service
 *     </li>
 *     <li>
 *         {@code Q} : DTO utilisé pour les données reçues lors des créations
 *         et modifications
 *     </li>
 * </ul>
 *
 * Exemple d'utilisation :
 *
 * <pre>
 * {@code
 * public interface CitoyenService
 *        extends Service<CitoyenResponseDTO, CitoyenRequestDTO> {
 * }
 * </pre>
 *
 * @param <R> type du DTO de réponse
 * @param <Q> type du DTO de requête
 */
public interface Service<
                    R, // Response DTO 
                    Q // Request DTO
                    > {

    List<R> findAll();



    R findById(Long id);



    R save(Q dto);



    R update(Long id, Q dto);


    
    void delete(Long id);
}
