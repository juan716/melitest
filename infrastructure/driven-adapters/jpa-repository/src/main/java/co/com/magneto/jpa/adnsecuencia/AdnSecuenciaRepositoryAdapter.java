package co.com.magneto.jpa.adnsecuencia;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import co.com.magneto.model.adnsecuencia.AdnSecuencia;
import co.com.magneto.model.adnsecuencia.gateways.AdnSecuenciaRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Repository
public class AdnSecuenciaRepositoryAdapter implements AdnSecuenciaRepository {

    @Autowired
    private  AdnSecuenciaDataRepository repository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public AdnSecuencia save(AdnSecuencia adn) {
        AdnSecuenciaData data = mapper.convertValue(adn, AdnSecuenciaData.class);
        repository.save(data);
        return adn;
    }

    @Override
    public List<AdnSecuencia> getStats() throws IOException {
        List<AdnSecuenciaData> diffObjectsData  =  mapper.convertValue(repository.findAll(), List.class);
        String json = new Gson().toJson(diffObjectsData);
        List<AdnSecuencia> myObjects = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, AdnSecuencia.class));
        return  myObjects;
    }


}
