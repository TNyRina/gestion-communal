package ny.rina.gestioncommune.etat_civile.mariage.dto;

import java.util.List;

import ny.rina.gestioncommune.etat_civile.acte.dto.ActeMapperDTO;
import ny.rina.gestioncommune.etat_civile.mariage.ActeMariage;

public class ActeMariageMapperDTO {
    public static ActeMariageRequestDTO toRequestDTO(ActeMariage acte){
        ActeMariageRequestDTO dto = ActeMapperDTO.toDTO(acte, ActeMariageRequestDTO::new);

        dto.setFemmeId(acte.getFemme().getId());
        dto.setMariId(acte.getMari().getId());
        
        dto.setTemoinsId(
        acte.getTemoins() == null
                ? List.of()
                : acte.getTemoins()
                    .stream()
                    .map(temoin -> temoin.getId())
                    .toList()
        );

        return dto;
    }

    public static ActeMariageResponseDTO toResponseDTO(ActeMariage acte){
        ActeMariageResponseDTO dto = ActeMapperDTO.toDTO(acte, ActeMariageResponseDTO::new);

        dto.setId(acte.getId());
        dto.setFemmeId(acte.getFemme().getId());
        dto.setMariId(acte.getMari().getId());
        
        dto.setTemoinsId(
        acte.getTemoins() == null
                ? List.of()
                : acte.getTemoins()
                    .stream()
                    .map(temoin -> temoin.getId())
                    .toList()
        );

        return dto;
    }
}
