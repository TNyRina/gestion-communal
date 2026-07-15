package ny.rina.test_tech.geo.fokontany;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ny.rina.test_tech.geo.commune.Commune;
import ny.rina.test_tech.geo.commune.CommuneRepository;
import ny.rina.test_tech.geo.fokontany.dto.FokontanyDTO;
import ny.rina.test_tech.geo.fokontany.dto.FokontanyMapper;
import ny.rina.test_tech.geo.location.service.LocationServiceImpl;

@Service
@Transactional
public class FokontanyService extends LocationServiceImpl<Fokontany, FokontanyDTO>{

    private final CommuneRepository communeRepository;

    protected FokontanyService(FokontanyRepository repository, CommuneRepository communeRepository) {
        super(repository);
        this.communeRepository = communeRepository;
    }

    public List<FokontanyDTO> createMany(List<FokontanyDTO> dtos) {

        return dtos.stream()
                .map(this::create)
                .toList();
    }

    @Override
    protected Fokontany toEntity(FokontanyDTO dto) {

        Fokontany fokontany = new Fokontany();

        fokontany.setNom(dto.getNom());
        fokontany.setCode(dto.getCode());


        Commune commune = communeRepository.findById(dto.getCommuneId())
                .orElseThrow(() ->
                    new RuntimeException(
                        "Commune introuvable : " + dto.getCommuneId()
                    )
                );

        fokontany.setCommune(commune);

        return fokontany;
    }

    @Override
    protected FokontanyDTO toDTO(Fokontany fokontany) {
        return FokontanyMapper.toDTO(fokontany);
    }
}
