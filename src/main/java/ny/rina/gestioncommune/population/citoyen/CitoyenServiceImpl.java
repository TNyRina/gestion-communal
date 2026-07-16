package ny.rina.gestioncommune.population.citoyen;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import ny.rina.gestioncommune.geo.fokontany.Fokontany;
import ny.rina.gestioncommune.geo.fokontany.FokontanyRepository;
import ny.rina.gestioncommune.population.citoyen.dto.CitoyenMapper;
import ny.rina.gestioncommune.population.citoyen.dto.CitoyenRequestDTO;
import ny.rina.gestioncommune.population.citoyen.dto.CitoyenResponseDTO;
import ny.rina.gestioncommune.population.personne.dto.PersonneMapperDTO;
import ny.rina.gestioncommune.population.personne.service.PersonneServiceImpl;


@Service
public class CitoyenServiceImpl 
        extends PersonneServiceImpl<Citoyen, CitoyenResponseDTO, CitoyenRequestDTO> {


    private final CitoyenRepository citoyenRepository;

    private final FokontanyRepository fokontanyRepository;



    public CitoyenServiceImpl(
            CitoyenRepository citoyenRepository,
            FokontanyRepository fokontanyRepository
    ){
        super(citoyenRepository);
        this.citoyenRepository = citoyenRepository;
        this.fokontanyRepository = fokontanyRepository;
    }



    @Override
    public CitoyenResponseDTO save(CitoyenRequestDTO dto) {
        Citoyen citoyen = new Citoyen();
        citoyen = PersonneMapperDTO.toEntity(dto, Citoyen::new);


        citoyen.setProfession(dto.getProfession());
        citoyen.setSituationFamiliale(
                dto.getSituationFamiliale()
        );
        

        Fokontany fokontany =
            fokontanyRepository.findById(dto.getFokontanyId())
            .orElseThrow(
                () -> new EntityNotFoundException(
                    "Fokontany introuvable"
                )
            );
        citoyen.setFokontany(fokontany);



        return CitoyenMapper.toResponseDTO(
                citoyenRepository.save(citoyen)
        );
    }




    @Override
    public CitoyenResponseDTO update(
            Long id,
            CitoyenRequestDTO dto
    ){

        Citoyen citoyen = citoyenRepository.findById(id)
                .orElseThrow(
                    () -> new EntityNotFoundException(
                        "Citoyen introuvable"
                    )
                );

        citoyen = PersonneMapperDTO.updateEntity(citoyen, dto);

        
        citoyen.setProfession(dto.getProfession());
        citoyen.setSituationFamiliale(
                dto.getSituationFamiliale()
        );


        Fokontany fokontany =
                fokontanyRepository.findById(dto.getFokontanyId())
                .orElseThrow();


        citoyen.setFokontany(fokontany);



        return CitoyenMapper.toResponseDTO(
                citoyenRepository.save(citoyen)
        );
    }


	@Override
	protected CitoyenResponseDTO toResponseDTO(Citoyen citoyen) {
		return CitoyenMapper.toResponseDTO(citoyen);
	}

}