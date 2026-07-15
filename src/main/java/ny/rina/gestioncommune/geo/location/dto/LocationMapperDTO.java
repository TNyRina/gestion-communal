package ny.rina.gestioncommune.geo.location.dto;

import java.util.function.Supplier;

import ny.rina.gestioncommune.geo.location.Location;

/**
 * Mapper générique permettant de convertir une entité de localisation
 * vers son objet de transfert de données (DTO) correspondant.
 *
 * Cette classe évite la duplication du code de conversion pour les différentes
 * entités de localisation (par exemple : Commune, Fokontany, Région, etc.).
 *
 * La méthode {@code toDTO} utilise les génériques afin d'accepter :
 * - une entité héritant de {@link Location}
 * - un DTO héritant de {@link LocationDTO}
 *
 * Le {@link Supplier} permet de fournir dynamiquement une instance du DTO
 * à créer (exemple : {@code CommuneDTO::new}).
 */

public class LocationMapperDTO {

    public static <E extends Location, D extends LocationDTO>
    D toDTO(E entity, Supplier<D> supplier) {

        D dto = supplier.get();

        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setCode(entity.getCode());

        return dto;
    }
}
