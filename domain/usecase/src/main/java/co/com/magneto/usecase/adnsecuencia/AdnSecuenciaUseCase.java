package co.com.magneto.usecase.adnsecuencia;

import co.com.magneto.model.adnsecuencia.AdnSecuencia;
import co.com.magneto.model.adnsecuencia.gateways.AdnSecuenciaRepository;
import co.com.magneto.model.common.ResponseData;
import co.com.magneto.model.common.Stats;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class AdnSecuenciaUseCase {

    private final AdnSecuenciaRepository ticketRepository;
    private ResponseData responseData = new ResponseData();

    @Async("asyncExecutor")
    public ResponseData sendAdnSecuencia(AdnSecuencia adn) {

        try {
            this.ticketRepository.save(adn);
            responseData.setCode("200");

        } catch (IllegalArgumentException ex) {
            responseData.setCode("400");
            responseData.setDetail("Error al intentar guardar en la bd");

        }
        return responseData;
    }


    public Stats getStats() {
        Stats objStats = new Stats();
        try {
            List<AdnSecuencia> listaAdn = new ArrayList<>();
            listaAdn =  this.ticketRepository.getStats();
            objStats.setCount_human_dna(listaAdn.stream().filter(human -> human.isMutant() == false).count());
            objStats.setCount_mutant_dna(listaAdn.stream().filter(human -> human.isMutant() == true).count());
            objStats.setRatio(objStats.getCount_mutant_dna()/ objStats.getCount_human_dna());

        } catch (IllegalArgumentException | IOException ex) {
            return objStats;
        }
        return objStats;
    }

}
