import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class TicketManagerTest {
    private NotificationService notificationService;
    private LogService logService;
    private TicketRepository ticketRepository;
    private TicketManager ticketManager;

    @BeforeEach
    public void setUp() {
        notificationService = Mockito.mock(NotificationService.class);
        logService = Mockito.mock(LogService.class);
        ticketRepository = Mockito.mock(TicketRepository.class);
        ticketManager = new TicketManager(notificationService, logService, ticketRepository);
    }

    @Test
    public void testCreateTicketSuccess() {
        Ticket ticket = new Ticket("email@test.com", "Issue description", TicketPriority.NORMAL);

        ticketManager.createTicket(ticket);

        verify(logService).logTicketCreation(ticket);
        verify(notificationService).notifyCustomer(eq("email@test.com"), any(String.class));
        verify(ticketRepository).save(ticket);
    }

    @Test
    public void testCreateTicketEmptyEmail() {

        Ticket ticket = new Ticket("", "Issue description", TicketPriority.NORMAL);

        ticketManager.createTicket(ticket);

        verify(logService).logTicketCreation(ticket);
        verify(notificationService).notifyCustomer(eq(""), any(String.class));
        verify(ticketRepository).save(ticket);
    }

    @Test
    public void testCreateTicketNotificationFailure() {

        Ticket ticket = new Ticket("email@test.com", "Issue description", TicketPriority.NORMAL);
        doThrow(new RuntimeException("Notification failure")).when(notificationService).notifyCustomer(anyString(),
                anyString());

        ticketManager.createTicket(ticket);

        verify(logService).logTicketCreation(ticket);
        verify(notificationService).notifyCustomer(eq("email@test.com"), any(String.class));
        verify(ticketRepository).save(ticket);
    }

    @Test
    public void testCreateTicketLogFailure() {

        Ticket ticket = new Ticket("email@test.com", "Issue description", TicketPriority.NORMAL);
        doThrow(new RuntimeException("Log failure")).when(logService).logTicketCreation(any(Ticket.class));

        ticketManager.createTicket(ticket);

        verify(logService).logTicketCreation(ticket);
        verify(notificationService).notifyCustomer(eq("email@test.com"), any(String.class));
        verify(ticketRepository).save(ticket);
    }
}
