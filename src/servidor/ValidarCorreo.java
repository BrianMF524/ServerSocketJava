package servidor;

public class ValidarCorreo {
    public static boolean esValido(String correo){
        for(String dominio:GeneradorCorreo.DOMINIOS){
            if (correo.endsWith(dominio)){
                return true;
            }
        }
        return false;
    }
}
