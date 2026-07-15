package ny.rina.gestioncommune.population.citoyen.dto;

import lombok.*;
import ny.rina.gestioncommune.population.personne.dto.PersonneDTO;
import ny.rina.gestioncommune.population.citoyen.type.SituationFamiliale;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CitoyenDTO extends PersonneDTO{

    private String profession;

    private SituationFamiliale situationFamiliale;
    
}
