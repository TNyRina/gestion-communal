package ny.rina.gestioncommune.population.citoyen;

import org.springframework.web.bind.annotation.*;

import ny.rina.gestioncommune.population.citoyen.dto.CitoyenRequestDTO;
import ny.rina.gestioncommune.population.citoyen.dto.CitoyenResponseDTO;
import ny.rina.gestioncommune.population.personne.PersonneController;


@RestController
@RequestMapping("/api/citoyens")
public class CitoyenController extends PersonneController<Citoyen, CitoyenResponseDTO, CitoyenRequestDTO, CitoyenServiceImpl> {

    protected CitoyenController(CitoyenServiceImpl service) {
        super(service);
    }
}
