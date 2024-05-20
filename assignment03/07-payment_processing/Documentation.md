Documentation payment_processing

A. We check that the onTransactionComplete method has been called the right amount of times by creating mocks of the auditService class. We test that each method gets invoked exactly once.

B. By creating an argumentcaptor of the transaction object we can verify that the correct parameters have been passed. since mockito does not allow mocking of final class objects, and the Transaction object is not simply instantiable we pass null as the parameter. this still allows us to check whether this null has been passed as parameter to the method.

C. The process_payment method now returns a boolean to verify whether a payment has been completed or not. before there was no easy way to assert whether the payment was accepted or not.

D. The advantages of using ArgumentCaptors are definitely its simplicity as we can check the arguments in the test code itself. we can test interactions between classes and there is no refactoring required, which can be very time-costly depending on the project. there is the possibility of over-mocking, which decreases the effectiveness of the test as it becomes less "real"
 the advantages of refactoring and increasing observability are that the real code is being tested without mocking, as well as it is a great way to make a system more maintainable. The problem is, that it is usually time-costly to refactor code to have great observability and it is usually also more complex than using argumentcaptors.