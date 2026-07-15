package ny.rina.gestioncommune.geo.commune;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ny.rina.gestioncommune.geo.commune.dto.CommuneDTO;

@RestController
@RequestMapping("/api/communes")
public class CommuneController {
    private final CommuneService communeService;

    public CommuneController(CommuneService communeService){
        this.communeService = communeService;
    }


    @PostMapping
    public ResponseEntity<CommuneDTO> create(
            @RequestBody CommuneDTO dto) {

        CommuneDTO created = this.communeService.create(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }


    @GetMapping
    public ResponseEntity<List<CommuneDTO>> findAll() {

        return ResponseEntity.ok(
                this.communeService.findAll()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<CommuneDTO> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                this.communeService.findById(id)
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<CommuneDTO> update(
            @PathVariable Long id,
            @RequestBody CommuneDTO dto) {

        CommuneDTO updated =
                this.communeService.update(id, dto);

        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        this.communeService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
