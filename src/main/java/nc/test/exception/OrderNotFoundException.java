package nc.test.exception;

public class OrderNotFoundException extends RuntimeException {

    private final long orderId;

    public OrderNotFoundException(long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String getMessage() {
        return "Order with id = " + orderId + " not found";
    }
}
