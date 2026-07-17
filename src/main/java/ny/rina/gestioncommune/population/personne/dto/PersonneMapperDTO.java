package ny.rina.gestioncommune.population.personne.dto;

import java.util.function.Supplier;

import ny.rina.gestioncommune.population.personne.Personne;



/**
 * Classe utilitaire permettant de convertir les entités de type {@link Personne}
 * vers leurs objets de transfert de données (DTO) correspondants.
 *
 * Cette classe centralise la logique commune de conversion des attributs
 * partagés par toutes les classes héritant de {@link Personne}, afin d'éviter
 * la duplication de code dans les différents mappers spécialisés.
 *
 * Les propriétés communes traitées sont notamment :
 * <ul>
 *     <li>nom</li>
 *     <li>prénom</li>
 *     <li>date de naissance</li>
 *     <li>lieu de naissance</li>
 *     <li>sexe</li>
 *     <li>numéro CIN</li>
 *     <li>adresse</li>
 * </ul>
 *
 * Les propriétés spécifiques aux sous-classes ne sont pas gérées par cette
 * classe. Elles doivent être ajoutées dans les mappers dédiés à chaque type
 * de personne.
 *
 * Exemple :
 * <ul>
 *     <li>{@code CitoyenMapper} pour les informations spécifiques aux citoyens</li>
 *     <li>{@code AgentCommunaleMapper} pour les informations spécifiques aux agents communaux</li>
 * </ul>
 *
 * Cette classe utilise les génériques afin d'être compatible avec plusieurs
 * types d'entités et de DTO :
 * <ul>
 *     <li>{@code T} représente une entité héritant de {@link Personne}</li>
 *     <li>{@code D} représente un DTO héritant de {@link PersonneDTO}</li>
 * </ul>
 *
 * Le {@link Supplier} utilisé lors de la conversion permet de créer
 * dynamiquement une instance du DTO cible sans dépendre d'une classe concrète.
 *
 * Exemple d'utilisation :
 *
 * <pre>
 * {@code
 * CitoyenResponseDTO dto =
 *      PersonneMapperDTO.toDTO(
 *          citoyen,
 *          CitoyenResponseDTO::new
 *      );
 * }
 * </pre>
 *
 * Cette classe ne contient que des méthodes statiques et n'est pas destinée
 * à être instanciée.
 */
public class PersonneMapperDTO {


    /**
     * Convertit une entité héritant de {@link Personne} en objet DTO.
     *
     * Cette méthode récupère les attributs communs d'une personne et les copie
     * dans le DTO fourni. Les propriétés spécifiques aux sous-classes
     * (par exemple : profession d'un citoyen, matricule d'un agent) doivent être
     * traitées dans les mappers spécialisés.
     *
     * Le {@link Supplier} permet de créer dynamiquement une instance du DTO cible,
     * ce qui rend la méthode compatible avec plusieurs types de DTO.
     *
     * Exemple :
     * <pre>
     * CitoyenDTO dto = PersonneMapperDTO.toDTO(
     *      citoyen,
     *      CitoyenDTO::new
     * );
     * </pre>
     *
     * @param personne entité source héritant de {@link Personne}
     * @param supplier fournisseur permettant de créer le DTO cible
     * @param <T> type de l'entité source
     * @param <D> type du DTO cible
     * @return DTO contenant les données communes de la personne
     */
    public static <T extends Personne,D extends PersonneDTO> D toDTO(T personne, Supplier<D> supplier) {

        D dto = supplier.get();

        dto.setNom(personne.getNom());
        dto.setPrenom(personne.getPrenom());
        dto.setDateNaissance(personne.getDateNaissance());
        dto.setLieuNaissance(personne.getLieuNaissance());
        dto.setSexe(personne.getSexe());
        dto.setNumeroCIN(personne.getNumeroCIN());
        dto.setAdresse(personne.getAdresse());

        return dto;
    }

}
