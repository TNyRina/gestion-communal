package ny.rina.gestioncommune.population.officierEtat;

import org.springframework.web.bind.annotation.*;

import ny.rina.gestioncommune.population.officierEtat.dto.OfficierEtatRequestDTO;
import ny.rina.gestioncommune.population.officierEtat.dto.OfficierEtatResponseDTO;
import ny.rina.gestioncommune.population.personne.PersonneController;

@RestController
@RequestMapping("/api/officiers")
public class OfficierEtatController extends PersonneController<OfficierEtat, OfficierEtatResponseDTO, OfficierEtatRequestDTO, OfficierEtatServiceImpl>{

    protected OfficierEtatController(OfficierEtatServiceImpl service) {
        super(service);
    }}