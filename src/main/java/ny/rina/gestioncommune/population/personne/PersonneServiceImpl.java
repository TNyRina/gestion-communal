package ny.rina.gestioncommune.population.personne;

import java.util.function.Supplier;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.EntityNotFoundException;
import ny.rina.gestioncommune.core.service.ServiceImpl;
import ny.rina.gestioncommune.population.personne.dto.PersonneDTO;




/**
 * Service métier générique permettant de gérer les entités de type
 * {@link Personne}.
 *
 * Cette classe étend {@link ServiceImpl} afin de réutiliser les opérations
 * CRUD communes, puis ajoute les traitements spécifiques aux entités
 * représentant une personne.
 *
 * Elle centralise notamment :
 * <ul>
 *     <li>la création d'une nouvelle personne</li>
 *     <li>la modification d'une personne existante</li>
 *     <li>la conversion des données communes provenant des DTO</li>
 *     <li>la création dynamique d'une instance d'entité</li>
 * </ul>
 *
 * Les classes filles doivent définir les comportements spécifiques à chaque
 * type de personne, notamment la conversion vers un DTO de réponse et la
 * conversion complète d'un DTO en entité.
 *
 * Exemples de classes utilisant ce service :
 * <ul>
 *     <li>{@link CitoyenServiceImpl}</li>
 *     <li>{@link AgentCommunaleServiceImpl}</li>
 * </ul>
 *
 * Les paramètres génériques utilisés sont :
 * <ul>
 *     <li>
 *         {@code E} : type de l'entité métier héritant de {@link Personne}
 *     </li>
 *     <li>
 *         {@code R} : type du DTO utilisé pour les réponses API
 *     </li>
 *     <li>
 *         {@code Q} : type du DTO utilisé pour les requêtes API
 *     </li>
 * </ul>
 *
 * @param <E> type de l'entité Personne manipulée
 * @param <R> type du DTO de réponse
 * @param <Q> type du DTO de requête
 */
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
