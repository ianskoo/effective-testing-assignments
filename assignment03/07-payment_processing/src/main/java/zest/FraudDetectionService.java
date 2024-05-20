package zest;

import org.h2.mvstore.tx.Transaction;

public interface FraudDetectionService {
    boolean evaluateTransaction(Transaction transaction);
}
