package ny.rina.test_tech.population.fokontany.DTO;

import ny.rina.test_tech.population.fokontany.Fokontany;

public class FokontanyMapper {
    public static FokontanyDTO toDTO(Fokontany fokontany) {
        FokontanyDTO dto = new FokontanyDTO();
        dto.setId(fokontany.getId());
        dto.setNom(fokontany.getNom());
        dto.setCode(fokontany.getCode());

        return dto;
    }
}

    