package co.com.magneto.usecase.mutant;

import co.com.magneto.model.adnsecuencia.AdnSecuencia;
import co.com.magneto.model.common.RequestAdn;
import co.com.magneto.model.coordinates.Coordinates;
import co.com.magneto.usecase.adnsecuencia.AdnSecuenciaUseCase;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static co.com.magneto.model.common.Constants.*;

@RequiredArgsConstructor
public class MutantUseCase {


    public boolean isMutant(RequestAdn adn){
        return  secuenciaByLetter(adn);
    }

    public boolean secuenciaByLetter(RequestAdn adn){

        List<Coordinates> ListPosbyLetter = new ArrayList<>();
        List<List<Coordinates>> ListaOrientation = new ArrayList<>();
        int secuencia = 0;


        for (int i = 0; i < BASENITRO.length; i++) {
            ListPosbyLetter = PositionbyLetter(adn,BASENITRO[i]);
            ListaOrientation= ListbyOrientation(ListPosbyLetter);
            for (int n = 0; n < 3; n++)
                 if(CheckSecuencia(ListaOrientation.get(n),ListPosbyLetter)){
                   secuencia++;
                 }
            if (secuencia>=2)
            {
                return true;
            }
        }
        return false;
    }

    public List<List<Coordinates>> ListbyOrientation(List<Coordinates> ListPosbyLetter){

        List<Coordinates> ListaOrientationh = new ArrayList<>();
        List<Coordinates> ListaOrientationv = new ArrayList<>();
        List<Coordinates> ListaOrientationo = new ArrayList<>();
        List<List<Coordinates>> ListaOrientation = new ArrayList<>();

        for (Coordinates row:ListPosbyLetter) {
            ListaOrientationh.addAll(Vecinos(row.getX(), row.getY(),"h"));
            ListaOrientationv.addAll(Vecinos(row.getX(), row.getY(),"v"));
            ListaOrientationo.addAll(Vecinos(row.getX(), row.getY(),"o"));
        }
        ListaOrientation.add(ListaOrientationh);
        ListaOrientation.add(ListaOrientationv);
        ListaOrientation.add(ListaOrientationo);

        return ListaOrientation;
    }

    public List<Coordinates> PositionbyLetter(RequestAdn adn,String Letra){

        int tamaño = adn.getDna().size();
        List<List<String>> matrix = new ArrayList<List<String>>();
        List<Coordinates> listaPos = new ArrayList<Coordinates>();;

        for (int i = 0; i < tamaño; i++) {
            matrix.add(Arrays.asList(adn.getDna().get(i).split("")));
        }
        //sacar posiciones por letra
        for (List<String> row : matrix) {
            int count = 0;
            for (String L : row) {
                count++;
                if (L.indexOf(Letra)==0){
                    Coordinates pos = new Coordinates();
                    pos.setX(matrix.indexOf(row)+1);
                    pos.setY(count);
                    listaPos.add(pos);
                }
            }
        }
        return listaPos;
    }

    public List<Coordinates> Vecinos(int newx,int newy,String o) {

        List<Coordinates> ListVecinos = new ArrayList<Coordinates>();
        switch (o){
            case "h":
                ListVecinos.add(new Coordinates() {{ setX(newx);setY(newy + 1); }});
                if(newy-1 > 0) {
                    ListVecinos.add(new Coordinates() {{ setX(newx);setY(newy - 1); }});
                };
                break;
            case "v":
                ListVecinos.add(new Coordinates() {{ setX(newx+1);setY(newy); }});
                if(newx-1 > 0) {
                    ListVecinos.add(new Coordinates() {{ setX(newx-1);setY(newy); }});
                };
                break;
            case "o":
                if(newy-1 > 0 && newx-1 > 0) {
                    ListVecinos.add(new Coordinates() {{ setX(newx - 1);setY(newy - 1); }});
                }
                if(newx-1 > 0) {
                    ListVecinos.add(new Coordinates() {{ setX(newx - 1);setY(newy + 1); }});
                }
                if(newy-1 > 0) {
                    ListVecinos.add(new Coordinates() {{ setX(newx + 1);setY(newy - 1); }});
                }
                ListVecinos.add(new Coordinates() {{ setX(newx+1);setY(newy+1); }});
                break;
        }
        return ListVecinos;
    }

    public boolean CheckSecuencia(List<Coordinates> sentido,List<Coordinates> lista) {

        long countGlobal =0;
        for (Coordinates row : lista) {
            long count =0;
            count = sentido.stream().filter(e -> e.getX() == row.getX() && e.getY()==row.getY()).count();
            if (count ==2 ) countGlobal++;
        }
        if (countGlobal>=2) {
            return true;
        }
        return false;
    }


}
