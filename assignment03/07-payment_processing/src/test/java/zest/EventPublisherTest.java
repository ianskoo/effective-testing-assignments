package zest;

import org.h2.mvstore.tx.Transaction;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class EventPublisherTest {

    private AuditService auditService;
    private AuditService auditService2;

    @Test
    public void testNumberOfInvocations() {
        EventPublisher eventPublisher = new EventPublisher();
        auditService = Mockito.mock(AuditService.class);
        eventPublisher.subscribe(auditService);
        eventPublisher.publishTransactionComplete(null);
        Mockito.verify(auditService, Mockito.times(1)).onTransactionComplete(null);
    }

    @Test
    public void testNumberOfInvocationsMultipleListeners() {
        EventPublisher eventPublisher = new EventPublisher();
        auditService = Mockito.mock(AuditService.class);
        auditService2 = Mockito.mock(AuditService.class);
        eventPublisher.subscribe(auditService);
        eventPublisher.subscribe(auditService2);
        eventPublisher.publishTransactionComplete(null);
        Mockito.verify(auditService, Mockito.times(1)).onTransactionComplete(null);
        Mockito.verify(auditService2, Mockito.times(1)).onTransactionComplete(null);
    }

    @Test
    public void testCorrectParameter() {
        EventPublisher eventPublisher = new EventPublisher();
        auditService = Mockito.mock(AuditService.class);
        ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);
        eventPublisher.subscribe(auditService);
        eventPublisher.publishTransactionComplete(null);
        Mockito.verify(auditService).onTransactionComplete(captor.capture());
        assertNull(captor.getValue());
    }

    @Test
    public void testPaymentProcessing() {
        FraudDetectionService fraudDetectionService = Mockito.mock(FraudDetectionService.class);
        when(fraudDetectionService.evaluateTransaction(null)).thenReturn(true);
        PaymentProcessor paymentProcessor = new PaymentProcessor(new EventPublisher(), Mockito.mock(TransactionService.class), fraudDetectionService);
        assertTrue(paymentProcessor.processPayment(null));
    }
}
