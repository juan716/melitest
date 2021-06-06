package co.com.magneto.jpa.adnsecuencia;

import co.com.magneto.model.adnsecuencia.AdnSecuencia;
import co.com.magneto.model.adnsecuencia.gateways.AdnSecuenciaRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import co.com.magneto.jpa.helper.AdapterOperations;


import java.util.List;


@Repository
public class AdnSecuenciaRepositoryAdapter
        extends AdapterOperations<AdnSecuencia, AdnSecuenciaData, Long, AdnSecuenciaDataRepository>
        implements AdnSecuenciaRepository
{
    @Autowired
    public AdnSecuenciaRepositoryAdapter(AdnSecuenciaDataRepository repository, ObjectMapper mapper) {
        /**
         * Could be use mapper.mapBuilder if your domain model implement builder pattern
         * super(repository, mapper, d ->
         * mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build()); Or using
         * mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, AdnSecuencia.class));
        }

    @Override
    public AdnSecuencia save(AdnSecuencia adn) {

        AdnSecuenciaData sessionData = repository.save(toData(adn));

        return super.toEntity(sessionData);
    }

    @Override
    public List<AdnSecuencia> getStats() {
        List<AdnSecuenciaData> secuencia = (List<AdnSecuenciaData>) repository.findAll();
        return super.toList(secuencia);

    }


}
