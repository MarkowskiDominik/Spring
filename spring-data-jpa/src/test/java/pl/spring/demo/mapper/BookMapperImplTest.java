package pl.spring.demo.mapper;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "BookMapperImplTest-context.xml")
public class BookMapperImplTest {

	@Autowired
	private BookMapper bookMapper;

	@Test
	public void testShouldConvertBookToToBookEntity() {
		// given
		BookTo bookTo = new BookTo(1L, "title", "new author");
		// when
		BookEntity bookEntity = bookMapper.bookToToBookEntity(bookTo);
		// then
		assertEquals(bookTo.getId(), bookEntity.getId());
		assertEquals(bookTo.getTitle(), bookEntity.getTitle());
	}

	@Test
	public void testShouldConvertBookEntityToBookTo() {
		// given
		BookEntity bookEntity = new BookEntity(1L, "title", Arrays.asList(new AuthorEntity(1L, "new", "author")));
		// when
		BookTo bookTo = bookMapper.bookEntityToBookTo(bookEntity);
		// then
		assertEquals(bookEntity.getId(), bookTo.getId());
		assertEquals(bookEntity.getTitle(), bookTo.getTitle());
	}
	
	@Test
	public void testShouldConvertBookEntityToBookToToBookEntity() {
		// given
		BookEntity bookEntity = new BookEntity(1L, "title", Arrays.asList(new AuthorEntity(1L, "new", "author")));
		// when
		BookEntity resultBookEntity = bookMapper.bookToToBookEntity(bookMapper.bookEntityToBookTo(bookEntity));
		// then
		assertEquals(bookEntity.getId(), resultBookEntity.getId());
		assertEquals(bookEntity.getTitle(), resultBookEntity.getTitle());
		assertEquals(bookEntity.getClass(), resultBookEntity.getClass());
	}
}
