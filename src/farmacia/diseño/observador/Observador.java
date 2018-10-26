package farmacia.diseño.observador;

/**
 *
 * @author USUARIO
 */
public interface Observador {
    public void update(Object o,Long cod); //actualizar cuando el sujeto lo notifique o dispare un evento
}
