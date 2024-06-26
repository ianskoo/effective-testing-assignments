package zest;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.of;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.stream.Stream;

import org.mockito.ArgumentCaptor;

public class EventPublisherTest {

    static Stream<Arguments> ordersForSpecTest() {
        // Create lists of orders
        return Stream.of(
            of(new Order[]{
                new Order("1", 2), 
                new Order("2", 1), 
                new Order("3", 14)
            }, 3),
            of(new Order[]{}, 0),
            of(new Order[]{new Order("", 2)}, 1), // unclear spec
            of(new Order[]{new Order("1", 0)}, 1) // unclear spec
        );
    }
        
    // A. Number of invocations
    // First, test whether the onOrderPlaced method is called as many times as it should.
    @ParameterizedTest
    @MethodSource("ordersForSpecTest")
    public void onOrderPlacedTimesCalledTest(Order[] someOrders, int expcTimesCalled) {
        // Create mocks of EventListener-based classes
        InventoryManager mockListenerInventory = mock(InventoryManager.class);
        EmailNotificationService mockListenerEmail = mock(EmailNotificationService.class);
        
        // Pass the mocks to the class under test
        EventPublisher aEventPublisher = new EventPublisher();
        aEventPublisher.subscribe(mockListenerInventory);
        aEventPublisher.subscribe(mockListenerEmail);

        // Send orders to EventPublisher
        for (Order o : someOrders) {
            aEventPublisher.publishOrderToAllListeners(o);
        }

        // Verify that all orders have been correctly published to the Listeners
        verify(mockListenerInventory, times(expcTimesCalled)).onOrderPlaced(any(Order.class));
        verify(mockListenerEmail, times(expcTimesCalled)).onOrderPlaced(any(Order.class));
    }

    // B. Content of invocations — ArgumentCaptor
    // Counting the number of invocations may not be enough; additionally, test whether 
    // the contents of the order are as expected. Use ArgumentCaptor to do this.
    @ParameterizedTest
    @MethodSource("ordersForSpecTest")
    public void onOrderPlacedContentTest(Order[] someOrders, int expcTimesCalled) {
        // For simpler content checking, use only examples with 1 or 0 orders
        Assumptions.assumeTrue(someOrders.length <= 1);
        
        // Create mocks of EventListener-based classes
        InventoryManager mockListenerInventory = mock(InventoryManager.class);
        EmailNotificationService mockListenerEmail = mock(EmailNotificationService.class);
        
        // Pass the mocks to the class under test
        EventPublisher aEventPublisher = new EventPublisher();
        aEventPublisher.subscribe(mockListenerInventory);
        aEventPublisher.subscribe(mockListenerEmail);

        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);

        // Send orders to EventPublisher
        for (Order o : someOrders) {
            aEventPublisher.publishOrderToAllListeners(o);

            // Capture Orders with argumentcaptor
            verify(mockListenerInventory).onOrderPlaced(captor.capture());
            
            // Verify that published orders have the right contents
            assertEquals(o.getOrderId(), captor.getValue().getOrderId());
            assertEquals(o.getAmount(), captor.getValue().getAmount());

            // Do the same for EmailService
            verify(mockListenerEmail).onOrderPlaced(captor.capture());
            assertEquals(o.getOrderId(), captor.getValue().getOrderId());
            assertEquals(o.getAmount(), captor.getValue().getAmount());
        }

    }


    // C. Content of invocations — Increasing observability
    // Instead of using ArgumentCaptor, you could increase the observability of one or more 
    // classes to achieve the same goal. Implement the necessary code for increasing the 
    // observability and write additional test(s) to test whether the content of the 
    // messages is as expected.

    @ParameterizedTest
    @MethodSource("ordersForSpecTest")
    public void onOrderPlacedTimesCalledObservabilityTest(Order[] someOrders, int expcTimesCalled) {
        // Instantiate EventListener-based classes
        InventoryManager ListenerInventory = new InventoryManager();
        EmailNotificationService ListenerEmail = new EmailNotificationService();
        
        // Pass the listeners to the class under test
        EventPublisher aEventPublisher = new EventPublisher();
        aEventPublisher.subscribe(ListenerInventory);
        aEventPublisher.subscribe(ListenerEmail);

        // Send orders to EventPublisher
        int callCounter = 0;
        for (Order o : someOrders) {
            aEventPublisher.publishOrderToAllListeners(o);
            callCounter++;
        }

        assertEquals(expcTimesCalled, callCounter);
    }

    @ParameterizedTest
    @MethodSource("ordersForSpecTest")
    public void onOrderPlacedContentObservabilityTest(Order[] someOrders, int expcTimesCalled) {
        // Instantiate EventListener-based classes
        InventoryManager ListenerInventory = new InventoryManager();
        EmailNotificationService ListenerEmail = new EmailNotificationService();
        
        // Pass the listeners to the class under test
        EventPublisher aEventPublisher = new EventPublisher();
        aEventPublisher.subscribe(ListenerInventory);
        aEventPublisher.subscribe(ListenerEmail);

        // Send orders to EventPublisher
        for (Order o : someOrders) {
            assertTrue(aEventPublisher.publishOrderToAllListeners(o));
        }
    }
}
