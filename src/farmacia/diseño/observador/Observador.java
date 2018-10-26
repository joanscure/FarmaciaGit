package farmacia.diseño.observador;

import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public interface Observador {
    public void update(ArrayList<Object> o); //actualizar cuando el sujeto lo notifique o dispare un evento
}
