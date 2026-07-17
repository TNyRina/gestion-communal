package ny.rina.gestioncommune.etat_civile.acte.dto;

import java.util.function.Supplier;

import ny.rina.gestioncommune.etat_civile.acte.Acte;



/**
 * Classe utilitaire permettant de convertir les entités de type {@link Acte}
 * vers leurs objets de transfert de données (DTO) correspondants.
 *
 * Cette classe fournit un mapper générique afin de centraliser la conversion
 * des attributs communs des actes administratifs et d'éviter la duplication
 * du code dans les différents mappers spécialisés.
 *
 * Elle utilise les génériques afin d'être compatible avec les différentes
 * classes héritant de {@link Acte} et leurs DTO associés :
 * <ul>
 *     <li>
 *         {@code E} représente une entité héritant de {@link Acte}
 *     </li>
 *     <li>
 *         {@code D} représente un DTO héritant de {@link ActeDTO}
 *     </li>
 * </ul>
 *
 * Les attributs communs gérés par ce mapper sont :
 * <ul>
 *     <li>numéro de l'acte</li>
 *     <li>date d'établissement</li>
 *     <li>identifiant de l'officier d'état civil</li>
 *     <li>identifiant de la commune associée</li>
 * </ul>
 *
 * Le {@link Supplier} permet de créer dynamiquement une instance du DTO cible
 * sans dépendre directement de sa classe concrète.
 *
 * Exemple d'utilisation :
 *
 * <pre>
 * {@code
 * ActeNaissanceDTO dto = ActeMapperDTO.toDTO(
 *      acteNaissance,
 *      ActeNaissanceDTO::new
 * );
 * }
 * </pre>
 */
public class ActeMapperDTO {
    
    public static <E extends Acte, D extends ActeDTO> D toDTO(E entity, Supplier<D> supplier){

        D dto = supplier.get();

        dto.setNumero(entity.getNumero());
        dto.setDateEtablissement(entity.getDateEtablissement());
        dto.setOfficierEtatId(entity.getOfficierEtat().getId());
        dto.setCommuneId(entity.getCommune().getId());

        return dto;
    }
}
