package ny.rina.gestioncommune.core;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import ny.rina.gestioncommune.core.service.ServiceImpl;


public abstract class Controller<
        E, // Entity
        R, // Response DTO
        Q, // Request DTO
        S extends ServiceImpl<E,R, Q>
> {


    protected final S service;


    protected Controller(S service) {
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