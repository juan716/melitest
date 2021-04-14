package co.com.magneto.api;

import co.com.magneto.model.common.Stats;
import co.com.magneto.usecase.adnsecuencia.AdnSecuenciaUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class statsController {

    private final AdnSecuenciaUseCase useCaseAdnSecuencia;

    @ApiOperation(value="stats", notes="servicio que devuelve las estadísticas de las verificaciones de ADN enviando la secuencia de ADN mediante")
    @ApiResponses(value= {@ApiResponse(code = 200, message = "Se procesa la información correctamente"),
            @ApiResponse(code = 403, message="Información no fue procesada")})
    @GetMapping()
    public Stats stats() {

      return   this.useCaseAdnSecuencia.getStats();
    }
}
