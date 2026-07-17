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

/**
 * Service métier permettant de gérer les opérations relatives aux actes
 * de naissance.
 *
 * Cette classe étend {@link ActeServiceImpl} afin de réutiliser la logique
 * commune de gestion des actes d'état civil, notamment :
 * <ul>
 *     <li>la création d'un acte</li>
 *     <li>la modification d'un acte existant</li>
 *     <li>la gestion des informations communes aux actes</li>
 *     <li>l'association avec une commune et un officier d'état civil</li>
 * </ul>
 *
 * Elle ajoute la logique spécifique aux actes de naissance, notamment :
 * <ul>
 *     <li>l'association de l'enfant concerné par l'acte</li>
 *     <li>l'association de la mère de l'enfant</li>
 *     <li>l'association du père de l'enfant</li>
 * </ul>
 *
 * Les conversions entre l'entité {@link ActeNaissance} et ses DTOs sont
 * déléguées à {@link ActeNaissanceMapperDTO}.
 *
 * Cette classe utilise :
 * <ul>
 *     <li>{@code ActeNaissance} comme entité métier</li>
 *     <li>{@link ActeNaissanceResponseDTO} comme DTO de réponse</li>
 *     <li>{@link ActeNaissanceRequestDTO} comme DTO de requête</li>
 * </ul>
 *
 * Exemple d'utilisation :
 *
 * <pre>
 * {@code
 * ActeNaissanceServiceImpl service;
 * }
 * </pre>
 */
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
