package zest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MovieStreamingManagerTest {
    // Write unit tests for the methods in `MovieStreamingManager` class, considering the following:
    // 1. **Use of Doubles for FileStreamService and CacheService**
    //    - Identify external dependencies and implement tests using mocks to verify proper interactions.
    // 2. **Mocking File System and Cache Interactions**
    //    - Test interactions with the mock file system and cache to ensure metadata and tokens are handled correctly.
    // 3. **Handling of Failures**
    //    - Simulate file system downtimes and cache misses to test the system's resilience and error handling.

    private MovieStreamingManager manager;

    @Mock
    private FileStreamService mockFileStreamService;

    @Mock
    private CacheService mockCacheService;

    @BeforeEach
    public void setUp() {
        // Init mocks
        MockitoAnnotations.openMocks(this);

        // Create mocks
        // mockFileStreamService = mock(FileStreamService.class);
        // mockCacheService = mock(CacheService.class);

        // Instantiate MovieStreamingManager with mock dependencies
        manager = new MovieStreamingManager(mockFileStreamService, mockCacheService);
    }

    @Test
    public void emptyMovieIdTest() {
        /** 
         * Case: empty movieID string
         * Behavior: mockCacheService returns null, fileStreamServiceMock returns null 
         * Expected output: null
         */
        String movieId = "";

        when(mockCacheService.getDetails(movieId)).thenReturn(null);
        // when(mockCacheService.cacheDetails(movieId, null));
        when(mockFileStreamService.retrieveMovie(movieId)).thenReturn(null);
        
        assertEquals(null, manager.streamMovie(movieId));
    }

    @Test
    public void existingCacheTest() {
        /** 
         * Case: movieID with existing cache 
         * Behavior: mockCacheService returns details, fileStreamServiceMock not called 
         * Expected output: cached movie details
         */
        String movieId = "1";
        String streamToken = "a";
        MovieMetadata meta = new MovieMetadata("Shrek", "Fantasy, Children");
        StreamingDetails details = new StreamingDetails(movieId, streamToken, meta);

        when(mockCacheService.getDetails(movieId)).thenReturn(details);
        
        verify(mockFileStreamService, times(0)).retrieveMovie(movieId);
        assertEquals(details, manager.streamMovie(movieId));
    }

    @Test
    public void noCacheNoMovieTest() {
        /** 
         * Case: movieID without cache, no movie found 
         * Behavior: mockCacheService returns null, fileStreamServiceMock returns null 
         * Expected output: null
         */
        String movieId = "1";

        when(mockCacheService.getDetails(movieId)).thenReturn(null);
        when(mockFileStreamService.retrieveMovie(movieId)).thenReturn(null);
        
        assertEquals(null, manager.streamMovie(movieId));
    }

    @Test
    public void noCacheMovieFoundTest() {
        /** 
         * Case: movieID without cache, movie found 
         * Behavior: fileStreamServiceMock returns MovieMetadata and streamToken, mockCacheService caches details 
         * Expected output: retrieved movie details
         */
        String movieId = "1";
        String streamToken = "a";
        MovieMetadata meta = new MovieMetadata("Shrek", "Fantasy, Children");
        StreamingDetails details = new StreamingDetails(movieId, streamToken, meta);

        when(mockCacheService.getDetails(movieId)).thenReturn(null);
        when(mockFileStreamService.retrieveMovie(movieId)).thenReturn(meta);
        when(mockFileStreamService.generateToken(movieId)).thenReturn(streamToken);
        
        
        StreamingDetails result = manager.streamMovie(movieId); 
        verify(mockCacheService, times(1)).cacheDetails(any(), any());
        assertEquals(details.getMetadata(), result.getMetadata());
        assertEquals(details.getMovieId(), result.getMovieId());
        assertEquals(details.getStreamToken(), result.getStreamToken());
    }
    
    @Test
    public void cacheFailTest() {
        /** 
         * Case: CacheService fails 
         * Behavior: mockCacheService throws Exception after theoretical timeout 
         * Expected output: Exception
         */
        String movieId = "1";
        String streamToken = "a";
        MovieMetadata meta = new MovieMetadata("Shrek", "Fantasy, Children");
        StreamingDetails details = new StreamingDetails(movieId, streamToken, meta);

        // when(mockCacheService.getDetails(movieId)).thenThrow(new Exception());
        
        // assertThrows(Exception.class, manager.streamMovie(movieId)); 
    }
    
    @Test
    public void fileStreamFailTest() {
        /** 
         * Case: FileStreamService fails 
         * Behavior: fileStreamServiceMock throws Exception after theoretical timeout 
         * Expected output: Exception
         */
    }
    
}
