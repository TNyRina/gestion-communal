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


    /**
     * Crée plusieurs Fokontany à partir d'une liste de DTOs.
     *
     * Elle permet d'effectuer une création en masse tout en réutilisant
     * la logique métier déjà définie dans la méthode {@code create}.
     *
     * Exemple d'utilisation :
     *
     * <pre>
     * List&lt;FokontanyDTO&gt; created = service.createMany(fokontanyList);
     * </pre>
     *
     * @param dtos liste des DTOs représentant les Fokontany à créer
     * @return liste des DTOs des Fokontany créés
     */
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
