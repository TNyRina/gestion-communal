package ny.rina.gestioncommune.population.personne;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ny.rina.gestioncommune.population.personne.service.PersonneServiceImpl;

/**
 * Contrôleur REST générique permettant de gérer les opérations CRUD
 * communes aux entités héritant de {@code Personne}.
 *
 * Cette classe factorise les endpoints REST partagés par les différentes
 * ressources liées aux personnes afin d'éviter la duplication du code dans
 * les contrôleurs spécialisés (par exemple : CitoyenController,
 * AgentCommunaleController, etc.).
 *
 * Les opérations HTTP exposées sont :
 * <ul>
 *     <li>
 *         GET : récupérer la liste de toutes les personnes
 *     </li>
 *     <li>
 *         GET /{id} : rechercher une personne par son identifiant
 *     </li>
 *     <li>
 *         POST : créer une nouvelle personne
 *     </li>
 *     <li>
 *         PUT /{id} : modifier une personne existante
 *     </li>
 *     <li>
 *         DELETE /{id} : supprimer une personne
 *     </li>
 * </ul>
 *
 * Cette classe utilise des types génériques afin d'être réutilisable pour
 * différentes implémentations de personnes.
 *
 * Paramètres génériques :
 * <ul>
 *     <li>
 *         {@code E} : type de l'entité manipulée
 *     </li>
 *     <li>
 *         {@code R} : type du DTO utilisé pour les réponses API
 *     </li>
 *     <li>
 *         {@code Q} : type du DTO utilisé pour les requêtes API
 *     </li>
 *     <li>
 *         {@code S} : type du service associé, héritant de
 *         {@link PersonneServiceImpl}
 *     </li>
 * </ul>
 *
 * Exemple d'utilisation :
 *
 * <pre>
 * {@code
 * @RestController
 * @RequestMapping("/api/citoyens")
 * public class CitoyenController extends PersonneController<
 *          Citoyen,
 *          CitoyenResponseDTO,
 *          CitoyenRequestDTO,
 *          CitoyenServiceImpl> {
 *
 *     public CitoyenController(CitoyenServiceImpl service) {
 *         super(service);
 *     }
 * }
 * }
 * </pre>
 *
 * Cette approche permet de centraliser la logique REST commune tout en
 * laissant aux contrôleurs spécialisés la possibilité d'ajouter des
 * comportements spécifiques.
 *
 * @param <E> type de l'entité Personne
 * @param <R> type du DTO de réponse
 * @param <Q> type du DTO de requête
 * @param <S> type du service associé
 */
public abstract class PersonneController<
        E, // Entity
        R, // Response DTO
        Q, // Request DTO
        S extends PersonneServiceImpl<E,R, Q>
> {


    protected final S service;


    protected PersonneController(S service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<R>> findAll(){

        return ResponseEntity.ok(
                service.findAll()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<R> findById(
            @PathVariable Long id
    ){

        return ResponseEntity.ok(
                service.findById(id)
        );
    }


    @PostMapping
    public ResponseEntity<R> save(
            @RequestBody Q dto
    ){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        service.save(dto)
                );
    }


    @PutMapping("/{id}")
    public ResponseEntity<R> update(
            @PathVariable Long id,
            @RequestBody Q dto
    ){

        return ResponseEntity.ok(
                service.update(id, dto)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ){

        service.delete(id);

        return ResponseEntity.noContent()
                .build();
    }
}