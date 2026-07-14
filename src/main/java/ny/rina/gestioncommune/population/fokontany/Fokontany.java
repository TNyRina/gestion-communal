package ny.rina.gestioncommune.population.fokontany;

import jakarta.persistence.*;
import lombok.*;
import ny.rina.gestioncommune.population.citoyen.Citoyen;

import java.util.List;

@Entity
@Table(name = "fokontany")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fokontany {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String nom;


    private String code;


    /*
     * Un Fokontany possède plusieurs citoyens
     */
    @OneToMany(
        mappedBy = "fokontany",
        cascade = CascadeType.ALL
    )
    private List<Citoyen> citoyens;

}