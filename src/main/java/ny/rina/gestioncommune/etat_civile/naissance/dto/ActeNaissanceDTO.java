package ny.rina.gestioncommune.etat_civile.naissance.dto;

import lombok.*;
import ny.rina.gestioncommune.etat_civile.acte.dto.ActeDTO;


@Getter
@Setter
public class ActeNaissanceDTO extends ActeDTO {

    private Long enfantId;

    private Long pereId;

    private Long mereId;
    
}
