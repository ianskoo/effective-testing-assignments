# 05 Ticket System

**Identify dependencies**

- NotificationService: 
This service notifies the customer. Mocking this service will allow us to verify that a notification is sent without actually sending an email.

- LogService:
This service logs ticket creations. By mocking this service we can verify that a log entry is made without interacting with the real logging system.

- TicketRepository:
We may also want to mock the repository to avoid interacting with a real database during testing.

**Drawbacks of using Mocks in this scenario**

- By using mocks our tests naturally are more coupled with the code they test. This makes refactoring difficult.

- Mocking services like this can sometimes give us a false sense of security because they do not test the actual integration between the components.

- Keeping our mocks updated with the actual service interfaces requires more time and effort.

**Tests**

For our tests we use mocks of NotificationService, LogService and TicketRepository.

To test if all services are called correctly when creating a ticket we added the testCreateTicketSuccess test to the TicketManagerTest.

We also added the testCreateTicketEmptyEmail test to see how the system handles ticket creation with an empty email input.

**Handling of Failures in Notification and Logging**

To simulate failures in notification and logging we created two more tests: testCreateTicketNotificationFailure and testCreateTicketLogFailure. The mocks in these tests are set to throw an exception when notifyCustomer (for first test) and logTicketCreation (for second test) are called. By simulating these failures and verifying that ticket creation still completes we can ensure that our ticket creation process is resilient. We had to add exception handling to our createTicket method so that if one part of the ticket creation fails the rest still completes.

