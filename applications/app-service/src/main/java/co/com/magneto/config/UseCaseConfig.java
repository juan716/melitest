package co.com.magneto.config;


import co.com.magneto.model.adnsecuencia.AdnSecuencia;
import co.com.magneto.model.adnsecuencia.gateways.AdnSecuenciaRepository;
import co.com.magneto.usecase.adnsecuencia.AdnSecuenciaUseCase;
import co.com.magneto.usecase.mutant.MutantUseCase;
import co.com.magneto.usecase.validationrequest.ValidationRequestUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public AdnSecuenciaUseCase adnSecuenciaUseCaseConfig(AdnSecuenciaRepository adnSecuenciaRepository, ObjectMapper mapper ) {
        return new AdnSecuenciaUseCase(adnSecuenciaRepository,mapper );
    }
}