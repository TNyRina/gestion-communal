package ny.rina.test_tech.geo.fokontany.dto;

import lombok.*;
import ny.rina.test_tech.geo.location.dto.LocationDTO;

@Getter
@Setter
public class FokontanyDTO extends LocationDTO{

    private Long communeId;
}
