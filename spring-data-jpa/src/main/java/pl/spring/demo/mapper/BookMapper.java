package pl.spring.demo.mapper;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.BookTo;

public interface BookMapper {

	BookTo mappBookEntityToBookTo(BookEntity bookEntity);
	
	BookEntity mappBookToToBookEntity(BookTo bookTo);
}
