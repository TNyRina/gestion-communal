package ny.rina.gestioncommune.population.personne;

import ny.rina.gestioncommune.core.Controller;
import ny.rina.gestioncommune.core.service.ServiceImpl;


public class PersonneController<E, R, Q, S extends ServiceImpl<E,R,Q>> extends Controller<E, R, Q, S>{

    protected PersonneController(S service) {
        super(service);
    }}