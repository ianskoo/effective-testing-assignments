package zest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class BookManager {
    private BookRatingsFetcher fetcher;


    public BookManager(BookRatingsFetcher fetcher) {
        this.fetcher = fetcher;
    }

    public List<Book> highRatedBooks() {
        List<Book> allBooks = fetcher.all();
        try {
            if (allBooks == null) {
                return new ArrayList<>();
            }
            return allBooks.stream()
                    .filter(book -> book.getRating() >= 4)
                    .collect(Collectors.toList());
        } finally {
            fetcher.close();
        }
    }

    public List<String> uniqueAuthors() {
        List<Book> allBooks = fetcher.all();
        try {
            if (allBooks == null) {
                return new ArrayList<>();
            }
            return allBooks.stream()
                    .map(Book::getAuthor)
                    .distinct()
                    .collect(Collectors.toList());
        } finally {
            fetcher.close();
        }
    }
}
