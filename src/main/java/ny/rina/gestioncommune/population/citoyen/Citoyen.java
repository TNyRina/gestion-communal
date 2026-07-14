package ny.rina.gestioncommune.population.citoyen;

import jakarta.persistence.*;
import lombok.*;
import ny.rina.gestioncommune.population.citoyen.type.SituationFamiliale;
import ny.rina.gestioncommune.population.fokontany.Fokontany;
import ny.rina.gestioncommune.population.personne.Personne;

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
