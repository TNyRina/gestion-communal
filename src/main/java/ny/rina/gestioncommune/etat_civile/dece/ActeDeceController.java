package ny.rina.gestioncommune.etat_civile.dece;

import org.springframework.web.bind.annotation.*;

import ny.rina.gestioncommune.core.Controller;
import ny.rina.gestioncommune.etat_civile.dece.dto.ActeDeceRequestDTO;
import ny.rina.gestioncommune.etat_civile.dece.dto.ActeDeceResponseDTO;

@RestController
@RequestMapping("/api/actes/dece")
public class ActeDeceController extends Controller<ActeDece, ActeDeceResponseDTO, ActeDeceRequestDTO, ActeDeceServiceImpl>{

    protected ActeDeceController(ActeDeceServiceImpl service) {
        super(service);
    }
    
}
