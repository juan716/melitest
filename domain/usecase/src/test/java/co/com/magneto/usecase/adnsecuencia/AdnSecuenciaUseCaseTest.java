package co.com.magneto.usecase.adnsecuencia;

import co.com.magneto.model.adnsecuencia.AdnSecuencia;
import co.com.magneto.model.adnsecuencia.gateways.AdnSecuenciaRepository;
import co.com.magneto.model.common.ResponseData;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = AdnSecuenciaUseCase.class)
public class AdnSecuenciaUseCaseTest {
    @Mock
    private AdnSecuenciaRepository adnSecuenciaRepository;

    @InjectMocks
    private AdnSecuenciaUseCase adnSecuenciaUseCase;

    AdnSecuencia adnSecuencia;
    AdnSecuencia adnSecuenciaResp;

    @Before
    public void setup() {
    adnSecuencia = AdnSecuencia.builder().dna("{\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"}").mutant(true).build();
    adnSecuenciaResp = AdnSecuencia.builder().dna("{\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"}").mutant(true).build();

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void saveSecuenciaTrue() {

        Mockito.when(adnSecuenciaRepository.save(adnSecuencia)).thenReturn(adnSecuenciaResp);
        ResponseData responseData = adnSecuenciaUseCase.sendAdnSecuencia(adnSecuencia);
        assertTrue(responseData.getCode() == "200" );

    }

    @Test(expected=NullPointerException.class)
    public void saveSecuenciaTrueException() throws IOException {

        Mockito.when(adnSecuenciaRepository.save(adnSecuencia)).thenThrow(NullPointerException.class);
        ResponseData responseData = adnSecuenciaUseCase.sendAdnSecuencia(adnSecuencia);

        thrown.expectMessage(responseData.getDetail());
        assertNotNull(responseData.getDetail());

    }
}
