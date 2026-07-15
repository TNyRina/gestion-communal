package ny.rina.test_tech.population.citoyen.dto;

import ny.rina.test_tech.population.citoyen.Citoyen;
import ny.rina.test_tech.population.personne.dto.PersonneMapperDTO;

public class CitoyenMapper {

    public static CitoyenRequestDTO toRequestDTO(Citoyen citoyen){
        CitoyenRequestDTO dto = PersonneMapperDTO.toDTO(citoyen, CitoyenRequestDTO::new);

        dto.setProfession(citoyen.getProfession());
        dto.setSituationFamiliale(citoyen.getSituationFamiliale());
        dto.setFokontanyId(citoyen.getFokontany().getId());

        return dto;
    }

    public static CitoyenResponseDTO toResponseDTO(Citoyen citoyen){
        CitoyenResponseDTO dto = PersonneMapperDTO.toDTO(citoyen, CitoyenResponseDTO::new);
        
        dto.setId(citoyen.getId());
        dto.setProfession(citoyen.getProfession());
        dto.setSituationFamiliale(citoyen.getSituationFamiliale());
        dto.setFokotany(citoyen.getFokontany().getNom());
        dto.setCommune(citoyen.getFokontany().getCommune().getNom());

        return dto;
    }
}
