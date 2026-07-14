package ny.rina.gestioncommune.population.fokontany;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ny.rina.gestioncommune.population.fokontany.DTO.FokontanyDTO;

import java.util.List;

@RestController
@RequestMapping("/api/fokontany")
public class FokontanyController {

    private final FokontanyService fokontanyService;

    public FokontanyController(FokontanyService fokontanyService) {
        this.fokontanyService = fokontanyService;
    }

    @GetMapping
    public ResponseEntity<List<FokontanyDTO>> getAllFokontany() {
        return ResponseEntity.ok(fokontanyService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FokontanyDTO> getFokontanyById(@PathVariable Long id) {
        return ResponseEntity.ok(fokontanyService.getById(id));
    }

    @PostMapping
    public ResponseEntity<FokontanyDTO> createFokontany(@RequestBody FokontanyDTO fokontanyDTO) {
        FokontanyDTO created = fokontanyService.create(fokontanyDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FokontanyDTO> updateFokontany(@PathVariable Long id, @RequestBody FokontanyDTO fokontanyDTO) {
        return ResponseEntity.ok(fokontanyService.update(id, fokontanyDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFokontany(@PathVariable Long id) {
        fokontanyService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<FokontanyDTO>> createMany(
            @RequestBody List<FokontanyDTO> fokontanyDTOs) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(fokontanyService.createMany(fokontanyDTOs));
    }
}