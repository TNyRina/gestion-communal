package ny.rina.gestioncommune.geo.commune;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import ny.rina.gestioncommune.etat_civile.acte.Acte;
import ny.rina.gestioncommune.geo.fokontany.Fokontany;
import ny.rina.gestioncommune.geo.location.Location;
import ny.rina.gestioncommune.population.agentCommunale.AgentCommunale;

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

    /*
     * Une commune possède plusieurs acts
     */
    @OneToMany(
        mappedBy = "commune"
    )
    private List<Acte> acts= new ArrayList<>();
}
