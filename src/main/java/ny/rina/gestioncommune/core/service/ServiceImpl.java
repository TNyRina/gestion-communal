package ny.rina.gestioncommune.core.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class ServiceImpl<
        E, // Enity
        R, // Response DTO
        Q // Request DTO
> implements Service<R, Q> {


    protected final JpaRepository<E, Long> repository;


    protected ServiceImpl(
            JpaRepository<E, Long> repository
    ){
        this.repository = repository;
    }

     @Override
    public R findById(Long id) {

        E entity = repository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Introuvable"));

        return toResponseDTO(entity);
    }


    @Override
    public List<R> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }


    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


    protected abstract R toResponseDTO(E entity);

}