package ny.rina.gestioncommune.etat_civile.naissance;

import org.springframework.web.bind.annotation.*;

import ny.rina.gestioncommune.core.Controller;
import ny.rina.gestioncommune.etat_civile.naissance.dto.ActeNaissanceRequestDTO;
import ny.rina.gestioncommune.etat_civile.naissance.dto.ActeNaissanceResponseDTO;


@RestController
@RequestMapping("/api/actes/naissance")
public class ActeNaissanceController extends Controller<ActeNaissance, ActeNaissanceResponseDTO, ActeNaissanceRequestDTO, ActeNaissanceServiceImpl> {

    protected ActeNaissanceController(ActeNaissanceServiceImpl service) {
        super(service);
    }
    
}
