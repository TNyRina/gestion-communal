package ny.rina.gestioncommune.etat_civile.dece.dto;


import ny.rina.gestioncommune.etat_civile.acte.dto.ActeMapperDTO;
import ny.rina.gestioncommune.etat_civile.dece.ActeDece;




/**
 * Classe utilitaire permettant de convertir une entité {@link ActeDece}
 * vers ses objets de transfert de données (DTO) associés.
 *
 * Cette classe gère la conversion spécifique aux actes de décès en complétant
 * le mapping générique fourni par {@link ActeMapperDTO}.
 *
 * La conversion des attributs communs à tous les actes :
 * <ul>
 *     <li>numéro de l'acte</li>
 *     <li>date d'établissement</li>
 *     <li>officier d'état civil</li>
 *     <li>commune associée</li>
 * </ul>
 *
 * est déléguée à {@link ActeMapperDTO}.
 *
 * Cette classe ajoute ensuite les informations propres à un acte de décès :
 * <ul>
 *     <li>la personne décédée concernée par l'acte</li>
 * </ul>
 *
 * Les relations avec les entités associées ne sont pas exposées directement.
 * Seuls leurs identifiants sont transférés dans les DTO afin de limiter
 * le couplage entre la couche API et les entités JPA.
 *
 * Deux types de conversion sont disponibles :
 * <ul>
 *     <li>
 *         Conversion vers {@link ActeDeceRequestDTO}, utilisé lors de la
 *         création ou modification d'un acte de décès.
 *     </li>
 *     <li>
 *         Conversion vers {@link ActeDeceResponseDTO}, utilisé pour les
 *         réponses retournées par l'API.
 *     </li>
 * </ul>
 *
 * Exemple d'utilisation :
 *
 * <pre>
 * {@code
 * ActeDeceResponseDTO dto =
 *      ActeDeceMapperDTO.toResponseDTO(acteDece);
 * }
 * </pre>
 */
public class ActeDeceMapperDTO {



    public static ActeDeceRequestDTO toRequestDTO(ActeDece acte){
        ActeDeceRequestDTO dto = ActeMapperDTO.toDTO(acte, ActeDeceRequestDTO::new);

        dto.setDeceId(acte.getDece().getId());

        return dto;
    }



    
    public static ActeDeceResponseDTO toResponseDTO(ActeDece acte){
        ActeDeceResponseDTO dto = ActeMapperDTO.toDTO(acte, ActeDeceResponseDTO::new);
        
        dto.setId(acte.getId());
        dto.setDeceId(acte.getDece().getId());

        return dto;
    }
}
