package ny.rina.gestioncommune.core;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import ny.rina.gestioncommune.core.service.ServiceImpl;



/**
 * Contrôleur REST générique fournissant les opérations CRUD communes
 * pour les différentes ressources de l'application.
 *
 * Cette classe permet de centraliser les endpoints HTTP standards afin
 * d'éviter la duplication du code dans les contrôleurs spécialisés.
 *
 * Elle expose les opérations REST suivantes :
 * <ul>
 *     <li>
 *         GET : récupérer la liste de toutes les ressources
 *     </li>
 *     <li>
 *         GET /{id} : rechercher une ressource par son identifiant
 *     </li>
 *     <li>
 *         POST : créer une nouvelle ressource
 *     </li>
 *     <li>
 *         PUT /{id} : modifier une ressource existante
 *     </li>
 *     <li>
 *         DELETE /{id} : supprimer une ressource
 *     </li>
 * </ul>
 *
 * Cette classe utilise des paramètres génériques afin d'être réutilisable
 * pour différents domaines métier.
 *
 * Paramètres génériques :
 * <ul>
 *     <li>
 *         {@code E} : type de l'entité manipulée
 *     </li>
 *     <li>
 *         {@code R} : type du DTO retourné dans les réponses API
 *     </li>
 *     <li>
 *         {@code Q} : type du DTO reçu dans les requêtes API
 *     </li>
 *     <li>
 *         {@code S} : type du service associé, héritant de
 *         {@link ServiceImpl}
 *     </li>
 * </ul>
 *
 * Exemple d'utilisation :
 *
 * <pre>
 * {@code
 * @RestController
 * @RequestMapping("/api/citoyens")
 * public class CitoyenController extends Controller<
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
 * Les contrôleurs spécialisés peuvent ainsi uniquement définir leur chemin
 * d'accès HTTP et ajouter des traitements spécifiques si nécessaire.
 *
 * @param <E> type de l'entité métier
 * @param <R> type du DTO de réponse
 * @param <Q> type du DTO de requête
 * @param <S> type du service associé
 */
public abstract class Controller<
        E, // Entity
        R, // Response DTO
        Q, // Request DTO
        S extends ServiceImpl<E,R, Q>
> {


    protected final S service;


    protected Controller(S service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<R>> findAll(){

        return ResponseEntity.ok(
                service.findAll()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<R> findById(@PathVariable Long id){

        return ResponseEntity.ok(
                service.findById(id)
        );
    }


    @PostMapping
    public ResponseEntity<R> save(@RequestBody Q dto){

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
    public ResponseEntity<Void> delete(@PathVariable Long id){

        service.delete(id);

        return ResponseEntity.noContent()
                .build();
    }
}