package ny.rina.gestioncommune.etat_civile.mariage.dto;

import java.util.List;

import lombok.*;
import ny.rina.gestioncommune.etat_civile.acte.dto.ActeDTO;


@Getter
@Setter
public class ActeMariageDTO extends ActeDTO{
    
    private Long mariId;

    private Long femmeId;

    private List<Long> temoinsId;
}
