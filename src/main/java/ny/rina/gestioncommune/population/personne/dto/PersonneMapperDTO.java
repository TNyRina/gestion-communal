package ny.rina.gestioncommune.population.personne.dto;

import java.util.function.Supplier;

import ny.rina.gestioncommune.population.personne.Personne;



/**
 * Classe utilitaire générique permettant de gérer les conversions entre les
 * entités héritant de {@link Personne} et leurs objets de transfert de données
 * {@link PersonneDTO}.
 *
 * Cette classe centralise la logique de mapping des attributs communs à toutes
 * les personnes afin d'éviter la duplication de code dans les différents
 * mappers spécialisés (par exemple : {@code CitoyenMapper},
 * {@code AgentCommunaleMapper}, etc.).
 *
 * Les attributs gérés par cette classe sont ceux définis dans l'entité
 * {@link Personne} :
 * <ul>
 *     <li>nom</li>
 *     <li>prenom</li>
 *     <li>date de naissance</li>
 *     <li>lieu de naissance</li>
 *     <li>sexe</li>
 *     <li>numéro CIN</li>
 *     <li>adresse</li>
 * </ul>
 *
 * La classe utilise les génériques afin d'être réutilisable pour toutes les
 * sous-classes de {@code Personne} et de {@code PersonneDTO} :
 * <ul>
 *     <li>{@code T} représente une entité héritant de {@link Personne}</li>
 *     <li>{@code D} représente un DTO héritant de {@link PersonneDTO}</li>
 * </ul>
 *
 * Le {@link Supplier} est utilisé pour créer dynamiquement les instances des
 * objets cibles sans dépendre directement de leurs classes concrètes.
 *
 * Les opérations disponibles sont :
 * <ul>
 *     <li>
 *         Conversion d'une entité {@code Personne} vers un DTO.
 *     </li>
 *     <li>
 *         Création d'une nouvelle entité {@code Personne} à partir d'un DTO.
 *     </li>
 *     <li>
 *         Mise à jour d'une entité existante avec les données provenant
 *         d'un DTO.
 *     </li>
 * </ul>
 *
 * Exemple d'utilisation :
 *
 * <pre>
 * CitoyenDTO dto = PersonneMapperDTO.toDTO(
 *      citoyen,
 *      CitoyenDTO::new
 * );
 *
 * Citoyen citoyen = PersonneMapperDTO.toEntity(
 *      dto,
 *      Citoyen::new
 * );
 *
 * PersonneMapperDTO.updateEntity(citoyen, dto);
 * </pre>
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


    /**
     * Crée une nouvelle entité héritant de {@link Personne} à partir
     * d'un objet DTO.
     *
     * Cette méthode instancie une nouvelle entité grâce au {@link Supplier},
     * puis initialise ses attributs communs avec les valeurs présentes dans
     * le DTO.
     *
     * Elle est utilisée principalement lors de la création d'une nouvelle
     * personne avant son enregistrement en base de données.
     *
     * Exemple :
     * <pre>
     * Citoyen citoyen = PersonneMapperDTO.toEntity(
     *      dto,
     *      Citoyen::new
     * );
     * </pre>
     *
     * @param dto DTO contenant les données de la personne
     * @param supplier fournisseur permettant de créer l'entité cible
     * @param <T> type de l'entité à créer
     * @param <D> type du DTO source
     * @return nouvelle entité initialisée avec les données du DTO
     */
    public static <T extends Personne, D extends PersonneDTO> T toEntity(D dto, Supplier<T> supplier){
        T entity = supplier.get();

        return dtoToEntity(dto, entity);
    }




    /**
     * Met à jour une entité existante héritant de {@link Personne}
     * avec les informations provenant d'un DTO.
     *
     * Elle modifie directement l'entité existante afin de conserver
     * son identité et ses relations JPA.
     *
     * Elle est utilisée lors des opérations de modification (UPDATE).
     *
     * Exemple :
     * <pre>
     * PersonneMapperDTO.updateEntity(
     *      citoyenExistant,
     *      citoyenDTO
     * );
     * </pre>
     *
     * @param entity entité existante à mettre à jour
     * @param dto DTO contenant les nouvelles valeurs
     * @param <T> type de l'entité à modifier
     * @param <D> type du DTO source
     * @return l'entité mise à jour
     */
    public static <T extends Personne, D extends PersonneDTO> T updateEntity (T entity, D dto){
        
        return dtoToEntity(dto, entity);
    }




    private static <T extends Personne, D extends PersonneDTO> T dtoToEntity(D dto, T entity){
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setDateNaissance(dto.getDateNaissance());
        entity.setLieuNaissance(dto.getLieuNaissance());
        entity.setSexe(dto.getSexe());
        entity.setNumeroCIN(dto.getNumeroCIN());
        entity.setAdresse(dto.getAdresse());

        return entity;
    }
}
