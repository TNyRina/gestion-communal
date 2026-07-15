package ny.rina.test_tech.population.citoyen.dto;

import ny.rina.test_tech.population.citoyen.Citoyen;
import ny.rina.test_tech.population.personne.Personne;
import ny.rina.test_tech.population.personne.dto.PersonneMapperDTO;


/**
 * Classe utilitaire permettant de convertir une entité {@link Citoyen}
 * vers ses différents objets de transfert de données (DTO).
 *
 * Cette classe gère deux types de conversion :
 *
 * - {@code CitoyenRequestDTO} :
 *   utilisé pour recevoir les données lors de la création ou modification
 *   d'un citoyen. Les informations liées aux entités sont représentées
 *   par leurs identifiants (par exemple : fokontanyId).
 *
 * - {@code CitoyenResponseDTO} :
 *   utilisé pour retourner les données vers le client. Les informations
 *   associées sont enrichies avec des données lisibles (par exemple :
 *   nom du Fokontany et nom de la Commune).
 *
 * La conversion des attributs communs hérités de {@link Personne} est déléguée
 * à {@link PersonneMapperDTO}, tandis que cette classe ajoute les propriétés
 * spécifiques au citoyen.
 *
 * Exemple :
 *
 * <pre>
 * CitoyenResponseDTO response = CitoyenMapper.toResponseDTO(citoyen);
 * </pre>
 */
public class CitoyenMapper {


    /**
     * Convertit une entité Citoyen en DTO utilisé pour les requêtes.
     *
     * Cette méthode transforme l'entité en {@link CitoyenRequestDTO} en
     * conservant uniquement les références nécessaires pour les relations
     * avec d'autres entités (ici : fokontanyId).
     *
     * @param citoyen entité Citoyen à convertir
     * @return DTO contenant les données nécessaires pour une requête
     */
    public static CitoyenRequestDTO toRequestDTO(Citoyen citoyen){
        CitoyenRequestDTO dto = PersonneMapperDTO.toDTO(citoyen, CitoyenRequestDTO::new);

        dto.setProfession(citoyen.getProfession());
        dto.setSituationFamiliale(citoyen.getSituationFamiliale());
        dto.setFokontanyId(citoyen.getFokontany().getId());

        return dto;
    }



    /**
     * Convertit une entité Citoyen en DTO utilisé pour les réponses API.
     *
     * Cette méthode enrichit le DTO avec les informations de localisation
     * du citoyen :
     * - nom du Fokontany
     * - nom de la Commune associée
     *
     * Cela permet de retourner des informations directement exploitables
     * par le client sans exposer les entités JPA.
     *
     * @param citoyen entité Citoyen à convertir
     * @return DTO contenant les informations détaillées du citoyen
     */
    public static CitoyenResponseDTO toResponseDTO(Citoyen citoyen){
        CitoyenResponseDTO dto = PersonneMapperDTO.toDTO(citoyen, CitoyenResponseDTO::new);
        
        dto.setId(citoyen.getId());
        dto.setProfession(citoyen.getProfession());
        dto.setSituationFamiliale(citoyen.getSituationFamiliale());
        dto.setFokotany(citoyen.getFokontany().getNom());
        dto.setCommune(citoyen.getFokontany().getCommune().getNom());

        return dto;
    }
}
