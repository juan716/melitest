package co.com.magneto.api;

import co.com.magneto.model.adnsecuencia.AdnSecuencia;
import co.com.magneto.model.common.RequestAdn;
import co.com.magneto.model.common.ResponseData;
import co.com.magneto.usecase.adnsecuencia.AdnSecuenciaUseCase;
import co.com.magneto.usecase.mutant.MutantUseCase;
import co.com.magneto.usecase.validationrequest.ValidationRequestUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping(value = "/mutant", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class mutantController {

    private final MutantUseCase useCaseMutant;
    private final ValidationRequestUseCase useCaseValidation;
    private final AdnSecuenciaUseCase useCaseAdnSecuencia;

    private ResponseData responseData = new ResponseData();

    @ApiOperation(value="mutant", notes="Servicio que puede detectar si un humano es mutante enviando la secuencia de ADN mediante")
    @ApiResponses(value= {@ApiResponse(code = 200, message = "Se procesa la información correctamente"),
            @ApiResponse(code = 403, message="Información no fue procesada")})
    @PostMapping()
    public ResponseEntity mutant(@RequestBody RequestAdn adn) {

        boolean isMutant;

        responseData = this.useCaseValidation.Valido(adn);
        if (responseData.getCode()!="200"){
            return new ResponseEntity<String>(responseData.getDetail(), HttpStatus.BAD_REQUEST);
        }
        isMutant=this.useCaseMutant.isMutant(adn);

        AdnSecuencia objAdn =  AdnSecuencia.builder().dna(adn.getDna().toString()).build();
        //se consume metodo async
        this.useCaseAdnSecuencia.sendAdnSecuencia(objAdn);

        if (isMutant){
           return new ResponseEntity<String>("", HttpStatus.OK);
        }
        return new ResponseEntity<String>("", HttpStatus.FORBIDDEN);
    }
}
