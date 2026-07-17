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



/**
 * Service métier permettant de gérer les opérations relatives aux actes de décès.
 *
 * Cette classe étend {@link ActeServiceImpl} afin de réutiliser la logique
 * commune de gestion des actes d'état civil, notamment :
 * <ul>
 *     <li>la création d'un acte</li>
 *     <li>la mise à jour d'un acte existant</li>
 *     <li>la gestion des informations communes aux actes</li>
 *     <li>la résolution de la commune et de l'officier d'état civil associés</li>
 * </ul>
 *
 * Elle ajoute la logique spécifique à l'acte de décès, notamment la gestion
 * de la personne décédée associée à l'acte.
 *
 * La conversion entre l'entité {@link ActeDece} et ses DTOs est déléguée
 * à {@link ActeDeceMapperDTO}.
 *
 * Cette classe utilise :
 * <ul>
 *     <li>
 *         {@code ActeDece} comme entité métier
 *     </li>
 *     <li>
 *         {@link ActeDeceResponseDTO} comme DTO de réponse API
 *     </li>
 *     <li>
 *         {@link ActeDeceRequestDTO} comme DTO de requête API
 *     </li>
 * </ul>
 *
 * Exemple d'utilisation :
 *
 * <pre>
 * {@code
 * ActeDeceServiceImpl service =
 *      new ActeDeceServiceImpl(...);
 * }
 * </pre>
 */
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
