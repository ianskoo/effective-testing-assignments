package zest;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class CatFactsRetrieverTest {
    @Test
    void randomFactApi() throws IOException {
        HttpUtil httpUtil = new HttpUtil();
        CatFactsRetriever retriever = new CatFactsRetriever(httpUtil);
        String randomFact = retriever.retrieveRandom();
        System.out.println("Random Cat Fact: " + randomFact);
    }

    @Test
    void longestFactApi() throws IOException {
        HttpUtil httpUtil = new HttpUtil();
        CatFactsRetriever retriever = new CatFactsRetriever(httpUtil);
        String randomFact = retriever.retrieveLongest(15);
        System.out.println("Longest Cat Fact: " + randomFact);
    }

    @Test
    void randomFactMock() throws IOException{
        HttpUtil MockHttpUtil = mock(HttpUtil.class);
        when(MockHttpUtil.get(anyString())).thenReturn("{\"fact\":\"Cats can rotate their ears 180 degrees.\", \"length\":38}");
        CatFactsRetriever retriever = new CatFactsRetriever(MockHttpUtil);
        String randomFact = retriever.retrieveRandom();
        assertEquals(randomFact, "Cats can rotate their ears 180 degrees.");
    }

    @Test
    void longestFactMock() throws IOException {
        HttpUtil mockHttpUtil = mock(HttpUtil.class);
        String jsonResponse = "{\"data\":[{\"fact\":\"Cats are cute.\", \"length\":14}, {\"fact\":\"The world's richest cat is worth over $100 million.\", \"length\":48}]}";
        when(mockHttpUtil.get(anyString())).thenReturn(jsonResponse);

        CatFactsRetriever retriever = new CatFactsRetriever(mockHttpUtil);
        String longestFact = retriever.retrieveLongest(2);
        assertEquals("The world's richest cat is worth over $100 million.", longestFact);
    }

    @Test
    void longestFactWithSameLength() throws IOException {
        HttpUtil mockHttpUtil = mock(HttpUtil.class);
        String jsonResponse = "{\"data\":[{\"fact\":\"Cats have nine lives.\", \"length\":20}, {\"fact\":\"Cats can jump well.\", \"length\":20}]}";
        when(mockHttpUtil.get(anyString())).thenReturn(jsonResponse);

        CatFactsRetriever retriever = new CatFactsRetriever(mockHttpUtil);
        String longestFact = retriever.retrieveLongest(2);
        assertEquals("Cats have nine lives.", longestFact, "Return the first fact encountered if facts have equal length.");
    }


    @Test
    void nullResponseRandom() throws IOException {
        HttpUtil mockHttpUtil = mock(HttpUtil.class);
        when(mockHttpUtil.get(anyString())).thenReturn(null);

        CatFactsRetriever retriever = new CatFactsRetriever(mockHttpUtil);

        assertThrows(NullPointerException.class, retriever::retrieveRandom);
    }

    @Test
    void nullResponseLongest() throws IOException {
        HttpUtil mockHttpUtil = mock(HttpUtil.class);
        when(mockHttpUtil.get(anyString())).thenReturn(null);

        CatFactsRetriever retriever = new CatFactsRetriever(mockHttpUtil);

        assertThrows(NullPointerException.class, () -> {
            retriever.retrieveLongest(7);
        });
    }

    @Test
    void zeroLimitLongest() throws IOException {
        HttpUtil mockHttpUtil = mock(HttpUtil.class);
        when(mockHttpUtil.get(contains("limit=0"))).thenReturn("{\"data\":[]}");

        CatFactsRetriever retriever = new CatFactsRetriever(mockHttpUtil);
        String fact = retriever.retrieveLongest(0);
        assertTrue(fact.isEmpty(), "For limit 0 there should not be any facts");
    }

    @Test
    void negativeLimitLongest() throws IOException {
        CatFactsRetriever retriever = new CatFactsRetriever(new HttpUtil());
        assertThrows(IllegalArgumentException.class, () -> retriever.retrieveLongest(-1));
    }

    @Test
    void testIOExceptions() throws IOException {
        HttpUtil mockHttpUtil = mock(HttpUtil.class);
        when(mockHttpUtil.get(anyString())).thenThrow(new IOException("Failed to fetch data"));

        CatFactsRetriever retriever = new CatFactsRetriever(mockHttpUtil);

        assertThrows(IOException.class, retriever::retrieveRandom);
    }

    @Test
    void emptyJsonResponse() throws IOException {
        HttpUtil mockHttpUtil = mock(HttpUtil.class);
        when(mockHttpUtil.get(anyString())).thenReturn("{}");

        CatFactsRetriever retriever = new CatFactsRetriever(mockHttpUtil);

        assertThrows(JSONException.class, retriever::retrieveRandom);
    }

    @Test
    void invalidJsonResponse() throws IOException {
        HttpUtil mockHttpUtil = mock(HttpUtil.class);
        when(mockHttpUtil.get(anyString())).thenReturn("}this is not a valid json");

        CatFactsRetriever retriever = new CatFactsRetriever(mockHttpUtil);

        assertThrows(JSONException.class, retriever::retrieveRandom);
    }

}