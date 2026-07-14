package ny.rina.test_tech.population.citoyen;

import ny.rina.test_tech.population.citoyen.type.SituationFamiliale;
import ny.rina.test_tech.population.fokontany.Fokontany;
import ny.rina.test_tech.population.personne.Personne;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "citoyens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Citoyen extends Personne {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fokontany_id")
    private Fokontany fokontany;


    private String profession;


    @Enumerated(EnumType.STRING)
    private SituationFamiliale situationFamiliale;

}
