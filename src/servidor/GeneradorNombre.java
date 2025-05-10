package servidor;

import java.util.Random;

public class GeneradorNombre {
    private static String vocales="aeiou";
    private static String consonantes="bcdfghjklmnñpqrstvwxyz";
    private static String letras=vocales+consonantes;
    public static String generar(int longitud) {
        Random random= new Random();

        char[] resultado=new char[longitud];

        //determino la posicion aleatoria de una vocal y una consonante
        int posVocal=random.nextInt(longitud);
        int posConsonante=random.nextInt(longitud);
        while (posVocal==posConsonante){
            posConsonante= random.nextInt(longitud);
        }
        //Inserto vocal y consonante en las posiciones establecidas
        resultado[posVocal]=vocales.charAt(random.nextInt(vocales.length()));
        resultado[posConsonante]=consonantes.charAt(random.nextInt(consonantes.length()));

        //Inserto letras aleatorias en el resto del string
        for(int i=0;i<longitud;i++){
            if(i!=posVocal && i!=posConsonante){
                resultado[i]=letras.charAt(random.nextInt(letras.length()));
            }
        }

        return new String(resultado);
    }
}
