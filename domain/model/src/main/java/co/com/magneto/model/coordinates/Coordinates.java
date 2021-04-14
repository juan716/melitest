package co.com.magneto.model.coordinates;

import lombok.*;


@Getter
@Setter
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class Coordinates {

    private Integer x;
    private Integer y;
}