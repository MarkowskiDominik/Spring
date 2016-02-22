package pl.spring.demo.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.BookTo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

public class BookServiceImplTest {

	@InjectMocks
	private BookServiceImpl bookService;
	@Mock
	private BookDao bookDao;
	@Mock
	private BookMapper bookMapper;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testShouldSaveBook() {
		// given
		BookTo bookTo = new BookTo(null, "Title", "new author");
		BookEntity bookEntity = new BookEntity(null, "Title", Arrays.asList(new AuthorEntity(1L, "new", "author")));
		
		BookTo bookToExpected = new BookTo(1L, "Title", "new author");
		BookEntity bookEntityExpected = new BookEntity(1L, "Title", Arrays.asList(new AuthorEntity(1L, "new", "author")));
		/*
		BookTo saveBook(BookTo bookTo) {
			bookEntity = bookMapper.bookToToBookEntity(bookTo)					(1)
			bookeEntityExpected = bookDao.save(bookEntity)						(2)
			bookToExpected = bookMapper.bookEntityToBookTo(bookeEntityExpected)	(3)
			return bookToExpected
		}
		*/
		Mockito.when(bookMapper.bookToToBookEntity(bookTo)).thenReturn(bookEntity);					//(1)
		Mockito.when(bookDao.save(bookEntity)).thenReturn(bookEntityExpected);						//(2)
		Mockito.when(bookMapper.bookEntityToBookTo(bookEntityExpected)).thenReturn(bookToExpected);	//(3)
		
		// when
		BookTo result = bookService.saveBook(bookTo);
		
		// then
		Mockito.verify(bookMapper).bookToToBookEntity(bookTo);
		Mockito.verify(bookDao).save(bookEntity);
		Mockito.verify(bookMapper).bookEntityToBookTo(bookEntityExpected);
		
		assertEquals(result, bookToExpected);
	}
}
