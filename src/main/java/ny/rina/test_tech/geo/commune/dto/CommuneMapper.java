package ny.rina.test_tech.geo.commune.dto;

import ny.rina.test_tech.geo.commune.Commune;
import ny.rina.test_tech.geo.location.dto.LocationMapperDTO;

public class CommuneMapper {
    
    public static CommuneDTO toDTO(Commune commune){

        return LocationMapperDTO.toDTO(commune, CommuneDTO::new);
    }
}
