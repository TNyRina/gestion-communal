package ny.rina.gestioncommune.etat_civile.naissance;

import org.springframework.stereotype.Service;

import ny.rina.gestioncommune.core.service.ServiceImpl;
import ny.rina.gestioncommune.etat_civile.naissance.dto.ActeNaissanceMapperDTO;
import ny.rina.gestioncommune.etat_civile.naissance.dto.ActeNaissanceRequestDTO;
import ny.rina.gestioncommune.etat_civile.naissance.dto.ActeNaissanceResponseDTO;
import ny.rina.gestioncommune.geo.commune.Commune;
import ny.rina.gestioncommune.geo.commune.CommuneRepository;
import ny.rina.gestioncommune.population.citoyen.Citoyen;
import ny.rina.gestioncommune.population.citoyen.CitoyenRepository;
import ny.rina.gestioncommune.population.officierEtat.OfficierEtat;
import ny.rina.gestioncommune.population.officierEtat.OfficierEtatRepository;


@Service
public class ActeNaissanceServiceImpl extends ServiceImpl<ActeNaissance, ActeNaissanceResponseDTO, ActeNaissanceRequestDTO>{
    private final CommuneRepository communeRepository;
    private final OfficierEtatRepository officierEtatRepository;
    private final CitoyenRepository citoyenRepository;

    protected ActeNaissanceServiceImpl(ActeNaissanceRepository repository, CommuneRepository communeRepository, OfficierEtatRepository officierEtatRepository, CitoyenRepository citoyenRepository) {
        super(repository);
        this.communeRepository = communeRepository;
        this.officierEtatRepository = officierEtatRepository;
        this.citoyenRepository = citoyenRepository;
    }

    @Override
    public ActeNaissanceResponseDTO save(ActeNaissanceRequestDTO dto) {
        ActeNaissance acte = new ActeNaissance();

        return ActeNaissanceMapperDTO.toResponseDTO(
            repository.save(toEntity(acte, dto))
        );
    }

    @Override
    public ActeNaissanceResponseDTO update(Long id, ActeNaissanceRequestDTO dto) {
        ActeNaissance acte = repository.findById(id).orElseThrow(() ->
                    new RuntimeException("Acte Introuvable"));

        return ActeNaissanceMapperDTO.toResponseDTO(
            repository.save(toEntity(acte, dto))
        );
    }

    @Override
    protected ActeNaissanceResponseDTO toResponseDTO(ActeNaissance entity) {
        return ActeNaissanceMapperDTO.toResponseDTO(entity);
    }

    private ActeNaissance toEntity(ActeNaissance acte, ActeNaissanceRequestDTO dto){
        acte.setNumero(dto.getNumero());
        acte.setDateEtablissement(dto.getDateEtablissement());

        
        Commune commune = communeRepository.findById(dto.getCommuneId()).orElseThrow(() ->
                        new RuntimeException("Commune Introuvable!"));
        acte.setCommune(commune);
        


        OfficierEtat officier = officierEtatRepository.findById(dto.getOfficierId()).orElseThrow(() ->
                        new RuntimeException("Officer Introuvable!"));
        acte.setOfficierEtat(officier);



        Citoyen enfant = citoyenRepository.findById(dto.getEnfantId()).orElseThrow(() ->
                        new RuntimeException("Enfant Introuvable!"));
        acte.setEnfant(enfant);



        Citoyen mere = citoyenRepository.findById(dto.getMereId()).orElseThrow(() ->
                        new RuntimeException("Mere Introuvable!"));
        acte.setMere(mere);

        Citoyen pere = citoyenRepository.findById(dto.getPereId()).orElseThrow(() ->
                        new RuntimeException("Pere Introuvable!"));
        acte.setPere(pere);


        return acte;
    }
    
}
