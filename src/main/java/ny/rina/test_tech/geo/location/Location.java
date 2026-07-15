package ny.rina.test_tech.geo.location;

import jakarta.persistence.*;
import lombok.*;


/**
 * Classe abstraite représentant les attributs communs des entités
 * de localisation.
 *
 *
 * Elle centralise les propriétés communes aux différentes localisations
 * administratives (par exemple : Commune, Fokontany, Région, etc.) :
 *
 * - {@code id} : identifiant unique généré automatiquement par la base de données
 * - {@code nom} : nom de la localisation, obligatoire et unique
 * - {@code code} : code administratif optionnel mais unique
 *
 * Les classes qui héritent de cette classe pourront utiliser directement
 * ces attributs tout en ajoutant leurs propres propriétés spécifiques.
 *
 * Exemple :
 *
 * <pre>
 * {@code
 * @Entity
 * public class Commune extends Location {
 *     // attributs spécifiques à la commune
 * }
 * }
 * </pre>
 */
@MappedSuperclass
@Getter
@Setter
public abstract class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    protected String nom;

    @Column(unique = true)
    protected String code;
    
}