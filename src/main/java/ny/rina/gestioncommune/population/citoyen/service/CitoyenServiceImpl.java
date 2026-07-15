package ny.rina.gestioncommune.population.citoyen.service;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import ny.rina.gestioncommune.geo.fokontany.Fokontany;
import ny.rina.gestioncommune.geo.fokontany.FokontanyRepository;
import ny.rina.gestioncommune.population.citoyen.Citoyen;
import ny.rina.gestioncommune.population.citoyen.CitoyenRepository;
import ny.rina.gestioncommune.population.citoyen.dto.CitoyenMapper;
import ny.rina.gestioncommune.population.citoyen.dto.CitoyenRequestDTO;
import ny.rina.gestioncommune.population.citoyen.dto.CitoyenResponseDTO;


@Service
public class CitoyenServiceImpl 
        implements CitoyenService {


    private final CitoyenRepository citoyenRepository;

    private final FokontanyRepository fokontanyRepository;



    public CitoyenServiceImpl(
            CitoyenRepository citoyenRepository,
            FokontanyRepository fokontanyRepository
    ){

        this.citoyenRepository = citoyenRepository;
        this.fokontanyRepository = fokontanyRepository;
    }



    @Override
    public List<CitoyenResponseDTO> findAll(){

        return citoyenRepository.findAll()
                .stream()
                .map(CitoyenMapper::toResponseDTO)
                .toList();
    }



    @Override
    public CitoyenResponseDTO findById(Long id){

        Citoyen citoyen = citoyenRepository.findById(id)
                .orElseThrow(
                    () -> new EntityNotFoundException(
                        "Citoyen introuvable"
                    )
                );


        return CitoyenMapper.toResponseDTO(citoyen);
    }




    @Override
    public CitoyenResponseDTO save(
            CitoyenRequestDTO dto
    ){

        Fokontany fokontany =
            fokontanyRepository.findById(dto.getFokontanyId())
            .orElseThrow(
                () -> new EntityNotFoundException(
                    "Fokontany introuvable"
                )
            );


        Citoyen citoyen = new Citoyen();


        citoyen.setNom(dto.getNom());
        citoyen.setPrenom(dto.getPrenom());
        citoyen.setDateNaissance(dto.getDateNaissance());
        citoyen.setSexe(dto.getSexe());
        citoyen.setLieuNaissance(dto.getLieuNaissance());
        citoyen.setNumeroCIN(dto.getNumeroCIN());
        citoyen.setAdresse(dto.getAdresse());


        citoyen.setProfession(dto.getProfession());
        citoyen.setSituationFamiliale(
                dto.getSituationFamiliale()
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


        citoyen.setNom(dto.getNom());
        citoyen.setPrenom(dto.getPrenom());
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
    public void delete(Long id){

        citoyenRepository.deleteById(id);
    }

}