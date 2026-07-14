package ny.rina.gestioncommune.population.citoyen.service;

import java.util.List;

import ny.rina.gestioncommune.population.citoyen.DTO.CitoyenRequestDTO;
import ny.rina.gestioncommune.population.citoyen.DTO.CitoyenResponseDTO;

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
