package ny.rina.gestioncommune.population.personne.dto;

import java.util.function.Supplier;

import ny.rina.gestioncommune.population.personne.Personne;



/**
 * Classe utilitaire permettant de convertir les entités de type {@link Personne}
 * vers leurs objets de transfert de données (DTO) correspondants.
 *
 * Cette classe fournit un mapper générique afin d'éviter la duplication
 * du code de conversion pour les différentes classes héritant de {@code Personne}
 * (par exemple : Citoyen, Agent, etc.).
 *
 * Elle utilise les génériques pour accepter :
 * - une entité {@code T} héritant de {@link Personne}
 * - un DTO {@code D} héritant de {@link PersonneDTO}
 *
 * Exemple d'utilisation :
 *
 * <pre>
 * CitoyenDTO dto = PersonneMapperDTO.toDTO(
 *      citoyen,
 *      CitoyenDTO::new
 * );
 * </pre>
 */

public class PersonneMapperDTO {

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

    public static <T extends Personne, D extends PersonneDTO> T toEntity(D dto, Supplier<T> supplier){
        T entity = supplier.get();

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
