package ny.rina.gestioncommune.etat_civile.naissance;

import org.springframework.stereotype.Service;

import ny.rina.gestioncommune.etat_civile.acte.ActeServiceImpl;
import ny.rina.gestioncommune.etat_civile.naissance.dto.ActeNaissanceMapperDTO;
import ny.rina.gestioncommune.etat_civile.naissance.dto.ActeNaissanceRequestDTO;
import ny.rina.gestioncommune.etat_civile.naissance.dto.ActeNaissanceResponseDTO;
import ny.rina.gestioncommune.geo.commune.CommuneRepository;
import ny.rina.gestioncommune.population.citoyen.Citoyen;
import ny.rina.gestioncommune.population.citoyen.CitoyenRepository;
import ny.rina.gestioncommune.population.officierEtat.OfficierEtatRepository;


@Service
public class ActeNaissanceServiceImpl extends ActeServiceImpl<
                                                                ActeNaissance, 
                                                                ActeNaissanceResponseDTO, 
                                                                ActeNaissanceRequestDTO>{
    
    private final CitoyenRepository citoyenRepository;



    protected ActeNaissanceServiceImpl(
                                        ActeNaissanceRepository repository, 
                                        CommuneRepository communeRepository, 
                                        OfficierEtatRepository officierEtatRepository, 
                                        CitoyenRepository citoyenRepository) {
        super(
                repository, 
                communeRepository, 
                officierEtatRepository, 
                ActeNaissance::new);

        this.citoyenRepository = citoyenRepository;
    }


    @Override
    protected ActeNaissanceResponseDTO toResponseDTO(ActeNaissance entity) {
        return ActeNaissanceMapperDTO.toResponseDTO(entity);
    }

    @Override
    protected void toEntity(ActeNaissance acte, ActeNaissanceRequestDTO dto){
        toActeEntity(acte, dto);

        Citoyen enfant = citoyenRepository.findById(dto.getEnfantId()).orElseThrow(() ->
                        new RuntimeException("Enfant Introuvable!"));
        acte.setEnfant(enfant);



        Citoyen mere = citoyenRepository.findById(dto.getMereId()).orElseThrow(() ->
                        new RuntimeException("Mere Introuvable!"));
        acte.setMere(mere);

        Citoyen pere = citoyenRepository.findById(dto.getPereId()).orElseThrow(() ->
                        new RuntimeException("Pere Introuvable!"));
        acte.setPere(pere);

    }
    
}
