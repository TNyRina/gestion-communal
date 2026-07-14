package ny.rina.test_tech.population.fokontany;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
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
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FokontanyDTO getById(Long id) {
        Fokontany fokontany = fokontanyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fokontany non trouvé avec l'id : " + id));
        return convertToDTO(fokontany);
    }

    public FokontanyDTO create(FokontanyDTO dto) {
        Fokontany fokontany = new Fokontany();
        fokontany.setNom(dto.getNom());
        fokontany.setCode(dto.getCode());
        
        Fokontany saved = fokontanyRepository.save(fokontany);
        return convertToDTO(saved);
    }

    public FokontanyDTO update(Long id, FokontanyDTO dto) {
        Fokontany fokontany = fokontanyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fokontany non trouvé avec l'id : " + id));
        
        fokontany.setNom(dto.getNom());
        fokontany.setCode(dto.getCode());
        
        Fokontany updated = fokontanyRepository.save(fokontany);
        return convertToDTO(updated);
    }

    public void delete(Long id) {
        if (!fokontanyRepository.existsById(id)) {
            throw new EntityNotFoundException("Fokontany non trouvé avec l'id : " + id);
        }
        fokontanyRepository.deleteById(id);
    }

    private FokontanyDTO convertToDTO(Fokontany fokontany) {
        FokontanyDTO dto = new FokontanyDTO();
        dto.setId(fokontany.getId());
        dto.setNom(fokontany.getNom());
        dto.setCode(fokontany.getCode());
        return dto;
    }

    public List<FokontanyDTO> createMany(List<FokontanyDTO> fokontanyDTOs) {

        return fokontanyDTOs.stream()
                .map(this::create)
                .toList();
    }
}
