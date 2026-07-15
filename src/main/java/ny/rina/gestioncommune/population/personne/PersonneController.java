package ny.rina.gestioncommune.population.personne;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ny.rina.gestioncommune.population.personne.service.PersonneServiceImpl;


public abstract class PersonneController<
        E, // Entity
        R, // Response DTO
        Q, // Request DTO
        S extends PersonneServiceImpl<E,R, Q>
> {


    protected final S service;


    protected PersonneController(S service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<R>> findAll(){

        return ResponseEntity.ok(
                service.findAll()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<R> findById(
            @PathVariable Long id
    ){

        return ResponseEntity.ok(
                service.findById(id)
        );
    }


    @PostMapping
    public ResponseEntity<R> save(
            @RequestBody Q dto
    ){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        service.save(dto)
                );
    }


    @PutMapping("/{id}")
    public ResponseEntity<R> update(
            @PathVariable Long id,
            @RequestBody Q dto
    ){

        return ResponseEntity.ok(
                service.update(id, dto)
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