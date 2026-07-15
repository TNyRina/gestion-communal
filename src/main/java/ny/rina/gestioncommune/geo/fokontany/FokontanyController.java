package ny.rina.gestioncommune.geo.fokontany;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ny.rina.gestioncommune.geo.fokontany.dto.FokontanyDTO;
import ny.rina.gestioncommune.geo.location.LocationController;

import java.util.List;

@RestController
@RequestMapping("/api/fokontany")
public class FokontanyController
        extends LocationController<
                Fokontany,
                FokontanyDTO,
                FokontanyService> {

    public FokontanyController(
            FokontanyService service) {
        super(service);
    }


    /**
     * Crée plusieurs Fokontany en une seule requête.
     *
     * Cette opération permet d'enregistrer une liste de Fokontany à partir
     * des DTOs fournis dans le corps de la requête. Chaque élément est traité
     * par le service de création, puis la liste des Fokontany créés est
     * retournée au client.
     *
     * Cette méthode est particulièrement utile pour les imports de données
     * ou les créations en lot.
     *
     * @param dtos liste des DTOs représentant les Fokontany à créer
     * @return une réponse HTTP 201 (Created) contenant la liste des
     *         Fokontany créés
     */
    @PostMapping("/bulk")
    public ResponseEntity<List<FokontanyDTO>> createMany(
            @RequestBody List<FokontanyDTO> dtos) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createMany(dtos));
    }
}