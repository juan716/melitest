package co.com.magneto.usecase.validationrequest;

import co.com.magneto.model.adnsecuencia.AdnSecuencia;
import co.com.magneto.model.common.RequestAdn;
import co.com.magneto.model.common.ResponseData;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static co.com.magneto.model.common.Constants.*;

@RequiredArgsConstructor
public class ValidationRequestUseCase {


    public boolean BaseNitrogenada(RequestAdn adn){
        boolean resp = false;

        for (int i = 0; i < adn.getDna().size(); i++) {
                String pattern = "^([ATGC]*)$";
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(adn.getDna().get(i));
                if(!m.matches()){
                    resp= true;
                }
        }
        return resp;
    }

    public boolean SizeBaseNitrogenada(RequestAdn adn){
        int size = adn.getDna().size();
        int tamañon = 0;
        boolean resp = false;

        for (String row : adn.getDna()){
            tamañon= row.length();
            if (size!=tamañon){
                resp=true;
            }
        }
        return resp;
    }



}
