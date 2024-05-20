package zest;

import org.h2.mvstore.tx.Transaction;

public interface TransactionService {
    void processTransaction(Transaction transaction);
}
