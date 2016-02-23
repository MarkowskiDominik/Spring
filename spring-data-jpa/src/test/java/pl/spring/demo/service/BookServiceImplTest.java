package pl.spring.demo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookTo;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() {
        cacheManager.getCache("booksCache").clear();
    }

    @Test
    public void testShouldFindAllBooks() {
        // when
        List<BookTo> allBooks = bookService.findAllBooks();
        // then
        assertNotNull(allBooks);
        assertFalse(allBooks.isEmpty());
        assertEquals(6, allBooks.size());
    }

    @Test
    public void testShouldFindAllBooksByTitle() {
        // given
        final String title = "Opium w rosole";
        // when
        List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
        // then
        assertNotNull(booksByTitle);
        assertFalse(booksByTitle.isEmpty());
    }

    @Test
    public void testShouldFindAllBooksByIncompleteTitle() {
    	// given
    	final String title = "w rosole";
    	// when
    	List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
    	// then
    	assertNotNull(booksByTitle);
    	assertFalse(booksByTitle.isEmpty());
    }
    
    @Test
    public void testShouldFindAllBooksByEmptyTitle() {
    	// given
    	final String title = "";
    	// when
    	List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
    	// then
    	assertNotNull(booksByTitle);
    	assertFalse(booksByTitle.isEmpty());
    	assertEquals(booksByTitle.size(), bookService.findAllBooks().size());
    }
    
    @Test
    public void testShouldFindAllBooksByAuthor() {
    	// given
    	final String author = "Aleksander Fredro";
    	// when
    	List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
    	// then
    	assertNotNull(booksByAuthor);
    	assertFalse(booksByAuthor.isEmpty());
    }
    
    @Test
    public void testShouldFindAllBooksByIncompleteAuthor() {
    	// given
    	final String author = "Fredro";
    	// when
    	List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
    	// then
    	assertNotNull(booksByAuthor);
    	assertFalse(booksByAuthor.isEmpty());
    }
    
    @Test
    public void testShouldFindAllBooksByEmptyAuthor() {
    	// given
    	final String author = "";
    	// when
    	List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
    	// then
    	assertNotNull(booksByAuthor);
    	assertFalse(booksByAuthor.isEmpty());
    	assertEquals(booksByAuthor.size(), bookService.findAllBooks().size());
    }

    @Test(expected = BookNotNullIdException.class)
    public void testShouldThrowBookNotNullIdException() {
        // given
        final BookTo bookToSave = new BookTo();
        bookToSave.setId(22L);
        // when
        bookService.saveBook(bookToSave);
        // then
        fail("test should throw BookNotNullIdException");
    }
}
