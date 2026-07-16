package ny.rina.gestioncommune.etat_civile.mariage;

import java.util.List;

import org.springframework.stereotype.Service;

import ny.rina.gestioncommune.core.service.ServiceImpl;
import ny.rina.gestioncommune.etat_civile.mariage.dto.ActeMariageMapperDTO;
import ny.rina.gestioncommune.etat_civile.mariage.dto.ActeMariageRequestDTO;
import ny.rina.gestioncommune.etat_civile.mariage.dto.ActeMariageResponseDTO;
import ny.rina.gestioncommune.geo.commune.Commune;
import ny.rina.gestioncommune.geo.commune.CommuneRepository;
import ny.rina.gestioncommune.population.citoyen.Citoyen;
import ny.rina.gestioncommune.population.citoyen.CitoyenRepository;
import ny.rina.gestioncommune.population.officierEtat.OfficierEtat;
import ny.rina.gestioncommune.population.officierEtat.OfficierEtatRepository;

@Service
public class ActeMariageServiceImpl extends ServiceImpl<ActeMariage, ActeMariageResponseDTO, ActeMariageRequestDTO> {


    private final CommuneRepository communeRepository;
    private final OfficierEtatRepository officierEtatRepository;
    private final CitoyenRepository citoyenRepository;


    protected ActeMariageServiceImpl(ActeMariageRepository repository, CommuneRepository communeRepository, OfficierEtatRepository officierEtatRepository, CitoyenRepository citoyenRepository) {
        super(repository);
        this.communeRepository = communeRepository;
        this.officierEtatRepository = officierEtatRepository;
        this.citoyenRepository = citoyenRepository;
        
    }

    @Override
    public ActeMariageResponseDTO save(ActeMariageRequestDTO dto) {
        ActeMariage acte = new ActeMariage();

        return ActeMariageMapperDTO.toResponseDTO(
            repository.save(
                toEntity(acte, dto)
            )
        );
    }

    @Override
    public ActeMariageResponseDTO update(Long id, ActeMariageRequestDTO dto) {
        ActeMariage acte = repository.findById(id).orElseThrow(() ->
                    new RuntimeException("Acte Introuvable"));
        
        return ActeMariageMapperDTO.toResponseDTO(
            repository.save(
                toEntity(acte, dto)
            )
        );
    }

    @Override
    protected ActeMariageResponseDTO toResponseDTO(ActeMariage entity) {
        return ActeMariageMapperDTO.toResponseDTO(entity);
    }
    

    private ActeMariage toEntity(ActeMariage acte, ActeMariageRequestDTO dto){
        acte.setNumero(dto.getNumero());
        acte.setDateEtablissement(dto.getDateEtablissement());

        
        Commune commune = communeRepository.findById(dto.getCommuneId()).orElseThrow(() ->
                        new RuntimeException("Commune Introuvable!"));
        acte.setCommune(commune);
        


        OfficierEtat officier = officierEtatRepository.findById(dto.getOfficierId()).orElseThrow(() ->
                        new RuntimeException("Officer Introuvable!"));
        acte.setOfficierEtat(officier);



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

        return acte;
    }
}
