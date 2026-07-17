package ny.rina.gestioncommune.population.officierEtat;

import org.springframework.stereotype.Service;

import ny.rina.gestioncommune.geo.commune.CommuneRepository;
import ny.rina.gestioncommune.population.agentCommunale.AgentCommunaleServiceImpl;
import ny.rina.gestioncommune.population.officierEtat.dto.OfficierEtatRequestDTO;
import ny.rina.gestioncommune.population.officierEtat.dto.OfficierEtatResponseDTO;
import ny.rina.gestioncommune.population.officierEtat.dto.OffierEtatMapper;


@Service
public class OfficierEtatServiceImpl extends AgentCommunaleServiceImpl<
                                                OfficierEtat, OfficierEtatResponseDTO, OfficierEtatRequestDTO, OfficierEtatRepository> {

    protected OfficierEtatServiceImpl(OfficierEtatRepository officerEtatrepository, CommuneRepository communeRepository) {
        super(officerEtatrepository, communeRepository ,OfficierEtat::new);
    }

    @Override
    protected OfficierEtatResponseDTO toResponseDTO(OfficierEtat officier) {
        return OffierEtatMapper.toResponseDTO(officier);
    }

    @Override
    protected void toEntity(OfficierEtat officier, OfficierEtatRequestDTO dto) {
        toAgentCommunaleEntity(officier, dto);
    }

   

    
    
}
