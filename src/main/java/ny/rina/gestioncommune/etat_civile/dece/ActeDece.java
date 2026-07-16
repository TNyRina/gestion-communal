package ny.rina.gestioncommune.etat_civile.dece;

import jakarta.persistence.*;
import lombok.*;
import ny.rina.gestioncommune.etat_civile.acte.Acte;
import ny.rina.gestioncommune.population.citoyen.Citoyen;

@Entity
@Table(name = "actes_dece")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActeDece extends Acte {


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dece_id", unique = true) 
    private Citoyen dece;
    
}
