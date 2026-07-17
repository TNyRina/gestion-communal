package ny.rina.gestioncommune.population.officierEtat;

import org.springframework.stereotype.Service;

import ny.rina.gestioncommune.geo.commune.CommuneRepository;
import ny.rina.gestioncommune.population.agentCommunale.AgentCommunaleServiceImpl;
import ny.rina.gestioncommune.population.officierEtat.dto.OfficierEtatRequestDTO;
import ny.rina.gestioncommune.population.officierEtat.dto.OfficierEtatResponseDTO;
import ny.rina.gestioncommune.population.officierEtat.dto.OffierEtatMapper;




/**
 * Service métier permettant de gérer les officiers d'état civil.
 *
 * Cette classe étend {@link AgentCommunaleServiceImpl} afin de réutiliser
 * la logique commune de gestion des agents communaux, notamment :
 * <ul>
 *     <li>la gestion des informations personnelles de l'agent</li>
 *     <li>la gestion du matricule</li>
 *     <li>la gestion de la date d'embauche</li>
 *     <li>l'association avec une commune</li>
 * </ul>
 *
 * Elle représente le service spécifique aux officiers d'état civil qui sont
 * des agents communaux ayant une responsabilité particulière dans la gestion
 * des actes d'état civil.
 *
 * Les opérations de conversion entre l'entité {@link OfficierEtat} et les DTO
 * associés sont déléguées à {@link OffierEtatMapper}.
 *
 * Cette classe utilise :
 * <ul>
 *     <li>{@link OfficierEtat} comme entité métier</li>
 *     <li>{@link OfficierEtatResponseDTO} comme DTO de réponse</li>
 *     <li>{@link OfficierEtatRequestDTO} comme DTO de requête</li>
 *     <li>{@link OfficierEtatRepository} comme repository de persistance</li>
 * </ul>
 *
 * Exemple d'utilisation :
 *
 * <pre>
 * {@code
 * OfficierEtatServiceImpl service;
 * }
 * </pre>
 */
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
