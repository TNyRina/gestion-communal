package ny.rina.gestioncommune.etat_civile.mariage;

import java.util.List;

import org.springframework.stereotype.Service;
import ny.rina.gestioncommune.etat_civile.acte.ActeServiceImpl;
import ny.rina.gestioncommune.etat_civile.mariage.dto.ActeMariageMapperDTO;
import ny.rina.gestioncommune.etat_civile.mariage.dto.ActeMariageRequestDTO;
import ny.rina.gestioncommune.etat_civile.mariage.dto.ActeMariageResponseDTO;
import ny.rina.gestioncommune.geo.commune.CommuneRepository;
import ny.rina.gestioncommune.population.citoyen.Citoyen;
import ny.rina.gestioncommune.population.citoyen.CitoyenRepository;
import ny.rina.gestioncommune.population.officierEtat.OfficierEtatRepository;

@Service
public class ActeMariageServiceImpl extends ActeServiceImpl<
                                                                ActeMariage, 
                                                                ActeMariageResponseDTO, 
                                                                ActeMariageRequestDTO> {

    private final CitoyenRepository citoyenRepository;


    protected ActeMariageServiceImpl(
                                        ActeMariageRepository repository, 
                                        CommuneRepository communeRepository, 
                                        OfficierEtatRepository officierEtatRepository, 
                                        CitoyenRepository citoyenRepository) {

        super(
            repository, 
            communeRepository, 
            officierEtatRepository, 
            ActeMariage::new);
        this.citoyenRepository = citoyenRepository;
        
    }


    @Override
    protected ActeMariageResponseDTO toResponseDTO(ActeMariage entity) {
        return ActeMariageMapperDTO.toResponseDTO(entity);
    }
    
    @Override
    protected void toEntity(ActeMariage acte, ActeMariageRequestDTO dto){
        toActeEntity(acte, dto);

        Citoyen mari = citoyenRepository.findById(dto.getMariId()).orElseThrow(() ->
                        new RuntimeException("Mari Introuvable!"));
        acte.setMari(mari);

        Citoyen femme = citoyenRepository.findById(dto.getFemmeId()).orElseThrow(() ->
                        new RuntimeException("Femme Introuvable!"));
        acte.setFemme(femme);

        List<Citoyen> temoins = dto.getTemoinsId() == null
                                    ? List.of()
                                    : dto.getTemoinsId()
                                        .stream()
                                        .map((id) -> citoyenRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("Mari Introuvable!")))
                                        .toList();

        acte.setTemoins(temoins);
    }
}
