package zest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class testBusTracker {

    private GPSDeviceService gpsDeviceService;
    private MapService mapService;
    private NotificationService notificationService;
    private BusTracker busTracker;

    @BeforeEach
    public void setUp() {
        gpsDeviceService = Mockito.mock(GPSDeviceService.class);
        mapService = Mockito.mock(MapService.class);
        notificationService = Mockito.mock(NotificationService.class);
        busTracker = new BusTracker(gpsDeviceService, mapService, notificationService);
    }

    @Test
    public void testUpdateLocation() {
        Location testLocation = new Location(1, 1, false, "testLocation");
        when(gpsDeviceService.getCurrentLocation("test")).thenReturn(testLocation);
        busTracker.updateBusLocation("test");
        verify(mapService, Mockito.times(1)).updateMap("test", testLocation);
    }

    @Test
    public void testNotification() {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> idCaptor = ArgumentCaptor.forClass(String.class);
        Location testLocation = new Location(1, 1, true, "testWaypoint");
        when(gpsDeviceService.getCurrentLocation("test")).thenReturn(testLocation);
        busTracker.updateBusLocation("test");
        Mockito.verify(notificationService, Mockito.times(1)).notifyPassengers(idCaptor.capture(), captor.capture());
        assertEquals(idCaptor.getValue(), "test");
        assertEquals(captor.getValue(), "The bus has arrived at " + testLocation.getWaypointName());

    }

    @Test
    public void testGPSSignalLoss() {
        when(gpsDeviceService.getCurrentLocation("test")).thenReturn(null);
        busTracker.updateBusLocation("test");
        Mockito.verify(notificationService, Mockito.times(1)).notifyPassengers("test", "GPS signal lost. Please check back later.");
    }
}
