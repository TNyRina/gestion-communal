package ny.rina.gestioncommune.etat_civile.naissance.dto;

import ny.rina.gestioncommune.etat_civile.acte.dto.ActeMapperDTO;
import ny.rina.gestioncommune.etat_civile.naissance.ActeNaissance;
import ny.rina.gestioncommune.population.citoyen.Citoyen;


/**
 * Classe utilitaire permettant de convertir une entité {@link ActeNaissance}
 * vers ses objets de transfert de données (DTO) associés.
 *
 * Cette classe gère la conversion spécifique aux actes de naissance en
 * complétant le mapping générique fourni par {@link ActeMapperDTO}.
 *
 * La conversion des attributs communs à tous les actes
 * (numéro, date d'établissement, officier d'état civil, commune)
 * est déléguée à {@code ActeMapperDTO}.
 *
 * Cette classe prend ensuite en charge les informations spécifiques à un
 * acte de naissance :
 * <ul>
 *     <li>l'enfant concerné par l'acte</li>
 *     <li>la mère de l'enfant</li>
 *     <li>le père de l'enfant</li>
 * </ul>
 *
 * Les relations avec les entités {@link Citoyen} sont représentées par leurs
 * identifiants afin d'éviter d'exposer directement les entités JPA dans les
 * objets DTO.
 *
 * Deux types de conversion sont proposés :
 * <ul>
 *     <li>
 *         Conversion vers {@link ActeNaissanceRequestDTO} utilisée lors de la
 *         création ou modification d'un acte de naissance.
 *     </li>
 *     <li>
 *         Conversion vers {@link ActeNaissanceResponseDTO} utilisée pour les
 *         réponses retournées par l'API.
 *     </li>
 * </ul>
 *
 * Exemple d'utilisation :
 *
 * <pre>
 * {@code
 * ActeNaissanceResponseDTO dto =
 *      ActeNaissanceMapperDTO.toResponseDTO(acteNaissance);
 * }
 * </pre>
 */
public class ActeNaissanceMapperDTO {



    /**
     * Convertit une entité ActeNaissance en DTO de requête.
     *
     * Cette méthode récupère les informations communes d'un acte grâce à
     * {@link ActeMapperDTO}, puis ajoute les identifiants des personnes liées
     * à l'acte (enfant, père et mère).
     *
     * Le DTO obtenu est destiné aux opérations nécessitant les données
     * d'entrée de l'API.
     *
     * @param acteNaissance entité ActeNaissance à convertir
     * @return DTO contenant les données nécessaires à une requête
     */
    public static ActeNaissanceRequestDTO toRequestDTO(ActeNaissance acteNaissance){
        ActeNaissanceRequestDTO dto = ActeMapperDTO.toDTO(acteNaissance, ActeNaissanceRequestDTO::new);
        
        dto.setEnfantId(acteNaissance.getEnfant().getId());
        dto.setMereId(acteNaissance.getMere().getId());
        dto.setPereId(acteNaissance.getPere().getId());
        
        return dto;
    }





    /**
     * Convertit une entité ActeNaissance en DTO de réponse.
     *
     * Cette méthode transforme l'entité en objet destiné à être retourné
     * au client. Elle ajoute l'identifiant de l'acte ainsi que les références
     * des personnes associées.
     *
     * Les entités liées ne sont pas exposées directement ; seuls leurs
     * identifiants sont transférés dans le DTO.
     *
     * @param acteNaissance entité ActeNaissance à convertir
     * @return DTO contenant les informations détaillées de l'acte
     */
    public static ActeNaissanceResponseDTO toResponseDTO(ActeNaissance acteNaissance){
        ActeNaissanceResponseDTO dto = ActeMapperDTO.toDTO(acteNaissance, ActeNaissanceResponseDTO::new);
        
        dto.setId(acteNaissance.getId());
        dto.setEnfantId(acteNaissance.getEnfant().getId());
        dto.setMereId(acteNaissance.getMere().getId());
        dto.setPereId(acteNaissance.getPere().getId());

        return dto;
    }
}
