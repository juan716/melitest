package co.com.magneto.config;


import co.com.magneto.jpa.adnsecuencia.AdnSecuenciaRepositoryAdapter;
import co.com.magneto.usecase.adnsecuencia.AdnSecuenciaUseCase;
import co.com.magneto.usecase.mutant.MutantUseCase;
import co.com.magneto.usecase.validationrequest.ValidationRequestUseCase;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UseCaseConfig {
    @Bean
    public MutantUseCase MutantUseCase() {
        return new MutantUseCase();
    }
    @Bean
    public ValidationRequestUseCase ValidationRequestUseCase() {
        return new ValidationRequestUseCase();
    }
    @Bean
    public AdnSecuenciaUseCase adnSecuenciaUseCaseConfig(AdnSecuenciaRepositoryAdapter adnSecuenciaRepository ) {
        return new AdnSecuenciaUseCase(adnSecuenciaRepository );
    }
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapperImp();
    }
}