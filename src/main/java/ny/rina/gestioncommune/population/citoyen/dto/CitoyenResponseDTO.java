package ny.rina.gestioncommune.population.citoyen.dto;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CitoyenResponseDTO extends CitoyenDTO{
    private Long id;

    private String Fokotany;

    private String Commune;
}
