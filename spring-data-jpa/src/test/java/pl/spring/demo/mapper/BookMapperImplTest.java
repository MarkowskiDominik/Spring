package pl.spring.demo.mapper;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.BookTo;

public class BookMapperImplTest {

	@Autowired
	private BookMapper bookMapper;

	@Test
	public void testShouldConvertBookEntityToBookTo() {
		// given
		BookTo bookTo = new BookTo(1L, "title", "author");
		// when
		BookEntity bookEntity = bookMapper.mappBookToToBookEntity(bookTo);
		System.out.println("Id" + bookTo.getId());

		// then
		assertEquals(bookTo.getTitle(), bookEntity.getTitle());
	}

	@Test
	public void testShouldConvertBookToToBookEntity() {
		// given
		BookEntity bookEntity = new BookEntity(1L, "title", Arrays.asList(new AuthorEntity(1L, "author", "")));
		// when
		BookTo bookTo = bookMapper.mappBookEntityToBookTo(bookEntity);
		// then
		assertEquals(bookEntity.getTitle(), bookTo.getTitle());
	}
}
