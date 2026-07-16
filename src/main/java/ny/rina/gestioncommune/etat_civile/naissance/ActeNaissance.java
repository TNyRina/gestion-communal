package ny.rina.gestioncommune.etat_civile.naissance;

import jakarta.persistence.*;
import lombok.*;
import ny.rina.gestioncommune.etat_civile.acte.Acte;
import ny.rina.gestioncommune.population.citoyen.Citoyen;

@Entity
@Table(name = "acts_naissance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActeNaissance extends Acte {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enfant_id", unique = true) 
    private Citoyen enfant;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pere_id")
    private Citoyen pere;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mere_id")
    private Citoyen mere;
}
