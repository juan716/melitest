package co.com.magneto.model.common;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class RequestAdn {
    private List<String> dna;
}
