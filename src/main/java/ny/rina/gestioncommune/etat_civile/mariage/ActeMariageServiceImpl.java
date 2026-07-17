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


/**
 * Service métier permettant de gérer les opérations relatives aux actes
 * de mariage.
 *
 * Cette classe étend {@link ActeServiceImpl} afin de réutiliser la logique
 * commune de gestion des actes d'état civil, notamment :
 * <ul>
 *     <li>la création d'un acte</li>
 *     <li>la modification d'un acte existant</li>
 *     <li>la gestion des informations communes d'un acte</li>
 *     <li>la récupération de la commune et de l'officier d'état civil</li>
 * </ul>
 *
 * Elle ajoute les traitements spécifiques à l'acte de mariage :
 * <ul>
 *     <li>association du mari</li>
 *     <li>association de la femme</li>
 *     <li>association des témoins du mariage</li>
 * </ul>
 *
 * Les conversions entre l'entité {@link ActeMariage} et les DTOs associés
 * sont déléguées à {@link ActeMariageMapperDTO}.
 *
 * Cette classe utilise :
 * <ul>
 *     <li>{@code ActeMariage} comme entité métier</li>
 *     <li>{@link ActeMariageResponseDTO} comme DTO de réponse</li>
 *     <li>{@link ActeMariageRequestDTO} comme DTO de requête</li>
 * </ul>
 *
 * Exemple d'utilisation :
 *
 * <pre>
 * {@code
 * ActeMariageServiceImpl service;
 * }
 * </pre>
 */
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
                        new RuntimeException("Temoin Introuvable!")))
                                        .toList();

        acte.setTemoins(temoins);
    }
}
