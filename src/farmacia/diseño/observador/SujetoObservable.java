package farmacia.diseño.observador;

/**
 *
 * @author USUARIO
 */
public interface SujetoObservable {
    public void enlazarObservador(Observador o);
    public void notificar();
}
