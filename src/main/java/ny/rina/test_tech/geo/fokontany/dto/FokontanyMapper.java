package ny.rina.test_tech.geo.fokontany.dto;

import ny.rina.test_tech.geo.fokontany.Fokontany;
import ny.rina.test_tech.geo.location.dto.LocationMapperDTO;

/**
 * Classe utilitaire permettant de convertir une entité {@link Fokontany}
 * en objet de transfert de données {@link FokontanyDTO}.
 *
 * Cette classe utilise {@link LocationMapperDTO} pour mapper les attributs
 * communs hérités de {@link Location} (id, nom, code).
 *
 * Elle complète ensuite la conversion avec les informations spécifiques
 * à l'entité Fokontany, notamment la relation avec {@link Commune}.
 *
 * Seul l'identifiant de la commune associée est transféré dans le DTO afin
 * d'éviter de charger et d'exposer toute l'entité Commune.
 *
 * Exemple :
 *
 * <pre>
 * FokontanyDTO dto = FokontanyMapper.toDTO(fokontany);
 * </pre>
 */

public class FokontanyMapper {
    public static FokontanyDTO toDTO(Fokontany fokontany) {

        FokontanyDTO dto = LocationMapperDTO.toDTO(fokontany, FokontanyDTO::new);
        
        if (fokontany.getCommune() != null) {
            dto.setCommuneId(fokontany.getCommune().getId());
        }

        return dto;
    }
}

    