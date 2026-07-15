package ny.rina.test_tech.geo.fokontany;

import jakarta.persistence.*;
import lombok.*;
import ny.rina.test_tech.geo.commune.Commune;
import ny.rina.test_tech.geo.location.Location;
import ny.rina.test_tech.population.citoyen.Citoyen;

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