package zest;

import org.h2.mvstore.tx.Transaction;

public interface AuditService {
    void onTransactionComplete(Transaction transaction);
}
