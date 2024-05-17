package zest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class BookManagerTest {

    @Test
    void testEmptyDatabaseRated() {
        BookRatingsFetcher mockFetcher = mock(BookRatingsFetcher.class);
        when(mockFetcher.all()).thenReturn(Collections.emptyList());

        BookManager manager = new BookManager(mockFetcher);
        List<Book> highRatedBooks = manager.highRatedBooks();

        assertTrue(highRatedBooks.isEmpty(), "There should be no highest rated books when there are no books in the database.");
        verify(mockFetcher).close();
    }

    @Test
    void testNullRated() {
        BookRatingsFetcher mockFetcher = mock(BookRatingsFetcher.class);
        when(mockFetcher.all()).thenReturn(null);

        BookManager manager = new BookManager(mockFetcher);
        List<Book> highRatedBooks = manager.highRatedBooks();

        assertTrue(highRatedBooks.isEmpty(), "There should be no highest rated books when  when the database returns null.");

        verify(mockFetcher).close();
    }

    @Test
    void testHighRatedBooksMultiple() {
        BookRatingsFetcher mockFetcher = mock(BookRatingsFetcher.class);
        when(mockFetcher.all()).thenReturn(Arrays.asList(
                new Book("book_rated_5", "author", 5),
                new Book("book_rated_4", "author", 4),
                new Book("book_rated_3", "author", 3)
        ));

        BookManager manager = new BookManager(mockFetcher);
        List<Book> highRatedBooks = manager.highRatedBooks();

        // Here is checked if the two books with rating 4 and 5 are returned by the method
        assertThat(highRatedBooks)
                .extracting(Book::getTitle)
                .containsExactlyInAnyOrder("book_rated_5", "book_rated_4");

        verify(mockFetcher).close();
    }

    @Test
    void testHighRatedBooksSingle() {
        BookRatingsFetcher mockFetcher = mock(BookRatingsFetcher.class);
        when(mockFetcher.all()).thenReturn(Arrays.asList(
                new Book("book_rated_5", "author", 5)
        ));

        BookManager manager = new BookManager(mockFetcher);
        List<Book> highRatedBooks = manager.highRatedBooks();

        // Here is checked if the two books with rating 4 and 5 are returned by the method
        assertThat(highRatedBooks)
                .extracting(Book::getTitle)
                .containsExactlyInAnyOrder("book_rated_5");

        verify(mockFetcher).close();
    }

    @Test
    void testSingleAuthor() {
        BookRatingsFetcher mockFetcher = mock(BookRatingsFetcher.class);
        when(mockFetcher.all()).thenReturn(Arrays.asList(
                new Book("Hamlet", "William Shakespeare", 5)
        ));

        BookManager manager = new BookManager(mockFetcher);
        List<String> authors = manager.uniqueAuthors();

        // Checking if the list contains only unique authors
        assertThat(authors).containsExactlyInAnyOrder("William Shakespeare");
        verify(mockFetcher).close();
    }

    @Test
    void testMultipleAuthors() {
        BookRatingsFetcher mockFetcher = mock(BookRatingsFetcher.class);
        when(mockFetcher.all()).thenReturn(Arrays.asList(
                new Book("Hamlet", "William Shakespeare", 5),
                new Book("Pride And Prejudice", "Jane Austen", 4),
                new Book("Othello", "William Shakespeare", 3)
        ));

        BookManager manager = new BookManager(mockFetcher);
        List<String> authors = manager.uniqueAuthors();

        // Checking if the list contains only unique authors
        assertThat(authors).containsExactlyInAnyOrder("William Shakespeare", "Jane Austen");
        verify(mockFetcher).close();
    }

    @Test
    void testEmptyDatabaseAuthor() {
        BookRatingsFetcher mockFetcher = mock(BookRatingsFetcher.class);
        when(mockFetcher.all()).thenReturn(Collections.emptyList());

        BookManager manager = new BookManager(mockFetcher);
        List<String> authors = manager.uniqueAuthors();

        assertTrue(authors.isEmpty(), "There should be no authors when there are no books in the database.");
        verify(mockFetcher).close();
    }

    @Test
    void testNullAuthor() {
        BookRatingsFetcher mockFetcher = mock(BookRatingsFetcher.class);
        when(mockFetcher.all()).thenReturn(null);

        BookManager manager = new BookManager(mockFetcher);
        List<String> authors = manager.uniqueAuthors();

        assertTrue(authors.isEmpty(), "There should be no author when when the database returns null.");

        verify(mockFetcher).close();
    }


}