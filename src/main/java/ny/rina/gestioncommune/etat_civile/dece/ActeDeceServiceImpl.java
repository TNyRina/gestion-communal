package ny.rina.gestioncommune.etat_civile.dece;

import org.springframework.stereotype.Service;

import ny.rina.gestioncommune.etat_civile.acte.ActeServiceImpl;
import ny.rina.gestioncommune.etat_civile.dece.dto.ActeDeceMapperDTO;
import ny.rina.gestioncommune.etat_civile.dece.dto.ActeDeceRequestDTO;
import ny.rina.gestioncommune.etat_civile.dece.dto.ActeDeceResponseDTO;
import ny.rina.gestioncommune.geo.commune.CommuneRepository;
import ny.rina.gestioncommune.population.citoyen.Citoyen;
import ny.rina.gestioncommune.population.citoyen.CitoyenRepository;
import ny.rina.gestioncommune.population.officierEtat.OfficierEtatRepository;


@Service
public class ActeDeceServiceImpl extends ActeServiceImpl<
                                                            ActeDece, 
                                                            ActeDeceResponseDTO, 
                                                            ActeDeceRequestDTO
                                                        > {


                                                            
    private final CitoyenRepository citoyenRepository;

    protected ActeDeceServiceImpl(
        ActeDeceRepository repository, 
        CommuneRepository communeRepository,
        OfficierEtatRepository officierEtatRepository, 
        CitoyenRepository citoyenRepository) {
        
            super(
                repository, 
                communeRepository, 
                officierEtatRepository, 
                ActeDece::new);
            this.citoyenRepository = citoyenRepository;
    }

    @Override
    protected ActeDeceResponseDTO toResponseDTO(ActeDece entity) {
        return ActeDeceMapperDTO.toResponseDTO(entity);
    }


     @Override
    protected void toEntity(ActeDece acte, ActeDeceRequestDTO dto){
        toActeEntity(acte, dto);
        
        Citoyen dece = citoyenRepository.findById(dto.getDeceId()).orElseThrow(() ->
                        new RuntimeException("Dece Introuvable!"));
        acte.setDece(dece);

    }
    
}
