package ny.rina.gestioncommune.population.fokontany;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ny.rina.gestioncommune.population.fokontany.DTO.FokontanyDTO;
import ny.rina.gestioncommune.population.fokontany.DTO.FokontanyMapper;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FokontanyService {

    private final FokontanyRepository fokontanyRepository;

    public FokontanyService(FokontanyRepository fokontanyRepository) {
        this.fokontanyRepository = fokontanyRepository;
    }

    public List<FokontanyDTO> getAll() {
        return fokontanyRepository.findAll().stream()
                .map(FokontanyMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FokontanyDTO getById(Long id) {
        Fokontany fokontany = fokontanyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fokontany non trouvé avec l'id : " + id));
        return FokontanyMapper.toDTO(fokontany);
    }

    public FokontanyDTO create(FokontanyDTO dto) {
        Fokontany fokontany = new Fokontany();
        fokontany.setNom(dto.getNom());
        fokontany.setCode(dto.getCode());
        
        Fokontany saved = fokontanyRepository.save(fokontany);
        return FokontanyMapper.toDTO(saved);
    }

    public FokontanyDTO update(Long id, FokontanyDTO dto) {
        Fokontany fokontany = fokontanyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fokontany non trouvé avec l'id : " + id));
        
        fokontany.setNom(dto.getNom());
        fokontany.setCode(dto.getCode());
        
        Fokontany updated = fokontanyRepository.save(fokontany);
        return FokontanyMapper.toDTO(updated);
    }

    public void delete(Long id) {
        if (!fokontanyRepository.existsById(id)) {
            throw new EntityNotFoundException("Fokontany non trouvé avec l'id : " + id);
        }
        fokontanyRepository.deleteById(id);
    }


    public List<FokontanyDTO> createMany(List<FokontanyDTO> fokontanyDTOs) {

        return fokontanyDTOs.stream()
                .map(this::create)
                .toList();
    }
}
