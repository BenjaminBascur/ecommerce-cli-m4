public class CantidadInvalidaException extends Exception {
    
    // Este constructor recibe el mensaje de error que queramos mostrar
    public CantidadInvalidaException(String mensaje) {
        super(mensaje);
    }
}