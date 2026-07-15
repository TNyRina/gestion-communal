package ny.rina.gestioncommune.population.citoyen.service;

import java.util.List;

import ny.rina.test_tech.population.citoyen.dto.CitoyenRequestDTO;
import ny.rina.test_tech.population.citoyen.dto.CitoyenResponseDTO;

public interface CitoyenService {
    List<CitoyenResponseDTO> findAll();


    CitoyenResponseDTO findById(Long id);


    CitoyenResponseDTO save(
            CitoyenRequestDTO dto
    );


    CitoyenResponseDTO update(
            Long id,
            CitoyenRequestDTO dto
    );


    void delete(Long id);
}
