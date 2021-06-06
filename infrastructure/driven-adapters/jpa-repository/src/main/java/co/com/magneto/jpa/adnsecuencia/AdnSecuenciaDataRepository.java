package co.com.magneto.jpa.adnsecuencia;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;


public interface AdnSecuenciaDataRepository extends CrudRepository<AdnSecuenciaData, Long>, QueryByExampleExecutor<AdnSecuenciaData> {

}


