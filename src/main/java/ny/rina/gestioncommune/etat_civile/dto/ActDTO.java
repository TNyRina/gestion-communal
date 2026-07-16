package ny.rina.gestioncommune.etat_civile.dto;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
public class ActDTO {

    private String numero;

    private LocalDate dateEtablissement;

    private Long officierId;

    private Long communeId;
}
