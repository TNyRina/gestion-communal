package ny.rina.gestioncommune.geo.fokontany.dto;

import lombok.*;
import ny.rina.gestioncommune.geo.location.dto.LocationDTO;

@Getter
@Setter
public class FokontanyDTO extends LocationDTO{

    private Long communeId;
}
