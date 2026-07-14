package ny.rina.gestioncommune.population.citoyen.DTO;

import ny.rina.gestioncommune.population.citoyen.Citoyen;
import ny.rina.gestioncommune.population.fokontany.DTO.FokontanyMapper;

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
