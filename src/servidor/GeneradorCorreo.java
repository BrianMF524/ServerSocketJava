package servidor;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GeneradorCorreo {
    //Dominios validos
    public static final List<String> DOMINIOS= Arrays.asList("@gmail.com","@hotmail.com");

    public static String generar(String usuario) {
        Random random=new Random();
        String dominio=DOMINIOS.get(random.nextInt(DOMINIOS.size()));
        return usuario+dominio;
    }
}
