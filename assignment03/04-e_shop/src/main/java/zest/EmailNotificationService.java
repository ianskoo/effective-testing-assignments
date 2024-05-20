package zest;

public class EmailNotificationService implements EventListener {
    @Override
    public Order onOrderPlaced(Order order) {
        // Logic to send email about the order would go here...
        System.out.println("Email sent for order " + order.getOrderId());

        // Return order if no exceptions were raised
        return order;
    }
}