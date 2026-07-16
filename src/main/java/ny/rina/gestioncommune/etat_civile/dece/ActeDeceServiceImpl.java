package ny.rina.gestioncommune.etat_civile.dece;

import ny.rina.gestioncommune.etat_civile.acte.ActeServiceImpl;
import ny.rina.gestioncommune.etat_civile.dece.dto.ActeDeceMapperDTO;
import ny.rina.gestioncommune.etat_civile.dece.dto.ActeDeceRequestDTO;
import ny.rina.gestioncommune.etat_civile.dece.dto.ActeDeceResponseDTO;
import ny.rina.gestioncommune.geo.commune.CommuneRepository;
import ny.rina.gestioncommune.population.citoyen.Citoyen;
import ny.rina.gestioncommune.population.citoyen.CitoyenRepository;
import ny.rina.gestioncommune.population.officierEtat.OfficierEtatRepository;

public class ActeDeceServiceImpl extends ActeServiceImpl<ActeDece, ActeDeceResponseDTO, ActeDeceRequestDTO>{

    private final CitoyenRepository citoyenRepository;
    private final ActeDeceRepository repository;

    protected ActeDeceServiceImpl(ActeDeceRepository repository, CommuneRepository communeRepository,
            OfficierEtatRepository officierEtatRepository, CitoyenRepository citoyenRepository) {
        super(repository, communeRepository, officierEtatRepository);
        this.repository = repository;
        this.citoyenRepository = citoyenRepository;
    }

    @Override
    public ActeDeceResponseDTO save(ActeDeceRequestDTO dto) {
        ActeDece acte = new ActeDece();

        return ActeDeceMapperDTO.toResponseDTO(repository.save(map(acte, dto)));
    }

    @Override
    public ActeDeceResponseDTO update(Long id, ActeDeceRequestDTO dto) {
        ActeDece acte = repository.findById(id).orElseThrow(() ->
                        new RuntimeException("Dece Introuvable!"));
        

        return ActeDeceMapperDTO.toResponseDTO(repository.save(map(acte, dto)));
    }

    @Override
    protected ActeDeceResponseDTO toResponseDTO(ActeDece entity) {
        return ActeDeceMapperDTO.toResponseDTO(entity);
    }

    private ActeDece map(ActeDece acte, ActeDeceRequestDTO dto){
        acte = super.toEntity(acte, dto);
        
        Citoyen dece = citoyenRepository.findById(dto.getDeceId()).orElseThrow(() ->
                        new RuntimeException("Dece Introuvable!"));
        acte.setDece(dece);

        return acte;
    }
    
}
