import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MessageProcessorTest {

    @Mock
    private MessageService messageService;

    private MessageProcessor messageProcessor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        messageProcessor = new MessageProcessor(messageService);
    }

    @Test
    void testProcessMessagesNumberOfInvocations() {
        List<Message> messages = Arrays.asList(
                new Message("Tim", "Tom", "Hello Tom!"),
                new Message("Sven", "Nils", "Hello Nils!"));

        messageProcessor.processMessages(messages);

        verify(messageService, times(1)).sendMessage("Tom", "Hello Tom!");
        verify(messageService, times(1)).sendMessage("Nils", "Hello Nils!");
    }

    @Test
    void testProcessMessagesContentOfInvocationsWithArgumentCaptor() {
        List<Message> messages = Arrays.asList(
                new Message("Tim", "Tom", "Hello Tom!"),
                new Message("Sven", "Nils", "Hello Nils!"));

        messageProcessor.processMessages(messages);

        ArgumentCaptor<String> receiverCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> contentCaptor = ArgumentCaptor.forClass(String.class);
        verify(messageService, times(2)).sendMessage(receiverCaptor.capture(), contentCaptor.capture());

        List<String> capturedReceivers = receiverCaptor.getAllValues();
        List<String> capturedContents = contentCaptor.getAllValues();

        assertEquals("Tom", capturedReceivers.get(0));
        assertEquals("Hello Tom!", capturedContents.get(0));
        assertEquals("Nils", capturedReceivers.get(1));
        assertEquals("Hello Nils!", capturedContents.get(1));
    }

    @Test
    void testProcessMessagesContentOfInvocationsWithIncreasedObservability() {
        messageService = new MessageService();
        messageProcessor = new MessageProcessor(messageService);

        List<Message> messages = Arrays.asList(
                new Message("Tim", "Tom", "Hello Tom!"),
                new Message("Sven", "Nils", "Hello Nils!"));

        messageProcessor.processMessages(messages);

        assertEquals(2, messageService.getSentMessages().size());
        assertEquals("Hello Tom!", messageService.getSentMessages().get(0).getContent());
        assertEquals("Hello Nils!", messageService.getSentMessages().get(1).getContent());
    }
}