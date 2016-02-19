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
        BookEntity bookEntity = new BookEntity(1L, "Title", Arrays.asList(new AuthorEntity(1L, "Author", "")));
        Mockito.when(bookDao.save(bookEntity)).thenReturn(new BookEntity(1L, "Title", Arrays.asList(new AuthorEntity(1L, "Author", ""))));
        // when
        BookTo result = bookService.saveBook(bookMapper.mappBookEntityToBookTo(bookEntity));
        // then
        Mockito.verify(bookDao).save(bookEntity);
        assertEquals(1L, result.getId().longValue());
    }
}
