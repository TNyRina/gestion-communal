package ny.rina.gestioncommune.population.agentCommunale.dto;

import java.util.function.Supplier;

import ny.rina.gestioncommune.population.agentCommunale.AgentCommunale;
import ny.rina.gestioncommune.population.personne.Personne;
import ny.rina.gestioncommune.population.personne.dto.PersonneMapperDTO;

/**
 * Classe utilitaire permettant de convertir les entités héritant de
 * {@link AgentCommunale} vers leurs objets de transfert de données (DTO).
 *
 * Cette classe fournit des méthodes génériques afin de réutiliser la logique
 * de conversion pour les différents types d'agents communaux
 * (par exemple : OfficierEtatCivil, EmployeCommunal, etc.).
 *
 * Les propriétés communes héritées de {@link Personne} sont converties par
 * {@link PersonneMapperDTO}, tandis que cette classe complète la conversion
 * avec les attributs propres aux agents communaux.
 *
 * Deux types de conversion sont proposés :
 * <ul>
 *     <li>
 *         Conversion vers un {@link AgentCommunaleRequestDTO}, utilisé lors
 *         de la création ou de la mise à jour d'un agent. Les relations sont
 *         représentées par leurs identifiants (par exemple : communeId).
 *     </li>
 *     <li>
 *         Conversion vers un {@link AgentCommunaleResponseDTO}, utilisé pour
 *         les réponses de l'API. Les relations sont représentées par des
 *         informations lisibles (par exemple : le nom de la commune).
 *     </li>
 * </ul>
 *
 * Le {@link Supplier} permet d'instancier dynamiquement le type de DTO cible,
 * ce qui rend cette classe réutilisable pour toutes les sous-classes
 * d'AgentCommunale.
 */
public class AgentCommunaleMapper{



    /**
     * Convertit une entité AgentCommunale en DTO de requête.
     *
     * La méthode copie les informations communes d'une personne ainsi que les
     * attributs spécifiques à l'agent communal. La commune associée est
     * représentée uniquement par son identifiant.
     *
     * @param agent entité à convertir
     * @param supplier fournisseur permettant de créer le DTO cible
     * @param <T> type de l'entité héritant d'AgentCommunale
     * @param <Q> type du DTO de requête
     * @return DTO de requête correspondant
     */
    public static <T extends AgentCommunale,Q extends AgentCommunaleRequestDTO> Q toRequestDTO(T agent, Supplier<Q> supplier){
        Q dto = PersonneMapperDTO.toDTO(agent,supplier);

        dto.setMatricule(agent.getMatricule());
        dto.setDateEmbauche(agent.getDateEmbauche());
        dto.setCommuneId(agent.getCommune().getId());

        return dto;
    }


     /**
     * Convertit une entité AgentCommunale en DTO de réponse.
     *
     * La méthode copie les informations communes d'une personne ainsi que les
     * attributs spécifiques à l'agent communal. La commune associée est
     * représentée par son nom afin de fournir une réponse plus lisible.
     *
     * @param agent entité à convertir
     * @param supplier fournisseur permettant de créer le DTO cible
     * @param <T> type de l'entité héritant d'AgentCommunale
     * @param <R> type du DTO de réponse
     * @return DTO de réponse correspondant
     */
    public static <T extends AgentCommunale, R extends AgentCommunaleResponseDTO> R toResponseDTO(T agent, Supplier<R> supplier){
        R dto = PersonneMapperDTO.toDTO(agent, supplier);

        dto.setId(agent.getId());
        dto.setMatricule(agent.getMatricule());
        dto.setDateEmbauche(agent.getDateEmbauche());
        dto.setCommune(agent.getCommune().getNom());

        return dto;
    }
}
