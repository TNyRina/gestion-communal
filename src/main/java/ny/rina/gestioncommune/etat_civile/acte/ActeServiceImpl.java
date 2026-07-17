package ny.rina.gestioncommune.etat_civile.acte;

import java.util.function.Supplier;

import org.springframework.data.jpa.repository.JpaRepository;

import ny.rina.gestioncommune.core.service.ServiceImpl;
import ny.rina.gestioncommune.etat_civile.acte.dto.ActeDTO;
import ny.rina.gestioncommune.geo.commune.Commune;
import ny.rina.gestioncommune.geo.commune.CommuneRepository;
import ny.rina.gestioncommune.population.officierEtat.OfficierEtat;
import ny.rina.gestioncommune.population.officierEtat.OfficierEtatRepository;





/**
 * Implémentation générique des opérations métier communes aux actes d'état civil.
 *
 * Cette classe étend {@link ServiceImpl} afin de réutiliser les opérations CRUD
 * génériques et ajoute la logique spécifique aux entités héritant de
 * {@link Acte}.
 *
 * Elle centralise le traitement commun des différents types d'actes d'état civil
 * (par exemple : ActeNaissance, ActeMariage, ActeDécès, etc.) afin d'éviter
 * la duplication du code dans les services spécialisés.
 *
 * Les opérations prises en charge sont :
 * <ul>
 *     <li>
 *         Création d'un acte à partir d'un DTO de requête.
 *     </li>
 *     <li>
 *         Mise à jour d'un acte existant.
 *     </li>
 *     <li>
 *         Conversion des informations communes d'un DTO vers une entité Acte.
 *     </li>
 * </ul>
 *
 * Les attributs communs gérés par cette classe sont :
 * <ul>
 *     <li>numéro de l'acte</li>
 *     <li>date d'établissement</li>
 *     <li>commune de déclaration</li>
 *     <li>officier d'état civil responsable</li>
 * </ul>
 *
 * Les classes filles doivent compléter la méthode {@code toEntity()} afin de
 * gérer les informations spécifiques à chaque type d'acte.
 *
 * Les paramètres génériques sont :
 * <ul>
 *     <li>
 *         {@code E} : type de l'entité acte manipulée, héritant de {@link Acte}
 *     </li>
 *     <li>
 *         {@code R} : type du DTO utilisé pour les réponses, héritant de
 *         {@link ActeDTO}
 *     </li>
 *     <li>
 *         {@code Q} : type du DTO utilisé pour les requêtes, héritant de
 *         {@link ActeDTO}
 *     </li>
 * </ul>
 *
 * Exemple d'utilisation :
 *
 * <pre>
 * {@code
 * public class ActeNaissanceServiceImpl
 *        extends ActeServiceImpl<
 *              ActeNaissance,
 *              ActeNaissanceResponseDTO,
 *              ActeNaissanceRequestDTO> {
 * }
 * </pre>
 *
 * @param <E> type de l'entité Acte
 * @param <R> type du DTO de réponse
 * @param <Q> type du DTO de requête
 */
public abstract class ActeServiceImpl<
                                        E extends Acte, 
                                        R extends ActeDTO, 
                                        Q extends ActeDTO
                                    > extends ServiceImpl<E,R,Q> {



    private final CommuneRepository communeRepository;
    private final OfficierEtatRepository officierEtatRepository;

    
    /**
     * Fournisseur permettant de créer dynamiquement une instance de l'acte
     * à enregistrer.
     *
     * Permet à la classe générique de créer différents types d'actes
     * sans connaître leur classe concrète.
     */
    private final Supplier<E> supplier;



    protected ActeServiceImpl(
                                JpaRepository<E,Long> repository, 
                                CommuneRepository communeRepository, 
                                OfficierEtatRepository officierEtatRepository, 
                                Supplier<E> supplier) {
        
        super(repository);

        this.communeRepository = communeRepository;
        this.officierEtatRepository = officierEtatRepository;
        this.supplier = supplier;
    }





    @Override
    public R save(Q dto) {
        E acte = supplier.get();
        toEntity(acte, dto);

        return toResponseDTO(repository.save(acte));
    }



    
    @Override
    public R update(Long id, Q dto) {
        E acte = repository.findById(id).orElseThrow(() ->
                        new RuntimeException("Acte Introuvable!"));
        toEntity(acte, dto);

        return toResponseDTO(repository.save(acte));
    }



    /**
     * Initialise les propriétés communes d'un acte à partir d'un DTO.
     *
     * Cette méthode copie les informations communes à tous les actes :
     * <ul>
     *     <li>numéro</li>
     *     <li>date d'établissement</li>
     *     <li>commune associée</li>
     *     <li>officier d'état civil</li>
     * </ul>
     *
     * Les classes filles peuvent appeler cette méthode dans leur implémentation
     * de {@code toEntity()} afin de compléter la conversion avec leurs propres
     * attributs spécifiques.
     *
     * @param acte entité Acte à initialiser
     * @param dto DTO contenant les données sources
     */
    protected void toActeEntity(E acte, Q dto){
        acte.setNumero(dto.getNumero());
        acte.setDateEtablissement(dto.getDateEtablissement());

        
        Commune commune = communeRepository.findById(dto.getCommuneId()).orElseThrow(() ->
                        new RuntimeException("Commune Introuvable!"));
        acte.setCommune(commune);
        


        OfficierEtat officier = officierEtatRepository.findById(dto.getOfficierEtatId()).orElseThrow(() ->
                        new RuntimeException("Officer Introuvable!"));
        acte.setOfficierEtat(officier);
    }
}
