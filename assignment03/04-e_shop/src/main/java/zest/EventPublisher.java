package zest;

import java.util.ArrayList;
import java.util.List;

public class EventPublisher {
    private List<EventListener> listeners = new ArrayList<>();

    public void subscribe(EventListener listener) {
        listeners.add(listener);
    }

    public boolean publishOrderToAllListeners(Order order) {
        boolean wereOrdersCorrectlyPublished = true;

        for (EventListener listener : listeners) {
            Order resOrder = listener.onOrderPlaced(order);
            if (resOrder == null || order.getAmount() != resOrder.getAmount() || order.getOrderId() != resOrder.getOrderId()) {
                wereOrdersCorrectlyPublished = false;
            };
        }
        // We know that the Order was correctly published
        return wereOrdersCorrectlyPublished; 
    }
}
