package ny.rina.gestioncommune.population.citoyen;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ny.rina.gestioncommune.population.citoyen.dto.CitoyenRequestDTO;
import ny.rina.gestioncommune.population.citoyen.dto.CitoyenResponseDTO;
import ny.rina.gestioncommune.population.citoyen.service.CitoyenService;


@RestController
@RequestMapping("/api/citoyens")
public class CitoyenController {
    private final CitoyenService service;


    public CitoyenController(
            CitoyenService service
    ){

        this.service = service;
    }



    @GetMapping
    public ResponseEntity<List<CitoyenResponseDTO>> findAll(){

        return ResponseEntity.ok(
                service.findAll()
        );
    }




    @GetMapping("/{id}")
    public ResponseEntity<CitoyenResponseDTO> findById(
            @PathVariable Long id
    ){

        return ResponseEntity.ok(
                service.findById(id)
        );
    }




    @PostMapping
    public ResponseEntity<CitoyenResponseDTO> save(
            @RequestBody CitoyenRequestDTO dto
    ){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                    service.save(dto)
                );
    }




    @PutMapping("/{id}")
    public ResponseEntity<CitoyenResponseDTO> update(
            @PathVariable Long id,
            @RequestBody CitoyenRequestDTO dto
    ){

        return ResponseEntity.ok(
                service.update(id,dto)
        );
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ){

        service.delete(id);

        return ResponseEntity.noContent()
                .build();
    }
}
