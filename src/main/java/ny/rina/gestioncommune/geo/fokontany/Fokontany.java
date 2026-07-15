package ny.rina.gestioncommune.geo.fokontany;

import jakarta.persistence.*;
import lombok.*;
import ny.rina.gestioncommune.geo.commune.Commune;
import ny.rina.gestioncommune.geo.location.Location;
import ny.rina.gestioncommune.population.citoyen.Citoyen;

import java.util.List;

@Entity
@Table(name = "fokontany")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fokontany extends Location{

    /*
     * Un Fokontany possède plusieurs citoyens
     */
    @OneToMany(
        mappedBy = "fokontany"
    )
    private List<Citoyen> citoyens;

    /*
     * Un Fokontany appartient à une seule commune
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commune_id", nullable = false)
    private Commune commune;

}