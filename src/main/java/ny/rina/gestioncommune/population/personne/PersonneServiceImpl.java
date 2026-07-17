package ny.rina.gestioncommune.population.personne;

import java.util.function.Supplier;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.EntityNotFoundException;
import ny.rina.gestioncommune.core.service.ServiceImpl;
import ny.rina.gestioncommune.population.personne.dto.PersonneDTO;

public abstract class PersonneServiceImpl<
        E extends Personne, // Enity
        R extends PersonneDTO, // Response DTO
        Q extends PersonneDTO// Request DTO
> extends ServiceImpl<E,R,Q>{

    private final Supplier<E> supplier;

    protected PersonneServiceImpl(JpaRepository<E, Long> repository, Supplier<E> supplier) {
        super(repository);
        this.supplier = supplier;
    }


     @Override
    public R save(Q dto) {
        E personne = supplier.get();
        toEntity(personne, dto);



        return toResponseDTO(
                repository.save(personne)
        );
    }




    @Override
    public R update(
            Long id,
            Q dto
    ){

        E personne = repository.findById(id)
                .orElseThrow(
                    () -> new EntityNotFoundException(
                        "Personne introuvable"
                    )
                );

        toEntity(personne, dto);



        return toResponseDTO(
                repository.save(personne)
        );
    }



    protected void toPersonneEntity(E entity, Q dto){
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setDateNaissance(dto.getDateNaissance());
        entity.setLieuNaissance(dto.getLieuNaissance());
        entity.setSexe(dto.getSexe());
        entity.setNumeroCIN(dto.getNumeroCIN());
        entity.setAdresse(dto.getAdresse());
    }
}
