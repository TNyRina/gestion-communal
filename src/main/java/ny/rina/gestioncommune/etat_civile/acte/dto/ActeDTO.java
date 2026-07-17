package ny.rina.gestioncommune.etat_civile.acte.dto;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
public class ActeDTO {

    private String numero;

    private LocalDate dateEtablissement;

    private Long officierEtatId;

    private Long communeId;
}
