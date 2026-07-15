package ny.rina.test_tech.geo.commune.dto;

import ny.rina.test_tech.geo.commune.Commune;
import ny.rina.test_tech.geo.location.dto.LocationMapperDTO;


/**
 * Classe utilitaire permettant de convertir une entité {@link Commune}
 * en objet de transfert de données {@link CommuneDTO}.
 *
 * Cette classe s'appuie sur {@link LocationMapperDTO} afin de réutiliser
 * la logique commune de conversion des attributs hérités de {@link Location}
 * (id, nom, code).
 *
 *
 * Exemple d'utilisation :
 *
 * <pre>
 * CommuneDTO dto = CommuneMapper.toDTO(commune);
 * </pre>
 */

public class CommuneMapper {
    
    public static CommuneDTO toDTO(Commune commune){

        return LocationMapperDTO.toDTO(commune, CommuneDTO::new);
    }
}
