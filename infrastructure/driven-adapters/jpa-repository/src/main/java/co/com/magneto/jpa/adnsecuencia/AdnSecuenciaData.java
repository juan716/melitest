package co.com.magneto.jpa.adnsecuencia;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "AdnSecuencia")
@NoArgsConstructor
@Data
public class AdnSecuenciaData {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    @Column(name="dna")
    private String dna;

    @Column(name="mutant")
    private boolean mutant;



}
