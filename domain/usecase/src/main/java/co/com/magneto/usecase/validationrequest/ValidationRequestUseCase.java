package co.com.magneto.usecase.validationrequest;

import co.com.magneto.model.adnsecuencia.AdnSecuencia;
import co.com.magneto.model.common.RequestAdn;
import co.com.magneto.model.common.ResponseData;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static co.com.magneto.model.common.Constants.*;

@RequiredArgsConstructor
public class ValidationRequestUseCase {

    private ResponseData responseData = new ResponseData();


    public ResponseData Valido(RequestAdn adn){

        if (SizeBaseNitrogenada(adn).getCode() != "200"){
            return SizeBaseNitrogenada(adn);
        }else if (BaseNitrogenada(adn).getCode() != "200"){
            return BaseNitrogenada(adn);
        }
        return BaseNitrogenada(adn);
    }

    public ResponseData BaseNitrogenada(RequestAdn adn){
        long differences = 0;

        List<List<String>> triangle = new ArrayList<List<String>>();

        for (int i = 0; i < adn.getDna().size(); i++) {
            triangle.add(Arrays.asList(adn.getDna().get(i).split("")));
        }


        for (List<String> row : triangle){
            differences += row.stream()
                    .filter(e -> !e.contains(BASENITRO[0]) && !e.contains(BASENITRO[1])&& !e.contains(BASENITRO[2])&& !e.contains(BASENITRO[3])).count();
        }

        if (differences!=0){
            return new ResponseData("404", ERRORBASENITRO);
        }

        return new ResponseData("200", "");
    }

    public ResponseData SizeBaseNitrogenada(RequestAdn adn){
        int size = adn.getDna().size();
        int tamañon = 0;

        for (String row : adn.getDna()){
            tamañon= row.length();
            if (size!=tamañon){
                return new ResponseData("404", ERRORSIZEBASENITRO);
            }
        }
        return new ResponseData("200", "");
    }



}
