package pl.spring.demo.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.to.BookTo;

@Service("bookMapper")
public class BookMapperImpl implements BookMapper {

	private static final String SPLIT_AUTHOR = ",";
	private static final String SPLIT_FIRSTNAME_LASTNAME = " ";

	@Override
	public BookTo mappBookEntityToBookTo(BookEntity bookEntity) {
		String authors = null;
		for (AuthorEntity authorEntity : bookEntity.getAuthors()) {
			authors = authors + authorEntity.getFirstName() + SPLIT_FIRSTNAME_LASTNAME + authorEntity.getLastName()
					+ SPLIT_AUTHOR;
		}
		return new BookTo(bookEntity.getId(), bookEntity.getTitle(), authors.substring(0, authors.length() - 1));
	}

	@Override
	public BookEntity mappBookToToBookEntity(BookTo bookTo) {
		List<AuthorEntity> authors = new ArrayList<AuthorEntity>();
		if (bookTo.getAuthors() != null) {
			for (String authorTo : bookTo.getAuthors().split(SPLIT_AUTHOR)) {
				authors.add(new AuthorEntity(1l, authorTo.split(SPLIT_FIRSTNAME_LASTNAME)[0].trim(),
						authorTo.split(SPLIT_FIRSTNAME_LASTNAME)[1].trim()));
			}
		}
		return new BookEntity(bookTo.getId(), bookTo.getTitle(), authors);
	}

}
