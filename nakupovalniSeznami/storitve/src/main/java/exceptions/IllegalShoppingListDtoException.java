package exceptions;

public class IllegalShoppingListDtoException extends RuntimeException {
    private static final long serialVersionUID = -1869579335155803521L;

    public IllegalShoppingListDtoException(String message) {
        super(message);
    }
}
