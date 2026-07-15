package ny.rina.test_tech.geo.commune;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import ny.rina.test_tech.geo.fokontany.Fokontany;
import ny.rina.test_tech.geo.location.Location;
import ny.rina.test_tech.population.agentCommunale.AgentCommunale;

@Entity
@Table(name = "communes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Commune extends Location {

    /*
     * Une commune possède plusieurs agents
     */
    @OneToMany(
        mappedBy = "commune"
    )
    private List<AgentCommunale> agentCommunales = new ArrayList<>();

    /*
     * Une commune possède plusieurs fokotany
     */
    @OneToMany(
        mappedBy = "commune"
    )
    private List<Fokontany> fokontany= new ArrayList<>();
}
