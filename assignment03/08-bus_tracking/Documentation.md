Documentation bus_tracking

A. for this task we make use of the gpsDeviceService and mock it to return our own Location object when called.
    we verify that the buses location has been updated by check if the MapService mock has been called.

B. Again we use the gpsDeviceService mock to get our own location object in the method. then we use the argumentcaptor to check that the notificationService was provided with the correct busId as well as the waypoint name

C. A loss of the GPS Signal means that our notificationService mock's notifyPassengers method should be evoked, with a different message than if the GPS signal is still there. we assert that this method has been called with the GPS signal lost message as well as the correct bus id.

D. Direct method calls are simpler and faster than a event-driven update. they are generally easier to implement and the programmer has full control over the process. event-driven updates are in general more complex and can take longer depending on the latency. it's easier scalable than the direct method calling as one only needs to add another "Subscriber". As they are not as tightly coupled as direct method calls, they are much more flexible