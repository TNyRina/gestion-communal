package ny.rina.gestioncommune.etat_civile.dece.dto;


import ny.rina.gestioncommune.etat_civile.acte.dto.ActeMapperDTO;
import ny.rina.gestioncommune.etat_civile.dece.ActeDece;

public class ActeDeceMapperDTO {
    public static ActeDeceRequestDTO toRequestDTO(ActeDece acte){
        ActeDeceRequestDTO dto = ActeMapperDTO.toDTO(acte, ActeDeceRequestDTO::new);

        dto.setDeceId(acte.getDece().getId());

        return dto;
    }

    public static ActeDeceResponseDTO toResponseDTO(ActeDece acte){
        ActeDeceResponseDTO dto = ActeMapperDTO.toDTO(acte, ActeDeceResponseDTO::new);
        
        dto.setId(acte.getId());
        dto.setDeceId(acte.getDece().getId());

        return dto;
    }
}
