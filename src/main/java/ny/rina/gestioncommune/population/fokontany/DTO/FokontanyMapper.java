package ny.rina.gestioncommune.population.fokontany.DTO;

import ny.rina.gestioncommune.population.fokontany.Fokontany;

public class FokontanyMapper {
    public static FokontanyDTO toDTO(Fokontany fokontany) {
        FokontanyDTO dto = new FokontanyDTO();
        dto.setId(fokontany.getId());
        dto.setNom(fokontany.getNom());
        dto.setCode(fokontany.getCode());

        return dto;
    }
}

    