package ny.rina.gestioncommune.geo.location;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ny.rina.gestioncommune.geo.location.dto.LocationDTO;
import ny.rina.gestioncommune.geo.location.service.LocationService;


/**
 * Contrôleur REST générique fournissant les opérations CRUD de base
 * pour les ressources de type {@link Location}.
 *
 * Cette classe centralise les points d'entrée HTTP communs afin d'éviter
 * la duplication du code dans les contrôleurs des différentes entités
 * de localisation (par exemple : CommuneController, FokontanyController, etc.).
 *
 * Les opérations REST suivantes sont implémentées :
 * <ul>
 *     <li>POST : création d'une localisation</li>
 *     <li>GET : récupération de toutes les localisations</li>
 *     <li>GET /{id} : récupération d'une localisation par son identifiant</li>
 *     <li>PUT /{id} : mise à jour d'une localisation</li>
 *     <li>DELETE /{id} : suppression d'une localisation</li>
 * </ul>
 *
 * Les contrôleurs concrets héritent de cette classe et injectent simplement
 * le service correspondant.
 *
 * Exemple :
 *
 * <pre>
 * {@code
 * @RestController
 * @RequestMapping("/api/communes")
 * public class CommuneController
 *         extends LocationController<
 *                 Commune,
 *                 CommuneDTO,
 *                 CommuneService> {
 *
 *     public CommuneController(CommuneService service) {
 *         super(service);
 *     }
 * }
 * </pre>
 *
 * @param <E> type de l'entité de localisation
 * @param <D> type du DTO associé
 * @param <S> type du service chargé des opérations métier
 */
public abstract class LocationController<
        E extends Location,
        D extends LocationDTO,
        S extends LocationService<E, D>> {

    protected final S service;

    protected LocationController(S service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<D> create(
            @RequestBody D dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<D>> findAll() {

        return ResponseEntity.ok(
                service.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.findById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<D> update(
            @PathVariable Long id,
            @RequestBody D dto) {

        return ResponseEntity.ok(
                service.update(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
