package ny.rina.gestioncommune.geo.commune;
import org.springframework.stereotype.Service;

import ny.rina.gestioncommune.geo.commune.dto.CommuneDTO;
import ny.rina.gestioncommune.geo.commune.dto.CommuneMapper;
import ny.rina.gestioncommune.geo.location.service.LocationServiceImpl;

@Service
public class CommuneService extends LocationServiceImpl<Commune, CommuneDTO>{
    protected CommuneService(CommuneRepository repository) {
        super(repository);
    }

    @Override
    protected Commune toEntity(CommuneDTO dto) {

        Commune commune = new Commune();

        commune.setNom(dto.getNom());
        commune.setCode(dto.getCode());

        return commune;
    }

    @Override
    protected CommuneDTO toDTO(Commune commune) {
        return CommuneMapper.toDTO(commune);
    }
}
