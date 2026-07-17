package ny.rina.gestioncommune.population.citoyen;

import org.springframework.stereotype.Service;

import ny.rina.gestioncommune.geo.fokontany.Fokontany;
import ny.rina.gestioncommune.geo.fokontany.FokontanyRepository;
import ny.rina.gestioncommune.population.citoyen.dto.CitoyenMapper;
import ny.rina.gestioncommune.population.citoyen.dto.CitoyenRequestDTO;
import ny.rina.gestioncommune.population.citoyen.dto.CitoyenResponseDTO;
import ny.rina.gestioncommune.population.personne.Personne;
import ny.rina.gestioncommune.population.personne.PersonneServiceImpl;

/**
 * Service métier permettant de gérer les opérations relatives aux citoyens.
 *
 * Cette classe étend {@link PersonneServiceImpl} afin de réutiliser la logique
 * commune de gestion des personnes, notamment :
 * <ul>
 *     <li>la création d'un citoyen</li>
 *     <li>la modification d'un citoyen existant</li>
 *     <li>la conversion des informations communes d'une personne</li>
 * </ul>
 *
 * Elle ajoute les traitements spécifiques à l'entité {@link Citoyen} :
 * <ul>
 *     <li>la profession</li>
 *     <li>la situation familiale</li>
 *     <li>l'association avec un Fokontany</li>
 * </ul>
 *
 * La conversion entre l'entité {@link Citoyen} et les DTO associés est
 * déléguée à {@link CitoyenMapper}.
 *
 * Cette classe utilise :
 * <ul>
 *     <li>{@link Citoyen} comme entité métier</li>
 *     <li>{@link CitoyenResponseDTO} comme DTO de réponse</li>
 *     <li>{@link CitoyenRequestDTO} comme DTO de requête</li>
 * </ul>
 *
 * Exemple d'utilisation :
 *
 * <pre>
 * {@code
 * CitoyenServiceImpl service;
 * }
 * </pre>
 */
@Service
public class CitoyenServiceImpl 
        extends PersonneServiceImpl<Citoyen, CitoyenResponseDTO, CitoyenRequestDTO> {


    private final FokontanyRepository fokontanyRepository;



    public CitoyenServiceImpl(
            CitoyenRepository citoyenRepository,
            FokontanyRepository fokontanyRepository
    ){
        super(citoyenRepository, Citoyen::new);
        this.fokontanyRepository = fokontanyRepository;
    }



	@Override
	protected CitoyenResponseDTO toResponseDTO(Citoyen citoyen) {
		return CitoyenMapper.toResponseDTO(citoyen);
	}




    /**
     * Convertit un DTO de requête en entité {@link Citoyen}.
     *
     * Cette méthode initialise d'abord les attributs communs hérités de
     * {@link Personne} via {@link PersonneServiceImpl#toPersonneEntity(Object, Object)}.
     *
     * Elle complète ensuite la conversion avec les propriétés spécifiques
     * au citoyen :
     * <ul>
     *     <li>profession</li>
     *     <li>situation familiale</li>
     *     <li>Fokontany de résidence</li>
     * </ul>
     *
     * Le Fokontany est recherché en base de données à partir de son identifiant
     * fourni dans le DTO avant d'être associé au citoyen.
     *
     * @param citoyen entité Citoyen à initialiser
     * @param dto DTO contenant les données du citoyen
     */
    @Override
    protected void toEntity(Citoyen citoyen, CitoyenRequestDTO dto) {
        toPersonneEntity(citoyen, dto);

        citoyen.setProfession(dto.getProfession());
        citoyen.setSituationFamiliale(
                dto.getSituationFamiliale()
        );


        Fokontany fokontany =
                fokontanyRepository.findById(dto.getFokontanyId())
                .orElseThrow();


        citoyen.setFokontany(fokontany);
    }
}