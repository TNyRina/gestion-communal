package ny.rina.gestioncommune.geo.location.dto;
import lombok.*;

/**
 * Classe abstraite représentant les attributs communs des DTOs de {@link Location}.
 *
 * Cette classe sert de base pour les différents objets de transfert de données
 * liés aux entités géographiques (par exemple : Commune, Fokontany, Région, etc.).
 *
 * Elle centralise les propriétés partagées :
 * - id : identifiant unique de la localisation
 * - nom : nom de la localisation
 * - code : code administratif ou référence de la localisation
 *
 * Les classes DTO filles héritent de cette classe afin d'éviter la duplication
 * de ces attributs communs.
 */

@Getter
@Setter
abstract public class LocationDTO {
    
    private Long id;

    private String nom;

    private String code;

}
