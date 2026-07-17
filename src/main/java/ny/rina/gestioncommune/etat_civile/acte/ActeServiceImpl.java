package ny.rina.gestioncommune.etat_civile.acte;

import java.util.function.Supplier;

import org.springframework.data.jpa.repository.JpaRepository;

import ny.rina.gestioncommune.core.service.ServiceImpl;
import ny.rina.gestioncommune.etat_civile.acte.dto.ActeDTO;
import ny.rina.gestioncommune.geo.commune.Commune;
import ny.rina.gestioncommune.geo.commune.CommuneRepository;
import ny.rina.gestioncommune.population.officierEtat.OfficierEtat;
import ny.rina.gestioncommune.population.officierEtat.OfficierEtatRepository;

public abstract class ActeServiceImpl<
                                        E extends Acte, 
                                        R extends ActeDTO, 
                                        Q extends ActeDTO
                                    > extends ServiceImpl<E,R,Q> {



    private final CommuneRepository communeRepository;
    private final OfficierEtatRepository officierEtatRepository;
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
