package ny.rina.gestioncommune.population.citoyen;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import ny.rina.gestioncommune.etat_civile.dece.ActeDece;
import ny.rina.gestioncommune.etat_civile.mariage.ActeMariage;
import ny.rina.gestioncommune.etat_civile.naissance.ActeNaissance;
import ny.rina.gestioncommune.geo.fokontany.Fokontany;
import ny.rina.gestioncommune.population.citoyen.type.SituationFamiliale;
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

    @OneToOne(mappedBy = "enfant", fetch = FetchType.LAZY)
    private ActeNaissance acteNaissance;

    @OneToOne(mappedBy = "dece", fetch = FetchType.LAZY)
    private ActeDece acteDece;

    @OneToMany(mappedBy = "pere", fetch = FetchType.LAZY)
    private List<ActeNaissance> enfantsEnTantQuePere = new ArrayList<>();

    @OneToMany(mappedBy = "mere", fetch = FetchType.LAZY)
    private List<ActeNaissance> enfantsEnTantQueMere = new ArrayList<>();

    @OneToMany(mappedBy = "mari")
    private List<ActeMariage> mariagesCommeMari = new ArrayList<>();

    @OneToMany(mappedBy = "femme")
    private List<ActeMariage> mariagesCommeFemme = new ArrayList<>();

    @ManyToMany(mappedBy = "temoins")
    private List<ActeMariage> acteMariages = new ArrayList<>();

}
