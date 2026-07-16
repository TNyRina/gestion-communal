package ny.rina.gestioncommune.etat_civile.acte.dto;

import java.util.function.Supplier;

import ny.rina.gestioncommune.etat_civile.acte.Acte;

public class ActeMapperDTO {
    
    public static <E extends Acte, D extends ActeDTO> D toDTO(E entity, Supplier<D> supplier){

        D dto = supplier.get();

        dto.setNumero(entity.getNumero());
        dto.setDateEtablissement(entity.getDateEtablissement());
        dto.setOfficierId(entity.getOfficierEtat().getId());
        dto.setCommuneId(entity.getCommune().getId());

        return dto;
    }
}
