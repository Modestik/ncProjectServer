package nc.test.exception;

public class NotFoundException extends RuntimeException {

    private final Object object;

    public NotFoundException(Object object) {
        this.object = object;
    }

    @Override
    public String getMessage() {
        return "Object = " + object + " not found";
    }
}
