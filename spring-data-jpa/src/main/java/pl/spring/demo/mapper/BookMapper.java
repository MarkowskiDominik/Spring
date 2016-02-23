package pl.spring.demo.mapper;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.BookTo;

public interface BookMapper {

	/**
	 * transform object class BookEntity to class BookTo
	 * @param object BookEntity
	 * @return new BookTo object
	 */
	BookTo bookEntityToBookTo(BookEntity bookEntity);

	/**
	 * transform object class BookTo to class BookEntity
	 * @param object BookTo
	 * @return new BookEntity object
	 */
	BookEntity bookToToBookEntity(BookTo bookTo);
}
