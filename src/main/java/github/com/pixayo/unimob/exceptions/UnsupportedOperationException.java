package github.com.pixayo.unimob.exceptions;

/**
 * Exceção lançada para indicar que uma operação não foi implementada.
 */
public class UnsupportedOperationException extends RuntimeException {
    public UnsupportedOperationException(String message) {
        super(message);
    }
}
