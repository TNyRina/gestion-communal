package ny.rina.gestioncommune.population.personne.dto;

import java.util.function.Supplier;

import ny.rina.gestioncommune.population.personne.Personne;


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
