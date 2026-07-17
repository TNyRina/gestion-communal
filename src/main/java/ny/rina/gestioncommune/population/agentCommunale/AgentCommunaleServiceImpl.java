package ny.rina.gestioncommune.population.agentCommunale;
import java.util.function.Supplier;

import jakarta.persistence.EntityNotFoundException;
import ny.rina.gestioncommune.geo.commune.Commune;
import ny.rina.gestioncommune.geo.commune.CommuneRepository;
import ny.rina.gestioncommune.population.agentCommunale.dto.AgentCommunaleRequestDTO;
import ny.rina.gestioncommune.population.agentCommunale.dto.AgentCommunaleResponseDTO;
import ny.rina.gestioncommune.population.personne.PersonneServiceImpl;



/**
 * Service métier générique permettant de gérer les agents communaux.
 *
 * Cette classe étend {@link PersonneServiceImpl} afin de réutiliser la logique
 * commune de gestion des personnes, puis ajoute les traitements spécifiques
 * aux entités héritant de {@link AgentCommunale}.
 *
 * Elle centralise la conversion des données communes aux agents communaux :
 * <ul>
 *     <li>les informations générales d'une personne</li>
 *     <li>le matricule de l'agent</li>
 *     <li>la date d'embauche</li>
 *     <li>la commune d'affectation</li>
 * </ul>
 *
 * Cette classe est abstraite car elle ne représente pas un type concret
 * d'agent communal. Les classes filles doivent définir les comportements
 * spécifiques selon le rôle de l'agent (par exemple : officier d'état civil,
 * autre employé communal).
 *
 * Les paramètres génériques utilisés sont :
 * <ul>
 *     <li>
 *         {@code A} : type de l'entité agent communal manipulée
 *     </li>
 *     <li>
 *         {@code R} : type du DTO utilisé pour les réponses API
 *     </li>
 *     <li>
 *         {@code Q} : type du DTO utilisé pour les requêtes API
 *     </li>
 *     <li>
 *         {@code T} : type du repository associé à l'agent communal
 *     </li>
 * </ul>
 *
 * Exemple d'utilisation :
 *
 * <pre>
 * {@code
 * public class OfficierEtatServiceImpl extends AgentCommunaleServiceImpl<
 *        OfficierEtat,
 *        OfficierEtatResponseDTO,
 *        OfficierEtatRequestDTO,
 *        OfficierEtatRepository> {
 * }
 * </pre>
 *
 * @param <A> type de l'entité AgentCommunale
 * @param <R> type du DTO de réponse
 * @param <Q> type du DTO de requête
 * @param <T> type du repository AgentCommunale
 */
public abstract class AgentCommunaleServiceImpl<
A extends AgentCommunale, 
R extends AgentCommunaleResponseDTO, 
Q extends AgentCommunaleRequestDTO, 
T extends AgentCommunaleRepository<A>
> extends PersonneServiceImpl<A,R,Q> {

    final private CommuneRepository communeRepository;

    protected AgentCommunaleServiceImpl(T repository, CommuneRepository communeRepository, Supplier<A> supplier){
        super(repository, supplier);

        this.communeRepository = communeRepository;
    }





    /**
     * Initialise une entité AgentCommunale à partir d'un DTO.
     *
     * Cette méthode complète la conversion héritée de {@link PersonneServiceImpl}
     * en ajoutant les informations spécifiques aux agents communaux :
     * <ul>
     *     <li>matricule</li>
     *     <li>date d'embauche</li>
     *     <li>commune d'affectation</li>
     * </ul>
     *
     * La commune est recherchée en base de données à partir de son identifiant
     * fourni dans le DTO avant d'être associée à l'agent.
     *
     * Les classes filles peuvent appeler cette méthode afin de réutiliser
     * la conversion commune avant d'ajouter leurs propres propriétés.
     *
     * @param agent entité AgentCommunale à initialiser
     * @param dto DTO contenant les données de l'agent
     * @throws EntityNotFoundException si la commune associée n'existe pas
     */
    protected void toAgentCommunaleEntity(A agent, Q dto){
        toPersonneEntity(agent, dto);
        agent.setMatricule(dto.getMatricule());
        agent.setDateEmbauche(dto.getDateEmbauche());

        Commune commune = communeRepository.findById(dto.getCommuneId()).orElseThrow( () -> new EntityNotFoundException(
                    "Commune introuvable"
                ));
        agent.setCommune(commune);
    }
}
