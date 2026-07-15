package ny.rina.gestioncommune.geo.commune;
import org.springframework.web.bind.annotation.*;

import ny.rina.gestioncommune.geo.commune.dto.CommuneDTO;
import ny.rina.gestioncommune.geo.location.LocationController;

@RestController
@RequestMapping("/api/communes")
public class CommuneController
        extends LocationController<
                Commune,
                CommuneDTO,
                CommuneService> {

    public CommuneController(
            CommuneService service) {
        super(service);
    }
}
