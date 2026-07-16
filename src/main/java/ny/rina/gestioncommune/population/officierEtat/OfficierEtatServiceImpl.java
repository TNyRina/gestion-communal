package ny.rina.gestioncommune.population.officierEtat;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import ny.rina.gestioncommune.geo.commune.Commune;
import ny.rina.gestioncommune.geo.commune.CommuneRepository;
import ny.rina.gestioncommune.population.agentCommunale.AgentCommunaleServiceImpl;
import ny.rina.gestioncommune.population.officierEtat.dto.OfficierEtatRequestDTO;
import ny.rina.gestioncommune.population.officierEtat.dto.OfficierEtatResponseDTO;
import ny.rina.gestioncommune.population.officierEtat.dto.OffierEtatMapper;
import ny.rina.gestioncommune.population.personne.dto.PersonneMapperDTO;


@Service
public class OfficierEtatServiceImpl extends AgentCommunaleServiceImpl<OfficierEtat, OfficierEtatResponseDTO, OfficierEtatRequestDTO, OfficierEtatRepository> {

    private final OfficierEtatRepository officerEtatrepository;
    private final CommuneRepository communeRepository;

    protected OfficierEtatServiceImpl(OfficierEtatRepository officerEtatrepository, CommuneRepository communeRepository) {
        super(officerEtatrepository);
        this.officerEtatrepository = officerEtatrepository;
        this.communeRepository = communeRepository;
    }

    @Override
    public OfficierEtatResponseDTO save(OfficierEtatRequestDTO dto) {
        OfficierEtat officier = new OfficierEtat();
        officier = PersonneMapperDTO.toEntity(dto, OfficierEtat::new);

       
        officier.setMatricule(dto.getMatricule());
        officier.setDateEmbauche(dto.getDateEmbauche());

        Commune commune = communeRepository.findById(dto.getCommuneId()).orElseThrow( () -> new EntityNotFoundException(
                    "Commune introuvable"
                ));
        officier.setCommune(commune);

        return OffierEtatMapper.toResponseDTO(this.officerEtatrepository.save(officier));
    }

    @Override
    public OfficierEtatResponseDTO update(Long id, OfficierEtatRequestDTO dto) {
        OfficierEtat officier = this.officerEtatrepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                        "Officier d'Etat introuvable"
                    ));
        officier = PersonneMapperDTO.updateEntity(officier, dto);

        officier.setMatricule(dto.getMatricule());
        officier.setDateEmbauche(dto.getDateEmbauche());

        Commune commune = communeRepository.findById(dto.getCommuneId()).orElseThrow( () -> new EntityNotFoundException(
                    "Commune introuvable"
                ));
        officier.setCommune(commune);

        return OffierEtatMapper.toResponseDTO(this.officerEtatrepository.save(officier));


    }

    @Override
    protected OfficierEtatResponseDTO toResponseDTO(OfficierEtat officier) {
        return OffierEtatMapper.toResponseDTO(officier);
    }

   

    
    
}
