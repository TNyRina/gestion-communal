package ny.rina.gestioncommune.etat_civile.acte;

import org.springframework.data.jpa.repository.JpaRepository;

import ny.rina.gestioncommune.core.service.ServiceImpl;
import ny.rina.gestioncommune.etat_civile.acte.dto.ActeDTO;
import ny.rina.gestioncommune.geo.commune.Commune;
import ny.rina.gestioncommune.geo.commune.CommuneRepository;
import ny.rina.gestioncommune.population.officierEtat.OfficierEtat;
import ny.rina.gestioncommune.population.officierEtat.OfficierEtatRepository;

public abstract class ActeServiceImpl<E extends Acte, R extends ActeDTO, Q extends ActeDTO> extends ServiceImpl<E,R,Q> {

    private final CommuneRepository communeRepository;
    private final OfficierEtatRepository officierEtatRepository;

    protected ActeServiceImpl(JpaRepository<E,Long> repository, CommuneRepository communeRepository, OfficierEtatRepository officierEtatRepository) {
        super(repository);
        this.communeRepository = communeRepository;
        this.officierEtatRepository = officierEtatRepository;
    }

    protected E toEntity(E acte, Q dto){
        acte.setNumero(dto.getNumero());
        acte.setDateEtablissement(dto.getDateEtablissement());

        
        Commune commune = communeRepository.findById(dto.getCommuneId()).orElseThrow(() ->
                        new RuntimeException("Commune Introuvable!"));
        acte.setCommune(commune);
        


        OfficierEtat officier = officierEtatRepository.findById(dto.getOfficierId()).orElseThrow(() ->
                        new RuntimeException("Officer Introuvable!"));
        acte.setOfficierEtat(officier);

        return acte;
    }
}
