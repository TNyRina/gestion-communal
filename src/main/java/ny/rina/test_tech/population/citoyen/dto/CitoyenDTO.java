package ny.rina.test_tech.population.citoyen.dto;

import lombok.*;
import ny.rina.test_tech.population.citoyen.type.SituationFamiliale;
import ny.rina.test_tech.population.personne.dto.PersonneDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CitoyenDTO extends PersonneDTO{

    private String profession;

    private SituationFamiliale situationFamiliale;
    
}
