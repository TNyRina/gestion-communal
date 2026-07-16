package ny.rina.gestioncommune.etat_civile.dto;

import java.util.function.Supplier;

import ny.rina.gestioncommune.etat_civile.Act;

public class ActMapperDTO {
    
    public static <E extends Act, Q extends ActRequestDTO> Q toRequestDTO(E entity, Supplier<Q> supplier){

        Q dto = supplier.get();

        dto.setNumero(entity.getNomero());
        dto.setDateEtablissement(entity.getDateEtablissement());
        dto.setOfficierId(entity.getOfficierEtat().getId());
        dto.setCommuneId(entity.getCommune().getId());

        return dto;
    }

    public static <E extends Act, R extends ActResponseDTO> R toResponseDTO(E entity, Supplier<R> supplier){

        R dto = supplier.get();

        dto.setId(entity.getId());
        dto.setNumero(entity.getNomero());
        dto.setDateEtablissement(entity.getDateEtablissement());
        dto.setOfficierId(entity.getOfficierEtat().getId());
        dto.setCommuneId(entity.getCommune().getId());

        return dto;
    }

}
