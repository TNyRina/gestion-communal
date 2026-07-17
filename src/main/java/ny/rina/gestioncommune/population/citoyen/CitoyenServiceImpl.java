package ny.rina.gestioncommune.population.citoyen;

import org.springframework.stereotype.Service;

import ny.rina.gestioncommune.geo.fokontany.Fokontany;
import ny.rina.gestioncommune.geo.fokontany.FokontanyRepository;
import ny.rina.gestioncommune.population.citoyen.dto.CitoyenMapper;
import ny.rina.gestioncommune.population.citoyen.dto.CitoyenRequestDTO;
import ny.rina.gestioncommune.population.citoyen.dto.CitoyenResponseDTO;
import ny.rina.gestioncommune.population.personne.PersonneServiceImpl;


@Service
public class CitoyenServiceImpl 
        extends PersonneServiceImpl<Citoyen, CitoyenResponseDTO, CitoyenRequestDTO> {


    private final FokontanyRepository fokontanyRepository;



    public CitoyenServiceImpl(
            CitoyenRepository citoyenRepository,
            FokontanyRepository fokontanyRepository
    ){
        super(citoyenRepository, Citoyen::new);
        this.fokontanyRepository = fokontanyRepository;
    }



	@Override
	protected CitoyenResponseDTO toResponseDTO(Citoyen citoyen) {
		return CitoyenMapper.toResponseDTO(citoyen);
	}



    @Override
    protected void toEntity(Citoyen citoyen, CitoyenRequestDTO dto) {
        toPersonneEntity(citoyen, dto);

        citoyen.setProfession(dto.getProfession());
        citoyen.setSituationFamiliale(
                dto.getSituationFamiliale()
        );


        Fokontany fokontany =
                fokontanyRepository.findById(dto.getFokontanyId())
                .orElseThrow();


        citoyen.setFokontany(fokontany);
    }
}