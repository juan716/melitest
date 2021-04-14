package co.com.magneto.model.common;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class Stats {
    private long count_mutant_dna;
    private long count_human_dna;
    private float ratio;
}
