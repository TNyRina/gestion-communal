package ny.rina.test_tech.population.citoyen.DTO;

import ny.rina.test_tech.geo.fokontany.dto.FokontanyMapper;
import ny.rina.test_tech.population.citoyen.Citoyen;

public class CitoyenMapper {
    public static CitoyenResponseDTO toDTO(Citoyen citoyen){
        return new CitoyenResponseDTO(
                citoyen.getId(),
                citoyen.getNom(),
                citoyen.getPrenom(),
                citoyen.getDateNaissance(),
                citoyen.getSexe(),
                citoyen.getLieuNaissance(),
                citoyen.getNumeroCIN(),
                citoyen.getAdresse(),
                citoyen.getProfession(),
                citoyen.getSituationFamiliale(),
                FokontanyMapper.toDTO(citoyen.getFokontany())
        );
    }
}
