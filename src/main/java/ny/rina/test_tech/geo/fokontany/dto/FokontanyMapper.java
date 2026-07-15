package ny.rina.test_tech.geo.fokontany.dto;

import ny.rina.test_tech.geo.fokontany.Fokontany;
import ny.rina.test_tech.geo.location.dto.LocationMapperDTO;

public class FokontanyMapper {
    public static FokontanyDTO toDTO(Fokontany fokontany) {

        FokontanyDTO dto = LocationMapperDTO.toDTO(fokontany, FokontanyDTO::new);
        
        if (fokontany.getCommune() != null) {
            dto.setCommuneId(fokontany.getCommune().getId());
        }

        return dto;
    }
}

    