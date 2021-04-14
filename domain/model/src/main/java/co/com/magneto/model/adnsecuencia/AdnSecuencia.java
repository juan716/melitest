package co.com.magneto.model.adnsecuencia;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class AdnSecuencia {
    private long id;
    private String dna;
    private boolean mutant;
}
