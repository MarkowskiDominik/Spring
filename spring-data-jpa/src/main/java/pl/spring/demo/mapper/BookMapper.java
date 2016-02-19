package pl.spring.demo.mapper;

import java.util.List;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.BookTo;

public interface BookMapper {

	BookTo mappBookEntityToBookTo(BookEntity bookEntity);
	
	List<BookTo> mappBookEntityToBookTo(List<BookEntity> bookEntities);

	BookEntity mappBookToToBookEntity(BookTo bookTo);
	
	List<BookEntity> mappBookToToBookEntity(List<BookTo> bookTo);

}
