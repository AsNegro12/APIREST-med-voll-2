package med.voll.api.infra.errores;

public class ValidacionDeIntegridadException extends RuntimeException {
    public ValidacionDeIntegridadException(String s)
    {
        super(s);
    }
}
