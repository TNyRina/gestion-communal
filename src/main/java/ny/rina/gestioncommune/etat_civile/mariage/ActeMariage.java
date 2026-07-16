package ny.rina.gestioncommune.etat_civile.mariage;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import ny.rina.gestioncommune.etat_civile.acte.Acte;
import ny.rina.gestioncommune.population.citoyen.Citoyen;


@Entity
@Table(name = "actes_naissance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActeMariage extends Acte {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mari")
    private Citoyen mari;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "femme")
    private Citoyen femme;

    @ManyToMany
    @JoinTable(
        name = "acte_mariage_temoins",
        joinColumns = @JoinColumn(name = "acte_mariage_id"),
        inverseJoinColumns = @JoinColumn(name = "citoyen_id")
    )
    private List<Citoyen> temoins = new ArrayList<>();
    
}
