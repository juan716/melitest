package co.com.magneto.model.adnsecuencia.gateways;

import co.com.magneto.model.adnsecuencia.AdnSecuencia;

import java.io.IOException;
import java.util.List;

public interface AdnSecuenciaRepository {
    AdnSecuencia save(AdnSecuencia adn);
    List<AdnSecuencia> getStats() throws IOException;
}
