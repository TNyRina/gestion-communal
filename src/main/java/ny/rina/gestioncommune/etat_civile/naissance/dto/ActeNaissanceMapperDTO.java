package ny.rina.gestioncommune.etat_civile.naissance.dto;

import ny.rina.gestioncommune.etat_civile.acte.dto.ActeMapperDTO;
import ny.rina.gestioncommune.etat_civile.naissance.ActeNaissance;

public class ActeNaissanceMapperDTO {
    public static ActeNaissanceRequestDTO toRequestDTO(ActeNaissance acteNaissance){
        ActeNaissanceRequestDTO dto = ActeMapperDTO.toDTO(acteNaissance, ActeNaissanceRequestDTO::new);
        
        dto.setEnfantId(acteNaissance.getEnfant().getId());
        dto.setMereId(acteNaissance.getMere().getId());
        dto.setPereId(acteNaissance.getPere().getId());
        
        return dto;
    }

    public static ActeNaissanceResponseDTO toResponseDTO(ActeNaissance acteNaissance){
        ActeNaissanceResponseDTO dto = ActeMapperDTO.toDTO(acteNaissance, ActeNaissanceResponseDTO::new);
        
        dto.setId(acteNaissance.getId());
        dto.setEnfantId(acteNaissance.getEnfant().getId());
        dto.setMereId(acteNaissance.getMere().getId());
        dto.setPereId(acteNaissance.getPere().getId());

        return dto;
    }
}
