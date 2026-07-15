package ny.rina.test_tech.population.personne.dto;

import java.util.function.Supplier;

import ny.rina.test_tech.population.personne.Personne;

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
}
