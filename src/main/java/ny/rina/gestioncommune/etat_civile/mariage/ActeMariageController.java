package ny.rina.gestioncommune.etat_civile.mariage;

import org.springframework.web.bind.annotation.*;

import ny.rina.gestioncommune.core.Controller;
import ny.rina.gestioncommune.etat_civile.mariage.dto.ActeMariageRequestDTO;
import ny.rina.gestioncommune.etat_civile.mariage.dto.ActeMariageResponseDTO;

@RestController
@RequestMapping("/api/actes/mariage")
public class ActeMariageController extends Controller<ActeMariage, ActeMariageResponseDTO, ActeMariageRequestDTO, ActeMariageServiceImpl>{

    protected ActeMariageController(ActeMariageServiceImpl service) {
        super(service);
    }
    
}
